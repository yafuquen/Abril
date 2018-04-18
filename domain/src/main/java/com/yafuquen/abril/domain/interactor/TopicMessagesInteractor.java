package com.yafuquen.abril.domain.interactor;

import com.yafuquen.abril.domain.model.Topic;
import com.yafuquen.abril.domain.model.TopicMessage;

import io.reactivex.observers.DisposableObserver;

/**
 * Use case for topic messages.
 *
 * @author yafuquen
 */
public interface TopicMessagesInteractor extends BaseInteractor {

    void loadTopicMessages(Topic topic,
            DisposableObserver<TopicMessage> topicMessagesDisposableObserver);

    void sendMessage(Topic topic, String message,
            DisposableObserver<Void> addMessageDisposableObserver);

    String getUsername();
}
