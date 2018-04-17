package com.yafuquen.abril.presenter;

import com.yafuquen.abril.domain.interactor.TopicMessagesInteractor;
import com.yafuquen.abril.domain.model.Topic;
import com.yafuquen.abril.model.TopicParcel;

import javax.inject.Inject;

/**
 * Presenter for the topic messages.
 *
 * @author yafuquen
 */
public class TopicMessagesPresenter extends BasePresenter {

    private final TopicMessagesInteractor topicMessagesInteractor;

    private View view;

    private Topic topic;

    @Inject
    public TopicMessagesPresenter(TopicMessagesInteractor topicMessagesInteractor) {
        this.topicMessagesInteractor = topicMessagesInteractor;
    }

    public void showTopicMessages() {
        if (topic != null) {
            topicMessagesInteractor.subscribeToTopic(topic);
        }
    }

    @Override
    public void destroy() {

    }

    @Override
    protected BaseView getView() {
        return view;
    }

    public void subscribeToTopic(Topic topic) {
        topicMessagesInteractor.subscribeToTopic(topic);
    }

    public void setView(View view) {
        this.view = view;
    }

    public void loadTopic(TopicParcel topicParcel) {
        this.topic = new Topic();
        this.topic.setName(topicParcel.getName());
    }

    public interface View extends BaseView {

    }
}
