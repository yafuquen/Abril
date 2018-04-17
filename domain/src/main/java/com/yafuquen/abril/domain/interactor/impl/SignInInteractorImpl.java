package com.yafuquen.abril.domain.interactor.impl;

import com.yafuquen.abril.domain.exception.SignInValidationException;
import com.yafuquen.abril.domain.interactor.SignInInteractor;
import com.yafuquen.abril.domain.model.User;
import com.yafuquen.abril.domain.repository.UserRepository;

import io.reactivex.Observable;

/**
 * Implementation of sign in use case.
 *
 * @author yafuquen
 */
class SignInInteractorImpl implements SignInInteractor {

    private final UserRepository userRepository;

    public SignInInteractorImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Observable<User> signIn(String user, String password) {
        if (!isValidUsername(user)) {
            return Observable.error(new SignInValidationException(
                    SignInValidationException.ValidationErrorType.INVALID_USERNAME));
        }
        if (!isValidPassword(password)) {
            return Observable.error(new SignInValidationException(
                    SignInValidationException.ValidationErrorType.INVALID_PASSWORD));
        }
        return userRepository.signIn(user, password);
    }

    private boolean isValidPassword(String password) {
        return password != null && password.trim().length() >= 8;
    }

    private boolean isValidUsername(String user) {
        return user != null && user.trim().length() >= 6;
    }
}
