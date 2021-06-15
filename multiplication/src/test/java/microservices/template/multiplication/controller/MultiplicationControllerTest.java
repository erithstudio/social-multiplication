package microservices.template.multiplication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import microservices.template.multiplication.domain.Multiplication;
import microservices.template.multiplication.service.MultiplicationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
// @WebMvcTest(MultiplicationController.class)
public class MultiplicationControllerTest {

    // @Autowired
    @MockBean
    private MultiplicationService multiplicationService;

    @Autowired
    private MockMvc mvc;
    // This object will be magically initialized by the initFields method below.
    private JacksonTester<Multiplication> json;

    @Before
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    public void getRandomMultiplicationTest() throws Exception {
        // given
        Multiplication multiplication = new Multiplication(70, 20);
        given(multiplicationService.createRandomMultiplication()).willReturn(multiplication);

        // when
        MockHttpServletResponse response = mvc.perform(
                get("/multiplications/random").accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(json.write(new Multiplication(70, 20)).getJson());
    }
}