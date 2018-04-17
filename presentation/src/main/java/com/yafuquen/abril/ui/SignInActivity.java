package com.yafuquen.abril.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.widget.Button;
import android.widget.EditText;

import com.yafuquen.abril.R;
import com.yafuquen.abril.domain.exception.SignInValidationException;
import com.yafuquen.abril.injection.component.UserComponent;
import com.yafuquen.abril.presenter.SignInPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignInActivity extends BaseActivity implements SignInPresenter.View {

    @Inject
    SignInPresenter signInPresenter;

    @BindView(R.id.sign_in_username)
    EditText usernameInput;

    @BindView(R.id.sign_in_password)
    EditText passwordInput;

    @BindView(R.id.sign_in_action)
    Button signInButton;

    @OnClick(R.id.sign_in_action)
    void onSignInClick() {
        signIn();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject();
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);
        signInPresenter.setView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (signInPresenter != null) {
            signInPresenter.destroy();
        }
    }

    private void signIn() {
        signInButton.setEnabled(false);
        signInPresenter.signIn(usernameInput.getText().toString(), passwordInput.getText().toString());
    }

    @Override
    public void onSuccessSignIn() {
        signInButton.setEnabled(true);
        Intent intent = TopicsActivity.getCallingIntent(this);
        startActivity(intent);
    }

    @Override
    public void onFailedSignIn(Throwable exception) {
        signInButton.setEnabled(true);
        new AlertDialog.Builder(this).setMessage(getSignInExceptionMessage(exception))
                .setTitle(R.string.sign_in)
                .setPositiveButton(R.string.ok, null)
                .create()
                .show();
    }

    private int getSignInExceptionMessage(Throwable exception) {
        if (exception instanceof SignInValidationException) {
            switch (((SignInValidationException) exception).getValidationErrorSource()) {
                case INVALID_USERNAME:
                    return R.string.invalid_username;
                case INVALID_PASSWORD:
                    return R.string.invalid_password;
            }
        }
        return R.string.failed_sign_in;
    }

    @Override
    protected void injectToActivity(UserComponent userComponent) {
        userComponent.inject(this);
    }
}
