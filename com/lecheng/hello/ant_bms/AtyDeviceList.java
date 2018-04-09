package com.lecheng.hello.ant_bms;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import java.util.Set;

public class AtyDeviceList extends Activity {
    private static final boolean f3D = true;
    public static String EXTRA_DEVICE_ADDRESS = "device_address";
    private static final String TAG = "AtyDeviceList";
    private BluetoothAdapter mBtAdapter;
    @SuppressLint({"NewApi"})
    private OnItemClickListener mDeviceClickListener = new C01932();
    private ArrayAdapter<String> mNewDevicesArrayAdapter;
    private ArrayAdapter<String> mPairedDevicesArrayAdapter;
    private final BroadcastReceiver mReceiver = new C01943();

    class C01921 implements OnClickListener {
        C01921() {
        }

        public void onClick(View v) {
            AtyDeviceList.this.doDiscovery();
            v.setVisibility(8);
        }
    }

    class C01932 implements OnItemClickListener {
        C01932() {
        }

        @SuppressLint({"NewApi"})
        public void onItemClick(AdapterView<?> adapterView, View v, int arg2, long arg3) {
            try {
                AtyDeviceList.this.mBtAdapter.cancelDiscovery();
                String info = ((TextView) v).getText().toString();
                String address = info.substring(info.length() - 17);
                Intent intent = new Intent();
                intent.putExtra(AtyDeviceList.EXTRA_DEVICE_ADDRESS, address);
                AtyDeviceList.this.setResult(-1, intent);
                AtyDeviceList.this.finish();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class C01943 extends BroadcastReceiver {
        C01943() {
        }

        @SuppressLint({"InlinedApi", "NewApi"})
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("android.bluetooth.device.action.FOUND".equals(action)) {
                BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                if (device.getBondState() != 12) {
                    AtyDeviceList.this.mNewDevicesArrayAdapter.add(device.getName() + "\n" + device.getAddress());
                }
            } else if ("android.bluetooth.adapter.action.DISCOVERY_FINISHED".equals(action)) {
                AtyDeviceList.this.setProgressBarIndeterminateVisibility(false);
                AtyDeviceList.this.setTitle(R.string.select_device);
                if (AtyDeviceList.this.mNewDevicesArrayAdapter.getCount() == 0) {
                    AtyDeviceList.this.mNewDevicesArrayAdapter.add(AtyDeviceList.this.getResources().getText(R.string.none_found).toString());
                }
            }
        }
    }

    @SuppressLint({"NewApi"})
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(5);
        setContentView(R.layout.aty_device);
        setResult(0);
        ((Button) findViewById(R.id.button_scan)).setOnClickListener(new C01921());
        this.mPairedDevicesArrayAdapter = new ArrayAdapter(this, R.layout.item_device);
        this.mNewDevicesArrayAdapter = new ArrayAdapter(this, R.layout.item_device);
        ListView pairedListView = (ListView) findViewById(R.id.paired_devices);
        pairedListView.setAdapter(this.mPairedDevicesArrayAdapter);
        pairedListView.setOnItemClickListener(this.mDeviceClickListener);
        ListView newDevicesListView = (ListView) findViewById(R.id.new_devices);
        newDevicesListView.setAdapter(this.mNewDevicesArrayAdapter);
        newDevicesListView.setOnItemClickListener(this.mDeviceClickListener);
        registerReceiver(this.mReceiver, new IntentFilter("android.bluetooth.device.action.FOUND"));
        registerReceiver(this.mReceiver, new IntentFilter("android.bluetooth.adapter.action.DISCOVERY_FINISHED"));
        this.mBtAdapter = BluetoothAdapter.getDefaultAdapter();
        Set<BluetoothDevice> pairedDevices = this.mBtAdapter.getBondedDevices();
        if (pairedDevices.size() > 0) {
            findViewById(R.id.title_paired_devices).setVisibility(0);
            for (BluetoothDevice device : pairedDevices) {
                this.mPairedDevicesArrayAdapter.add(device.getName() + "\n" + device.getAddress());
            }
            return;
        }
        this.mPairedDevicesArrayAdapter.add(getResources().getText(R.string.none_paired).toString());
    }

    @SuppressLint({"NewApi"})
    protected void onDestroy() {
        super.onDestroy();
        if (this.mBtAdapter != null) {
            this.mBtAdapter.cancelDiscovery();
        }
        unregisterReceiver(this.mReceiver);
    }

    @SuppressLint({"NewApi"})
    private void doDiscovery() {
        Log.d(TAG, "doDiscovery()");
        setProgressBarIndeterminateVisibility(f3D);
        setTitle(R.string.scanning);
        findViewById(R.id.title_new_devices).setVisibility(0);
        if (this.mBtAdapter.isDiscovering()) {
            this.mBtAdapter.cancelDiscovery();
        }
        this.mBtAdapter.startDiscovery();
    }
}
