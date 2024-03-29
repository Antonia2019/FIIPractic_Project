package fii.practic.health.entity.repository;

import fii.practic.health.entity.model.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class UserRepositoryImpl implements UserRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User findUserByEmailAndPassword(String email, String password) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        Predicate preEmail = builder.equal(root.get("email"), email);
        Predicate prePassword = builder.equal(root.get("password"), password);
        query.where(builder.and(preEmail, prePassword));

        try {
            return entityManager.createQuery(query.select(root)).getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }
}
