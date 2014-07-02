package com.globallogic.push_service_poc.demo.entity;

import org.eclipse.persistence.nosql.annotations.DataFormatType;
import org.eclipse.persistence.nosql.annotations.Field;
import org.eclipse.persistence.nosql.annotations.JoinField;
import org.eclipse.persistence.nosql.annotations.NoSql;


import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by arkadii.tetelman on 3/13/14.
 */

@Entity
@NoSql(dataFormat = DataFormatType.MAPPED, dataType = "invoice")
@Cacheable(false)
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Field(name = "invoiceId")
    private long invoiceId;

    @Basic
    private String invoiceName;
    @Basic
    private Double invoiceAmount;
    @Basic
    @Temporal(TemporalType.DATE)
    private Date invoiceSubmittedTS;
    @Basic
    @Temporal(TemporalType.DATE)
    private Date invoiceCompletedTS;
    @OneToMany
    @JoinField(name = "paymentId")
    private List<Payment> invoicePaymentList;

    public Invoice() {
    }

    public Invoice(long invoiceId, String invoiceName, Double invoiceAmount,
                   Date invoiceSubmittedTS, Date invoiceCompletedTS) {
        this.invoiceId = invoiceId;
        this.invoiceName = invoiceName;
        this.invoiceAmount = invoiceAmount;
        this.invoiceSubmittedTS = invoiceSubmittedTS;
        this.invoiceCompletedTS = invoiceCompletedTS;
    }

    public Invoice(long invoiceId, String invoiceName, Double invoiceAmount,
                   Date invoiceSubmittedTS, Date invoiceCompletedTS, List<Payment> invoicePaymentList) {
        this(invoiceId, invoiceName, invoiceAmount, invoiceSubmittedTS, invoiceCompletedTS);
        this.setInvoicePaymentList(invoicePaymentList);
    }

    public long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getInvoiceName() {
        return invoiceName;
    }

    public void setInvoiceName(String invoiceName) {
        this.invoiceName = invoiceName;
    }

    public Double getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(Double invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public Date getInvoiceSubmittedTS() {
        return invoiceSubmittedTS;
    }

    public void setInvoiceSubmittedTS(Date invoiceSubmittedTS) {
        this.invoiceSubmittedTS = invoiceSubmittedTS;
    }

    public Date getInvoiceCompletedTS() {
        return invoiceCompletedTS;
    }

    public void setInvoiceCompletedTS(Date invoiceCompletedTS) {
        this.invoiceCompletedTS = invoiceCompletedTS;
    }

    public List<Payment> getInvoicePaymentList() {
        return invoicePaymentList;
    }

    public void setInvoicePaymentList(List<Payment> invoicePaymentList) {
        this.invoicePaymentList = invoicePaymentList;
    }

    //TODO: override ToString
}
