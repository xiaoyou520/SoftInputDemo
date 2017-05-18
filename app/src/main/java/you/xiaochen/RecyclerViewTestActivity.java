package you.xiaochen;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;

/**
 * Created by you on 2016/4/20.
 */

public class RecyclerViewTestActivity extends AppCompatActivity implements View.OnClickListener {
    /**
     * 返回与关闭键盘按钮
     */
    private View iv_return, iv_menu;

    private RecyclerView recyclerview;
    /**
     * activity中的根目录
     */
    private View ll_root;

    private Toolbar toolbar;
    /**
     * 窗体控件上一次的高度,用于监听键盘弹起
     */
    private int mLastHeight;

    public static void lanuch(Context context) {
        context.startActivity(new Intent(context, RecyclerViewTestActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        ll_root = findViewById(R.id.ll_root);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        initToolbar();
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(llm);
        recyclerview.setAdapter(new TestAdapter());

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
                            params.height = r.bottom - ll_root.getTop()/*  - statusHeight*/;
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
                ConfigUtils.hideSoftInput(this);
                break;
        }
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        ActionBar bar = getSupportActionBar();
        bar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        View actionView = getLayoutInflater().inflate(R.layout.actionbar_public, null);
        iv_return = actionView.findViewById(R.id.iv_return);
        iv_menu = actionView.findViewById(R.id.iv_menu);
        iv_return.setOnClickListener(this);
        iv_menu.setOnClickListener(this);
        ActionBar.LayoutParams actionBarParams = new ActionBar.LayoutParams(
                ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        bar.setCustomView(actionView, actionBarParams);
        ConfigUtils.setStatusBarColor(this, getResources().getColor(R.color.colorPrimary));
    }

    private class TestAdapter extends RecyclerView.Adapter<TestHolder> {

        private final String[] strs = {"张三","李四","王二麻子","张冠","李戴","王三狗子","唐马儒","唐鸡儒","张全蛋", "赵铁柱", "王尼玛"};

        @Override
        public int getItemCount() {
            return strs.length;
        }

        @Override
        public TestHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new TestHolder(getLayoutInflater().inflate(R.layout.item_test, parent, false));
        }

        @Override
        public void onBindViewHolder(TestHolder holder, int position) {
            holder.tv.setText(strs[position] + "        position: "+position);
        }
    }

    private class TestHolder extends RecyclerView.ViewHolder {
        TextView tv;
        public TestHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv_name);
        }
    }

}
