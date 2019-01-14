package com.lmq.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.newapp.Manifest;
import com.example.newapp.R;
import com.lmq.base.BaseActivity;
import com.lmq.common.AppContact;
import com.lmq.common.Appstorage;
import com.lmq.common.CommonPresenter;
import com.lmq.common.CommonView;
import com.lmq.tool.PermisstionCheck;
import com.lmq.ui.adapter.PatientAdapter;
import com.lmq.ui.entity.Patient;
import com.netease.nim.uikit.api.NimUIKit;
import com.netease.nim.uikit.impl.NimUIKitImpl;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class MyPatients extends BaseActivity implements CommonView{

    CommonPresenter mpresenter=new CommonPresenter(this,this);
    ArrayList<Patient> source=new ArrayList<>();
    PatientAdapter sa;
    public static  final int CHOSEPATIENT=1;
    @Override
    protected int setContentView(){
        return R.layout.activity_mypatients;
    }

    @Override
    protected void initBundleData() {

    }
    public void onResult(String result){
        //刷新列表
    }

    @Override
    protected void initView() {
        try {
            setTitle("患者通讯录");
            initLocalData();
            initrefresh();
            setListView();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @OnClick(R.id.action)
    public void addPatient(){
        //添加患者去选择
        Intent it=new Intent(mContext,PersonList_Activity.class);
        startActivityForResult(it,CHOSEPATIENT);
    }
    public void initLocalData(){


        for(int i=0;i<20;i++){
            Patient p=new Patient();
            p.setName("张三"+i);
            p.setImg("");
            p.setPhone("13666666666");
            p.setAccount("acc02");
            source.add(p);
        }


    }
    @BindView(R.id.refreshLayout)SmartRefreshLayout refreshLayout;
    @BindView(R.id.recyclerView)RecyclerView recyclerView;


    public void initrefresh(){//上拉加载下拉刷新
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                // refreshlayout.finishRefresh(2000/**,false*/);//传入false表示刷新失败

              //  mpresenter.searchPatient(keyword);
            }
        });
      /*  refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadMore(2000*//**,false*//*);//传入false表示加载失败
            }
        });*/
        refreshLayout.setRefreshHeader(new ClassicsHeader(this));

        //    refreshLayout.setRefreshFooter(new ClassicsFooter(this));
    }
    public void setListView(){
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext);

        recyclerView.setLayoutManager(layoutManager);


        DividerItemDecoration divider = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        //divider.setDrawable(new ColorDrawable(Color.rgb(204,204,204)));
        divider.setDrawable(ContextCompat.getDrawable(this,R.drawable.line));
        recyclerView.addItemDecoration(divider);

        sa = new PatientAdapter(source,mContext);
        sa.setOnRecyclerViewListener(new PatientAdapter.OnRecyclerViewListener() {
            @Override
            public void onItemClick(int position) {
                showMes("点击项目");//进入健康档案
            }

            @Override
            public void onChatClick(int position) {
               // showMes("点击聊天");
                Patient p=source.get(position);
                goCommunicat();
            }

            @Override
            public void onPhoneClick(int position) {
                showMes("拨打电话");
                Patient p=source.get(position);
                callPhone(p.getPhone());
            }
        });
        recyclerView.setAdapter(sa);
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

                }

                @Override
                public void onFailed(int i) {
                    showMes("即时通讯登录失败！");
                }

                @Override
                public void onException(Throwable throwable) {
                    showMes("即时通讯登录异常");
                }
            });
        }


    }
    public void callPhone(String phoneNum){
        try {
            checkPermisson();

            if (!haspermission) {
                showMes("请允许拨打电话的权限之后才能进行拨打电话！");
                return;
            }
            Intent intent = new Intent(Intent.ACTION_CALL);
            Uri data = Uri.parse("tel:" + phoneNum);
            intent.setData(data);
            startActivity(intent);
        } catch (SecurityException e) {
            showMes("请允许拨打电话的权限之后才能进行拨打电话！");
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private boolean haspermission=false;
    public void checkPermisson(){
        if (Build.VERSION.SDK_INT >= 23) {
            haspermission=   PermisstionCheck.checkAndRequestPermission(MyPatients.this, android.Manifest.permission.CALL_PHONE) ;
        }else{
            haspermission=true;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            if(requestCode==CHOSEPATIENT){
                //
                String id=data.getStringExtra("id");
                mpresenter.addMypatient(id);

            }
        }
    }
}
