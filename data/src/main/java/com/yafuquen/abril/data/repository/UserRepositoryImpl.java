package com.yafuquen.abril.data.repository;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.yafuquen.abril.domain.exception.SignInException;
import com.yafuquen.abril.domain.model.User;
import com.yafuquen.abril.domain.repository.UserRepository;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

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
        return Observable.create(new ObservableOnSubscribe<User>() {
            @Override
            public void subscribe(final ObservableEmitter<User> emitter) throws Exception {
                firebaseAuth.signInWithEmailAndPassword(user, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    emitter.onNext(new User(
                                            firebaseAuth.getCurrentUser().getDisplayName()));
                                    emitter.onComplete();
                                } else {
                                    emitter.tryOnError(new SignInException());
                                }
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                emitter.tryOnError(new SignInException(e));
                            }
                        });
            }
        });
    }
}
