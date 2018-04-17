package com.yafuquen.abril.data.repository;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.messaging.FirebaseMessaging;
import com.yafuquen.abril.domain.repository.TopicsRepository;
import com.yafuquen.abril.domain.repository.UserRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Provides
    UserRepository provideUserRepository(FirebaseAuth firebaseAuth) {
        return new UserRepositoryImpl(firebaseAuth);
    }

    @Provides
    TopicsRepository provideTopicsRepository(FirebaseMessaging firebaseMessaging) {
        return new TopicsRepositoryImpl(firebaseMessaging);
    }
}
