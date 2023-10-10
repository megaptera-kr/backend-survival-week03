package kr.megaptera.assignment.controllers;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import kr.megaptera.assignment.utils.ApiClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CommentControllerTests {

    @Autowired
    private CommentController commentController;

    @DisplayName("contextLoads")
    void contextLoads() {
        assert commentController != null;
    }

    @DisplayName("댓글 조회")
    @Test
    void getComments() {
        int postId = 1;
        String requestPath = "/comments?postId=%d".formatted(postId);

        ExtractableResponse<Response> response = ApiClient._get(requestPath);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @DisplayName("댓글 작성")
    @Test
    void postComments() {
        int postId = 1;
        String requestPath = "/comments?postId=%d".formatted(postId);

        HashMap<String, String> requestBody = new HashMap<>() {{
            put("k", "v");
        }};

        ExtractableResponse<Response> response = ApiClient._post(requestPath, requestBody);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.asString()).isEqualTo("Complete!");
    }

    @DisplayName("댓글 수정")
    @Test
    void putComments() {
        int commentId = 0;
        int postId = 1;
        String requestPath = "/comments/%d?postId=%d".formatted(commentId, postId);

        HashMap<String, String> requestBody = new HashMap<>() {{
            put("k", "v");
        }};

        ExtractableResponse<Response> response = ApiClient._put(requestPath, requestBody);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.NO_CONTENT.value());
        assertThat(response.asString()).isEqualTo("");
    }

    @DisplayName("댓글 삭제")
    @Test
    void deleteComments() {
        int commentId = 0;
        int postId = 1;
        String requestPath = "/comments/%d?postId=%d".formatted(commentId, postId);

        ExtractableResponse<Response> response = ApiClient._delete(requestPath);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.NO_CONTENT.value());
        assertThat(response.asString()).isEqualTo("");
    }


}
