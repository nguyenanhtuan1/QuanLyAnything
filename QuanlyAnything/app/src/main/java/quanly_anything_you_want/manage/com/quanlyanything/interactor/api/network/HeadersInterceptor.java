package quanly_anything_you_want.manage.com.quanlyanything.interactor.api.network;


import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeadersInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        final Request originalRequest = chain.request();
        final Request requestWithUserAgent = originalRequest.newBuilder()
                .build();
        return chain.proceed(requestWithUserAgent);
    }

}
