package com.yafuquen.abril.presenter;

import com.yafuquen.abril.domain.interactor.TopicsInteractor;
import com.yafuquen.abril.domain.model.Topic;
import com.yafuquen.abril.model.TopicParcel;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

/**
 * Presenter for the topics management.
 *
 * @author yafuquen
 */
public class TopicsPresenter extends BasePresenter {

    private final TopicsInteractor topicsInteractor;

    private View view;

    @Inject
    public TopicsPresenter(TopicsInteractor topicsInteractor) {
        this.topicsInteractor = topicsInteractor;
    }

    public void loadTopics() {
        topicsInteractor.getAvailableTopics(
                new DisposableObserver<Topic>() {
                    @Override
                    public void onNext(Topic topic) {
                        if (isViewReady()) {
                            view.onReceivedTopic(new TopicParcel(topic));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void destroy() {
        topicsInteractor.destroy();
        view = null;
    }

    @Override
    protected BaseView getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public interface View extends BaseView {

        void onReceivedTopic(TopicParcel topic);
    }
}
