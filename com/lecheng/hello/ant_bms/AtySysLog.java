package com.lecheng.hello.ant_bms;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.lecheng.hello.ant_bms.utils.AFactory;
import com.lecheng.hello.ant_bms.utils.C0218U;
import com.lecheng.hello.ant_bms.utils.MyAdpt2;
import com.lecheng.hello.ant_bms.utils.MyToast;
import de.greenrobot.event.EventBus;

public class AtySysLog extends AppCompatActivity {
    private static final String TAG = "AtyParam2";
    Boolean AA = Boolean.valueOf(true);
    private MyAdpt2 adpt;
    TextView ch_mos_Test;
    int ch_mos_state_mack;
    private String[] ch_mos_value = new String[]{"Shut down", "Open", "Monomer overvoltage", "Overcurrent protection", "The battery is full", "Total pressure over pressure", "Battery over-temperature", "Power over-temperature", "Current is abnormal", "Balance line dropped cell", "Motherboard over-temperature", "Unknown", "Unknown", "Charge tube is abnormal", "Wait", "Close manually", "Two overvoltage", "Low temperature protection", "", "", "", "", "", "", ""};
    private int[] control_data = new int[]{0, 0, 0, 0};
    TextView dis_mos_Test;
    int dis_mos_state_mack;
    private String[] dis_mos_value = new String[]{"Shut down", "Open", "Undervoltage monomer", "Overcurrent protection", "Unknown", "Total pressure undervoltage", "Battery over-temperature", "Power over-temperature", "Current is abnormal", "Balance line dropped string", "Motherboard over-temperature", "Charged on", "Short circuit protection", "Discharge tube is abnormal", "Abnormal start", "Close manually", "Two undervoltage", "Low temperature protection", "", "", "", "", "", "", ""};
    Handler handler = new C02171();
    TextView j_h_Test;
    private String[] j_h_value = new String[]{"Shut down", "Equilibrium limit", "Pressure balance", "Balance over-temperature", "Automatic balance", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};
    int jun_heng_state_mack;
    @Bind({2131427455})
    LinearLayout llTab;
    @Bind({2131427453})
    GridView lv;
    private int f17p = 0;
    private short[] read_data_cache140 = new short[140];
    TextView run_temp;
    private String[] strMv = new String[]{"V", "A", "AH", "W", "V", "V", "V", "V", " ℃ ", " ℃ ", " ℃ ", " ℃ ", " ℃ ", " ℃ ", "V", "V", "V", "V", "V", "V", "V", "V", "V", "V", "V", "V", "V", "V", "V", "V", "V", "V", "V", "V", "V", "V", "V", "V", "V", "V", "V", "V", "V", "V", "V", "V", "V", "A", "V", "V", "V", "V", "V", "V", "V", "V", "V", "V", "V", "V", "V", "V", "V", "V", "V", "V", "V", "V", "V", "V", "V", "V", "V", "V", "V", "V", "V", "V", "V", "V"};
    private String strResult = "";
    private String[] strResult2 = new String[]{"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};
    private String[] strTitle = new String[]{"【1】", "【2】", "【3】", "【4】", "【5】", "【6】", "【7】", "【8】", "【9】", "【10】", "【11】", "【12】", "【13】", "【14】", "【15】", "【16】", "【17】", "【18】", "【19】", "【20】", "【21】", "【22】", "【23】", "【24】", "【25】", "【26】", "【27】", "【28】", "【29】", "【30】", "【31】", "【32】"};
    private String[] strTitle_en = new String[]{"Voltage 1", "Voltage 2", "Voltage 3", "Voltage 4", "Voltage 5", "Voltage 6", "Voltage 7", "Voltage 8", "Voltage 9", "Voltage 10", "Voltage 11", "Voltage 12", "Voltage 13", "Voltage 14", "Voltage 15", "Voltage 16", "Voltage 17", "Voltage 18", "Voltage 19", "Voltage 20", "Voltage 21", "Voltage 22", "Voltage 23", "Voltage 24", "Voltage 25", "Voltage 26", "Voltage 27", "Voltage 28", "Voltage 29", "Voltage 30", "Voltage 31", "Voltage 32", "Voltage 33", "Voltage 34", "Voltage 35", "Voltage 36", "Voltage 37", "Voltage 38", "Voltage 39", "Voltage 40", "Voltage 41", "Voltage 42", "Voltage 43", "Voltage 44", "Voltage 45", "Voltage 46", "Voltage 47", "Voltage 48", "Voltage 49", "Voltage 50", "Voltage 51", "Voltage 52", "Voltage 53", "Voltage 54", "Voltage 55", "Voltage 56", "Voltage 57", "Voltage 58", "Voltage 59", "Voltage 60", "Voltage 61", "Voltage 62", "Voltage 63", "Voltage 64", "Voltage 65", "Voltage 66", "Voltage 67", "Electric current1", "Electric current", "Electric current", "Percentage of remaining electricity", "Battery physical capacity 1", "Battery physical capacity 2", "Battery physical capacity 3", "Battery physical capacity 4", "Battery residual capacity", "Battery residual capacity", "Battery residual capacity", "Battery residual capacity", "Total battery cycle capacity", "Total battery cycle capacity", "Total battery cycle capacity", "Total battery cycle capacity", "Accumulate from boot", "Accumulate from boot", "Accumulate from boot", "Accumulate from boot", "Actual temperature", "Actual temperature", "Actual temperature", "Actual temperature", "Actual temperature", "Actual temperature", "Actual temperature", "Actual temperature", "Actual temperature", "Actual temperature", "Actual temperature", "Actual temperature", "Charging MOS pipe status flag", "Discharge MOS tube status flag", "Status flag", "Tire length", "Tire length", "Pulse times per week", "Pulse times per week", "Relay switch", "Current power", "Current power", "Current power", "Current power", "Maximum number of individual strings", "Highest monomer", "Highest monomer", "Minimum monomer string", "Minimum monomer", "Minimum monomer", "average value", "average value", "Effective battery quantity", "The voltage between the D-S poles of the discharge tube is detected", "The voltage between the D-S poles of the discharge tube is detected", "Discharge MOS tube drive voltage", "Discharge MOS tube drive voltage", "Charging MOS tube drive voltage", "Charging MOS tube drive voltage", "The initial value of the comparator is detected when the current is 0", "The initial value of the comparator is detected when the current is 0", "The control equilibrium corresponds to 1 equilibria", "The control equilibrium corresponds to 1 equilibria", "The control equilibrium corresponds to 1 equilibria", "The control equilibrium corresponds to 1 equilibria", "system log", "system log", "Checksum 1", "Checksum 2"};
    private int[] sys_log = new int[32];
    int timer_s_32_zhi;
    @Bind({2131427452})
    TextView tvTest;

    class C02171 extends Handler {
        C02171() {
        }

        public void handleMessage(Message msg) {
            if (AtySysLog.this.f17p < 3) {
                AtySysLog.this.lv.setAdapter(AtySysLog.this.adpt);
            } else {
                AtySysLog.this.adpt.notifyDataSetChanged();
                if (AtySysLog.this.ch_mos_state_mack < 20) {
                    AtySysLog.this.ch_mos_Test.setText(AtySysLog.this.ch_mos_value[AtySysLog.this.ch_mos_state_mack]);
                    if (AtySysLog.this.ch_mos_state_mack == 1) {
                        AtySysLog.this.ch_mos_Test.setTextColor(-16720128);
                    } else {
                        AtySysLog.this.ch_mos_Test.setTextColor(-2162688);
                    }
                }
                if (AtySysLog.this.dis_mos_state_mack < 20) {
                    AtySysLog.this.dis_mos_Test.setText(AtySysLog.this.dis_mos_value[AtySysLog.this.dis_mos_state_mack]);
                    if (AtySysLog.this.dis_mos_state_mack == 1) {
                        AtySysLog.this.dis_mos_Test.setTextColor(-16720128);
                    } else {
                        AtySysLog.this.dis_mos_Test.setTextColor(-2162688);
                    }
                }
                if (AtySysLog.this.jun_heng_state_mack < 20) {
                    AtySysLog.this.j_h_Test.setText(AtySysLog.this.j_h_value[AtySysLog.this.jun_heng_state_mack]);
                }
                AtySysLog.this.run_temp.setText("Operation hours:" + (((AtySysLog.this.timer_s_32_zhi / 60) / 60) / 24) + "D " + (((AtySysLog.this.timer_s_32_zhi / 60) / 60) % 24) + "H " + ((AtySysLog.this.timer_s_32_zhi / 60) % 60) + "M " + (AtySysLog.this.timer_s_32_zhi % 60) + "S");
            }
            ((AtyMain) AFactory.atyMain).send_6bit(0xDBDB, 0, 0);
        }
    }

    public class MyThread implements Runnable {
        public void run() {
            while (AtySysLog.this.AA.booleanValue()) {
                try {
                    AtySysLog.this.f17p = AtySysLog.this.f17p + 1;
                    Thread.sleep(200);
                    Message msg = new Message();
                    msg.what = 1;
                    AtySysLog.this.handler.sendMessage(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.aty_syslog);
        ButterKnife.bind((Activity) this);
        EventBus.getDefault().register(this);
        this.adpt = new MyAdpt2(this, C0218U.isZh(this) ? this.strTitle : this.strTitle_en, this.strResult2, this.strMv, this.control_data);
        new Thread(new MyThread()).start();
        this.llTab.setVisibility(8);
        this.ch_mos_Test = (TextView) findViewById(R.id.ch_mos);
        this.dis_mos_Test = (TextView) findViewById(R.id.dis_mos);
        this.j_h_Test = (TextView) findViewById(R.id.j_h);
        this.run_temp = (TextView) findViewById(R.id.run_temp);
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        this.AA = Boolean.valueOf(false);
    }

    public void onEvent(short[] s) {
        try {
            this.read_data_cache140 = s;
            Log.d(TAG, "Received 140 bytes of data---" + this.f17p);
            int temp = (this.read_data_cache140[136] * 256) + this.read_data_cache140[137];
            int shun_xu = (temp >> 10) & 31;
            int zhuang_tai = temp & 31;
            int bian_hao = (temp >> 5) & 31;
            if (zhuang_tai < 20 && shun_xu < 32 && temp != 0) {
                if ((temp >> 15) == 1) {
                    this.strResult2[shun_xu] = "[" + shun_xu + "]《Discharge》(" + bian_hao + ")" + this.dis_mos_value[zhuang_tai];
                } else {
                    this.strResult2[shun_xu] = "[" + shun_xu + "]《Charge》(" + bian_hao + ")" + this.ch_mos_value[zhuang_tai];
                }
            }
            this.ch_mos_state_mack = this.read_data_cache140[103];
            this.dis_mos_state_mack = this.read_data_cache140[104];
            this.jun_heng_state_mack = this.read_data_cache140[105];
            this.timer_s_32_zhi = (((((this.read_data_cache140[87] << 8) | this.read_data_cache140[88]) << 8) | this.read_data_cache140[89]) << 8) | this.read_data_cache140[90];
        } catch (Exception e) {
            MyToast myToast = new MyToast(this, "Did not receive the message, please try again\n" + e, 1);
        }
    }

    @OnClick({2131427435, 2131427454, 2131427446, 2131427456})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvMain:
                startActivity(new Intent(this, AtyMain.class));
                return;
            case R.id.btnSync:
                new Thread(new MyThread()).start();
                MyToast myToast = new MyToast(this, "anle ", 1);
                return;
            case R.id.tvParam1:
                startActivity(new Intent(this, AtyParam1.class));
                return;
            default:
                return;
        }
    }
}
