package com.lecheng.hello.ant_bms;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.media.TransportMediator;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.lecheng.hello.ant_bms.utils.AFactory;
import com.lecheng.hello.ant_bms.utils.MySP;
import com.lecheng.hello.ant_bms.utils.MyToast;
import java.util.Timer;
import java.util.TimerTask;

public class AtyMain extends Activity {
    private static final boolean f4D = true;
    public static final String DEVICE_NAME = "item_device";
    public static final int MESSAGE_DEVICE_NAME = 4;
    public static final int MESSAGE_READ = 2;
    public static final int MESSAGE_STATE_CHANGE = 1;
    public static final int MESSAGE_TOAST = 5;
    public static final int MESSAGE_WRITE = 3;
    private static final int REQUEST_CONNECT_DEVICE = 1;
    private static final int REQUEST_ENABLE_BT = 2;
    private static final String TAG = "AtyMain";
    public static final String TOAST = "toast";
    private AlertDialog ad;
    private Handler handlerConn = new C01962();
    private boolean isConn = false;
    private BluetoothAdapter mBluetoothAdapter = null;
    private BcService mChatService = null;
    private String mConnectedDeviceName = null;
    private ArrayAdapter<String> mConversationArrayAdapter;
    private ListView mConversationView;
    private final Handler mHandler = new C02005();
    private EditText mOutEditText;
    private StringBuffer mOutStringBuffer;
    private TextView mTitle;
    @SuppressLint({"NewApi"})
    private OnEditorActionListener mWriteListener = new C01984();
    private ProgressDialog pd;
    public byte ping_mu_kai_guan_mack = (byte) 1;
    public byte ping_mu_qie_huan_mack = (byte) 1;
    public int[] read_data_data = new int[1024];
    private TimerTask task2;
    private Timer timer;

    class C01951 extends TimerTask {
        C01951() {
        }

        public void run() {
            Message message = new Message();
            message.what = 1;
            AtyMain.this.handlerConn.sendMessage(message);
        }
    }

