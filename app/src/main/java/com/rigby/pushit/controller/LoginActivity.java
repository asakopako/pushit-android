package com.rigby.pushit.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.rigby.pushit.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.emailEditText) EditText emailEditText;
    @BindView(R.id.passwordEditText) EditText passwordEditText;
    @BindView(R.id.submitButton) Button submitButton;
    @BindView(R.id.registerButton) Button registerButton;
    @BindView(R.id.progressBar) public View progressBarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        configureViews();
    }

    private void configureViews() {
        submitButton.setOnClickListener(v -> {
        });

        registerButton.setOnClickListener(v -> {
            emailEditText.clearComposingText();
            passwordEditText.clearComposingText();
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        });
    }
}