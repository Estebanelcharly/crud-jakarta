package org.app.service;

import org.app.model.Course;

import java.util.List;
import java.util.Optional;

public interface ICourseService {
    List<Course> findAll();
    Optional<Course> findById(Long id);
    void save(Course course);
    void deleteById(Course id);
}
