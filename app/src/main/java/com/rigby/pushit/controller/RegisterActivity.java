package com.rigby.pushit.controller;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.rigby.pushit.R;
import com.rigby.pushit.model.User;
import com.rigby.pushit.service.UserService;
import com.rigby.pushit.service.tool.CryptoTool;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity  extends AppCompatActivity {

    @BindView(R.id.emailEditText) EditText emailEditText;
    @BindView(R.id.nameEditText) EditText nameEditText;
    @BindView(R.id.passwordEditText) EditText passwordEditText;
    @BindView(R.id.repeatPasswordEditText) EditText repeatPasswordEditText;
    @BindView(R.id.submitButton) Button submitButton;
    @BindView(R.id.backButton) Button backButton;
    @BindView(R.id.progressBar) public View progressBarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        configureViews();
    }

    private void configureViews() {

        submitButton.setOnClickListener(v -> {
            if(!passwordEditText.getText().toString().equals(repeatPasswordEditText.getText().toString())){
                Toast.makeText(RegisterActivity.this, getResources().getText(R.string.passwordError), Toast.LENGTH_SHORT).show();
            }
            else {
                User user = new User();
                user.setEmail(emailEditText.getText().toString());
                user.setName(nameEditText.getText().toString());
                user.setPassword(CryptoTool.digest(passwordEditText.getText().toString()));
                UserService.postRegister(RegisterActivity.this, user);
            }
        });

        backButton.setOnClickListener(v -> {
            emailEditText.clearComposingText();
            nameEditText.clearComposingText();
            passwordEditText.clearComposingText();
            repeatPasswordEditText.clearComposingText();

            onBackPressed();
        });
    }
}