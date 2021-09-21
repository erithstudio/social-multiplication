package microservices.template.multiplication.controller;

import microservices.template.multiplication.protobuf.Course;
import microservices.template.multiplication.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseController {
    @Autowired
    CourseRepository courseRepo;

    @RequestMapping(value = "/courses/{id}")
    // @RequestMapping(value = "/courses/{id}", produces = "application/x-protobuf")
    Course customer(@PathVariable Integer id) {
        return courseRepo.getCourse(id);
    }
}