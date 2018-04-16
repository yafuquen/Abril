package com.yafuquen.abril.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import butterknife.BindView;
import butterknife.OnClick;
import com.yafuquen.abril.R;
import com.yafuquen.abril.data.injection.module.BaseUserModule;
import com.yafuquen.abril.injection.component.DaggerUserComponent;
import com.yafuquen.abril.injection.component.UserComponent;
import com.yafuquen.abril.presenter.SignInPresenter;
import javax.inject.Inject;

public class SignInActivity extends AppCompatActivity {

  @Inject SignInPresenter signInPresenter;

  @BindView(R.id.sign_in_username) EditText usernameInput;

  @BindView(R.id.sign_in_password) EditText passwordInput;

  @OnClick(R.id.sign_in_action) void onSignInClick() {
    signIn();
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    UserComponent userComponent =
        DaggerUserComponent.builder().baseUserModule(new BaseUserModule()).build();
    userComponent.inject(this);
    setContentView(R.layout.activity_sign_in);
  }

  private void signIn() {
    signInPresenter.signIn(usernameInput.getText().toString(), passwordInput.getText().toString());
  }
}
