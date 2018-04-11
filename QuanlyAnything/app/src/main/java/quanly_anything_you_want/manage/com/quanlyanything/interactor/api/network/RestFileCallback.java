package quanly_anything_you_want.manage.com.quanlyanything.interactor.api.network;

import java.io.IOException;
import java.net.SocketTimeoutException;

import okhttp3.ResponseBody;
import quanly_anything_you_want.manage.com.quanlyanything.MainApplication;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class RestFileCallback implements Callback<ResponseBody> {
    private String unKnowErr = "unknow err";
    private String timeOut = "timeOut";
    private String nointernet = "no internet";

    public abstract void success(ResponseBody res);

    public abstract void failure(RestError error);

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        if (response.isSuccessful()) {
            success(response.body());
        } else {
            RestError error = new RestError(APIErrorUtils.API_ERROR_UNKNOWN, unKnowErr);
            failure(error);
        }
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable throwable) {
        RestError error = null;
        if (throwable instanceof IOException) {
            if (throwable instanceof SocketTimeoutException) {
                error = new RestError(APIErrorUtils.API_ERROR_TIMED_OUT, timeOut);
            } else {
                error = new RestError(APIErrorUtils.API_ERROR_NO_NETWORK, nointernet);
            }
        }
        if (error == null) {
            error = new RestError(APIErrorUtils.API_ERROR_UNKNOWN, unKnowErr);
        }
        failure(error);
    }

}
