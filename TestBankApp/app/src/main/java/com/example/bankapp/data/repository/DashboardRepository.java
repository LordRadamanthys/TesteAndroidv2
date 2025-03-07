package com.example.bankapp.data.repository;

import com.example.bankapp.data.remote.BuildApi;
import com.example.bankapp.data.remote.model.dashboard.StatementListModel;
import com.example.bankapp.data.remote.statementListAPI;
import com.example.bankapp.domain.DashboardContract;
import com.example.bankapp.helper.BaseCallback;
import com.example.bankapp.helper.ConstantsStrings;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardRepository extends BuildApi implements DashboardContract.IRepository {
    @Override
    public void getList(long id, final BaseCallback<StatementListModel> result) {
        super.getBuild(statementListAPI.class).getList(id).enqueue(new Callback<StatementListModel>() {
            @Override
            public void onResponse(Call<StatementListModel> call, Response<StatementListModel> response) {
                if (response.body().getError().getMessage() != null) {
                    result.onUnsuccessful(response.body().getError().getMessage());
                    return;
                }

                result.onSuccessful(response.body());
            }

            @Override
            public void onFailure(Call<StatementListModel> call, Throwable t) {
                result.onUnsuccessful(ConstantsStrings.ERROR_INTERNET);
            }
        });
    }
}
