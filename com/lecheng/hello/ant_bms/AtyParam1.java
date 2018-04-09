package com.lecheng.hello.ant_bms;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.lecheng.hello.ant_bms.utils.AFactory;
import com.lecheng.hello.ant_bms.utils.C0218U;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class AtyParam1 extends Activity {
    private static final String TAG = "BcService";
    public static final int add_current_AH_batt_wu_li = 81;
    public static final int add_current_sheng_yu_AH = 83;
    public static final int add_current_zong_gong_AH = 85;
    public MyAdapter adapter = null;
    public final int[] address_table = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 37, 38, 39, 40, 31, 33, 35, 41, 42, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74};
    public float[] float_ji_xian_zhi = new float[]{4.6f, 4.6f, 4.6f, 4.6f, 4.6f, 4.6f, 150.0f, 150.0f, 600.0f, 100.0f, 600.0f, 100.0f, 4.6f, 4.6f, 4.6f, 280.0f, 3.2f, 60000.0f, 60.0f, 600.0f, 60000.0f, 65535.0f, 5000.0f, 32.0f, 70.0f, 65.0f, 70.0f, 65.0f, 80.0f, 75.0f, 80.0f, 80.0f, 80.0f, 80.0f, 65535.0f, 65535.0f, 65535.0f, 10000.0f, 100.0f, 65535.0f, 65535.0f, 65535.0f, 65535.0f, 65535.0f, 65535.0f, 65535.0f, 65535.0f, 65535.0f, 65535.0f, 65535.0f, 65535.0f, 65535.0f, 65535.0f, 65535.0f, 65535.0f, 65535.0f, 65535.0f, 65535.0f, 65535.0f, 65535.0f, 65535.0f, 65535.0f, 65535.0f};
    Handler handler = new C02073();
    Handler handler1 = new C02084();
    public List<Map<String, Object>> mData;
    public int[] read_data_data_2 = new int[1024];
    public int read_data_i = 1;
    int send_parameter_dat;
    int send_parameter_position;
    public String[] string__name = new String[]{"single-Overvoltage alarm", "single-Undervoltage alarm", "single-Overvoltage protection", "single-Undervoltage protection", "single-Overvoltage recovery", "single-Undervoltage recovery", "Total pressure overvoltage protection", "Total pressure undervoltage protection", "Charging over-current protection", "Charging overcurrent delay", "Discharge over-current protection", "Discharge overcurrent delay", "Balanced limit voltage", "Balanced start voltage", "Equilibrium monomer pressure difference", "Equalization current size", "System reference voltage", "Current sensing range", "Power start current", "Short-circuit protection current", "Short-circuit protection delay", "Automatic standby time", "Total pressure adjustment param", "Actual number of batteries", "Charging high temperature protection", "Charging high temperature recovery", "Discharge high temperature protection", "Discharge high temperature recovery", "Power high temperature protection", "Power high temperature recovery", "Charging low temperature protection", "Charging low temperature recovery", "Discharge protection at low temperature", "Low temperature recovery of discharge", "Battery physical capacity", "Battery residual capacity", "Total circulating capacity", "Tire length per week", "Pulse times per week", "Connection resistance01", "Connection resistance02", "Connection resistance03", "Connection resistance04", "Connection resistance05", "Connection resistance06", "Connection resistance07", "Connection resistance08", "Connection resistance09", "Connection resistance10", "Connection resistance11", "Connection resistance12", "Connection resistance13", "Connection resistance14", "Connection resistance15", "Connection resistance16", "Connection resistance17", "Connection resistance18", "Connection resistance19", "Connection resistance20", "Connection resistance21", "Connection resistance22", "Connection resistance23", "Connection resistance24"};
    public String[] string__name_en = new String[]{"single-Overvoltage alarm", "single-Undervoltage alarm", "single-Overvoltage protection", "single-Undervoltage protection", "single-Overvoltage recovery", "single-Undervoltage recovery", "Total pressure overvoltage protection", "Total pressure undervoltage protection", "Charging over-current protection", "Charging overcurrent delay", "Discharge over-current protection", "Discharge overcurrent delay", "Balanced limit voltage", "Balanced start voltage", "Equilibrium monomer pressure difference", "Equalization current size", "System reference voltage", "Current sensing range", "Power start current", "Short-circuit protection current", "Short-circuit protection delay", "Automatic standby time", "Total pressure adjustment param", "Actual number of batteries", "Charging high temperature protection", "Charging high temperature recovery", "Discharge high temperature protection", "Discharge high temperature recovery", "Power high temperature protection", "Power high temperature recovery", "Charging low temperature protection", "Charging low temperature recovery", "Discharge protection at low temperature", "Low temperature recovery of discharge", "Battery physical capacity", "Battery residual capacity", "Total circulating capacity", "Tire length per week", "Pulse times per week", "Connection resistance01", "Connection resistance02", "Connection resistance03", "Connection resistance04", "Connection resistance05", "Connection resistance06", "Connection resistance07", "Connection resistance08", "Connection resistance09", "Connection resistance10", "Connection resistance11", "Connection resistance12", "Connection resistance13", "Connection resistance14", "Connection resistance15", "Connection resistance16", "Connection resistance17", "Connection resistance18", "Connection resistance19", "Connection resistance20", "Connection resistance21", "Connection resistance22", "Connection resistance23", "Connection resistance24"};
    public String[] string__name_help = new String[]{"When the voltage of the monomer reaches the set point, the buzzer alarms.Set range:X<=4.6V    Unit:V", "When the voltage of the monomer reaches the set point, the buzzer alarms.Set range:X<=4.6V    Unit:V", "When the monomer voltage reaches the set point, the protection plate protects.Set range:X<=4.6V    Unit:V", "When the monomer voltage reaches the set point, the protection plate protects.Set range:X<=4.6V    Unit:V", "The voltage is restored to the voltage after the monomer voltage is over, and the protection plate is resumed.Set range:X<=4.6V    Unit:V", "The voltage of the single body is restored to the voltage after undervoltage, and the guard plate is resumed.Set range:X<=4.6V    Unit:V", "When the total voltage reaches the set point, turn off the charge.Set range:X<=150V\t\tUnit:V", "When the total voltage reaches the set point, the discharge is turned off.Set range:X<=150V  \tUnit:V", "Charging current exceeds setting value to turn off charging.Set range:X<=200A  \tUnit:A", "Charge current exceeds the set value x seconds, then turn off the charge.Set range:X<=100S  \tUnit:S", "The discharge current is over the set value and then the discharge is turned off.Set range:X<=200A  \tUnit:A", "The discharge current is over the set value x seconds and then the discharge is turned off.Set range:X<=100S  \tUnit:S", "In any case, the monomer above this voltage will perform discharge balancing.Set range:X<=4.6V   Unit:V", "The discharge balance is greater when the charge is greater than the voltage and the pressure difference is greater than the set value.Set range:X<=4.6V   Unit:V", "When discharging the charge, the discharge balance is greater than the balanced start voltage and the voltage difference is greater than the single experience of the voltage.Set range:X<=4.6V Unit:V", "The equilibrium current size at equilibrium time, average equilibrium current of about 10-200ma, setting step value 10MA.Set range:X<=200MA   Unit:mA", "Set the system average voltage to calibrate the system voltage.Set range:X<=3.2V   Unit:V", "Set the current range of the current sensor used to calibrate the current.Set range:50-200    Unit:A", "Open the maximum current allowed at the MOS tube.Set range:X<100    Unit:A", "Set up short-circuit protection current of the system.Set range:X<=Current sensor range+5A    Unit:A", "Set short circuit protection, turn off the short delay time, can prevent misoperation.Set range:X<300    Unit:mS", "When the system detects no current of x seconds, it automatically enters the standby to save power. Set range:X<65535  Unit:S", "Used to calibrate the total voltage measured.Set range:X is worth around 3330", "Sets the actual number of battery strings used, and the system automatically blocks the ports that are not in use,Set range:X<=24    Unit:", "The battery temperature is higher than this value. Turn off the charge.Set range:X<70    Unit:'C", "The battery temperature is below this level, recharge the battery.Set range:X<65    Unit:'C", "The battery temperature is higher than this value, turn off the discharge.Set range:X<70    Unit:'C", "The battery temperature is below this value, and the discharge is resumed.Set range:X<65    Unit:'C", "MOS tube temperature is higher than this value, turn off charge / discharge.Set range:X<80    Unit:'C", "The MOS tube temperature is below this value to restore charge / discharge.Set range:X<75    Unit:'C", "The battery temperature is higher than this value. Turn off the charge.Set range:X<70    Unit:'C", "The battery temperature is below this level, recharge the battery.Set range:X<65    Unit:'C", "The battery temperature is higher than this value, turn off the discharge.Set range:X<70    Unit:'C", "The battery temperature is below this value, and the discharge is resumed.Set range:X<65    Unit:'C", "Total capacity of a whole set of batteries.Set range:X<&    Unit:AH", "Current remaining capacity of battery.Set range:X<&    Unit:AH", "The total circulating capacity of the battery is accumulated.Set range:X<&    Unit:AH", "The length of a wheel that is used to calibrate speed measurements.Set range:X<=65535    Unit:mm", "The number of Holzer pulses of a motor that is turned around and used to calibrate speed measurements.Set range:X<=100    Unit:Frequency", "An equivalent resistance of copper is connected between the battery and the battery:X<=65535    Unit:mΩ", "An equivalent resistance of copper is connected between the battery and the battery:X<=65535    Unit:mΩ", "An equivalent resistance of copper is connected between the battery and the battery:X<=65535    Unit:mΩ", "An equivalent resistance of copper is connected between the battery and the battery:X<=65535    Unit:mΩ", "An equivalent resistance of copper is connected between the battery and the battery:X<=65535    Unit:mΩ", "An equivalent resistance of copper is connected between the battery and the battery:X<=65535    Unit:mΩ", "An equivalent resistance of copper is connected between the battery and the battery:X<=65535    Unit:mΩ", "An equivalent resistance of copper is connected between the battery and the battery:X<=65535    Unit:mΩ", "An equivalent resistance of copper is connected between the battery and the battery:X<=65535    Unit:mΩ", "An equivalent resistance of copper is connected between the battery and the battery:X<=65535    Unit:mΩ", "An equivalent resistance of copper is connected between the battery and the battery:X<=65535    Unit:mΩ", "An equivalent resistance of copper is connected between the battery and the battery:X<=65535    Unit:mΩ", "An equivalent resistance of copper is connected between the battery and the battery:X<=65535    Unit:mΩ", "An equivalent resistance of copper is connected between the battery and the battery:X<=65535    Unit:mΩ", "An equivalent resistance of copper is connected between the battery and the battery:X<=65535    Unit:mΩ", "An equivalent resistance of copper is connected between the battery and the battery:X<=65535    Unit:mΩ", "An equivalent resistance of copper is connected between the battery and the battery:X<=65535    Unit:mΩ", "An equivalent resistance of copper is connected between the battery and the battery:X<=65535    Unit:mΩ", "An equivalent resistance of copper is connected between the battery and the battery:X<=65535    Unit:mΩ", "An equivalent resistance of copper is connected between the battery and the battery:X<=65535    Unit:mΩ", "An equivalent resistance of copper is connected between the battery and the battery:X<=65535    Unit:mΩ", "An equivalent resistance of copper is connected between the battery and the battery:X<=65535    Unit:mΩ", "An equivalent resistance of copper is connected between the battery and the battery:X<=65535    Unit:mΩ", "An equivalent resistance of copper is connected between the battery and the battery:X<=65535    Unit:mΩ"};
    public String[] string__name_help_en = new String[]{"When the voltage of the monomer reaches the set point, the buzzer alarms.Set range:X<=4.6V    Unit:V", "When the voltage of the monomer reaches the set point, the buzzer alarms.Set range:X<=4.6V    Unit:V", "When the monomer voltage reaches the set point, the protection plate protects.Set range:X<=4.6V    Unit:V", "When the monomer voltage reaches the set point, the protection plate protects.Set range:X<=4.6V    Unit:V", "The voltage is restored to the voltage after the monomer voltage is over, and the protection plate is resumed.Set range:X<=4.6V    Unit:V", "The voltage of the single body is restored to the voltage after undervoltage, and the guard plate is resumed.Set range:X<=4.6V    Unit:V", "When the total voltage reaches the set point, turn off the charge.Set range:X<=150V\t\tUnit:V", "When the total voltage reaches the set point, the discharge is turned off.Set range:X<=150V  \tUnit:V", "Charging current exceeds setting value to turn off charging.Set range:X<=200A  \tUnit:A", "Charge current exceeds the set value x seconds, then turn off the charge.Set range:X<=100S  \tUnit:S", "The discharge current is over the set value and then the discharge is turned off.Set range:X<=200A  \tUnit:A", "The discharge current is over the set value x seconds and then the discharge is turned off.Set range:X<=100S  \tUnit:S", "In any case, the monomer above this voltage will perform discharge balancing.Set range:X<=4.6V   Unit:V", "The discharge balance is greater when the charge is greater than the voltage and the pressure difference is greater than the set value.Set range:X<=4.6V   Unit:V", "When discharging the charge, the discharge balance is greater than the balanced start voltage and the voltage difference is greater than the single experience of the voltage.Set range:X<=4.6V Unit:V", "The equilibrium current size at equilibrium time, average equilibrium current of about 10-200ma, setting step value 10MA.Set range:X<=200MA   Unit:mA", "Set the system average voltage to calibrate the system voltage.Set range:X<=3.2V   Unit:V", "Set the current range of the current sensor used to calibrate the current.Set range:50-200    Unit:A", "Open the maximum current allowed at the MOS tube.Set range:X<100    Unit:A", "Set up short-circuit protection current of the system.Set range:X<=Current sensor range+5A    Unit:A", "Set short circuit protection, turn off the short delay time, can prevent misoperation.Set range:X<300    Unit:mS", "When the system detects no current of x seconds, it automatically enters the standby to save power. Set range:X<65535  Unit:S", "Used to calibrate the total voltage measured.Set range:X is worth around 3330", "Sets the actual number of battery strings used, and the system automatically blocks the ports that are not in use,Set range:X<=24    Unit:", "The battery temperature is higher than this value. Turn off the charge.Set range:X<70    Unit:'C", "The battery temperature is below this level, recharge the battery.Set range:X<65    Unit:'C", "The battery temperature is higher than this value, turn off the discharge.Set range:X<70    Unit:'C", "The battery temperature is below this value, and the discharge is resumed.Set range:X<65    Unit:'C", "MOS tube temperature is higher than this value, turn off charge / discharge.Set range:X<80    Unit:'C", "The MOS tube temperature is below this value to restore charge / discharge.Set range:X<75    Unit:'C", "The battery temperature is higher than this value. Turn off the charge.Set range:X<70    Unit:'C", "The battery temperature is below this level, recharge the battery.Set range:X<65    Unit:'C", "The battery temperature is higher than this value, turn off the discharge.Set range:X<70    Unit:'C", "The battery temperature is below this value, and the discharge is resumed.Set range:X<65    Unit:'C", "Total capacity of a whole set of batteries.Set range:X<&    Unit:AH", "Current remaining capacity of battery.Set range:X<&    Unit:AH", "The total circulating capacity of the battery is accumulated.Set range:X<&    Unit:AH", "The length of a wheel that is used to calibrate speed measurements.Set range:X<=65535    Unit:mm", "The number of Holzer pulses of a motor that is turned around and used to calibrate speed measurements.Set range:X<=100    Unit:Frequency", "An equivalent resistance of copper is connected between the battery and the battery:X<=65535    Unit:mΩ", "An equivalent resistance of copper is connected between the battery and the battery:X<=65535    Unit:mΩ", "An equivalent resistance of copper is connected between the battery and the battery:X<=65535    Unit:mΩ", "An equivalent resistance of copper is connected between the battery and the battery:X<=65535    Unit:mΩ", "An equivalent resistance of copper is connected between the battery and the battery:X<=65535    Unit:mΩ", "An equivalent resistance of copper is connected between the battery and the battery:X<=65535    Unit:mΩ", "An equivalent resistance of copper is connected between the battery and the battery:X<=65535    Unit:mΩ", "An equivalent resistance of copper is connected between the battery and the battery:X<=65535    Unit:mΩ", "An equivalent resistance of copper is connected between the battery and the battery:X<=65535    Unit:mΩ", "An equivalent resistance of copper is connected between the battery and the battery:X<=65535    Unit:mΩ", "An equivalent resistance of copper is connected between the battery and the battery:X<=65535    Unit:mΩ", "An equivalent resistance of copper is connected between the battery and the battery:X<=65535    Unit:mΩ", "An equivalent resistance of copper is connected between the battery and the battery:X<=65535    Unit:mΩ", "An equivalent resistance of copper is connected between the battery and the battery:X<=65535    Unit:mΩ", "An equivalent resistance of copper is connected between the battery and the battery:X<=65535    Unit:mΩ", "An equivalent resistance of copper is connected between the battery and the battery:X<=65535    Unit:mΩ", "An equivalent resistance of copper is connected between the battery and the battery:X<=65535    Unit:mΩ", "An equivalent resistance of copper is connected between the battery and the battery:X<=65535    Unit:mΩ", "An equivalent resistance of copper is connected between the battery and the battery:X<=65535    Unit:mΩ", "An equivalent resistance of copper is connected between the battery and the battery:X<=65535    Unit:mΩ", "An equivalent resistance of copper is connected between the battery and the battery:X<=65535    Unit:mΩ", "An equivalent resistance of copper is connected between the battery and the battery:X<=65535    Unit:mΩ", "An equivalent resistance of copper is connected between the battery and the battery:X<=65535    Unit:mΩ", "An equivalent resistance of copper is connected between the battery and the battery:X<=65535    Unit:mΩ"};
    public String[] string_dan_wei = new String[]{"V ", "V ", "V ", "V ", "V ", "V ", "V ", "V ", "A ", "S ", "A ", "S ", "V ", "V ", "V ", "MA", "V ", "A ", "A ", "A ", "US", "S ", "N", "S ", "℃", "℃", "℃", "℃ ", "℃", "℃", "℃ ", "℃", "℃ ", "℃ ", "AH ", "AH ", "AH ", "M ", "N ", "MR ", "MR ", "MR ", "MR ", "MR ", "MR ", "MR ", "MR ", "MR ", "MR ", "MR ", "MR ", "MR ", "MR ", "MR ", "MR ", "MR ", "MR ", "MR ", "MR ", "MR ", "MR ", "MR ", "MR "};
    TimerTask task = new C02062();
    TimerTask task1 = null;
    @Bind({2131427443})
    TextView test2;
    Timer timer = new Timer();
    Timer timer1 = null;
    public float[] zhuan_huan_xi_shu = new float[]{1000.0f, 1000.0f, 1000.0f, 1000.0f, 1000.0f, 1000.0f, 10.0f, 10.0f, 10.0f, 1.0f, 10.0f, 1.0f, 1000.0f, 1000.0f, 1000.0f, 0.1f, 1000.0f, 10.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 100.0f, 100.0f, 100.0f, 1.0f, 1.0f, 10.0f, 10.0f, 10.0f, 10.0f, 10.0f, 10.0f, 10.0f, 10.0f, 10.0f, 10.0f, 10.0f, 10.0f, 10.0f, 10.0f, 10.0f, 10.0f, 10.0f, 10.0f, 10.0f, 10.0f, 10.0f, 10.0f, 10.0f, 10.0f};

    class C02051 extends TimerTask {
        C02051() {
        }

        public void run() {
            Message message = new Message();
            message.what = 1;
            AtyParam1.this.handler1.sendMessage(message);
        }
    }

    class C02062 extends TimerTask {
        C02062() {
        }

        public void run() {
            Message message = new Message();
            message.what = 1;
            AtyParam1.this.handler.sendMessage(message);
        }
    }

    class C02073 extends Handler {
        C02073() {
        }

        public void handleMessage(Message msg) {
            int mack = 0;
            switch (msg.what) {
                case 1:
                    int i;
                    for (i = 0; i < 1024; i++) {
                        if (AtyParam1.this.read_data_data_2[i] != ((AtyMain) AFactory.atyMain).read_data_data[i]) {
                            mack = 1;
                            AtyParam1.this.read_data_data_2[i] = ((AtyMain) AFactory.atyMain).read_data_data[i];
                        }
                    }
                    if (mack == 1) {
                        for (i = 0; i < AtyParam1.this.string__name.length; i++) {
                            ((Map) AtyParam1.this.mData.get(i)).put("info", AtyParam1.this.read_data_zhuan_huan_string(i));
                            ((Map) AtyParam1.this.mData.get(i)).put("set_text", AtyParam1.this.read_data_zhuan_huan_string(i));
                        }
                        AtyParam1.this.adapter.notifyDataSetChanged();
                        break;
                    }
                    break;
            }
            super.handleMessage(msg);
        }
    }

    class C02084 extends Handler {
        C02084() {
        }

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    AtyMain atyMain = (AtyMain) AFactory.atyMain;
                    AtyParam1 atyParam1 = AtyParam1.this;
                    int i = atyParam1.read_data_i;
                    atyParam1.read_data_i = i + 1;
                    atyMain.send_6bit(0x5A5A, i, 0);
                    if (AtyParam1.this.read_data_i > 80) {
                        AtyParam1.this.read_data_i = 1;
                        AtyParam1.this.timer1.cancel();
                        AtyParam1.this.timer1 = null;
                        AtyParam1.this.task1 = null;
                        break;
                    }
                    break;
            }
            super.handleMessage(msg);
        }
    }

    class C02095 extends TimerTask {
        C02095() {
        }

        public void run() {
            Message message = new Message();
            message.what = 1;
            AtyParam1.this.handler1.sendMessage(message);
        }
    }

    class C02106 implements OnClickListener {
        C02106() {
        }

        public void onClick(DialogInterface dialog, int which) {
        }
    }

    class C02117 implements OnClickListener {
        C02117() {
        }

        public void onClick(DialogInterface dialog, int which) {
            if (AtyParam1.this.send_parameter_dat >= -40) {
                AtyParam1.this.send_data_zhuan_huan(AtyParam1.this.send_parameter_position, AtyParam1.this.send_parameter_dat);
            } else {
                AtyParam1.this.shu_ru_show_help(AtyParam1.this.send_parameter_position);
            }
        }
    }

    class C02128 implements OnClickListener {
        C02128() {
        }

        public void onClick(DialogInterface dialog, int which) {
        }
    }

    public class MyAdapter extends BaseAdapter {
        private LayoutInflater mInflater;

        public MyAdapter(Context context) {
            this.mInflater = LayoutInflater.from(context);
        }

        public int getCount() {
            return AtyParam1.this.mData.size();
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = this.mInflater.inflate(R.layout.item_cell, null);
                holder.title = (TextView) convertView.findViewById(R.id.title);
                holder.info = (TextView) convertView.findViewById(R.id.info);
                holder.info_mv = (TextView) convertView.findViewById(R.id.info_mv);
                holder.viewBtn = (Button) convertView.findViewById(R.id.view_btn);
                holder.set_text = (EditText) convertView.findViewById(R.id.editText1);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.title.setText((String) ((Map) AtyParam1.this.mData.get(position)).get("title"));
            holder.info.setText((String) ((Map) AtyParam1.this.mData.get(position)).get("info"));
            holder.info_mv.setText((String) ((Map) AtyParam1.this.mData.get(position)).get("info_mv"));
            holder.set_text.setText((String) ((Map) AtyParam1.this.mData.get(position)).get("set_text"));
            holder.viewBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    try {
                        float data_float = Float.parseFloat(holder.set_text.getText().toString());
                        int data = AtyParam1.this.shu_ru_zhuan_huan(position, data_float);
                        Log.e(AtyParam1.TAG, "Button to get the value" + data_float);
                        AtyParam1.this.shu_ru_show_confirm(position, data);
                    } catch (Exception e) {
                    }
                }
            });
            holder.title.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    AtyParam1.this.show_help(position);
                }
            });
            holder.info.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    ((AtyMain) AFactory.atyMain).send_6bit(0x5A5A, AtyParam1.this.address_table[position], 0);
                    ((AtyMain) AFactory.atyMain).send_6bit(0x5A5A, AtyParam1.this.address_table[position] + 1, 0);
                }
            });
            return convertView;
        }
    }

    public final class ViewHolder {
        public TextView info;
        public TextView info_mv;
        public EditText set_text;
        public TextView title;
        public Button viewBtn;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_param1);
        ButterKnife.bind((Activity) this);
        this.mData = getData();
        ListView listView = (ListView) findViewById(R.id.listView);
        this.adapter = new MyAdapter(this);
        listView.setAdapter(this.adapter);
        this.timer.schedule(this.task, 500, 200);
        if (this.timer1 == null && this.task1 == null) {
            this.timer1 = new Timer();
            this.task1 = new C02051();
            this.timer1.schedule(this.task1, 80, 8);
        }
    }

    public void Button1_click(View v) {
        if (this.timer1 == null && this.task1 == null) {
            this.timer1 = new Timer();
            this.task1 = new C02095();
            this.timer1.schedule(this.task1, 80, 8);
        }
    }

    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList();
        Map<String, Object> map = new HashMap();
        for (int i = 0; i < this.string__name.length; i++) {
            map = new HashMap();
            if (C0218U.isZh(this)) {
                map.put("title", this.string__name[i]);
            } else {
                map.put("title", this.string__name_en[i]);
            }
            map.put("info", "0");
            map.put("set_text", "0");
            map.put("info_mv", this.string_dan_wei[i]);
            list.add(map);
        }
        return list;
    }

    @OnClick({2131427446, 2131427447})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvMain:
                startActivity(new Intent(this, AtyMain.class));
                return;
            case R.id.tvParam2:
                startActivity(new Intent(this, AtyParam2.class));
                return;
            default:
                return;
        }
    }

    public void show_help(int position) {
        String string_help_title;
        String string_help;
        if (C0218U.isZh(this)) {
            string_help_title = this.string__name[position];
            string_help = this.string__name_help[position];
        } else {
            string_help_title = this.string__name_en[position];
            string_help = this.string__name_help_en[position];
        }
        new Builder(this).setTitle(string_help_title).setMessage(string_help).setPositiveButton("Yes", new C02106()).show();
    }

    public void shu_ru_show_confirm(int position, int data) {
        this.send_parameter_position = position;
        this.send_parameter_dat = data;
        new Builder(this).setTitle("Are you sure you want to set the parameters??").setMessage(this.string__name_help[position]).setPositiveButton("Yes", new C02117()).show();
    }

    public void shu_ru_show_help(int position) {
        new Builder(this).setTitle("Out of range").setMessage(this.string__name_help[position]).setPositiveButton("Yes", new C02128()).show();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void send_data_zhuan_huan(int r8, int r9) {
        /*
        r7 = this;
        r3 = 65535; // 0xffff float:9.1834E-41 double:3.23786E-319;
        r6 = 255; // 0xff float:3.57E-43 double:1.26E-321;
        r5 = 0;
        r4 = 42405; // 0xa5a5 float:5.9422E-41 double:2.0951E-319;
        switch(r8) {
            case 30: goto L_0x00a6;
            case 31: goto L_0x00be;
            case 32: goto L_0x00d6;
            case 33: goto L_0x00ee;
            case 34: goto L_0x0021;
            case 35: goto L_0x004d;
            case 36: goto L_0x0079;
            default: goto L_0x000c;
        };
    L_0x000c:
        if (r9 < 0) goto L_0x0020;
    L_0x000e:
        r2 = com.lecheng.hello.ant_bms.utils.AFactory.atyMain;
        r2 = (com.lecheng.hello.ant_bms.AtyMain) r2;
        r3 = r7.address_table;
        r3 = r3[r8];
        r2.send_6bit(r4, r3, r9);
        r2 = com.lecheng.hello.ant_bms.utils.AFactory.atyMain;
        r2 = (com.lecheng.hello.ant_bms.AtyMain) r2;
        r2.send_6bit(r4, r6, r5);
    L_0x0020:
        return;
    L_0x0021:
        if (r9 < 0) goto L_0x004d;
    L_0x0023:
        r2 = r9 * 10000;
        r2 = r2 >> 16;
        r0 = r2 & r3;
        r2 = r9 * 10000;
        r1 = r2 & r3;
        r2 = com.lecheng.hello.ant_bms.utils.AFactory.atyMain;
        r2 = (com.lecheng.hello.ant_bms.AtyMain) r2;
        r3 = r7.address_table;
        r3 = r3[r8];
        r2.send_6bit(r4, r3, r1);
        r2 = com.lecheng.hello.ant_bms.utils.AFactory.atyMain;
        r2 = (com.lecheng.hello.ant_bms.AtyMain) r2;
        r3 = r7.address_table;
        r3 = r3[r8];
        r3 = r3 + 1;
        r2.send_6bit(r4, r3, r0);
        r2 = com.lecheng.hello.ant_bms.utils.AFactory.atyMain;
        r2 = (com.lecheng.hello.ant_bms.AtyMain) r2;
        r2.send_6bit(r4, r6, r5);
        goto L_0x0020;
    L_0x004d:
        if (r9 < 0) goto L_0x0079;
    L_0x004f:
        r2 = r9 * 10000;
        r2 = r2 >> 16;
        r0 = r2 & r3;
        r2 = r9 * 10000;
        r1 = r2 & r3;
        r2 = com.lecheng.hello.ant_bms.utils.AFactory.atyMain;
        r2 = (com.lecheng.hello.ant_bms.AtyMain) r2;
        r3 = r7.address_table;
        r3 = r3[r8];
        r2.send_6bit(r4, r3, r1);
        r2 = com.lecheng.hello.ant_bms.utils.AFactory.atyMain;
        r2 = (com.lecheng.hello.ant_bms.AtyMain) r2;
        r3 = r7.address_table;
        r3 = r3[r8];
        r3 = r3 + 1;
        r2.send_6bit(r4, r3, r0);
        r2 = com.lecheng.hello.ant_bms.utils.AFactory.atyMain;
        r2 = (com.lecheng.hello.ant_bms.AtyMain) r2;
        r2.send_6bit(r4, r6, r5);
        goto L_0x0020;
    L_0x0079:
        if (r9 < 0) goto L_0x00a6;
    L_0x007b:
        r2 = r9 * 10000;
        r2 = r2 >> 16;
        r0 = r2 & r3;
        r2 = r9 * 10000;
        r1 = r2 & r3;
        r2 = com.lecheng.hello.ant_bms.utils.AFactory.atyMain;
        r2 = (com.lecheng.hello.ant_bms.AtyMain) r2;
        r3 = r7.address_table;
        r3 = r3[r8];
        r2.send_6bit(r4, r3, r1);
        r2 = com.lecheng.hello.ant_bms.utils.AFactory.atyMain;
        r2 = (com.lecheng.hello.ant_bms.AtyMain) r2;
        r3 = r7.address_table;
        r3 = r3[r8];
        r3 = r3 + 1;
        r2.send_6bit(r4, r3, r0);
        r2 = com.lecheng.hello.ant_bms.utils.AFactory.atyMain;
        r2 = (com.lecheng.hello.ant_bms.AtyMain) r2;
        r2.send_6bit(r4, r6, r5);
        goto L_0x0020;
    L_0x00a6:
        if (r9 >= 0) goto L_0x00aa;
    L_0x00a8:
        r2 = (short) r9;
        r9 = r9 | r2;
    L_0x00aa:
        r2 = com.lecheng.hello.ant_bms.utils.AFactory.atyMain;
        r2 = (com.lecheng.hello.ant_bms.AtyMain) r2;
        r3 = r7.address_table;
        r3 = r3[r8];
        r2.send_6bit(r4, r3, r9);
        r2 = com.lecheng.hello.ant_bms.utils.AFactory.atyMain;
        r2 = (com.lecheng.hello.ant_bms.AtyMain) r2;
        r2.send_6bit(r4, r6, r5);
        goto L_0x0020;
    L_0x00be:
        if (r9 >= 0) goto L_0x00c2;
    L_0x00c0:
        r2 = (short) r9;
        r9 = r9 | r2;
    L_0x00c2:
        r2 = com.lecheng.hello.ant_bms.utils.AFactory.atyMain;
        r2 = (com.lecheng.hello.ant_bms.AtyMain) r2;
        r3 = r7.address_table;
        r3 = r3[r8];
        r2.send_6bit(r4, r3, r9);
        r2 = com.lecheng.hello.ant_bms.utils.AFactory.atyMain;
        r2 = (com.lecheng.hello.ant_bms.AtyMain) r2;
        r2.send_6bit(r4, r6, r5);
        goto L_0x0020;
    L_0x00d6:
        if (r9 >= 0) goto L_0x00da;
    L_0x00d8:
        r2 = (short) r9;
        r9 = r9 | r2;
    L_0x00da:
        r2 = com.lecheng.hello.ant_bms.utils.AFactory.atyMain;
        r2 = (com.lecheng.hello.ant_bms.AtyMain) r2;
        r3 = r7.address_table;
        r3 = r3[r8];
        r2.send_6bit(r4, r3, r9);
        r2 = com.lecheng.hello.ant_bms.utils.AFactory.atyMain;
        r2 = (com.lecheng.hello.ant_bms.AtyMain) r2;
        r2.send_6bit(r4, r6, r5);
        goto L_0x0020;
    L_0x00ee:
        if (r9 >= 0) goto L_0x00f2;
    L_0x00f0:
        r2 = (short) r9;
        r9 = r9 | r2;
    L_0x00f2:
        r2 = com.lecheng.hello.ant_bms.utils.AFactory.atyMain;
        r2 = (com.lecheng.hello.ant_bms.AtyMain) r2;
        r3 = r7.address_table;
        r3 = r3[r8];
        r2.send_6bit(r4, r3, r9);
        r2 = com.lecheng.hello.ant_bms.utils.AFactory.atyMain;
        r2 = (com.lecheng.hello.ant_bms.AtyMain) r2;
        r2.send_6bit(r4, r6, r5);
        goto L_0x0020;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lecheng.hello.ant_bms.AtyParam1.send_data_zhuan_huan(int, int):void");
    }

    public int read_data_zhuan_huan(int position) {
        switch (position) {
            case 30:
                return (short) this.read_data_data_2[this.address_table[position]];
            case 31:
                return (short) this.read_data_data_2[this.address_table[position]];
            case 32:
                return (short) this.read_data_data_2[this.address_table[position]];
            case 33:
                return (short) this.read_data_data_2[this.address_table[position]];
            case 34:
                return ((this.read_data_data_2[this.address_table[position] + 1] << 16) + this.read_data_data_2[this.address_table[position]]) / 10000;
            case 35:
                return ((this.read_data_data_2[this.address_table[position] + 1] << 16) + this.read_data_data_2[this.address_table[position]]) / 10000;
            case 36:
                return ((this.read_data_data_2[this.address_table[position] + 1] << 16) + this.read_data_data_2[this.address_table[position]]) / 10000;
            default:
                return this.read_data_data_2[this.address_table[position]];
        }
    }

    public String read_data_zhuan_huan_string(int position) {
        if (this.zhuan_huan_xi_shu[position] > 1.0f) {
            return "" + (((float) read_data_zhuan_huan(position)) / this.zhuan_huan_xi_shu[position]);
        }
        return "" + ((int) (((float) read_data_zhuan_huan(position)) / this.zhuan_huan_xi_shu[position]));
    }

    public int shu_ru_zhuan_huan(int position, float data) {
        if (data <= this.float_ji_xian_zhi[position]) {
            return (int) (this.zhuan_huan_xi_shu[position] * data);
        }
        return -50;
    }
}
