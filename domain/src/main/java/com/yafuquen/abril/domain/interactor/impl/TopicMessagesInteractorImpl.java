package com.yafuquen.abril.domain.interactor.impl;

import com.yafuquen.abril.domain.interactor.TopicMessagesInteractor;
import com.yafuquen.abril.domain.model.Topic;
import com.yafuquen.abril.domain.repository.TopicsRepository;

/**
 * Implementation for topic messages use case.
 *
 * @author yafuquen
 */
public class TopicMessagesInteractorImpl implements TopicMessagesInteractor {

    private final TopicsRepository topicsRepository;

    public TopicMessagesInteractorImpl(TopicsRepository topicsRepository) {
        this.topicsRepository = topicsRepository;
    }

    @Override
    public void subscribeToTopic(Topic topic) {
        this.topicsRepository.subscribeToTopic(topic);
    }

    @Override
    public void destroy() {

    }
}
