package com.yafuquen.abril.domain.repository;

import com.yafuquen.abril.domain.model.Topic;
import com.yafuquen.abril.domain.model.TopicMessage;
import com.yafuquen.abril.domain.model.User;

import io.reactivex.Observable;

/**
 * Repository for topic messages.
 *
 * @author yafuquen
 */
public interface TopicMessagesRepository {

    Observable<TopicMessage> getMessagesForTopic(Topic topic);

    void stopTopicMessagesObservable();

    Observable<Void> sendMessage(Topic topic, User user, String message);
}
