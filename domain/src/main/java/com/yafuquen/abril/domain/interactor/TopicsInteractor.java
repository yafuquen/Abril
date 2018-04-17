package com.yafuquen.abril.domain.interactor;

import com.yafuquen.abril.domain.model.Topic;

import io.reactivex.Observable;


/**
 * Interactor for topics management.
 *
 * @author yafuquen
 */
public interface TopicsInteractor {

    Observable<Topic> getAvailableTopics();
}
