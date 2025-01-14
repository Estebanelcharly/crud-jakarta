package org.app.persistence;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@RequestScoped
public class EntityManagerProducer {

    @PersistenceContext(name = "persistenceContext")
    private EntityManager entityManager;

    @Produces
    @RequestScoped
    private EntityManager beanEntityManager(){
        return entityManager;
    }
}
