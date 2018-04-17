package com.yafuquen.abril.domain.repository;

import com.yafuquen.abril.domain.model.User;

import io.reactivex.Observable;

/**
 * Repository for user process.
 *
 * @author yafuquen
 */
public interface UserRepository {

    Observable<User> signIn(String user, String password);
}
