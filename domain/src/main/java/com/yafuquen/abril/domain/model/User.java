package com.yafuquen.abril.domain.model;

/**
 * Model for user entity.
 *
 * @author yafuquen
 */
public class User {

    private final String username;

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
