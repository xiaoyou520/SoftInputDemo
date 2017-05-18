package you.xiaochen;

import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.PopupWindow;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.bt_scroll).setOnClickListener(this);
        findViewById(R.id.bt_recycler).setOnClickListener(this);
        findViewById(R.id.bt_popup).setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_scroll:
                ScrollTestActivity.lanuch(this);
                break;
            case R.id.bt_recycler:
                RecyclerViewTestActivity.lanuch(this);
                break;
            case R.id.bt_popup:
                //showPopupWindow();
                break;
        }
    }





}
