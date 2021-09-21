package microservices.template.multiplication.configuration;

import microservices.template.multiplication.protobuf.Course;
import microservices.template.multiplication.protobuf.Student;
import microservices.template.multiplication.protobuf.Student.PhoneNumber;
import microservices.template.multiplication.repository.CourseRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.*;

@Configuration
public class ProtobufConfiguration extends WebMvcConfigurerAdapter {
    @Bean
    public HttpMessageConverter customProtobufMessageConverter() {
        return new ProtobufHttpMessageConverter();
    }

    @Bean
    public HttpMessageConverter customJacksonMessageConverter() {
        return new MappingJackson2HttpMessageConverter();
    }

    @Bean
    RestTemplate customRestTemplate(HttpMessageConverter customProtobufMessageConverter, HttpMessageConverter customJacksonMessageConverter) {
        RestTemplate restTemplate = new RestTemplate();
        List messageConverters = restTemplate.getMessageConverters();
        messageConverters.add(0, customJacksonMessageConverter);
        messageConverters.add(1, customProtobufMessageConverter);
        restTemplate.setMessageConverters(messageConverters);
        return restTemplate;
    }

    @Bean
    public CourseRepository createTestCourses() {
        Map<Integer, Course> courses = new HashMap<>();
        Course course1 = Course.newBuilder()
                .setId(1)
                .setCourseName("REST with Spring")
                .addAllStudent(createTestStudents())
                .build();
        Course course2 = Course.newBuilder()
                .setId(2)
                .setCourseName("Learn Spring Security")
                .addAllStudent(new ArrayList<>())
                .build();
        courses.put(course1.getId(), course1);
        courses.put(course2.getId(), course2);
        return new CourseRepository(courses);
    }

    private List<Student> createTestStudents() {
        PhoneNumber phone1 = createPhone("123456", Student.PhoneType.MOBILE);
        Student student1 = createStudent(1, "John", "Doe", "john.doe@baeldung.com", Arrays.asList(phone1));

        PhoneNumber phone2 = createPhone("234567", Student.PhoneType.LANDLINE);
        Student student2 = createStudent(2, "Richard", "Roe", "richard.roe@baeldung.com", Arrays.asList(phone2));

        PhoneNumber phone3_1 = createPhone("345678", Student.PhoneType.MOBILE);
        PhoneNumber phone3_2 = createPhone("456789", Student.PhoneType.LANDLINE);
        Student student3 = createStudent(3, "Jane", "Doe", "jane.doe@baeldung.com", Arrays.asList(phone3_1, phone3_2));

        return Arrays.asList(student1, student2, student3);
    }

    private Student createStudent(int id, String firstName, String lastName, String email, List<PhoneNumber> phones) {
        return Student.newBuilder().setId(id).setFirstName(firstName).setLastName(lastName).setEmail(email).addAllPhone(phones).build();
    }

    private PhoneNumber createPhone(String number, Student.PhoneType type) {
        return PhoneNumber.newBuilder().setNumber(number).setType(type).build();
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        converters.add(0, customProtobufMessageConverter());
        converters.add(1, customJacksonMessageConverter());
    }

}
