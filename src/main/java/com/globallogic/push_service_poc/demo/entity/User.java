package com.globallogic.push_service_poc.demo.entity;

import org.eclipse.persistence.nosql.annotations.DataFormatType;
import org.eclipse.persistence.nosql.annotations.Field;
import org.eclipse.persistence.nosql.annotations.JoinField;
import org.eclipse.persistence.nosql.annotations.NoSql;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by arkadii.tetelman on 3/19/14.
 */
@Entity
@NoSql(dataFormat = DataFormatType.MAPPED, dataType = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Field(name = "userId")
    private long userId;
    @Basic
    private String userEmail;
    @Basic
    private String userPassword;
    @Basic
    private String googleAPIKey;
    @OneToMany
    @JoinField(name = "deviceId")
    private List<Device> deviceList;
    @OneToMany
    @JoinField(name = "invoiceId")
    private List<Invoice> invoiceList;

    public User(long userId, String userEmail, String userPassword,
                String googleAPIKey) {
        this.userId = userId;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.googleAPIKey = googleAPIKey;
    }

    public User(long userId, String userEmail, String userPassword,
                String googleAPIKey, List<Device> deviceList, List<Invoice> invoiceList) {
        this(userId, userEmail, userPassword, googleAPIKey);
        this.setDeviceList(deviceList);
        this.setInvoiceList(invoiceList);
    }

    public User() {
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getGoogleAPIKey() {
        return googleAPIKey;
    }

    public void setGoogleAPIKey(String googleAPIKey) {
        this.googleAPIKey = googleAPIKey;
    }

    public List<Device> getDeviceList() {
        return deviceList;
    }

    public void setDeviceList(List<Device> deviceList) {
        this.deviceList = deviceList;
    }

    public List<Invoice> getInvoiceList() {
        return invoiceList;
    }

    public void setInvoiceList(List<Invoice> invoiceList) {
        this.invoiceList = invoiceList;
    }

    //TODO: override ToString
}
