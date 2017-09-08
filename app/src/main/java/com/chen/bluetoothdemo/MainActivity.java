package com.chen.bluetoothdemo;

import android.graphics.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.clj.fastble.BleManager;
import com.clj.fastble.data.ScanResult;
import com.socks.library.KLog;

public class MainActivity extends AppCompatActivity {
    private BleManager bleManager;
    private ScanResult scanResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        bleManager = new BleManager(this);
        bleManager.enableBluetooth();

    }
}
