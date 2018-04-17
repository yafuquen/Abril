package com.yafuquen.abril.domain.interactor;

import com.yafuquen.abril.domain.model.Topic;

/**
 * Use case for topic messages.
 *
 * @author yafuquen
 */
public interface TopicMessagesInteractor extends BaseInteractor {

    void subscribeToTopic(Topic topic);
}
