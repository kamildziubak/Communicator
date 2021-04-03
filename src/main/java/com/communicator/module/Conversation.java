package com.communicator.module;

public class Conversation {
    User[] users = new User[2];

    public Conversation(User[] users) {
        this.users = users;
    }

    public User[] getUsers() {
        return users;
    }

    public void setUsers(User[] users) {
        this.users = users;
    }
}