package com.yafuquen.abril.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.yafuquen.abril.data.injection.module.ApplicationModule;
import com.yafuquen.abril.data.injection.module.UserModule;
import com.yafuquen.abril.injection.component.DaggerUserComponent;
import com.yafuquen.abril.injection.component.UserComponent;
import com.yafuquen.abril.presenter.BaseView;

/**
 * Base activity.
 *
 * @author yafuquen
 */
public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject();
    }

    @Override
    public boolean isReady() {
        return !isFinishing();
    }

    protected abstract void injectToActivity(UserComponent userComponent);

    private void inject() {
        UserComponent userComponent = DaggerUserComponent.builder()
                .userModule(new UserModule())
                .applicationModule(new ApplicationModule())
                .build();
        injectToActivity(userComponent);
    }
}
