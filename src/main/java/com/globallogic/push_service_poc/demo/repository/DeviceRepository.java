package com.globallogic.push_service_poc.demo.repository;

public interface DeviceRepository {

    public void registerDevice(Long userId, String deviceId);

    public void unregisterDevice(Long userId, String deviceId);
}
