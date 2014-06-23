package com.globallogic.push_service_poc.demo.repository.mongo;

import com.globallogic.push_service_poc.demo.entity.Invoice;
import com.globallogic.push_service_poc.demo.entity.Invoice_;
import com.globallogic.push_service_poc.demo.repository.InvoiceRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class InvoiceRepositoryJPA implements InvoiceRepository {

    @PersistenceContext(unitName = "mongoDBUnit2")
    private EntityManager em;

    @Override
    public List<Invoice> getInvoiceList() {
        CriteriaBuilder queryBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Invoice> criteria = queryBuilder.createQuery(Invoice.class);
        Root<Invoice> invoiceRoot = criteria.from(Invoice.class);
        criteria.select(invoiceRoot);
        List<Invoice> invoiceList = em.createQuery(criteria).getResultList();
        em.close();
        return invoiceList;
    }

    @Override
    public Invoice getInvoice(Long invoiceId) {
        CriteriaBuilder queryBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Invoice> criteria = queryBuilder.createQuery(Invoice.class);
        Root<Invoice> invoiceRoot = criteria.from(Invoice.class);
        criteria.select(invoiceRoot);
        criteria.where(queryBuilder.equal(invoiceRoot.get(Invoice_.invoiceId), invoiceId));
        List<Invoice> invoiceQueried = em.createQuery(criteria).getResultList();
        em.close();
        return invoiceQueried.isEmpty() ? null : invoiceQueried.get(0);
    }
}
