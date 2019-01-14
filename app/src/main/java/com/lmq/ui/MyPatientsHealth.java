package com.lmq.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.newapp.R;
import com.lmq.base.BaseActivity;
import com.lmq.common.AppContact;
import com.lmq.common.Appstorage;
import com.lmq.common.CommonPresenter;
import com.lmq.common.CommonView;
import com.lmq.tool.PermisstionCheck;
import com.lmq.ui.adapter.PatientAdapter;
import com.lmq.ui.adapter.PatientHealthAdapter;
import com.lmq.ui.entity.Patient;
import com.lmq.ui.healthdocument.HealthInfo_Activity;
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

public class MyPatientsHealth extends BaseActivity implements CommonView{

    CommonPresenter mpresenter=new CommonPresenter(this,this);
    ArrayList<Patient> source=new ArrayList<>();
    PatientHealthAdapter sa;
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
            setTitle("健康档案");
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


        for(int i=0;i<4;i++){
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

        sa = new PatientHealthAdapter(source,mContext);
        sa.setOnRecyclerViewListener(new PatientHealthAdapter.OnRecyclerViewListener() {
            @Override
            public void onItemClick(int position) {
             //   showMes("点击项目");//进入健康档案
                Intent intent3 = new Intent(mContext, HealthInfo_Activity.class);
                startActivity(intent3);

            }

        });
        recyclerView.setAdapter(sa);
    }


    private boolean haspermission=false;
    public void checkPermisson(){
        if (Build.VERSION.SDK_INT >= 23) {
            haspermission=   PermisstionCheck.checkAndRequestPermission(MyPatientsHealth.this, android.Manifest.permission.CALL_PHONE) ;
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
