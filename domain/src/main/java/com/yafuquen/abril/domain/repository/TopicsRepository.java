package com.yafuquen.abril.domain.repository;

import com.yafuquen.abril.domain.model.Topic;

import io.reactivex.Observable;

/**
 * Repository for topic data.
 *
 * @author yafuquen
 */
public interface TopicsRepository {

    Observable<Topic> getAvailableTopics();
}
