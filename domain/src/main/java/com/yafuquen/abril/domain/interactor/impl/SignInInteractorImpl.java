package com.yafuquen.abril.domain.interactor.impl;

import com.yafuquen.abril.domain.exception.SignInValidationException;
import com.yafuquen.abril.domain.interactor.SignInInteractor;
import com.yafuquen.abril.domain.model.User;
import com.yafuquen.abril.domain.repository.UserRepository;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;

/**
 * Implementation of sign in use case.
 *
 * @author yafuquen
 */
class SignInInteractorImpl implements SignInInteractor {

    private final UserRepository userRepository;

    private final Scheduler scheduler;

    private final Scheduler observerScheduler;

    private final CompositeDisposable disposables = new CompositeDisposable();

    public SignInInteractorImpl(UserRepository userRepository, Scheduler scheduler, Scheduler observerScheduler) {
        this.userRepository = userRepository;
        this.scheduler = scheduler;
        this.observerScheduler = observerScheduler;
    }

    public void signIn(String user, String password, DisposableObserver<User> observer) {
        Observable<User> observable = null;
        if (!isValidUsername(user)) {
            observable = Observable.error(new SignInValidationException(
                    SignInValidationException.ValidationErrorType.INVALID_USERNAME));
        }
        if (!isValidPassword(password)) {
            observable = Observable.error(new SignInValidationException(
                    SignInValidationException.ValidationErrorType.INVALID_PASSWORD));
        }
        if (observable == null) {
            observable = userRepository.signIn(user, password);
        }
        disposables.add(observable.subscribeOn(scheduler)
                .observeOn(observerScheduler).subscribeWith(observer));
    }

    private boolean isValidPassword(String password) {
        return password != null && password.trim().length() >= 8;
    }

    private boolean isValidUsername(String user) {
        return user != null && user.trim().length() >= 6;
    }

    @Override
    public void destroy() {
        disposables.clear();
    }
}
