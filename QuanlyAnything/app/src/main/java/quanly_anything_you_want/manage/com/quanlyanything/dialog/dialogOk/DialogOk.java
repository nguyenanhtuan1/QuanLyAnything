package quanly_anything_you_want.manage.com.quanlyanything.dialog.dialogOk;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.horical.gito.chat.R;


public class DialogOk extends Dialog {

    private String mTitle, mMessage;
    private IOkDialogListener mListener;

    public DialogOk(Context context, String title, String message, IOkDialogListener listener) {
        super(context, R.style.FullscreenDialog);
        mTitle = title;
        mMessage = message;
        mListener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (getWindow() != null && getWindow().getDecorView() != null) {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            }
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        setContentView(R.layout.dialog_ok);

        TextView mTvTitle = (TextView) findViewById(R.id.dialog_title);
        TextView mTvMessage = (TextView) findViewById(R.id.dialog_message);
        Button mBtnOk = (Button) findViewById(R.id.dialog_btn_ok);

        mTvTitle.setText(mTitle);
        mTvMessage.setText(mMessage);
        mBtnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onIOkDialogAnswerOk(DialogOk.this);
                } else {
                    dismiss();
                }
            }
        });
    }

    public interface IOkDialogListener {
        void onIOkDialogAnswerOk(DialogOk dialog);
    }

}
