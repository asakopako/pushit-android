package com.rigby.pushit.service.tool;

import com.rigby.pushit.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PushItApi {

    @POST(RetrofitTool.CLIENT_ENDPOINT + "/users/register")
    Call<Void> postRegister(
            @Body User user
    );
}