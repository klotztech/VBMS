package butterknife;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.util.Property;
import android.view.View;
import butterknife.internal.ButterKnifeProcessor;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class ButterKnife {
    static final Map<Class<?>, ViewBinder<Object>> BINDERS = new LinkedHashMap();
    static final ViewBinder<Object> NOP_VIEW_BINDER = new C02821();
    private static final String TAG = "ButterKnife";
    private static boolean debug = false;

    public interface Action<T extends View> {
        void apply(T t, int i);
    }

    public enum Finder {
        VIEW {
            protected View findView(Object source, int id) {
                return ((View) source).findViewById(id);
            }

            public Context getContext(Object source) {
                return ((View) source).getContext();
            }
        },
        ACTIVITY {
            protected View findView(Object source, int id) {
                return ((Activity) source).findViewById(id);
            }

            public Context getContext(Object source) {
                return (Activity) source;
            }
        },
        DIALOG {
            protected View findView(Object source, int id) {
                return ((Dialog) source).findViewById(id);
            }

            public Context getContext(Object source) {
                return ((Dialog) source).getContext();
            }
        };

        protected abstract View findView(Object obj, int i);

        public abstract Context getContext(Object obj);

        private static <T> T[] filterNull(T[] views) {
            int end = 0;
            for (T view : views) {
                if (view != null) {
                    int end2 = end + 1;
                    views[end] = view;
                    end = end2;
                }
            }
            return Arrays.copyOfRange(views, 0, end);
        }

        public static <T> T[] arrayOf(T... views) {
            return filterNull(views);
        }

        public static <T> List<T> listOf(T... views) {
            return new ImmutableList(filterNull(views));
        }

        public <T> T findRequiredView(Object source, int id, String who) {
            T view = findOptionalView(source, id, who);
            if (view != null) {
                return view;
            }
            throw new IllegalStateException("Required view '" + getContext(source).getResources().getResourceEntryName(id) + "' with ID " + id + " for " + who + " was not found. If this view is optional add '@Nullable' annotation.");
        }

        public <T> T findOptionalView(Object source, int id, String who) {
            return castView(findView(source, id), id, who);
        }

        public <T> T castView(View view, int id, String who) {
            return view;
        }

        public <T> T castParam(Object value, String from, int fromPosition, String to, int toPosition) {
            return value;
        }
    }

    public interface Setter<T extends View, V> {
        void set(T t, V v, int i);
    }

    public interface ViewBinder<T> {
        void bind(Finder finder, T t, Object obj);

        void unbind(T t);
    }

    static class C02821 implements ViewBinder<Object> {
        C02821() {
        }

        public void bind(Finder finder, Object target, Object source) {
        }

        public void unbind(Object target) {
        }
    }

    private ButterKnife() {
        throw new AssertionError("No instances.");
    }

    public static void setDebug(boolean debug) {
        debug = debug;
    }

    public static void bind(Activity target) {
        bind(target, target, Finder.ACTIVITY);
    }

    public static void bind(View target) {
        bind(target, target, Finder.VIEW);
    }

    public static void bind(Dialog target) {
        bind(target, target, Finder.DIALOG);
    }

    public static void bind(Object target, Activity source) {
        bind(target, source, Finder.ACTIVITY);
    }

    public static void bind(Object target, View source) {
        bind(target, source, Finder.VIEW);
    }

    public static void bind(Object target, Dialog source) {
        bind(target, source, Finder.DIALOG);
    }

    public static void unbind(Object target) {
        Class<?> targetClass = target.getClass();
        try {
            if (debug) {
                Log.d(TAG, "Looking up view binder for " + targetClass.getName());
            }
            ViewBinder<Object> viewBinder = findViewBinderForClass(targetClass);
            if (viewBinder != null) {
                viewBinder.unbind(target);
            }
        } catch (Exception e) {
            throw new RuntimeException("Unable to unbind views for " + targetClass.getName(), e);
        }
    }

    static void bind(Object target, Object source, Finder finder) {
        Class<?> targetClass = target.getClass();
        try {
            if (debug) {
                Log.d(TAG, "Looking up view binder for " + targetClass.getName());
            }
            ViewBinder<Object> viewBinder = findViewBinderForClass(targetClass);
            if (viewBinder != null) {
                viewBinder.bind(finder, target, source);
            }
        } catch (Exception e) {
            throw new RuntimeException("Unable to bind views for " + targetClass.getName(), e);
        }
    }

    private static ViewBinder<Object> findViewBinderForClass(Class<?> cls) throws IllegalAccessException, InstantiationException {
        ViewBinder<Object> viewBinder = (ViewBinder) BINDERS.get(cls);
        if (viewBinder != null) {
            if (debug) {
                Log.d(TAG, "HIT: Cached in view binder map.");
            }
            return viewBinder;
        }
        String clsName = cls.getName();
        if (clsName.startsWith(ButterKnifeProcessor.ANDROID_PREFIX) || clsName.startsWith(ButterKnifeProcessor.JAVA_PREFIX)) {
            if (debug) {
                Log.d(TAG, "MISS: Reached framework class. Abandoning search.");
            }
            return NOP_VIEW_BINDER;
        }
        try {
            viewBinder = (ViewBinder) Class.forName(clsName + ButterKnifeProcessor.SUFFIX).newInstance();
            if (debug) {
                Log.d(TAG, "HIT: Loaded view binder class.");
            }
        } catch (ClassNotFoundException e) {
            if (debug) {
                Log.d(TAG, "Not found. Trying superclass " + cls.getSuperclass().getName());
            }
            viewBinder = findViewBinderForClass(cls.getSuperclass());
        }
        BINDERS.put(cls, viewBinder);
        return viewBinder;
    }

    public static <T extends View> void apply(List<T> list, Action<? super T> action) {
        int count = list.size();
        for (int i = 0; i < count; i++) {
            action.apply((View) list.get(i), i);
        }
    }

    public static <T extends View, V> void apply(List<T> list, Setter<? super T, V> setter, V value) {
        int count = list.size();
        for (int i = 0; i < count; i++) {
            setter.set((View) list.get(i), value, i);
        }
    }

    @TargetApi(14)
    public static <T extends View, V> void apply(List<T> list, Property<? super T, V> setter, V value) {
        int count = list.size();
        for (int i = 0; i < count; i++) {
            setter.set(list.get(i), value);
        }
    }

    public static <T extends View> T findById(View view, int id) {
        return view.findViewById(id);
    }

    public static <T extends View> T findById(Activity activity, int id) {
        return activity.findViewById(id);
    }

    public static <T extends View> T findById(Dialog dialog, int id) {
        return dialog.findViewById(id);
    }
}
