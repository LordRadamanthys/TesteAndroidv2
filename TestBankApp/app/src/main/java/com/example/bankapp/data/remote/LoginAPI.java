package com.example.bankapp.data.remote;

import com.example.bankapp.data.remote.model.user.UserAccountModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginAPI {
    @FormUrlEncoded
    @POST("login")
    Call<UserAccountModel> login(
            @Field("user") String userName,
            @Field("password") String password);
}
