package org.app.persistence.repository;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.app.model.Course;

import java.util.List;

@RequestScoped
public class CourseRepositoryImpl implements ICourseRepository{

    @Inject
    private EntityManager entityManager;

    @Override
    public List<Course> findAll() {
        return entityManager.createQuery("SELECT c FROM Course c").getResultList();
    }

    @Override
    public Course findById(Long id) {
        return entityManager.find(Course.class, id);
    }

    @Override
    public void save(Course course) {
        if(course.getId() != null && course.getId() > 0){
            this.entityManager.merge(course);
        } else {
            this.entityManager.persist(course);
        }
    }

    @Override
    public void deleteById(Course course) {
        this.entityManager.remove(course);
    }
}
