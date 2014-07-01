package com.globallogic.push_service_poc.demo.repository.mongo;

import com.globallogic.push_service_poc.demo.entity.User;
import com.globallogic.push_service_poc.demo.entity.User_;
import com.globallogic.push_service_poc.demo.repository.UserRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class UserRepositoryJPA implements UserRepository {

    @PersistenceContext(unitName = "mongoDBUnitJTA")
    private EntityManager em;

    @Override
    public List<User> getUserList() {
        CriteriaBuilder queryBuilder = em.getCriteriaBuilder();
        CriteriaQuery<User> criteria = queryBuilder.createQuery(User.class);
        Root<User> userRoot = criteria.from(User.class);
        criteria.select(userRoot);
        List<User> userList = em.createQuery(criteria).getResultList();
        em.close();
        return userList;
    }

    @Override
    public User getUser(Long userId) {
        CriteriaBuilder queryBuilder = em.getCriteriaBuilder();
        CriteriaQuery<User> criteria = queryBuilder.createQuery(User.class);
        Root<User> userRoot = criteria.from(User.class);
        criteria.select(userRoot);
        criteria.where(queryBuilder.equal(userRoot.get(User_.userId), userId));
        List<User> userQueried = em.createQuery(criteria).getResultList();
        em.close();
        return userQueried.isEmpty() ? null : userQueried.get(0);
    }
}