    class C01962 extends Handler {
        C01962() {
        }

        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                AtyMain.this.autoConn();
            }
            super.handleMessage(msg);
        }
    }

    class C01973 implements OnCancelListener {
        C01973() {
        }

        public void onCancel(DialogInterface dialog) {
            AtyMain.this.timer = null;
            AtyMain.this.startActivityForResult(new Intent(AtyMain.this, AtyDeviceList.class), 1);
            if (AtyMain.this.pd != null) {
                AtyMain.this.pd.dismiss();
            }
        }
    }

    class C01984 implements OnEditorActionListener {
        C01984() {
        }

        public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {
            Log.i(AtyMain.TAG, "END onEditorAction");
            return AtyMain.f4D;
        }
    }

    class C02005 extends Handler {

        class C01991 implements OnClickListener {
            C01991() {
            }

            public void onClick(DialogInterface dialog, int which) {
                AtyMain.this.startActivityForResult(new Intent(AtyMain.this, AtyDeviceList.class), 1);
            }
        }

        C02005() {
        }

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Log.i(AtyMain.TAG, "MESSAGE_STATE_CHANGE: " + msg.arg1);
                    switch (msg.arg1) {
                        case 0:
                        case 1:
                            AtyMain.this.mTitle.setText(R.string.title_not_connected);
                            return;
                        case 2:
                            AtyMain.this.mTitle.setText(R.string.title_connecting);
                            return;
                        case 3:
                            AtyMain.this.mTitle.setText(R.string.title_connected_to);
                            AtyMain.this.mTitle.append(AtyMain.this.mConnectedDeviceName);
                            AtyMain.this.mConversationArrayAdapter.clear();
                            return;
                        default:
                            return;
                    }
                case 2:
                    byte[] readBuf = (byte[]) msg.obj;
                    int[] readBuf_cache = new int[6];
                    for (byte i = (byte) 0; i < (byte) 6; i = (byte) (i + 1)) {
                        if (readBuf[i] >= (byte) 0) {
                            readBuf_cache[i] = readBuf[i];
                        } else {
                            readBuf_cache[i] = (readBuf[i] & TransportMediator.KEYCODE_MEDIA_PAUSE) | 128;
                        }
                    }
                    int read_address = readBuf_cache[2];
                    int read_data = (readBuf_cache[3] << 8) + readBuf_cache[4];
                    if (read_address < 1024) {
                        AtyMain.this.read_data_data[read_address] = read_data;
                    }
                    String str = "";
                    for (byte b : readBuf) {
                        str = str + b;
                    }
                    if (readBuf[2] != (byte) 0 && readBuf[0] != (byte) 90) {
                        AtyMain.this.mConversationArrayAdapter.add(AtyMain.this.mConnectedDeviceName + ":地址Address:" + read_address + "   数据Data:" + read_data);
                        return;
                    }
                    return;
                case 3:
                    byte[] writeBuf = (byte[]) msg.obj;
                    int[] writeBuf_cache = new int[6];
                    for (byte j = (byte) 0; j < (byte) 6; j = (byte) (j + 1)) {
                        if (writeBuf[j] >= (byte) 0) {
                            writeBuf_cache[j] = writeBuf[j];
                        } else {
                            writeBuf_cache[j] = (writeBuf[j] & TransportMediator.KEYCODE_MEDIA_PAUSE) | 128;
                        }
                    }
                    int write_address = writeBuf_cache[2];
                    int write_data = (writeBuf_cache[3] << 8) + writeBuf_cache[4];
                    if (writeBuf[2] != (byte) 0 && writeBuf[0] != (byte) 90) {
                        AtyMain.this.mConversationArrayAdapter.add("Me:  地址Address:" + write_address + "   数据Data:" + write_data);
                        return;
                    }
                    return;
                case 4:
                    AtyMain.this.mConnectedDeviceName = msg.getData().getString(AtyMain.DEVICE_NAME);
                    Toast.makeText(AtyMain.this.getApplicationContext(), "Connected to " + AtyMain.this.mConnectedDeviceName, 0).show();
                    return;
                case 5:
                    if (msg.getData().getString(AtyMain.TOAST).equals("Unable to connect device") && AtyMain.this.ad == null) {
                        AtyMain.this.ad = new Builder(AtyMain.this).setTitle("system hint").setMessage("Bluetooth connection can not be established！").setPositiveButton("Manually reconnect", new C01991()).show();
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    class C02016 implements OnClickListener {
        C02016() {
        }

        public void onClick(DialogInterface dialog, int which) {
            // btn2: Factory reset
            AtyMain.this.send_6bit(0xA5A5, 253, 0);
        }
    }

    class C02027 implements OnClickListener {
        C02027() {
        }

        public void onClick(DialogInterface dialog, int which) {
            // btn5: Li-Ion params dlg
            AtyMain.this.send_6bit(0xA5A5, 251, 0);
        }
    }

    class C02049 implements OnClickListener {
        C02049() {
        }

        public void onClick(DialogInterface dialog, int which) {
            // Tai_Li: Li-Ion params dlg
            AtyMain.this.send_6bit(0xA5A5, 240, 0);
        }
    }

    class C02038 implements OnClickListener {
        C02038() {
        }

        public void onClick(DialogInterface dialog, int which) {
            // button_bluetooth: Modify bt address
            AtyMain.this.send_6bit(0xA5A5, 246, 0);
        }
    }

    public interface Activity_send {
        void callBackFunction(int i);
    }

    @SuppressLint({"NewApi"})
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "+++ ON CREATE +++");
        AFactory.atyMain = this;
        setContentView(R.layout.aty_main);
        ButterKnife.bind((Activity) this);
        this.mTitle = (TextView) findViewById(R.id.screen);
        this.mTitle.setText(R.string.app_name);
        this.mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (this.mBluetoothAdapter == null) {
            Toast.makeText(this, "Bluetooth is not available", 1).show();
            finish();
        }
    }

    @SuppressLint({"NewApi", "InlinedApi"})
    public void onStart() {
        super.onStart();
        Log.e(TAG, "++ ON START ++");
        if (!this.mBluetoothAdapter.isEnabled()) {
            startActivityForResult(new Intent("android.bluetooth.adapter.action.REQUEST_ENABLE"), 2);
        } else if (this.mChatService == null) {
            setupChat();
        }
    }

    public synchronized void onResume() {
        super.onResume();
        Log.e(TAG, "+ ON RESUME +");
        if (this.mChatService != null && this.mChatService.getState() == 0) {
            this.mChatService.start();
        }
        this.timer = new Timer();
        this.task2 = new C01951();
        this.timer.schedule(this.task2, 1000, 3000);
    }

    private void autoConn() {
        try {
            if (this.mChatService.getState() == 2 || this.mChatService.getState() == 3) {
                cancelAutoConn();
                return;
            }
            if (this.pd == null) {
                ProgressDialog progressDialog = this.pd;
                this.pd = ProgressDialog.show(this, "Auto Connect", "Attempting to connecting to BMS");
            }
            this.pd.setOnCancelListener(new C01973());
            this.pd.setCancelable(f4D);
            String address = MySP.loadData(this, "adress", "") + "";
            this.mChatService.connect(this.mBluetoothAdapter.getRemoteDevice(address));
            MyToast myToast = new MyToast(this, "Auto Connection-" + address, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cancelAutoConn() {
        if (this.pd != null) {
            this.pd.dismiss();
        }
        if (this.timer != null) {
            this.timer.cancel();
        }
        if (this.task2 != null) {
            this.task2.cancel();
        }
        this.timer = null;
        this.task2 = null;
        this.handlerConn.removeCallbacksAndMessages(null);
    }

    @SuppressLint({"NewApi"})
    private void setupChat() {
        Log.d(TAG, "setupChat()");
        this.mConversationArrayAdapter = new ArrayAdapter(this, R.layout.item_msg);
        this.mConversationView = (ListView) findViewById(R.id.in);
        this.mConversationView.setAdapter(this.mConversationArrayAdapter);
        this.mChatService = new BcService(this, this.mHandler);
        this.mOutStringBuffer = new StringBuffer("");
    }

    public synchronized void onPause() {
        super.onPause();
        Log.e(TAG, "- ON PAUSE -");
        if (this.mChatService != null) {
            cancelAutoConn();
        }
    }

    public void onStop() {
        super.onStop();
        Log.e(TAG, "-- ON STOP --");
    }

    public void onDestroy() {
        super.onDestroy();
        this.isConn = false;
        if (this.mChatService != null) {
            this.mChatService.stop();
        }
        Log.e(TAG, "--- ON DESTROY ---");
        cancelAutoConn();
    }

    @SuppressLint({"NewApi"})
    private void ensureDiscoverable() {
        Log.d(TAG, "ensure discoverable");
        if (this.mBluetoothAdapter.getScanMode() != 23) {
            Intent discoverableIntent = new Intent("android.bluetooth.adapter.action.REQUEST_DISCOVERABLE");
            discoverableIntent.putExtra("android.bluetooth.adapter.extra.DISCOVERABLE_DURATION", 300);
            startActivity(discoverableIntent);
        }
    }

    public void send_6bit(int retardedHeader, int address, int data) {
        if (this.mChatService.getState() == 3) {
            byte[] send = new byte[]{
                    (byte) ((retardedHeader >> 8) & 255), // big endian
                    (byte) (retardedHeader & 255),
                    (byte) (address & 255),
                    (byte) ((data >> 8) & 255),
                    (byte) (data & 255),
                    0 // checksum
            };
            send[5] = (byte)((send[2] + send[3]) + send[4]);
            this.mChatService.write(send);
            this.mOutStringBuffer.setLength(0);
        }
    }

    @SuppressLint({"NewApi"})
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult " + resultCode);
        switch (requestCode) {
            case 1:
                if (resultCode == -1) {
                    String address = data.getExtras().getString(AtyDeviceList.EXTRA_DEVICE_ADDRESS);
                    BluetoothDevice device = this.mBluetoothAdapter.getRemoteDevice(address);
                    MyToast myToast = new MyToast(this, "Connect-" + address, 1);
                    MySP.saveData(this, "adress", address);
                    this.mChatService.connect(device);
                    this.isConn = f4D;
                    return;
                }
                return;
            case 2:
                if (resultCode != -1) {
                    Log.d(TAG, "BT not enabled");
                    Toast.makeText(this, R.string.bt_not_enabled_leaving, 0).show();
                    finish();
                    break;
                }
                setupChat();
                break;
        }
        this.isConn = false;
    }

    @OnClick({2131427428, 2131427426, 2131427421, 2131427420, 2131427422, 2131427435, 2131427436, 2131427437, 2131427438, 2131427439, 2131427440, 2131427441, 2131427442, 2131427423, 2131427432, 2131427433, 2131427434, 2131427429, 2131427430, 2131427431})
    public void onClick(View view) {
        MyToast myToast;
        String message_address;
        int password1;
        int password3;
        int password5;
        int password7;
        int password_1;
        int password_2;
        int password_3;
        int password_4;
        switch (view.getId()) {
            case R.id.btnDisc:
                ensureDiscoverable();
                return;
            case R.id.btnConn:
                startActivityForResult(new Intent(this, AtyDeviceList.class), 1);
                return;
            case R.id.sys_log:
                startActivity(new Intent(this, AtySysLog.class));
                return;
            case R.id.yan_zheng_password: // verification
                try {
                    message_address = ((TextView) findViewById(R.id.edit_yan_zheng_password)).getText().toString();
                    if (message_address.length() == 8) {
                        password1 = message_address.charAt(1) & 255;
                        password3 = message_address.charAt(3) & 255;
                        password5 = message_address.charAt(5) & 255;
                        password7 = message_address.charAt(7) & 255;
                        password_1 = ((message_address.charAt(0) & 255) * 256) + password1;
                        password_2 = ((message_address.charAt(2) & 255) * 256) + password3;
                        password_3 = ((message_address.charAt(4) & 255) * 256) + password5;
                        password_4 = ((message_address.charAt(6) & 255) * 256) + password7;
                        send_6bit(0xA5A5, 241, password_1);
                        send_6bit(0xA5A5, 242, password_2);
                        send_6bit(0xA5A5, 243, password_3);
                        send_6bit(0xA5A5, 244, password_4);
                        return;
                    }
                    return;
                } catch (Exception e) {
                    myToast = new MyToast(this, getResources().getString(R.string.send_fail), 1);
                    return;
                }
            case R.id.set_password:
                try {
                    message_address = ((TextView) findViewById(R.id.edit_set_password)).getText().toString();
                    if (message_address.length() == 8) {
                        password1 = message_address.charAt(1) & 255;
                        password3 = message_address.charAt(3) & 255;
                        password5 = message_address.charAt(5) & 255;
                        password7 = message_address.charAt(7) & 255;
                        password_2 = ((message_address.charAt(2) & 255) * 256) + password3;
                        password_3 = ((message_address.charAt(4) & 255) * 256) + password5;
                        password_4 = ((message_address.charAt(6) & 255) * 256) + password7;
                        send_6bit(0xA5A5, 102, ((message_address.charAt(0) & 255) * 256) + password1);
                        send_6bit(0xA5A5, 103, password_2);
                        send_6bit(0xA5A5, 104, password_3);
                        send_6bit(0xA5A5, 105, password_4);
                        return;
                    }
                    return;
                } catch (Exception e2) {
                    myToast = new MyToast(this, getResources().getString(R.string.send_fail), 1);
                    return;
                }
            case R.id.Tai_li: // Li-Ion params dlg
                new Builder(this).setTitle("Prompt:\nPrompt:").setMessage("After determination, the parameter will turn into lithium iron phosphate").setPositiveButton("Yes", new C02049()).show();
                return;
            case R.id.No_ChMOS: // enable charge mosfets
                send_6bit(0xA5A5, 250, 1);
                return;
            case R.id.No_DisMOS: // enable dischrage mosfets
                send_6bit(0xA5A5, 249, 1);
                return;
            case R.id.button_bluetooth: // Modify bt address
                new Builder(this).setTitle("Prompt:\nPrompt:").setMessage("After determination, the parameter will turn into lithium iron phosphate").setPositiveButton("Yes", new C02038()).show();
                return;
            case R.id.OFF_ChMOS: // disable charge mosfets
                send_6bit(0xA5A5, 250, 0);
                return;
            case R.id.OFF_DisMOS: // disable discharge mosfets
                send_6bit(0xA5A5, 249, 0);
                return;
            case R.id.btn1: // Reboot system
                send_6bit(0xA5A5, 254, 0);
                return;
            case R.id.btn2: // Factory reset
                new Builder(this).setTitle("Reset factory defaults\nRestore factory settings?").setMessage("After reset, all parameters will be set to default").setPositiveButton("确定 Yes", new C02016()).show();
                return;
            case R.id.btn3: // Screen switch
                if (this.ping_mu_qie_huan_mack > (byte) 6) {
                    this.ping_mu_qie_huan_mack = (byte) 1;
                }
                send_6bit(0xDBDB, 1, 0);
                return;
            case R.id.btn4: // Auto balance
                send_6bit(0xA5A5, 252, 0);
                return;
            case R.id.btn5: // Li-Ion params dlg
                new Builder(this).setTitle("Prompt:\nPrompt:").setMessage("After determination, the parameter will turn into lithium iron phosphate").setPositiveButton("Yes", new C02027()).show();
                return;
            case R.id.btn6: // Zero current
                send_6bit(0xA5A5, 248, 0);
                return;
            case R.id.param1: // Settings dlg
                startActivity(new Intent(this, AtyParam1.class));
                return;
            case R.id.param2: // Live data dlg
                startActivity(new Intent(this, AtyParam2.class));
                Log.d(TAG, "Parameter 2 interface is activated");
                return;
            default:
                return;
        }
    }

    public void onBackPressed() {
        new Builder(this).setMessage("Quit?").setPositiveButton("Yes", new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                AtyMain.this.finish();
            }
        }).show();
    }
}
