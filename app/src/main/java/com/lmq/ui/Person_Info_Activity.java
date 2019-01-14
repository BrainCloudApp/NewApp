package com.lmq.ui;

import android.content.Intent;
import android.util.Log;

import com.example.newapp.R;
import com.lmq.base.BaseActivity;
import com.lmq.common.AppContact;
import com.lmq.common.Appstorage;
import com.lmq.common.CommonPresenter;
import com.lmq.common.CommonView;
import com.netease.nim.uikit.api.NimUIKit;
import com.netease.nim.uikit.impl.NimUIKitImpl;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.auth.LoginInfo;

import butterknife.OnClick;

public class Person_Info_Activity  extends BaseActivity implements CommonView {

    CommonPresenter mpresenter=new CommonPresenter(this,this);
    @Override
    protected int setContentView(){
        return R.layout.activity_person_info;
    }

    @Override
    protected void initBundleData() {

    }


    @Override
    protected void initView() {
        try {
           setTitle("用户信息");
            mpresenter.getPersonInfo(getIntent().getStringExtra("id"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @OnClick(R.id.gochat)
    public void gochat(){
       // showMes("进入聊天界面");
       // goCommunicat();
        setResult(RESULT_OK);
        finish();
    }
    @OnClick(R.id.linear_practice)
    public void goPractice(){
        showMes("进入训练项目");
    }
    @OnClick(R.id.linear_share)
    public void goShare(){

        Intent it=new Intent(mContext,PersonShareList_Activity.class);
        startActivity(it);

    }
    public  void onResult(String result){

    }
    public void goCommunicat(){
        String account = Appstorage.getIMUser_Account_Acc(this);
        String token = Appstorage.getIMUser_Account_Pwd(this);
        if (account.length()>0&&token.length()>0){
            //已登录过。直接进入聊天
            NimUIKitImpl.setAccount(account);
            NimUIKit.startP2PSession(mContext, AppContact.localImDes);
        }else {
            final LoginInfo info = new LoginInfo(AppContact.localImAccount, AppContact.localImPwd);
            NimUIKit.login(info, new RequestCallback<LoginInfo>() {
                @Override
                public void onSuccess(LoginInfo loginInfo) {

                    //启动单聊界面
                    //  showMes("登录成功！");
                    Log.d("Login", "登录成功！");
                    NimUIKitImpl.setAccount(loginInfo.getAccount());
                    NimUIKit.startP2PSession(mContext, AppContact.localImDes);

                    // NimUIKit.startChatting(Login.this, "acc_02",SessionTypeEnum.P2P, null,null);
                    // 启动群聊界面
                    // NimUIKit.startTeamSession(MainActivity.this, "群ID");
                }

                @Override
                public void onFailed(int i) {

                }

                @Override
                public void onException(Throwable throwable) {

                }
            });
        }


    }
}
