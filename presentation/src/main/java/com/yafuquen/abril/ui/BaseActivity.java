package com.yafuquen.abril.ui;

import android.support.v7.app.AppCompatActivity;

import com.yafuquen.abril.data.injection.module.ApplicationModule;
import com.yafuquen.abril.data.injection.module.UserModule;
import com.yafuquen.abril.injection.component.DaggerUserComponent;
import com.yafuquen.abril.injection.component.UserComponent;
import com.yafuquen.abril.presenter.BaseView;

/**
 * Created by yeimi.fuquen on 17/04/18.
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    @Override
    public boolean isReady() {
        return !isFinishing();
    }

    protected void inject() {
        UserComponent userComponent = DaggerUserComponent.builder()
                .userModule(new UserModule())
                .applicationModule(new ApplicationModule())
                .build();
        injectToActivity(userComponent);
    }

    protected abstract void injectToActivity(UserComponent userComponent);
}
