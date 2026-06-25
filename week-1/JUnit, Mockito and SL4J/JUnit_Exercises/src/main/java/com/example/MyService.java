package com.example;

public class MyService {
    private final ExternalApi externalApi;

    public MyService(ExternalApi externalApi) {
        this.externalApi = externalApi;
    }

    public String fetchData() {
        return externalApi.getData();
    }

    public String fetchDataById(int id) {
        return externalApi.getDataById(id);
    }

    public void doAction() {
        externalApi.performAction();
    }
    
    public void doActionWithException() {
        externalApi.performActionWithException();
    }

    public void processMultiple() {
        externalApi.getData();
        externalApi.performAction();
    }
}
