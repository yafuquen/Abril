package com.yafuquen.abril.injection.component;

import com.yafuquen.abril.data.injection.module.ApplicationModule;
import com.yafuquen.abril.data.injection.module.UserModule;
import com.yafuquen.abril.ui.SignInActivity;
import com.yafuquen.abril.ui.TopicMessagesActivity;
import com.yafuquen.abril.ui.TopicsActivity;

import dagger.Component;

/**
 * Component for the user sandbox.
 *
 * @author yafuquen
 */
@Component(modules = {UserModule.class, ApplicationModule.class})
public interface UserComponent {

    void inject(SignInActivity signInActivity);

    void inject(TopicsActivity topicsActivity);

    void inject(TopicMessagesActivity topicMessagesActivity);
}
