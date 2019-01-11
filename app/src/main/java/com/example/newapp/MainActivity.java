package com.example.newapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.lmq.common.AppContact;
import com.lmq.common.Appstorage;
import com.lmq.common.MyApplication;
import com.lmq.im.reminder.ReminderItem;
import com.lmq.im.reminder.ReminderManager;
import com.netease.nim.uikit.api.NimUIKit;
import com.netease.nim.uikit.impl.NimUIKitImpl;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.StatusBarNotificationConfig;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.netease.nimlib.sdk.msg.SystemMessageService;

public class MainActivity extends AppCompatActivity {

    private Fragment1 fragment1 = new Fragment1();
    private Fragment2 fragment2 = new Fragment2();
    private Fragment3 fragment3 = new Fragment3();
    private Fragment[] fragments;
    private int lastFragment;



    private BottomNavigationView.OnNavigationItemSelectedListener changeFragment
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    if (lastFragment != 0) {
                        switchFragment(lastFragment, 0);
                        lastFragment = 0;
                    }
                    return true;
                case R.id.navigation_lib:
                    if (lastFragment != 1) {
                        switchFragment(lastFragment, 1);
                        lastFragment = 1;
                    }
                    return true;
                case R.id.navigation_my:
                    if (lastFragment != 2) {
                        switchFragment(lastFragment, 2);
                        lastFragment = 2;
                    }
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Appstorage.setContext(MainActivity.this);
        // Appstorage.setLoginState(this, true);
        Intent intent = getIntent();
//        String username = intent.getStringExtra("input_username");
//        Log.d("MainActivity",username);
        Bundle bundle = new Bundle();
        bundle.putString("input_username",intent.getStringExtra("input_username"));
        bundle.putString("reg_class",intent.getStringExtra("reg_class"));
        fragment3.setArguments(bundle);
        if (Appstorage.getLoginState(this) == true){
            initFragment();
            autologin();
            registerMsgUnreadInfoObserver(true);

        }else{
            Intent loginintent = new Intent(this, LoginActivity.class);
            startActivity(loginintent);
            finish();
        }




    }

    private void initFragment() {
        fragments = new Fragment[]{fragment1, fragment2, fragment3};
        lastFragment = 0;

        getSupportFragmentManager().beginTransaction().replace(R.id.main_view, fragment1)
                .show(fragment1).commit();
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(changeFragment);
    }

    private void switchFragment(int lastFragment, int index) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.hide(fragments[lastFragment]);
        if (fragments[index].isAdded() == false) {
            fragmentTransaction.add(R.id.main_view, fragments[index]);
        }
        fragmentTransaction.show(fragments[index]).commitAllowingStateLoss();
    }
    public void autologin(){
        String account = Appstorage.getIMUser_Account_Acc(this);
        String token = Appstorage.getIMUser_Account_Pwd(this);
        if (account.length()>0&&token.length()>0){
            //已登录过。直接进入聊天
            initNotificationConfig();
            NimUIKitImpl.setAccount(account);
            //   NimUIKit.startP2PSession(mContext, AppContact.localImDes);
        }else {
            final LoginInfo info = new LoginInfo(AppContact.localImAccount, AppContact.localImPwd);
            NimUIKit.login(info, new RequestCallback<LoginInfo>() {
                @Override
                public void onSuccess(LoginInfo loginInfo) {

                    //启动单聊界面
                    //  showMes("登录成功！");
                    Log.d("Login", "登录成功！");
                    initNotificationConfig();
                    NimUIKitImpl.setAccount(loginInfo.getAccount());
                    //   NimUIKit.startP2PSession(mContext, AppContact.localImDes);

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
    /**
     * 注册未读消息数量观察者
     */
    private void registerMsgUnreadInfoObserver(boolean register) {
        if (register) {
            ReminderManager.getInstance().registerUnreadNumChangedCallback(new ReminderManager.UnreadNumChangedCallback() {
                @Override
                public void onUnreadNumChanged(ReminderItem item) {
                    Log.d("IM","您有新的短消息！");
                }
            });
        } else {
            ReminderManager.getInstance().unregisterUnreadNumChangedCallback(new ReminderManager.UnreadNumChangedCallback() {
                @Override
                public void onUnreadNumChanged(ReminderItem item) {
                    Log.d("IM","您有新的短消息！");
                }
            });
        }
    }
    /**
     * 查询系统消息未读数
     */
    private void requestSystemMessageUnreadCount() {
        int unread = NIMClient.getService(SystemMessageService.class).querySystemMessageUnreadCountBlock();
        //  SystemMessageUnreadManager.getInstance().setSysMsgUnreadCount(unread);
        ReminderManager.getInstance().updateContactUnreadNum(unread);
    }
    private void initNotificationConfig() {
        // 初始化消息提醒
        NIMClient.toggleNotification(true);
        // 加载状态栏配置
        StatusBarNotificationConfig statusBarNotificationConfig = MyApplication.loadStatusBarNotificationConfig();
        // 更新配置
        NIMClient.updateStatusBarNotificationConfig(statusBarNotificationConfig);
    }
}
