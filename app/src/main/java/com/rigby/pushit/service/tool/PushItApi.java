package com.rigby.pushit.service.tool;

import com.rigby.pushit.model.User;
import com.rigby.pushit.model.request.LoginRequest;
import com.rigby.pushit.model.response.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PushItApi {

    @POST(RetrofitTool.CLIENT_ENDPOINT + "/users/register")
    Call<Void> postRegister(
            @Body User user
    );

    @POST(RetrofitTool.CLIENT_ENDPOINT + "/users/login")
    Call<LoginResponse> postLogin(
            @Body LoginRequest loginRequest
    );
}