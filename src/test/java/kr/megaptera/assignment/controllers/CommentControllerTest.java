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

import static org.junit.jupiter.api.Assertions.*;
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
        List<CommentDTO> commentDTOs = List.of(
                new CommentDTO(1L, 1L, "content1"),
                new CommentDTO(2L, 1L, "content2")
        );
        when(commentService.list(1L)).thenReturn(commentDTOs);

        // When // Then
        mockMvc.perform(get("/comments?postId={postId}", 1L))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].postId").value(1L))
                .andExpect(jsonPath("$[0].content").value("content1"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].postId").value(1L))
                .andExpect(jsonPath("$[1].content").value("content2"));
    }

    @DisplayName("댓글을 작성한다")
    @Test
    void create() throws Exception {
        // Given
        CommentDTO commentDTO = new CommentDTO(1L, 1L, "content1");
        doNothing().when(commentService).create(1L, commentDTO);
        String content = objectMapper.writeValueAsString(commentDTO);

        // When // Then
        mockMvc.perform(
                        post("/comments?postId={postId}", 1L)
                                .content(content)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }
}