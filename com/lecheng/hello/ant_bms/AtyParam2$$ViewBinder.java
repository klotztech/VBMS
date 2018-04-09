package com.lecheng.hello.ant_bms;

import android.view.View;
import android.widget.GridView;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import butterknife.internal.DebouncingOnClickListener;

public class AtyParam2$$ViewBinder<T extends AtyParam2> implements ViewBinder<T> {
    public void bind(Finder finder, final T target, Object source) {
        target.tvTest = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.tvTest, "field 'tvTest'"), R.id.tvTest, "field 'tvTest'");
        target.lv = (GridView) finder.castView((View) finder.findRequiredView(source, R.id.lv, "field 'lv'"), R.id.lv, "field 'lv'");
        ((View) finder.findRequiredView(source, R.id.btn1, "method 'onClick'")).setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onClick(p0);
            }
        });
        ((View) finder.findRequiredView(source, R.id.btnSync, "method 'onClick'")).setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onClick(p0);
            }
        });
        ((View) finder.findRequiredView(source, R.id.tvMain, "method 'onClick'")).setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onClick(p0);
            }
        });
        ((View) finder.findRequiredView(source, R.id.tvParam1, "method 'onClick'")).setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onClick(p0);
            }
        });
    }

    public void unbind(T target) {
        target.tvTest = null;
        target.lv = null;
    }
}
