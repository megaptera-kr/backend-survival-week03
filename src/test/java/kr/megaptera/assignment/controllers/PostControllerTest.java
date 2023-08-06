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

@WebMvcTest
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
        List<PostDTO> postDTOs = List.of(new PostDTO(1L, "title1", "content1"));
        when(postService.list()).thenReturn(postDTOs);

        // when // then
        mockMvc.perform(get("/posts"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].title").value("title1"))
                .andExpect(jsonPath("$[0].content").value("content1"));

    }

    @DisplayName("특정 게시글을 조회한다")
    @Test
    void getPost() throws Exception {
        // given
        PostDTO postDTO = new PostDTO(1L, "title1", "content1");
        when(postService.get(1L)).thenReturn(postDTO);

        // when // then
        mockMvc.perform(get("/posts/{id}", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("title1"))
                .andExpect(jsonPath("$.content").value("content1"));

    }

    @DisplayName("존재하지 않는 게시글의 id로 조회하면 예외가 발생한다")
    @Test
    void getPost2() throws Exception {
        // given
        when(postService.get(1L)).thenThrow(new NoSuchPostIdException());

        // when // then
        mockMvc.perform(get("/posts/{id}", "1"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("해당 id의 게시글은 존재하지 않습니다"));
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
        PostDTO postDTO = new PostDTO(1L, "title1", "content1");
        String content = objectMapper.writeValueAsString(postDTO);
        doNothing().when(postService).update(postDTO, 1L);

        // when // then
        mockMvc.perform(
                        put("/posts/{id}", 1L)
                                .content(content)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());

    }

    @DisplayName("게시글을 삭제한다")
    @Test
    void deletePost() throws Exception {
        // Given
        doNothing().when(postService).delete(1L);

        // When // Then
        mockMvc.perform(
                        delete("/posts/{id}", 1L)
                )
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}