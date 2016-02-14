package org.yndongyong.common;

import android.app.Activity;
import android.app.Application;
import org.yndongyong.common.utils.L;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dong on 2015/9/26.
 */
public class BaseApplication extends Application {

    private static BaseApplication mInstance; //全局实例

    //全局管理activity
    private List<Activity> activityList = new ArrayList<Activity>();

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
//        KLog.init(BuildConfig.LOG_DEBUG);//klog
        L.isPrintDebug=true; //控制 全局的日志打印。
        L.isPrintError=true;
    }

    public static BaseApplication getInstance() {
        return mInstance;
    }

    public void addActivity(Activity activity) {
        activityList.add(activity);

    }
    public void removeActivity(Activity activity) {
        activityList.add(activity);

    }

}
