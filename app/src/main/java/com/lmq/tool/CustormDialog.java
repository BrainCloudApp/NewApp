package com.lmq.tool;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.newapp.R;


/**
 * Created by liangminqiang on 2018/8/1.
 */

public class CustormDialog {

    private boolean hasshowdialog=false;
    Activity activity;
    View.OnClickListener onClickListener;
     Dialog dialog;
    public CustormDialog(Activity activity, View.OnClickListener onClickListener){
        this.activity=activity;
        this.onClickListener=onClickListener;
    }
    public void dismiss(){
        dialog.cancel();
        dialog.dismiss();
    }

    public  void showPopupWindow(final String titllestr,final String canclestr,final String surestr) {//弹出提示框
        if(activity==null)
            return;

        if(hasshowdialog)
            return;

        View contentView = LayoutInflater.from(activity).inflate(
                R.layout.dialog_update, null);
//        final PopupWindow popupWindow = new PopupWindow(contentView,
//                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        dialog = builder.create();
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                hasshowdialog=false;
            }
        });
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                hasshowdialog=false;
            }
        });

        // 设置按钮的点击事件
        TextView title=(TextView)contentView.findViewById(R.id.title);
        title.setText(titllestr);
        Button cancle=(Button)contentView.findViewById(R.id.cancle);
        Button sure=(Button)contentView.findViewById(R.id.sure);
        cancle.setText(canclestr);
        sure.setText(surestr);

        cancle.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                hasshowdialog=false;
                dialog.dismiss();
            }
        });
        sure.setOnClickListener(onClickListener);



        Window window = dialog.getWindow();

        //  window.setGravity(Gravity.TOP|Gravity.RIGHT);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.alpha = 1;//0.5f;    // 设置透明度为0.5
        window.setAttributes(lp);


        dialog.show();
        hasshowdialog=true;
        dialog.getWindow().setContentView(contentView);

    }
}
