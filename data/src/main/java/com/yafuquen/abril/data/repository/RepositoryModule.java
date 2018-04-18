package com.yafuquen.abril.data.repository;

import com.google.firebase.auth.FirebaseAuth;
import com.yafuquen.abril.domain.repository.TopicMessagesRepository;
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
    TopicsRepository provideTopicsRepository(TopicsRepositoryImpl topicsRepository) {
        return topicsRepository;
    }

    @Provides
    TopicMessagesRepository provideTopicMessagesRepository() {
        return new TopicMessagesRepositoryImpl();
    }
}
