package com.yafuquen.abril.data.injection.module;

import com.yafuquen.abril.domain.interactor.impl.InteractorModule;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Module
public class ApplicationModule {

    @Provides
    @Named(InteractorModule.SUBSCRIBER_SCHEDULER)
    Scheduler providesScheduler() {
        return Schedulers.io();
    }

    @Provides
    @Named(InteractorModule.OBSERVER_SCHEDULER)
    Scheduler providesObserverScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
