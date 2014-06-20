package com.globallogic.push_service_poc.demo.repository;

import com.globallogic.push_service_poc.demo.entity.Invoice;

import java.util.List;

public interface InvoiceRepository {

    public List<Invoice> getInvoiceList();

    public Invoice getInvoice(Long invoiceId);
}
