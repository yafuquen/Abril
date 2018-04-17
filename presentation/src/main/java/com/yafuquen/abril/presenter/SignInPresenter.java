package com.yafuquen.abril.presenter;

import com.yafuquen.abril.domain.interactor.SignInInteractor;
import com.yafuquen.abril.domain.model.User;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;

/**
 * Presenter for the sign in process.
 *
 * @author yafuquen
 */
public class SignInPresenter implements BasePresenter {

    private final SignInInteractor signInInteractor;

    private final Scheduler scheduler;

    private final CompositeDisposable disposables = new CompositeDisposable();

    private View view;

    @Inject
    public SignInPresenter(SignInInteractor signInInteractor, Scheduler scheduler) {
        this.signInInteractor = signInInteractor;
        this.scheduler = scheduler;
    }

    public void signIn(String username, String password) {
        disposables.add(signInInteractor.signIn(username, password).subscribeOn(scheduler)
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(
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
                        }));
    }

    private boolean isViewReady() {
        return view != null && view.isReady();
    }

    public void setView(View view) {
        this.view = view;
    }

    @Override
    public void destroy() {
        disposables.clear();
        view = null;
    }

    public interface View extends BaseView {

        void onSuccessSignIn();

        void onFailedSignIn(Throwable exception);
    }
}
