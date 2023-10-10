package kr.megaptera.assignment.utils;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

@SpringBootTest
public class ApiClient {
    public static ExtractableResponse<Response> _post(String path, HashMap<String, String> body) {
        return RestAssured.given().log().all()
                .when().contentType(ContentType.JSON).formParam(new Gson().toJson(body))
                .post(path)
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> _get(String path) {
        return RestAssured.given().log().all()
                .when().contentType(ContentType.JSON)
                .get(path)
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> _put(String path, HashMap<String, String> body) {
        return RestAssured.given().log().all()
                .when().contentType(ContentType.JSON).formParam(new Gson().toJson(body))
                .put(path)
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> _delete(String path) {
        return RestAssured.given().log().all()
                .when().contentType(ContentType.JSON)
                .delete(path)
                .then().log().all()
                .extract();
    }
}
