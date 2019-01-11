package com.lmq.ui;

import android.content.Intent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.newapp.LoginActivity;
import com.example.newapp.R;
import com.lmq.base.BaseActivity;
import com.lmq.common.Appstorage;
import com.lmq.tool.CustormDialog;
import com.lmq.tool.DataCleanManager;
import com.lmq.tool.Url_Web;

import butterknife.BindView;
import butterknife.OnClick;

public class Settings_Activity extends BaseActivity {
    @Override
    protected int setContentView(){
        return R.layout.activity_settings;
    }

    @Override
    protected void initBundleData() {

    }

    @Override
    protected void initView() {
        try {
         //  initListView();
            setTitle("设置");
            mes_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    Appstorage.setMessageState(mContext,isChecked);
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //@BindView(R.id.list)ListView listView;
    @BindView(R.id.mes_switch)Switch mes_switch;

    @OnClick(R.id.exit)
    public void exitlocin(){
        showExitLogin();


    }
    @OnClick(R.id.text_cache)
    public void  clearCache(){
        showclearCache();
    }
    @OnClick(R.id.text_advice)
    public void  goAdvice(){
        Intent it=new Intent(mContext,Settings_Advice_Activity.class);
        startActivity(it);
    }
    @OnClick(R.id.txt_about)
    public void  goAbout(){
        Intent itabout=new Intent(mContext, Url_Web.class);
        itabout.putExtra("title","关于");
        itabout.putExtra("url","http://www.buffalo-robot.com/list-10-1.html");
        startActivity(itabout);
    }
/*
    public void initListView(){

        final List<String> arr=new ArrayList<>();
        arr.add("消息推送");
        arr.add("清理缓存");
        arr.add("软件使用建议");
        arr.add("关于");
        SettingsAdapter adapter = new SettingsAdapter(mContext, R.layout.listitem_settings, arr);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String title=arr.get(i);
                switch (title){
                    case "消息推送":
                        break;
                    case "清理缓存":
                        clearCache();
                        break;
                    case "软件使用建议":
                        Intent it=new Intent(mContext,Settings_Advice_Activity.class);
                        startActivity(it);
                        break;
                    case "关于":
                        Intent itabout=new Intent(mContext, Url_Web.class);
                        itabout.putExtra("title","关于");
                        itabout.putExtra("url","http://www.buffalo-robot.com/list-10-1.html");
                        startActivity(itabout);
                        break;
                }


            }
        });
    }*/
    public CustormDialog custormDialog=null;
    public void showclearCache(){
       custormDialog=new CustormDialog(Settings_Activity.this,new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                custormDialog.dismiss();
                try {
                    DataCleanManager.clearAllCache(mContext);
                    showMes("清除成功！  ");


                }catch (Exception e){
                    e.printStackTrace();
                }

                // dialog.dismiss();
            }
        });
        custormDialog.showPopupWindow("您确定要清除软件缓存吗？","取消","确定");
    }
    public void showExitLogin(){
        custormDialog=new CustormDialog(Settings_Activity.this,new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                custormDialog.dismiss();
                try {
                    Appstorage.setLoginState(mContext, false);
                    Appstorage.setIMUser_Account(mContext,"","");//im清除
                    Intent it=new Intent(mContext, LoginActivity.class);
                    startActivity(it);


                }catch (Exception e){
                    e.printStackTrace();
                }

                // dialog.dismiss();
            }
        });
        custormDialog.showPopupWindow("您确定要退出登录吗？","取消","确定");
    }

}
