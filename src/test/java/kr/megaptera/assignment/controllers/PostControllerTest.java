package kr.megaptera.assignment.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.megaptera.assignment.dtos.PostDTO;
import kr.megaptera.assignment.exception.NoSuchPostIdException;
import kr.megaptera.assignment.service.PostService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = {PostController.class})
class PostControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    PostService postService;

    @DisplayName("게시글 목록을 조회한다")
    @Test
    void list() throws Exception {
        // given
        PostDTO postDTO = new PostDTO(1L, "title1", "content1");
        List<PostDTO> postDTOs = List.of(postDTO);
        when(postService.list()).thenReturn(postDTOs);

        // when // then
        mockMvc.perform(get("/posts"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(postDTO.id()))
                .andExpect(jsonPath("$[0].title").value(postDTO.title()))
                .andExpect(jsonPath("$[0].content").value(postDTO.content()));

    }

    @DisplayName("특정 게시글을 조회한다")
    @Test
    void getPost() throws Exception {
        // given
        long postId = 1L;
        PostDTO postDTO = new PostDTO(postId, "title1", "content1");
        when(postService.get(postId)).thenReturn(postDTO);

        // when // then
        mockMvc.perform(get("/posts/{id}", postId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(postDTO.id()))
                .andExpect(jsonPath("$.title").value(postDTO.title()))
                .andExpect(jsonPath("$.content").value(postDTO.content()));

    }

    @DisplayName("존재하지 않는 게시글의 id로 조회하면 예외가 발생한다")
    @Test
    void getPost2() throws Exception {
        // given
        long postId = 1L;
        String exceptionMessage = (new NoSuchPostIdException()).getMessage();
        when(postService.get(postId)).thenThrow(new NoSuchPostIdException());

        // when // then
        mockMvc.perform(get("/posts/{id}", postId))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value(exceptionMessage));
    }

    @DisplayName("게시글을 생성한다")
    @Test
    void create() throws Exception {
        // given
        PostDTO postDTO = new PostDTO(1L, "title1", "content1");
        String content = objectMapper.writeValueAsString(postDTO);
        String expectedContent = "Complete!";

        // when // then
        mockMvc.perform(
                post("/posts")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string(expectedContent));
    }

    @DisplayName("게시글을 수정한다")
    @Test
    void update() throws Exception {
        // given
        long postId = 1L;
        PostDTO postDTO = new PostDTO(postId, "title1", "content1");
        String content = objectMapper.writeValueAsString(postDTO);
        doNothing().when(postService).update(postDTO, postId);

        // when // then
        mockMvc.perform(
                        put("/posts/{id}", postId)
                                .content(content)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());

    }

    @DisplayName("게시글을 삭제한다")
    @Test
    void deletePost() throws Exception {
        // Given
        long postId = 1L;
        doNothing().when(postService).delete(postId);

        // When // Then
        mockMvc.perform(
                        delete("/posts/{id}", postId)
                )
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}