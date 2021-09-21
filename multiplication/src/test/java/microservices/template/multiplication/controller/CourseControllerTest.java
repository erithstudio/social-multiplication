package microservices.template.multiplication.controller;

import com.googlecode.protobuf.format.JsonFormat;
import microservices.template.multiplication.protobuf.Course;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CourseControllerTest {
    @LocalServerPort
    private int rdmServerPort;

    private String getURL() {
        return String.format("http://localhost:%d/courses/1", rdmServerPort);
    }

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void whenUsingRestTemplate_thenSucceed() {
        ResponseEntity<Course> course = restTemplate.getForEntity(getURL(), Course.class);
        assertResponse(course.toString());
    }

    @Test
    public void whenUsingHttpClient_thenSucceed() throws IOException {
        InputStream responseStream = executeHttpRequest(getURL());
        String jsonOutput = convertProtobufMessageStreamToJsonString(responseStream);
        assertResponse(jsonOutput);
    }

    private InputStream executeHttpRequest(String url) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet(url);
        HttpResponse httpResponse = httpClient.execute(request);
        return httpResponse.getEntity().getContent();
    }

    private String convertProtobufMessageStreamToJsonString(InputStream protobufStream) throws IOException {
        JsonFormat jsonFormat = new JsonFormat();
        Course course = Course.parseFrom(protobufStream);
        return jsonFormat.printToString(course);
    }

    private void assertResponse(String response) {
        assertThat(response, containsString("id"));
        assertThat(response, containsString("course_name"));
        assertThat(response, containsString("REST with Spring"));
        assertThat(response, containsString("student"));
        assertThat(response, containsString("first_name"));
        assertThat(response, containsString("last_name"));
        assertThat(response, containsString("email"));
        assertThat(response, containsString("john.doe@baeldung.com"));
        assertThat(response, containsString("richard.roe@baeldung.com"));
        assertThat(response, containsString("jane.doe@baeldung.com"));
        assertThat(response, containsString("phone"));
        assertThat(response, containsString("number"));
        assertThat(response, containsString("type"));
    }
}
