package com.yafuquen.abril.presenter;

import com.yafuquen.abril.domain.interactor.SignInInteractor;
import javax.inject.Inject;

/**
 * Presenter for the sign in process.
 *
 * @author yafuquen
 */
public class SignInPresenter {

  private final SignInInteractor signInInteractor;

  @Inject public SignInPresenter(SignInInteractor signInInteractor) {
    this.signInInteractor = signInInteractor;
  }

  public void signIn(String username, String password) {
    signInInteractor.signIn(username, password);
  }
}
