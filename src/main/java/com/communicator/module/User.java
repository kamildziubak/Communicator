package com.communicator.module;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    String login, name, password;

    public User(@JsonProperty("login") String login, @JsonProperty("password") String password, @JsonProperty("name") String name) {
        this.login = login;
        this.name = name;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
