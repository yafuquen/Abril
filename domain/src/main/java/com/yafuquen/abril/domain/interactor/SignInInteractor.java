package com.yafuquen.abril.domain.interactor;

import com.yafuquen.abril.domain.model.User;

import io.reactivex.observers.DisposableObserver;

/**
 * Use case for sign in process.
 *
 * @author yafuquen
 */
public interface SignInInteractor extends BaseInteractor {

    void signIn(String user, String password, DisposableObserver<User> observer);
}
