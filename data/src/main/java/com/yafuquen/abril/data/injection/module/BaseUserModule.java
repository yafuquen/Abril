package com.yafuquen.abril.data.injection.module;

import com.google.firebase.auth.FirebaseAuth;
import com.yafuquen.abril.data.repository.UserRepositoryImpl;
import com.yafuquen.abril.domain.interactor.SignInInteractor;
import com.yafuquen.abril.domain.interactor.impl.SignInInteractorImpl;
import com.yafuquen.abril.domain.repository.UserRepository;
import dagger.Module;
import dagger.Provides;

/**
 * Base module for user sandbox.
 *
 * @author yafuquen
 */
@Module public class BaseUserModule {

  @Provides FirebaseAuth provideFirebaseAuth() {
    return FirebaseAuth.getInstance();
  }

  @Provides UserRepository provideUserRepository(FirebaseAuth firebaseAuth) {
    return new UserRepositoryImpl(firebaseAuth);
  }

  @Provides SignInInteractor provideSignInInteractor(UserRepository userRepository) {
    return new SignInInteractorImpl(userRepository);
  }
}
