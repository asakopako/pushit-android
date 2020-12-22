package com.rigby.pushit.service;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.rigby.pushit.R;
import com.rigby.pushit.Session;
import com.rigby.pushit.controller.LoginActivity;
import com.rigby.pushit.controller.MainActivity;
import com.rigby.pushit.controller.RegisterActivity;
import com.rigby.pushit.model.User;
import com.rigby.pushit.model.request.LoginRequest;
import com.rigby.pushit.model.response.LoginResponse;
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

    public static void postLogin(LoginActivity loginActivity, LoginRequest loginRequest) {
        loginActivity.progressBarView.setVisibility(View.VISIBLE);

        RetrofitTool.getInstance().pushItApi.postLogin(loginRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    LoginResponse loginResponse = response.body();
                    Session.putString(loginActivity, Session.Key.TOKEN, loginResponse.getToken());
                    Session.putString(loginActivity, Session.Key.USER_ID, loginResponse.getId().toString());
                    Session.putString(loginActivity, Session.Key.EMAIL, loginRequest.getEmail());
                    Session.putString(loginActivity, Session.Key.PASSWORD, loginRequest.getPassword());

//                    //Creo que aquí debería comprobarse si el servicio existe para un usuario distinto
//                    //del actual (luego habría que detenerlo) o no, lanzarlo para este usuario

                    loginActivity.startActivity(new Intent(loginActivity, MainActivity.class));
                    loginActivity.finish();

//                    //Iniciar el servicio
//                    Intent intent = new Intent(activity, NotificationService.class);
//                    activity.startService(intent);

                } else {
                    loginActivity.progressBarView.setVisibility(View.GONE);
                    Toast.makeText(loginActivity, loginActivity.getString(R.string.loginError), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                loginActivity.progressBarView.setVisibility(View.GONE);
                Toast.makeText(loginActivity, loginActivity.getString(R.string.connectionError), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
