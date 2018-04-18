package quanly_anything_you_want.manage.com.quanlyanything.screen.scanActivity;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.CompoundBarcodeView;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

import quanly_anything_you_want.manage.com.quanlyanything.R;

public class CustomScannerActivity extends AppCompatActivity {

    private CaptureManager capture;
    private DecoratedBarcodeView barcodeScannerView;
    private Button btnFlash;
    boolean isChecked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_scanner);
        barcodeScannerView =  findViewById(R.id.zxing_barcode);
        btnFlash = (Button) findViewById(R.id.btn_flash);
        btnFlash.setText(getString(R.string.text_flash_off));

        if (!hasFlash()) {
            btnFlash.setVisibility(View.GONE);
        }

        btnFlash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isChecked = !isChecked;
                if (isChecked) {
                    barcodeScannerView.setTorchOn();
                    btnFlash.setText(getString(R.string.text_flash_on));

                } else {
                    barcodeScannerView.setTorchOff();
                    btnFlash.setText(getString(R.string.text_flash_off));
                }
            }
        });

        capture = new CaptureManager(this, barcodeScannerView);
        capture.initializeFromIntent(getIntent(), savedInstanceState);
        capture.decode();
    }

    @Override
    protected void onResume() {
        super.onResume();
        capture.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        capture.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        capture.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        capture.onSaveInstanceState(outState);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return barcodeScannerView.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
    }

    private boolean hasFlash() {
        return getApplicationContext().getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
    }

}
