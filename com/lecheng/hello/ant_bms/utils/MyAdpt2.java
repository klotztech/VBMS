package com.lecheng.hello.ant_bms.utils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.lecheng.hello.ant_bms.R;

public class MyAdpt2 extends BaseAdapter {
    private Context f7c;
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

    public MyAdpt2(Context c, String[] strTitle, String[] strValue, String[] strMv, int[] control_data) {
        this.f7c = c;
        this.strTitle = strTitle;
        this.strValue = strValue;
        this.strMv = strMv;
        this.control_data = control_data;
    }

    public int getCount() {
        return 32;
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
            convertView = View.inflate(this.f7c, R.layout.item_cell_sys_log, null);
            vh = new ViewHolder();
            vh.tv2 = (TextView) convertView.findViewById(R.id.info);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.tv2.setText(this.strValue[position]);
        return convertView;
    }
}
