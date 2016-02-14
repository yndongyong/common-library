package org.yndongyong.common.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import org.yndongyong.common.BaseApplication;
import org.yndongyong.common.R;
import org.yndongyong.common.view.HeaderLayout;

/**
 * 带自定义的ActionBar 的Activity
 * Created by Dong on 2015/9/28.
 */
public class WithHeadLayoutActivity  extends Activity{

    protected BaseApplication mApplication;

    protected HeaderLayout mHeaderLayout;

    protected int mScreenWidth;
    protected int mScreenHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        mApplication = BaseApplication.getInstance();
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        mScreenWidth = metric.widthPixels;
        mScreenHeight = metric.heightPixels;
    }


    /**
     * 只有title initTopBarLayoutByTitle
     * @Title: initTopBarLayoutByTitle
     * @throws
     */
    public void initTopBarForOnlyTitle(String titleName) {
        mHeaderLayout = (HeaderLayout)findViewById(R.id.common_actionbar);
        mHeaderLayout.init(HeaderLayout.HeaderStyle.DEFAULT_TITLE);
        mHeaderLayout.setDefaultTitle(titleName);
    }

    /**
     * 初始化标题栏-带左右按钮，左边是返回箭头，右边是按钮
     * @return void
     * @throws
     */
    public void initTopBarForBoth(String titleName, int rightDrawableId,String text,
                                  HeaderLayout.onRightImageButtonClickListener listener) {
        mHeaderLayout = (HeaderLayout) findViewById(R.id.common_actionbar);
        mHeaderLayout.init(HeaderLayout.HeaderStyle.TITLE_DOUBLE_IMAGEBUTTON);
        mHeaderLayout.setTitleAndLeftImageButton(titleName,
                R.drawable.base_action_bar_back_bg_selector,
                new OnLeftButtonClickListener());
        mHeaderLayout.setTitleAndRightButton(titleName, rightDrawableId,text,
                listener);
    }

    /**
     * 初始化ActionBar 默认左边为返回箭头，右边是图片
     * @param titleName
     * @param rightDrawableId
     * @param listener
     */
    public void initTopBarForBoth(String titleName, int rightDrawableId,
                                  HeaderLayout.onRightImageButtonClickListener listener) {
        mHeaderLayout = (HeaderLayout)findViewById(R.id.common_actionbar);
        mHeaderLayout.init(HeaderLayout.HeaderStyle.TITLE_DOUBLE_IMAGEBUTTON);
        mHeaderLayout.setTitleAndLeftImageButton(titleName,
                R.drawable.base_action_bar_back_bg_selector,
                new OnLeftButtonClickListener());
        mHeaderLayout.setTitleAndRightImageButton(titleName, rightDrawableId,
                listener);
    }

    /**
     * 只有左边按钮和Title initTopBarLayout
     *
     * @throws
     */
    public void initTopBarForLeft(String titleName) {
        mHeaderLayout = (HeaderLayout)findViewById(R.id.common_actionbar);
        mHeaderLayout.init(HeaderLayout.HeaderStyle.TITLE_DOUBLE_IMAGEBUTTON);
        mHeaderLayout.setTitleAndLeftImageButton(titleName,
                R.drawable.base_action_bar_back_bg_selector,
                new OnLeftButtonClickListener());
    }



    // 左边按钮的点击事件
    public class OnLeftButtonClickListener implements
            HeaderLayout.onLeftImageButtonClickListener {

        @Override
        public void onClick() {
            finish();
        }
    }

    /**
     * 保持屏幕常亮
     */
    public void keepScreenOn() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

}
