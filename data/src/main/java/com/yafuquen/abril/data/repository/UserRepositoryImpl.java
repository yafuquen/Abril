package com.yafuquen.abril.data.repository;

import com.google.firebase.auth.FirebaseAuth;
import com.yafuquen.abril.domain.exception.SignInException;
import com.yafuquen.abril.domain.model.User;
import com.yafuquen.abril.domain.repository.UserRepository;

import io.reactivex.Observable;

/**
 * Implementation of user repository.
 *
 * @author yafuquen
 */
class UserRepositoryImpl implements UserRepository {

    private final FirebaseAuth firebaseAuth;

    public UserRepositoryImpl(FirebaseAuth firebaseAuth) {
        this.firebaseAuth = firebaseAuth;
    }

    @Override
    public Observable<User> signIn(final String user, final String password) {
        return Observable.create(emitter -> firebaseAuth.signInWithEmailAndPassword(user, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        emitter.onNext(getCurrentUser());
                        emitter.onComplete();
                    } else {
                        emitter.tryOnError(new SignInException());
                    }
                })
                .addOnFailureListener(e -> emitter.tryOnError(new SignInException(e))));
    }

    @Override
    public User getCurrentUser() {
        if (firebaseAuth.getCurrentUser() != null) {
            return new User(firebaseAuth.getCurrentUser().getEmail());
        }
        return null;
    }
}
