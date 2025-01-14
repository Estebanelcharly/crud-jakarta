package org.app.service;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.app.model.Course;
import org.app.persistence.repository.ICourseRepository;

import java.util.List;
import java.util.Optional;

@Stateless
public class CourseServiceImpl implements ICourseService {

    @Inject
    private ICourseRepository courseRepository;

    @Override
    public List<Course> findAll() {
        return this.courseRepository.findAll();
    }

    @Override
    public Optional<Course> findById(Long id) {
        return Optional.ofNullable(this.courseRepository.findById(id));
    }

    @Override
    public void save(Course course) {
        this.courseRepository.save(course);
    }

    @Override
    public void deleteById(Course course) {
        this.courseRepository.deleteById(course);
    }
}
