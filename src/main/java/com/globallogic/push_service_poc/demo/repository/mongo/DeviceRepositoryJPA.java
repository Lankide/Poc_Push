package com.globallogic.push_service_poc.demo.repository.mongo;

import com.globallogic.push_service_poc.demo.entity.Device;
import com.globallogic.push_service_poc.demo.entity.Device_;
import com.globallogic.push_service_poc.demo.entity.User;
import com.globallogic.push_service_poc.demo.entity.User_;
import com.globallogic.push_service_poc.demo.repository.DeviceRepository;
import com.globallogic.push_service_poc.demo.repository.UserRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class DeviceRepositoryJPA implements DeviceRepository {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("mongoDBUnitLocal");

    private final Logger log = Logger.getLogger(getClass().getName());

    @Inject
    UserRepository userRepository;

    @Override
    public void registerDevice(Long userId, String deviceId) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        User user = userRepository.getUser(userId);
        Device device = new Device(deviceId);
        user.getDeviceList().add(device);

        log.info("User devices after adding new: " + StringUtils.join(user.getDeviceList(), ','));
        em.persist(device);
        em.merge(user);

        tx.commit();
        em.close();
    }

    @Override
    public void unregisterDevice(Long userId, String deviceId) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        CriteriaBuilder queryBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Device> criteria = queryBuilder.createQuery(Device.class);
        Root<Device> deviceRoot = criteria.from(Device.class);
        criteria.select(deviceRoot);
        criteria.where(queryBuilder.equal(deviceRoot.get(Device_.deviceId), deviceId));
        List<Device> deviceQueried = em.createQuery(criteria).getResultList();
        Device device = deviceQueried.isEmpty() ? null : deviceQueried.get(0);

        User user = userRepository.getUser(userId);
        user.getDeviceList().remove(device);

        log.info("User devices after removing one: " + StringUtils.join(user.getDeviceList(), ','));
        em.merge(user);
        em.remove(device);

        tx.commit();
        em.close();
    }
}
