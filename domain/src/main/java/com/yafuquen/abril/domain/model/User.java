package com.yafuquen.abril.domain.model;

/**
 * Model for user entity.
 *
 * @author yafuquen
 */
public class User {

    private final String displayName;

    private final String username;

    public User(String displayName, String username) {
        this.displayName = displayName;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
