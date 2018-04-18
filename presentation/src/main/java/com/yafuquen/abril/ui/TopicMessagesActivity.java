package com.yafuquen.abril.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.ListView;

import com.yafuquen.abril.R;
import com.yafuquen.abril.domain.model.Topic;
import com.yafuquen.abril.domain.model.TopicMessage;
import com.yafuquen.abril.injection.component.UserComponent;
import com.yafuquen.abril.model.TopicParcel;
import com.yafuquen.abril.presenter.TopicMessagesPresenter;
import com.yafuquen.abril.ui.adapter.TopicMessagesAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TopicMessagesActivity extends BaseActivity implements TopicMessagesPresenter.View {

    private static final String TOPIC = "topic";

    private TopicMessagesAdapter topicMessagesAdapter;

    @Inject
    TopicMessagesPresenter topicMessagesPresenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.message_text)
    EditText messageInput;

    @BindView(R.id.message_list)
    ListView messageList;

    @OnClick(R.id.send_message)
    void onSendMessageClick() {
        sendMessage();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_messages);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        topicMessagesPresenter.setView(this);
        topicMessagesAdapter = new TopicMessagesAdapter(this, topicMessagesPresenter.getUsername());
        messageList.setAdapter(topicMessagesAdapter);
        if (getIntent().hasExtra(TOPIC)) {
            topicMessagesPresenter.loadTopic(getIntent().getParcelableExtra(TOPIC));
        } else {
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        topicMessagesPresenter.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        topicMessagesAdapter.clear();
        topicMessagesPresenter.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        topicMessagesPresenter.destroy();
    }

    @Override
    public void showTopic(Topic topic) {
        setTitle(topic.getName());
    }

    @Override
    public void showMessage(TopicMessage topicMessage) {
        topicMessagesAdapter.add(topicMessage);

    }

    private void sendMessage() {
        topicMessagesPresenter.sendMessage(messageInput.getText().toString());
        messageInput.getText().clear();
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
