package com.yafuquen.abril.data.injection.module;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.messaging.FirebaseMessaging;
import com.yafuquen.abril.data.repository.RepositoryModule;
import com.yafuquen.abril.domain.interactor.impl.InteractorModule;

import dagger.Module;
import dagger.Provides;

/**
 * Base module for user sandbox.
 *
 * @author yafuquen
 */
@Module(includes = {InteractorModule.class, RepositoryModule.class})
public class UserModule {

    @Provides
    FirebaseAuth provideFirebaseAuth() {
        return FirebaseAuth.getInstance();
    }

    @Provides
    FirebaseMessaging provideFirebaseMessaging() {
        return FirebaseMessaging.getInstance();
    }
}
