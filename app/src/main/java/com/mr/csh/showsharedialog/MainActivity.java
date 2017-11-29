package com.mr.csh.showsharedialog;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.btn)
    Button mBtn;

    private Activity mActivity;
    private PopupWindow mSharePopWindow;
    private SharePopDialog mSharePopDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        mActivity = this;
        mSharePopDialog = new SharePopDialog(this, mSharePopCallback);
    }

    @OnClick({R.id.btn})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                showShareMenu();
                break;
        }
    }

    private void showShareMenu() {
        View view = View.inflate(this, R.layout.pop_menu, null);
        Button aBtn = view.findViewById(R.id.share_a_btn);
        Button bBtn = view.findViewById(R.id.share_b_btn);

        bBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mSharePopWindow != null && mSharePopWindow.isShowing()) {
                    mSharePopWindow.dismiss();
                }
                mSharePopDialog.showShareDialog(mActivity);
            }
        });

        aBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSharePopWindow != null && mSharePopWindow.isShowing()) {
                    mSharePopWindow.dismiss();
                }
                mSharePopDialog.showShareDialog(mActivity);
            }
        });

        if (mSharePopWindow == null) {
            mSharePopWindow = new PopupWindow(this);
            mSharePopWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
            mSharePopWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
            mSharePopWindow.setBackgroundDrawable(new BitmapDrawable());

            mSharePopWindow.setFocusable(true);
            mSharePopWindow.setOutsideTouchable(true);
        }

        mSharePopWindow.setContentView(view);
        mSharePopWindow.showAsDropDown(mBtn, 0, 0);
        mSharePopWindow.update();
    }

    private SharePopDialog.SharePopCallbackImpl mSharePopCallback = new SharePopDialog.SharePopCallbackImpl() {
        @Override
        public void onResponse(int position) {
            Toast.makeText(mActivity, "你点击了第" + position + "项", Toast.LENGTH_SHORT).show();
        }
    };
}
