package com.example.anil.shoppingApplication;

/**
 * Created by SYS1904 on 09-02-2017.
 */
public class BeginGetProfileRequest {

    String serviceProviderKey;
    String spCustomerId;
    String requestTransactionId;
    String transactionType;
    String appIdentity;
    boolean consent;

    public BeginGetProfileRequest() {
    }

    public BeginGetProfileRequest(String serviceProviderKey, String spCustomerId, String requestTransactionId, String transactionType, String deviceFingerPrint, String appIdentity, boolean consent) {
        this.serviceProviderKey = serviceProviderKey;
        this.spCustomerId = spCustomerId;
        this.requestTransactionId = requestTransactionId;
        this.transactionType = transactionType;
        this.appIdentity = appIdentity;
        this.consent = consent;
    }

    public String getServiceProviderKey() {
        return serviceProviderKey;
    }

    public void setServiceProviderKey(String serviceProviderKey) {
        this.serviceProviderKey = serviceProviderKey;
    }

    public String getSpCustomerId() {
        return spCustomerId;
    }

    public void setSpCustomerId(String spCustomerId) {
        this.spCustomerId = spCustomerId;
    }

    public String getRequestTransactionId() {
        return requestTransactionId;
    }

    public void setRequestTransactionId(String requestTransactionId) {
        this.requestTransactionId = requestTransactionId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }


    public String getAppIdentity() {
        return appIdentity;
    }

    public void setAppIdentity(String appIdentity) {
        this.appIdentity = appIdentity;
    }

    public boolean isConsent() {
        return consent;
    }

    public void setConsent(boolean consent) {
        this.consent = consent;
    }
}
