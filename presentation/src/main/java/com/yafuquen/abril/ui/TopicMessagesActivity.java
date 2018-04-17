package com.yafuquen.abril.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.yafuquen.abril.R;
import com.yafuquen.abril.injection.component.UserComponent;
import com.yafuquen.abril.model.TopicParcel;
import com.yafuquen.abril.presenter.TopicMessagesPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TopicMessagesActivity extends BaseActivity implements TopicMessagesPresenter.View {

    private static final String TOPIC = "topic";

    @Inject
    TopicMessagesPresenter topicMessagesPresenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject();
        setContentView(R.layout.activity_topic_messages);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        topicMessagesPresenter.setView(this);
        if (getIntent().hasExtra(TOPIC)) {
            topicMessagesPresenter.loadTopic((TopicParcel) getIntent().getParcelableExtra(TOPIC));
            topicMessagesPresenter.showTopicMessages();
        } else {
            finish();
        }
    }

    public static Intent getCallingIntent(Activity activity, TopicParcel topic) {
        Intent intent = new Intent(activity, TopicMessagesActivity.class);
        intent.putExtra(TOPIC, topic);
        return intent;
    }

    @Override
    protected void injectToActivity(UserComponent userComponent) {
        userComponent.inject(this);
    }
}
