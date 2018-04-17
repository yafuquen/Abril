package com.yafuquen.abril.data.injection.module;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

@Module
public class ApplicationModule {

    @Provides
    Scheduler providesScheduler() {
        return Schedulers.io();
    }
}
