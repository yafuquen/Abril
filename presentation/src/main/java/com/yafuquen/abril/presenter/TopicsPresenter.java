package com.yafuquen.abril.presenter;

import com.yafuquen.abril.domain.interactor.TopicsInteractor;
import com.yafuquen.abril.domain.model.Topic;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;

/**
 * Presenter for the topics management.
 *
 * @author yafuquen
 */
public class TopicsPresenter implements BasePresenter {

    private final TopicsInteractor topicsInteractor;

    private final Scheduler scheduler;

    private final CompositeDisposable disposables = new CompositeDisposable();

    private View view;

    @Inject
    public TopicsPresenter(TopicsInteractor topicsInteractor, Scheduler scheduler) {
        this.topicsInteractor = topicsInteractor;
        this.scheduler = scheduler;
    }

    public void loadTopics() {
        disposables.add(topicsInteractor.getAvailableTopics().subscribeOn(scheduler)
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(
                        new DisposableObserver<Topic>() {
                            @Override
                            public void onNext(Topic topic) {
                                if (isViewReady()) {
                                    view.onReceivedTopic(topic);
                                }
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        }));
    }

    @Override
    public void destroy() {
        disposables.clear();
        view = null;
    }

    private boolean isViewReady() {
        return view != null && view.isReady();
    }

    public void setView(View view) {
        this.view = view;
    }

    public interface View extends BaseView {

        void onReceivedTopic(Topic topic);
    }
}
