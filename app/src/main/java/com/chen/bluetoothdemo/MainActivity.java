package com.chen.bluetoothdemo;

import android.bluetooth.BluetoothGatt;
import android.graphics.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.clj.fastble.BleManager;
import com.clj.fastble.conn.BleGattCallback;
import com.clj.fastble.data.ScanResult;
import com.clj.fastble.exception.BleException;
import com.clj.fastble.scan.ListScanCallback;
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

    public void start_blue_tooth(View view) {
        bleManager.enableBluetooth();
    }

    public void close_blue_tooth(View view) {
        bleManager.disableBluetooth();
    }

    public void scan_blue_tooth(View view) {
        bleManager.scanDevice(new ListScanCallback(15000) {
            @Override
            public void onScanning(ScanResult result) {// 当前正在扫描状态，且搜索到一个外围设备的回调
                KLog.e("正在扫描");
                scanResult = result;
                KLog.e(scanResult.describeContents() + "---" + scanResult.getDevice().toString() + "---" + scanResult.getRssi());
            }

            @Override
            public void onScanComplete(ScanResult[] results) {//扫描时间到或手动取消扫描后的回调
                KLog.e("扫描完成");
                KLog.e(results.clone() + "---" + results.length);
            }
        });

    }

    public void connect_blue_tooth(View view) {
        bleManager.connectDevice(scanResult, true, new BleGattCallback() {

            @Override
            public void onConnecting(BluetoothGatt gatt, int status) {//正在连接
                KLog.e(gatt.getConnectedDevices().toString());
                KLog.e(status);
            }

            @Override
            public void onConnectError(BleException exception) {// 连接未成功的回调，通过解析BleException来判断具体未成功的原因；
                KLog.e(exception.toString());
            }

            @Override
            public void onConnectSuccess(BluetoothGatt gatt, int status) {//连接成功的回调

            }

            @Override
            public void onServicesDiscovered(BluetoothGatt gatt, int status) {//发现服务的回调；

            }

            @Override
            public void onDisConnected(BluetoothGatt gatt, int status, BleException exception) {//连接断开的回调，特指连接之后的断开。

            }

        });
    }
}
