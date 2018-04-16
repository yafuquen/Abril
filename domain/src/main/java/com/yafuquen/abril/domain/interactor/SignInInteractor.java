package com.yafuquen.abril.domain.interactor;

import com.yafuquen.abril.domain.model.User;
import io.reactivex.Observable;

/**
 * Use case for sign in process.
 *
 * @author yafuquen
 */
public interface SignInInteractor {

  Observable<User> signIn(String user, String password);
}
