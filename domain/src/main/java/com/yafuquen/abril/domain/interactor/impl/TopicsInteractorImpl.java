package com.yafuquen.abril.domain.interactor.impl;

import com.yafuquen.abril.domain.interactor.TopicsInteractor;
import com.yafuquen.abril.domain.model.Topic;
import com.yafuquen.abril.domain.repository.TopicsRepository;

import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;

/**
 * Interactor implementation for topics management.
 *
 * @author yafuquen
 */
class TopicsInteractorImpl implements TopicsInteractor {

    private final TopicsRepository topicsRepository;

    private final Scheduler scheduler;

    private final Scheduler observerScheduler;

    private final CompositeDisposable disposables = new CompositeDisposable();

    public TopicsInteractorImpl(TopicsRepository topicsRepository, Scheduler scheduler, Scheduler observerScheduler) {
        this.topicsRepository = topicsRepository;
        this.scheduler = scheduler;
        this.observerScheduler = observerScheduler;
    }

    @Override
    public void getAvailableTopics(DisposableObserver<Topic> observer) {
        disposables.add(topicsRepository.getAvailableTopics().subscribeOn(scheduler).observeOn(observerScheduler).subscribeWith(observer));
    }

    @Override
    public void destroy() {
        disposables.clear();
    }
}
