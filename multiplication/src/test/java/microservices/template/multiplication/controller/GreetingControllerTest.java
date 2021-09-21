package microservices.template.multiplication.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
public class GreetingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void greetingGetWithProvidedContent() throws Exception {
        this.mockMvc.perform(get("/greeting").param("name", "Everybody"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.content", is("Hello, Everybody!")))
                .andDo(document("{class-name}/{method-name}",
                        requestParameters(parameterWithName("name").description("Greeting's target")),
                        responseFields(fieldWithPath("id").description("Greeting's generated id"),
                                fieldWithPath("content").description("Greeting's content"),
                                fieldWithPath("optionalContent").description("Greeting's optional content").type(JsonFieldType.STRING).optional()
                        )));

    }

    @Test
    public void greetingGetWithDefaultContent() throws Exception {

        this.mockMvc.perform(get("/greeting"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect((ResultMatcher) jsonPath("$.content", is("Hello, World!")))
                .andDo(document("{class-name}/{method-name}",
                        responseFields(fieldWithPath("id").ignored(),
                                fieldWithPath("content").description("When name is not provided, this field contains the default value")
                        )));

    }

}