package com.yafuquen.abril.domain.interactor.impl;

import com.yafuquen.abril.domain.interactor.SignInInteractor;
import com.yafuquen.abril.domain.interactor.TopicsInteractor;
import com.yafuquen.abril.domain.repository.TopicsRepository;
import com.yafuquen.abril.domain.repository.UserRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class InteractorModule {

    @Provides
    SignInInteractor provideSignInInteractor(UserRepository userRepository) {
        return new SignInInteractorImpl(userRepository);
    }

    @Provides
    TopicsInteractor provideTopicsInteractor(TopicsRepository topicsRepository) {
        return new TopicsInteractorImpl(topicsRepository);
    }
}
