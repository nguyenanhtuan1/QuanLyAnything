package quanly_anything_you_want.manage.com.quanlyanything.images;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;

import quanly_anything_you_want.manage.com.quanlyanything.R;

public class ImageLoader {

    public static void loadImagePhoto(Context context, String url, ImageView imageView) {
        if (!TextUtils.isEmpty(url)) {
            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .placeholder(R.mipmap.ic_launcher_round)
                    .error(R.mipmap.ic_launcher_round);
            Glide.with(context).load(url).apply(options).into(imageView);
        }

    }

    public static void loadImagePhoto2(Context context, File file, ImageView imageView) {
        if (file != null) {
            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .placeholder(R.mipmap.ic_launcher_round)
                    .error(R.mipmap.ic_launcher_round);
            Glide.with(context).load(file.getPath()).apply(options).into(imageView);
        }

    }
}
