package com.yafuquen.abril.domain.interactor.impl;

import com.yafuquen.abril.domain.interactor.SignInInteractor;
import com.yafuquen.abril.domain.interactor.TopicMessagesInteractor;
import com.yafuquen.abril.domain.interactor.TopicsInteractor;
import com.yafuquen.abril.domain.repository.TopicsRepository;
import com.yafuquen.abril.domain.repository.UserRepository;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;

@Module
public class InteractorModule {

    public static final String SUBSCRIBER_SCHEDULER = "subscribeOn";

    public static final String OBSERVER_SCHEDULER = "observeOn";

    @Provides
    SignInInteractor provideSignInInteractor(UserRepository userRepository, @Named(SUBSCRIBER_SCHEDULER) Scheduler scheduler, @Named(OBSERVER_SCHEDULER) Scheduler observerScheduler) {
        return new SignInInteractorImpl(userRepository, scheduler, observerScheduler);
    }

    @Provides
    TopicsInteractor provideTopicsInteractor(TopicsRepository topicsRepository, @Named(SUBSCRIBER_SCHEDULER) Scheduler scheduler, @Named(OBSERVER_SCHEDULER) Scheduler observerScheduler) {
        return new TopicsInteractorImpl(topicsRepository, scheduler, observerScheduler);
    }

    @Provides
    TopicMessagesInteractor provideTopicMessagesInteractor(
            TopicsRepository topicsRepository) {
        return new TopicMessagesInteractorImpl(topicsRepository);
    }
}
