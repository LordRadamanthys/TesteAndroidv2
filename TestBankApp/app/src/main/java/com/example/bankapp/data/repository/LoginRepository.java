package com.example.bankapp.data.repository;

import com.example.bankapp.domain.UserContract;
import com.example.bankapp.helper.BaseCallback;
import com.example.bankapp.helper.ConstantsStrings;
import com.example.bankapp.data.remote.model.user.UserAccountModel;
import com.example.bankapp.data.remote.BuildApi;
import com.example.bankapp.data.remote.LoginAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository extends BuildApi implements UserContract.IRepository {


    @Override
    public void login(final String userName, final String password, final BaseCallback<UserAccountModel> result) {
        super.getBuild(LoginAPI.class)
                .login(userName, password).enqueue(new Callback<UserAccountModel>() {
            @Override
            public void onResponse(Call<UserAccountModel> call, Response<UserAccountModel> response) {
                if (response.body().getError().getMessage() != null) {
                    result.onUnsuccessful(response.body().getError().getMessage());
                    return;
                }

                result.onSuccessful(response.body());

            }

            @Override
            public void onFailure(Call<UserAccountModel> call, Throwable t) {
                result.onUnsuccessful(ConstantsStrings.ERROR_INTERNET);
            }
        });
    }


}
