package com.yafuquen.abril.injection.component;

import com.yafuquen.abril.data.injection.module.BaseUserModule;
import com.yafuquen.abril.data.injection.module.ApplicationModule;
import com.yafuquen.abril.ui.SignInActivity;

import dagger.Component;

/**
 * Component for the user sandbox.
 *
 * @author yafuquen
 */
@Component(modules = {BaseUserModule.class, ApplicationModule.class})
public interface UserComponent {

    void inject(SignInActivity signInActivity);
}
