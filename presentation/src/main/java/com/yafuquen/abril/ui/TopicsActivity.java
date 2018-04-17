package com.yafuquen.abril.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.yafuquen.abril.R;
import com.yafuquen.abril.data.injection.module.ApplicationModule;
import com.yafuquen.abril.data.injection.module.UserModule;
import com.yafuquen.abril.domain.model.Topic;
import com.yafuquen.abril.injection.component.DaggerUserComponent;
import com.yafuquen.abril.injection.component.UserComponent;
import com.yafuquen.abril.presenter.TopicsPresenter;
import com.yafuquen.abril.ui.adapter.TopicsAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TopicsActivity extends AppCompatActivity implements TopicsPresenter.View {

    private TopicsAdapter topicsAdapter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.topic_list)
    ListView topicListView;

    @Inject
    TopicsPresenter topicsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject();
        setContentView(R.layout.activity_topics);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        topicsAdapter = new TopicsAdapter(this);
        topicListView.setAdapter(topicsAdapter);
        topicsPresenter.setView(this);
        topicsPresenter.loadTopics();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (topicsPresenter != null) {
            topicsPresenter.destroy();
        }
    }

    public static Intent getCallingIntent(Activity activity) {
        return new Intent(activity, TopicsActivity.class);
    }

    private void inject() {
        UserComponent userComponent =
                DaggerUserComponent.builder().userModule(
                        new UserModule()).applicationModule(new ApplicationModule()).build();
        userComponent.inject(this);
    }

    @Override
    public void onReceivedTopic(Topic topic) {
        topicsAdapter.add(topic);
    }

    @Override
    public boolean isReady() {
        return !isFinishing();
    }
}
