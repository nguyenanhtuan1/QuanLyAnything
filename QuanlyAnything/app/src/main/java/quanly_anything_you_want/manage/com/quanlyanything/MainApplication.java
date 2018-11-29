package quanly_anything_you_want.manage.com.quanlyanything;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;


@SuppressLint("StaticFieldLeak")
public class MainApplication extends Application {
    public static Context context;
//    public static Retrofit retrofit;


    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().name("myrealm.realm").build();
        Realm.setDefaultConfiguration(config);
//        GsonBuilder gsonBuilder = new GsonBuilder();
//        int cacheSize = 10 * 1024 * 1024;
//        OkHttpClient.Builder builder = new OkHttpClient.Builder();
//        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//        builder.addInterceptor(logging);
//        builder.addInterceptor(new HeadersInterceptor());
//        builder.cache(new Cache(getCacheDir(), cacheSize));
//        builder.connectTimeout(60, TimeUnit.SECONDS);
//        builder.readTimeout(60, TimeUnit.SECONDS);
//
//        retrofit = new Retrofit.Builder()
//                .baseUrl(AppConstants.URL_APP)
//                .addConverterFactory(ScalarsConverterFactory.create())
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
//                .client(builder.build())
//                .build();
    }
}