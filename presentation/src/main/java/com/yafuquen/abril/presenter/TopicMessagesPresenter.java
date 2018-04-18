package com.yafuquen.abril.presenter;

import com.yafuquen.abril.domain.interactor.TopicMessagesInteractor;
import com.yafuquen.abril.domain.model.Topic;
import com.yafuquen.abril.domain.model.TopicMessage;
import com.yafuquen.abril.model.TopicParcel;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

/**
 * Presenter for the topic messages.
 *
 * @author yafuquen
 */
public class TopicMessagesPresenter extends Presenter {

    private final TopicMessagesInteractor topicMessagesInteractor;

    private View view;

    private Topic topic;

    @Inject
    public TopicMessagesPresenter(TopicMessagesInteractor topicMessagesInteractor) {
        this.topicMessagesInteractor = topicMessagesInteractor;
    }

    @Override
    public void resume() {
        if (topic != null) {
            topicMessagesInteractor.loadTopicMessages(topic,
                    new DisposableObserver<TopicMessage>() {
                        @Override
                        public void onNext(TopicMessage topicMessage) {
                            if (isViewReady()) {
                                view.showMessage(topicMessage);
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
    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }

    @Override
    protected BaseView getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public void loadTopic(TopicParcel topicParcel) {
        this.topic = new Topic();
        this.topic.setName(topicParcel.getName());
        if (isViewReady()) {
            view.showTopic(topic);
        }
    }

    public void sendMessage(String message) {
        if (message.trim().length() > 0) {
            topicMessagesInteractor.sendMessage(topic, message, new DisposableObserver<Void>() {
                @Override
                public void onNext(Void aVoid) {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onComplete() {

                }
            });
        }
    }

    public interface View extends BaseView {

        void showTopic(Topic topic);

        void showMessage(TopicMessage topicMessage);
    }
}
