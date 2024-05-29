package com.s28572.tpo10.DTOs;

public class LinkDTO {

    private String name;
    private String targetUrl;
    private String password;

    public LinkDTO() {
    }

    public LinkDTO(String name, String targetUrl) {
        this.name = name;
        this.targetUrl = targetUrl;
    }

    public LinkDTO(String name, String targetUrl, String password) {
        this.name = name;
        this.targetUrl = targetUrl;
        this.password = password;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
