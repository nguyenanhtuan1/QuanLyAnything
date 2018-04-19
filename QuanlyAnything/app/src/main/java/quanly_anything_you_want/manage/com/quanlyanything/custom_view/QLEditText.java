package quanly_anything_you_want.manage.com.quanlyanything.custom_view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import quanly_anything_you_want.manage.com.quanlyanything.R;
import quanly_anything_you_want.manage.com.quanlyanything.utils.CommonUtil;

@SuppressLint("AppCompatCustomView")
public class QLEditText extends EditText {
    private boolean isShowClear = true;
    boolean isInputPrice;
    String currencyVN;

    public QLEditText(Context context) {
        super(context);
        init();
    }

    public QLEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public QLEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        final Drawable x = ContextCompat.getDrawable(getContext(), R.drawable.ic_clear_text);
        x.setBounds(0, 0, x.getIntrinsicWidth(), x.getIntrinsicHeight());

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if ((event.getX() > (getWidth() - getPaddingRight() - getPaddingLeft() - x.getIntrinsicWidth())) && hasFocus()) {
                    setText("");

                    if (listenerClear != null) {
                        listenerClear.onClearText(QLEditText.this);
                        return true;
                    }
                }
                return false;
            }
        });
        addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

                if (hasFocus() && getText().length() > 0) {
                    setCompoundDrawables(null, null, x, null);
                } else {
                    setCompoundDrawables(null, null, null, null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
        });
    }

    public int getValueInt() {
        try {
            return Integer.valueOf(getText().toString());
        } catch (Exception e) {
            return 0;
        }
    }

    public float getValueFloat() {
        try {
            return Float.valueOf(getText().toString());
        } catch (Exception e) {
            return 0;
        }
    }

    public double getValueDouble() {
        try {
            return Double.valueOf(getText().toString());
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public void setCompoundDrawables(@Nullable Drawable left, @Nullable Drawable top, @Nullable Drawable right, @Nullable Drawable bottom) {
        if (isShowClear)
            super.setCompoundDrawables(left, top, right, bottom);
    }

    public void setShowClear(boolean showClear) {
        isShowClear = showClear;
    }

    public void setInputPrice(boolean inputPrice) {
        isInputPrice = inputPrice;
    }

    public void setCurrencyVN(String currencyVN) {
        this.currencyVN = currencyVN;
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        if (focused) {
            InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT);
            }

            if (getText().length() > 0) {
                final Drawable x = ContextCompat.getDrawable(getContext(), R.drawable.ic_clear_text);
                x.setBounds(0, 0, x.getIntrinsicWidth(), x.getIntrinsicHeight());
                setCompoundDrawables(null, null, x, null);
            }

        } else {
            setCompoundDrawables(null, null, null, null);

            if (isInputPrice) {
                String value = getText().toString();
                if (CommonUtil.isCurrencyVND(currencyVN)) {
                    value = value.replace(".", "");
                } else {
                    if (value.contains(",")) {
                        value = value.replace(".", "");
                    }

                    value = value.replace(",", ".");
                }
                try {
                    setText(CommonUtil.showPriceNotCurrency(currencyVN, Double.valueOf(value)));
                } catch (Exception e) {
                    setText("");
                }
            }

            if (listener != null) {
                listener.onDismissKeyBoard(this);
            }

            InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(this.getWindowToken(), 0);
            }
        }
    }

    @Override
    public boolean onKeyPreIme(int keyCode, @NonNull KeyEvent event) {
        if (isFocusable()) {
            if ((keyCode == KeyEvent.KEYCODE_BACK
                    || keyCode == KeyEvent.ACTION_DOWN
                    || keyCode == KeyEvent.KEYCODE_ENTER)
                    && event.getAction() == KeyEvent.ACTION_UP) {
                clearFocus();
                return true;
            }
        }
        return super.onKeyPreIme(keyCode, event);
    }

    @Override
    public void onEditorAction(int actionId) {
        super.onEditorAction(actionId);
        if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                actionId == EditorInfo.IME_ACTION_DONE) {
            clearFocus();
        }
    }

    KeyboardListener listener;
    ClearTextListener listenerClear;

    public void setOnKeyboardListener(KeyboardListener listener) {
        this.listener = listener;
    }

    public void setOnClearTextListener(ClearTextListener listener) {
        this.listenerClear = listener;
    }

    public interface KeyboardListener {
        void onDismissKeyBoard(QLEditText keyboardEditText);
    }

    public interface ClearTextListener {
        void onClearText(QLEditText keyboardEditText);
    }

}
