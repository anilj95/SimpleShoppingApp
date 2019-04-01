package com.example.anil.shoppingApplication;

/**
 * Created by SYS1904 on 09-02-2017.
 */
public class StatusRequest {

    String serviceProviderKey;
    String mobilenumber;
    String refRequestTransactionId;
    String deviceFingerPrint;
    String appIdentity;
    public StatusRequest() {
    }

    public String getServiceProviderKey() {
        return serviceProviderKey;
    }

    public void setServiceProviderKey(String serviceProviderKey) {
        this.serviceProviderKey = serviceProviderKey;
    }

    public String getMobilenumber() {
        return mobilenumber;
    }

    public void setMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    public String getRefRequestTransactionId() {
        return refRequestTransactionId;
    }

    public void setRefRequestTransactionId(String refRequestTransactionId) {
        this.refRequestTransactionId = refRequestTransactionId;
    }

    public String getDeviceFingerPrint() {
        return deviceFingerPrint;
    }

    public void setDeviceFingerPrint(String deviceFingerPrint) {
        this.deviceFingerPrint = deviceFingerPrint;
    }

    public String getAppIdentity() {
        return appIdentity;
    }

    public void setAppIdentity(String appIdentity) {
        this.appIdentity = appIdentity;
    }
}
