package com.yafuquen.abril.presenter;

import com.yafuquen.abril.domain.interactor.SignInInteractor;
import com.yafuquen.abril.domain.model.User;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

/**
 * Presenter for the sign in process.
 *
 * @author yafuquen
 */
public class SignInPresenter extends BasePresenter {

    private final SignInInteractor signInInteractor;

    private View view;

    @Inject
    public SignInPresenter(SignInInteractor signInInteractor) {
        this.signInInteractor = signInInteractor;
    }

    public void signIn(String username, String password) {
        signInInteractor.signIn(username, password,
                new DisposableObserver<User>() {
                    @Override
                    public void onNext(User user) {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (isViewReady()) {
                            view.onFailedSignIn(e);
                        }
                    }

                    @Override
                    public void onComplete() {
                        if (isViewReady()) {
                            view.onSuccessSignIn();
                        }
                    }
                });
    }

    public void setView(View view) {
        this.view = view;
    }

    @Override
    public void destroy() {
        signInInteractor.destroy();
        view = null;
    }

    @Override
    protected BaseView getView() {
        return view;
    }

    public interface View extends BaseView {

        void onSuccessSignIn();

        void onFailedSignIn(Throwable exception);
    }
}
