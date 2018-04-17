package com.yafuquen.abril.domain.interactor;

import com.yafuquen.abril.domain.model.Topic;

import io.reactivex.observers.DisposableObserver;

/**
 * Interactor for topics management.
 *
 * @author yafuquen
 */
public interface TopicsInteractor extends BaseInteractor {

    void getAvailableTopics(DisposableObserver<Topic> observer);
}
