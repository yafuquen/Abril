package com.yafuquen.abril.domain.interactor.impl;

import com.yafuquen.abril.domain.interactor.SignInInteractor;
import com.yafuquen.abril.domain.model.User;
import com.yafuquen.abril.domain.repository.UserRepository;
import io.reactivex.Observable;
import javax.inject.Inject;

/**
 * Implementation of sign in use case.
 *
 * @author yafuquen
 */
public class SignInInteractorImpl implements SignInInteractor {

  private final UserRepository userRepository;

  @Inject public SignInInteractorImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override public Observable<User> signIn(String user, String password) {
    return userRepository.signIn(user, password);
  }
}
