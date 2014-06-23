package com.globallogic.push_service_poc.demo.entity;

import org.eclipse.persistence.nosql.annotations.DataFormatType;
import org.eclipse.persistence.nosql.annotations.Field;
import org.eclipse.persistence.nosql.annotations.NoSql;

import javax.persistence.*;

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

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (o == this) return true;
        if (!(o instanceof Device)) return false;
        Device device = (Device) o;
        return this.getDeviceId() == device.getDeviceId();
    }

    //TODO: override ToString
}
