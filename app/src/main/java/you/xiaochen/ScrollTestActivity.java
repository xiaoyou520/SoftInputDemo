package you.xiaochen;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.ScrollView;

/**
 * Created by you on 2016/4/20.
 */

public class ScrollTestActivity extends AppCompatActivity implements View.OnClickListener {
    /**
     * 返回与关闭键盘按钮
     */
    private View iv_return, iv_menu;

    private Toolbar toolbar;

    private View ll_root;
    /**
     * 若干输入框
     */
    private EditText et_name, et_age, et_xingzuo;

    /**
     * 窗体控件上一次的高度,用于监听键盘弹起
     */
    private int mLastHeight;

    public static void lanuch(Context context) {
        context.startActivity(new Intent(context, ScrollTestActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_test);

        ll_root = findViewById(R.id.ll_root);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        et_name = (EditText) findViewById(R.id.et_name);
        et_age = (EditText) findViewById(R.id.et_age);
        et_xingzuo = (EditText) findViewById(R.id.et_xingzuo);

        initToolbar();

        final View decorView = this.getWindow().getDecorView();
        decorView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        Rect r = new Rect();
                        decorView.getWindowVisibleDisplayFrame(r);
                        if (mLastHeight != r.bottom) {
                            mLastHeight = r.bottom;
                            ViewGroup.LayoutParams params = ll_root.getLayoutParams();
                            params.height = r.bottom - ll_root.getTop();
                            ll_root.setLayoutParams(params);
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_return:
                finish();
                break;
            case R.id.iv_menu:
                Log.i("you", "hide...");
                ConfigUtils.hideSoftInput(iv_menu);
                break;
        }
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        ActionBar bar = getSupportActionBar();
        bar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        View actionView = LayoutInflater.from(this).inflate(R.layout.actionbar_public, null);
        iv_return = actionView.findViewById(R.id.iv_return);
        iv_menu = actionView.findViewById(R.id.iv_menu);
        iv_return.setOnClickListener(this);
        iv_menu.setOnClickListener(this);
        ActionBar.LayoutParams actionBarParams = new ActionBar.LayoutParams(
                ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        bar.setCustomView(actionView, actionBarParams);
        ConfigUtils.setStatusBarColor(this, getResources().getColor(R.color.colorPrimary));
    }


}
