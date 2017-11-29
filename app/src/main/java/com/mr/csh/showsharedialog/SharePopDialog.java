package com.mr.csh.showsharedialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Mr.CSH on 2017/6/26.
 */

//分享对话框
public class SharePopDialog extends Dialog {
    private Context mContext;
    private SimpleAdapter mShareGvAdapter;
    private Dialog mDialog;
    private SharePopCallbackImpl mSharePopCallback;

    public SharePopDialog(Context context, SharePopCallbackImpl sharePopCallback) {
        super(context);
        mContext = context;
        mSharePopCallback = sharePopCallback;
        init(context);
    }

    private void init(Context context) {
        mShareGvAdapter = new SimpleAdapter(context, getShareData(context),
                R.layout.share_item, new String[]{"img", "name"},
                new int[]{R.id.item_share_img, R.id.item_share_name});
    }

    //数据接口回调
    public interface SharePopCallbackImpl {
        void onResponse(int position);
    }

    private List<Map<String, Object>> getShareData(Context context) {
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < context.getResources().getStringArray(R.array.share_title).length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("img", context.getResources().obtainTypedArray(R.array.share_icon).getResourceId(i, 0));
            map.put("name", context.getResources().getStringArray(R.array.share_title)[i]);
            list.add(map);
        }
        return list;
    }

    //显示分享对话框
    public Dialog showShareDialog(Context context) {
        mDialog = new Dialog(context, R.style.BottomDialog);
        View view = View.inflate(context, R.layout.share_popup_menu, null);
        ImageView shareHideImg =view.findViewById(R.id.share_hide_img);
        GridView shareGridView =  view.findViewById(R.id.share_gridview);

        if (mShareGvAdapter != null) {
            shareGridView.setAdapter(mShareGvAdapter);
            shareGridView.setOnItemClickListener(mShareGridViewItemClick);
        }

        shareHideImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDialog != null && mDialog.isShowing()) {
                    mDialog.dismiss();
                }
            }
        });

        mDialog.setContentView(view);
        Utils.setDialogAttributes(mDialog, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM);
        mDialog.show();

        return mDialog;
    }

    AdapterView.OnItemClickListener mShareGridViewItemClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (mDialog != null && mDialog.isShowing()) {
                mDialog.dismiss();
            }
            mSharePopCallback.onResponse(position);
        }
    };
}
