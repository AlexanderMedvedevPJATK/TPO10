package com.s28572.tpo10;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Random;
import java.util.stream.Collectors;

@Entity
public class Link {

    @Id
    private String id;
    private String name;
    private String targetUrl;
    private String redirectUrl;
    private int visits;
    private String password;

    public Link() {
    }

    public Link(String name, String targetUrl) {
        this.id = generateId();
        this.name = name;
        this.targetUrl = targetUrl;
        this.redirectUrl = "http://localhost:8080/api/links/" + id;
        this.visits = 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public int getVisits() {
        return visits;
    }

    public void setVisits(int visits) {
        this.visits = visits;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void incrementVisits() {
        visits++;
    }

    private String generateId() {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Random random = new Random();

        return random.ints()
                .limit(10)
                .mapToObj(i -> alphabet.charAt(Math.abs(i % alphabet.length())))
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    @Override
    public String toString() {
        return "Link{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", targetUrl='" + targetUrl + '\'' +
                ", redirectUrl='" + redirectUrl + '\'' +
                ", visits=" + visits +
                ", password='" + password + '\'' +
                '}';
    }
}
