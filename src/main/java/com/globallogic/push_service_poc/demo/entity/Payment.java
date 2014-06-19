package com.globallogic.push_service_poc.demo.entity;

import org.eclipse.persistence.nosql.annotations.DataFormatType;
import org.eclipse.persistence.nosql.annotations.Field;
import org.eclipse.persistence.nosql.annotations.NoSql;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/** * Created by arkadii.tetelman on 3/19/14. */
@Entity
@NoSql(dataFormat= DataFormatType.MAPPED,dataType="payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Field(name = "paymentId")
    private long paymentId;
    @Basic
    @NotNull
    private Double amount;
    @Basic
    @Temporal(TemporalType.DATE)
    private Date debitTS;
    @Basic
    @Temporal(TemporalType.DATE)
    private Date creditTS;

    public Payment(long paymentId, Double amount, Date debitTs, Date creditTS) {
        this(paymentId,amount);
        this.setDebitTS(debitTs);
        this.setCreditTS(creditTS);
    }

    public Payment(long paymentId, Double amount) {
        this.setPaymentId(paymentId);
        this.setAmount(amount);
    }

    public Payment() {
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(long paymentId) {
        this.paymentId = paymentId;
    }

    public Date getDebitTS() {
        return debitTS;
    }

    public void setDebitTS(Date debitTS) {
        this.debitTS = debitTS;
    }

    public Date getCreditTS() {
        return creditTS;
    }

    public void setCreditTS(Date creditTS) {
        this.creditTS = creditTS;
    }

    //TODO: override ToString
}
