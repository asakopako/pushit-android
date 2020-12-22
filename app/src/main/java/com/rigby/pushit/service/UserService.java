package com.rigby.pushit.service;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.rigby.pushit.R;
import com.rigby.pushit.controller.LoginActivity;
import com.rigby.pushit.controller.RegisterActivity;
import com.rigby.pushit.model.User;
import com.rigby.pushit.service.tool.RetrofitTool;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserService {
    public static void postRegister(RegisterActivity registerActivity, User user) {
        registerActivity.progressBarView.setVisibility(View.VISIBLE);

        RetrofitTool.getInstance().pushItApi.postRegister(user).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful() /*&& response.body() != null*/) {
                    Toast.makeText(registerActivity, registerActivity.getResources().getText(R.string.registerOk), Toast.LENGTH_SHORT).show();
                    registerActivity.startActivity(new Intent(registerActivity, LoginActivity.class));
                    registerActivity.finish();
                } else {
                    registerActivity.progressBarView.setVisibility(View.GONE);
                    Toast.makeText(registerActivity, registerActivity.getString(R.string.registerError), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                registerActivity.progressBarView.setVisibility(View.GONE);
                Toast.makeText(registerActivity, registerActivity.getString(R.string.connectionError), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
