package com.globallogic.push_service_poc.demo.entity;

import org.eclipse.persistence.nosql.annotations.DataFormatType;
import org.eclipse.persistence.nosql.annotations.Field;
import org.eclipse.persistence.nosql.annotations.JoinField;
import org.eclipse.persistence.nosql.annotations.NoSql;

import javax.persistence.*;
import java.util.List;

@Entity
@NoSql(dataFormat = DataFormatType.MAPPED, dataType = "device")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Field(name = "deviceId")
    private String deviceId;

    public Device() {
    }

    public Device(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    //TODO: override ToString
}
