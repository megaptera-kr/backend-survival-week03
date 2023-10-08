package kr.megaptera.assignment.controllers;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import kr.megaptera.assignment.utils.ApiClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PostControllerTests {

    @Test
    void contextLoads() {
    }

    @DisplayName("게시글 조회")
    @Test
    void getPosts() {
        String requestPath = "/posts";

        ExtractableResponse<Response> response = ApiClient._get(requestPath);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @DisplayName("게시글 상세 조회")
    @Test
    void getPost() {
        int postId = 0;
        String requestPath = "/posts/%d".formatted(postId);

        ExtractableResponse<Response> response = ApiClient._get(requestPath);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @DisplayName("게시글 작성")
    @Test
    void postPosts() {
        String requestPath = "/posts";

        HashMap<String, String> requestBody = new HashMap<>() {{
            put("k", "v");
        }};

        ExtractableResponse<Response> response = ApiClient._post(requestPath, requestBody);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.asString()).isEqualTo("Complete!");
    }

    @DisplayName("게시글 수정")
    @Test
    void putPosts() {
        int postId = 0;
        String requestPath = "/posts/%d".formatted(postId);

        HashMap<String, String> requestBody = new HashMap<>() {{
            put("k", "v");
        }};

        ExtractableResponse<Response> response = ApiClient._put(requestPath, requestBody);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.NO_CONTENT.value());
        assertThat(response.asString()).isEqualTo("");
    }

    @DisplayName("게시글 삭제")
    @Test
    void deletePosts() {
        int postId = 0;
        String requestPath = "/posts/%d".formatted(postId);

        ExtractableResponse<Response> response = ApiClient._delete(requestPath);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.NO_CONTENT.value());
        assertThat(response.asString()).isEqualTo("");
    }

}
