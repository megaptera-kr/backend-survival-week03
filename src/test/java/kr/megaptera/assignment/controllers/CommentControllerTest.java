package kr.megaptera.assignment.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.megaptera.assignment.dtos.CommentDTO;
import kr.megaptera.assignment.service.CommentService;
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

@WebMvcTest(controllers = {CommentController.class})
class CommentControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    CommentService commentService;

    @DisplayName("댓글을 조회한다")
    @Test
    void getComments() throws Exception {
        // Given
        long postId = 1L;
        CommentDTO commentDTO1 = new CommentDTO(1L, postId, "content1");
        CommentDTO commentDTO2 = new CommentDTO(2L, postId, "content2");
        List<CommentDTO> commentDTOs = List.of(
                commentDTO1,
                commentDTO2
        );
        when(commentService.list(1L)).thenReturn(commentDTOs);

        // When // Then
        mockMvc.perform(get("/comments?postId={postId}", postId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(commentDTO1.id()))
                .andExpect(jsonPath("$[0].postId").value(commentDTO1.postId()))
                .andExpect(jsonPath("$[0].content").value(commentDTO1.content()))
                .andExpect(jsonPath("$[1].id").value(commentDTO2.id()))
                .andExpect(jsonPath("$[1].postId").value(commentDTO2.postId()))
                .andExpect(jsonPath("$[1].content").value(commentDTO2.content()));
    }

    @DisplayName("댓글을 작성한다")
    @Test
    void create() throws Exception {
        // Given
        long postId = 1L;
        CommentDTO commentDTO = new CommentDTO(1L, postId, "content1");
        doNothing().when(commentService).create(postId, commentDTO);
        String bodyContent = objectMapper.writeValueAsString(commentDTO);

        // When // Then
        mockMvc.perform(
                        post("/comments?postId={postId}", postId)
                                .content(bodyContent)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @DisplayName("댓글을 수정한다")
    @Test
    void update() throws Exception {
        // Given
        long commentId = 1L;
        long postId = 1L;
        String commentContent = "content1";
        CommentDTO commentDTO = CommentDTO.of(commentId, commentContent);
        String bodyContent = objectMapper.writeValueAsString(commentDTO);

        doNothing().when(commentService).update(commentId, postId, commentDTO);

        // When // Then
        mockMvc.perform(
                        put("/comments/{id}?postId={postId}", commentId, postId)
                                .content(bodyContent)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());

    }

    @DisplayName("댓글을 삭제한다")
    @Test
    void deleteComment() throws Exception{
        // Given
        long commentId = 1L;
        long postId = 1L;
        doNothing().when(commentService).delete(commentId, postId);

        // When // Then
        mockMvc.perform(
                        delete("/comments/{id}?postId={postId}", commentId, postId)
                )
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}