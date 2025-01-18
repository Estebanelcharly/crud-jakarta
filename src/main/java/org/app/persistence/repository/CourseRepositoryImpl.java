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
    @Transactional
    @Override
    public List<Course> findAll() {
        return entityManager.createQuery("SELECT c FROM Course c").getResultList();
    }
    @Transactional
    @Override
    public Course findById(Long id) {
        return entityManager.find(Course.class, id);
    }
    @Transactional
    @Override
    public void save(Course course) {
        if(course.getId() != null && course.getId() > 0){
            this.entityManager.merge(course);
        } else {
            this.entityManager.persist(course);
        }
    }
    /*@Transactional
    @Override
    public void deleteById(Course course) {
        this.entityManager.remove(course);
    }*/
    @Transactional
    @Override
    public void deleteById(Course course) {
        // Adjuntar la entidad al contexto si no está gestionada
        Course managedCourse = this.entityManager.merge(course);
        this.entityManager.remove(managedCourse);
    }
}
