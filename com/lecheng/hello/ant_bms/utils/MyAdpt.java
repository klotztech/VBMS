package com.lecheng.hello.ant_bms.utils;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.lecheng.hello.ant_bms.R;

public class MyAdpt extends BaseAdapter {
    private Context f8c;
    private int[] control_data;
    private String[] strMv;
    private String[] strTitle;
    private String[] strValue;

    class ViewHolder {
        TextView tv1;
        TextView tv2;
        TextView tv3;

        ViewHolder() {
        }
    }

    public MyAdpt(Context c, String[] strTitle, String[] strValue, String[] strMv, int[] control_data) {
        this.f8c = c;
        this.strTitle = strTitle;
        this.strValue = strValue;
        this.strMv = strMv;
        this.control_data = control_data;
    }

    public int getCount() {
        return this.control_data[1] + 14;
    }

    public Object getItem(int position) {
        return this.strValue[position];
    }

    public long getItemId(int position) {
        return (long) position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        return myAdapterTypeSelector(null, position, convertView, parent);
    }

    private View myAdapterTypeSelector(ViewHolder vh, int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(this.f8c, R.layout.item_cell3, null);
            vh = new ViewHolder();
            vh.tv1 = (TextView) convertView.findViewById(R.id.title);
            vh.tv2 = (TextView) convertView.findViewById(R.id.info);
            vh.tv3 = (TextView) convertView.findViewById(R.id.info_mv);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        if (position == 0 || position == 1) {
            vh.tv1.setTextSize(20.0f);
            vh.tv2.setTextSize(20.0f);
        } else {
            vh.tv1.setTextSize(16.0f);
            vh.tv2.setTextSize(16.0f);
            vh.tv3.setTextSize(16.0f);
        }
        if (position > 13) {
            if (((this.control_data[0] >> (position - 14)) & 1) != 0) {
                vh.tv1.setTextColor(-16720128);
            } else {
                vh.tv1.setTextColor(ViewCompat.MEASURED_STATE_MASK);
            }
        }
        if (position > 13) {
            if (position - 13 != this.control_data[2] && position - 13 != this.control_data[3]) {
                vh.tv2.setTextColor(ViewCompat.MEASURED_STATE_MASK);
            } else if (position - 13 == this.control_data[2]) {
                vh.tv2.setTextColor(-2162688);
            } else {
                vh.tv2.setTextColor(-16776993);
            }
        }
        vh.tv1.setText(this.strTitle[position]);
        vh.tv2.setText(this.strValue[position]);
        vh.tv3.setText(this.strMv[position]);
        return convertView;
    }
}
