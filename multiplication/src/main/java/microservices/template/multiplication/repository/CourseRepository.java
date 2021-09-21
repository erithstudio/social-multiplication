package microservices.template.multiplication.repository;

import microservices.template.multiplication.protobuf.Course;

import java.util.Map;

public class CourseRepository {
    Map<Integer, Course> courses;

    public CourseRepository(Map<Integer, Course> courses) {
        this.courses = courses;
    }

    public Course getCourse(int id) {
        return courses.get(id);
    }
}