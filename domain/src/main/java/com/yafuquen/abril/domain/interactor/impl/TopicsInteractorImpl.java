package com.yafuquen.abril.domain.interactor.impl;

import com.yafuquen.abril.domain.interactor.TopicsInteractor;
import com.yafuquen.abril.domain.model.Topic;
import com.yafuquen.abril.domain.repository.TopicsRepository;

import io.reactivex.Observable;

/**
 * Interactor implementation for topics management.
 *
 * @author yafuquen
 */
class TopicsInteractorImpl implements TopicsInteractor {

    private final TopicsRepository topicsRepository;

    public TopicsInteractorImpl(TopicsRepository topicsRepository) {
        this.topicsRepository = topicsRepository;
    }

    @Override
    public Observable<Topic> getAvailableTopics() {
        return topicsRepository.getAvailableTopics();
    }
}
