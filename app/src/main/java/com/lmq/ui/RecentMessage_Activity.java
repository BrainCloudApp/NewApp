package com.lmq.ui;

import com.example.newapp.R;
import com.lmq.base.BaseActivity;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.msg.MsgService;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;


public class RecentMessage_Activity extends BaseActivity {

  //RecentContactsFragment fragment;
    @Override
    public int setContentView() {
        return R.layout.activity_recentmessage;
    }

    @Override
    protected void initBundleData() {

    }

    @Override
    protected void initView() {

        setTitle("我的消息");
     //   fragment=(Fragment)findViewById(R.id.recent_contacts_fragment);

    }

  @Override
  protected void onResume() {
    super.onResume();
    NIMClient.getService(MsgService.class).setChattingAccount(MsgService.MSG_CHATTING_ACCOUNT_ALL, SessionTypeEnum.None);
  }


}
