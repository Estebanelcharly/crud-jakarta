package org.app.persistence.repository;

import org.app.model.Course;

import java.util.List;

public interface ICourseRepository {

    List<Course> findAll();
    Course findById(Long id);

    void save(Course course);

    void deleteById(Course course);
}
