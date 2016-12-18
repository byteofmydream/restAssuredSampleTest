package groupJ;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.json.*;

import java.io.IOException;
import java.io.OutputStream;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class TestClass {

    private final String BASE_URI = "http://jsonplaceholder.typicode.com";

    @Before
    public void precondition() {
        RestAssured.baseURI = BASE_URI;
    }

    @Ignore
    @Test
    public void getUserPostTest() throws IOException {
        String expectedTitle = "sunt aut facere repellat provident occaecati excepturi optio reprehenderit";
        String expectedBody = "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto";
        UserPost expecedPost = new UserPost(1, expectedTitle, expectedBody, 1);

        Response response = given().
                when().
                get("/posts");

        assertEquals(200, response.getStatusCode());
        JSONArray respJsonArray = new JSONArray(response.body().asString());
        JSONObject respJsonObject = respJsonArray.getJSONObject(0);
        UserPost actualPost = new ObjectMapper().readValue(respJsonObject.toString(), UserPost.class);
        assertEquals(expecedPost, actualPost);
    }

    @Test
    public void postUserPostTest() throws IOException {
        String title = "foo";
        String body = "bar";
        UserPost post = new UserPost(100600, title, body, 1);

        OutputStream os = new OutputStream() {
            StringBuilder sb = new StringBuilder();

            @Override
            public String toString() {
                return sb.toString();
            }

            public void write(int b) throws IOException {
                sb.append((char) b);
            }
        };

        ObjectMapper om = new ObjectMapper();
        om.writeValue(os, post);

        Response response = given().
                contentType(ContentType.JSON).
                body(os.toString()).
                when().
                post("/posts");

        assertEquals(201, response.getStatusCode());
        JSONObject respJsonObject = new JSONObject(response.body().asString());
        UserPost receivedPost = new ObjectMapper().readValue(respJsonObject.toString(), UserPost.class);
        assertEquals(post, receivedPost);
    }

}
