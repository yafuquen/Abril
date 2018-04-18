package com.yafuquen.abril.domain.interactor.impl;

import com.yafuquen.abril.domain.interactor.TopicMessagesInteractor;
import com.yafuquen.abril.domain.model.Topic;
import com.yafuquen.abril.domain.model.TopicMessage;
import com.yafuquen.abril.domain.repository.TopicMessagesRepository;
import com.yafuquen.abril.domain.repository.UserRepository;

import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;

/**
 * Implementation for topic messages use case.
 *
 * @author yafuquen
 */
public class TopicMessagesInteractorImpl implements TopicMessagesInteractor {

    private final TopicMessagesRepository topicMessagesRepository;

    private final UserRepository userRepository;

    private final Scheduler scheduler;

    private final Scheduler observerScheduler;

    private final CompositeDisposable disposables = new CompositeDisposable();

    private DisposableObserver<TopicMessage> topicMessageDisposableObserver;

    private Topic topic;

    public TopicMessagesInteractorImpl(TopicMessagesRepository topicMessagesRepository,
            UserRepository userRepository, Scheduler scheduler,
            Scheduler observerScheduler) {
        this.topicMessagesRepository = topicMessagesRepository;
        this.userRepository = userRepository;
        this.scheduler = scheduler;
        this.observerScheduler = observerScheduler;
    }

    @Override
    public void loadTopicMessages(Topic topic,
            DisposableObserver<TopicMessage> topicMessagesDisposableObserver) {
        this.topic = topic;
        this.topicMessageDisposableObserver = topicMessagesDisposableObserver;
        disposables.add(this.topicMessagesRepository.startObservingMessages(topic).subscribeOn(
                scheduler).observeOn(observerScheduler).subscribeWith(
                topicMessagesDisposableObserver));
    }

    @Override
    public void sendMessage(Topic topic, String message,
            DisposableObserver<Void> addMessageDisposableObserver) {
        disposables.add(this.topicMessagesRepository.sendMessage(topic,
                userRepository.getCurrentUser(), message).subscribeOn(
                scheduler).observeOn(observerScheduler).subscribeWith(
                addMessageDisposableObserver));
    }

    @Override
    public void destroy() {
        disposables.clear();
        topicMessageDisposableObserver.dispose();
        topicMessageDisposableObserver = null;
    }

    @Override
    public void pause() {
        if (topicMessageDisposableObserver != null) {
            topicMessageDisposableObserver.onComplete();
        }
        if (topic != null) {
            topicMessagesRepository.stopObservingMessages(topic);
        }
    }
}
