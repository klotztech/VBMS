package com.lecheng.hello.ant_bms;

import android.view.View;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import butterknife.internal.DebouncingOnClickListener;

public class AtyParam1$$ViewBinder<T extends AtyParam1> implements ViewBinder<T> {
    public void bind(Finder finder, final T target, Object source) {
        target.test2 = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.test2, "field 'test2'"), R.id.test2, "field 'test2'");
        ((View) finder.findRequiredView(source, R.id.tvMain, "method 'onClick'")).setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onClick(p0);
            }
        });
        ((View) finder.findRequiredView(source, R.id.tvParam2, "method 'onClick'")).setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onClick(p0);
            }
        });
    }

    public void unbind(T target) {
        target.test2 = null;
    }
}
