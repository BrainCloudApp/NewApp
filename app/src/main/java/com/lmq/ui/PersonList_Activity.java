package com.lmq.ui;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.newapp.R;
import com.lmq.base.BaseActivity;
import com.lmq.common.CommonPresenter;
import com.lmq.common.CommonView;
import com.lmq.ui.adapter.PersonAdapter;
import com.lmq.ui.entity.Patient;
import com.lmq.ui.entity.Person;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/12/28 0028.
 */

public class PersonList_Activity extends BaseActivity implements CommonView {

    CommonPresenter mpresenter=new CommonPresenter(this,this);
    ArrayList<Person> source=new ArrayList<>();
    private static  final int TAG_REFRESH=0;
    private static  final int TAG_LOADMORE=1;
    private  int requestTag=0;//0刷新，1 加载更多
    private String keyword="";
    int choseposition=-1;
    @Override
    protected int setContentView(){
        return R.layout.activity_person;
    }

    @Override
    protected void initBundleData() {

    }

    @Override
    protected void initView() {
        try {
            setTitle("患者列表");
            initLocalData();
            initrefresh();
            setListView();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void onResult(String result){

    }

    @Override
    public void showError(String err) {
        super.showError(err);
        switch (requestTag){
            case TAG_REFRESH:
                refreshLayout.finishRefresh(false);
                break;
            case TAG_LOADMORE:
                refreshLayout.finishLoadMore(false);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mpresenter.getActivity()==null)
            mpresenter=new CommonPresenter(this,this);
    }
    PersonAdapter sa;

    @BindView(R.id.refreshLayout)SmartRefreshLayout refreshLayout;
    @BindView(R.id.recyclerView)RecyclerView recyclerView;


    public void initrefresh(){//上拉加载下拉刷新
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
               // refreshlayout.finishRefresh(2000/**,false*/);//传入false表示刷新失败
                requestTag=0;
                mpresenter.searchPatient(keyword);
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
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(dy>0)
                    closeKeyboard();
            }
        });


        sa = new PersonAdapter(source,mContext);
        sa.setOnRecyclerViewListener(new PersonAdapter.OnRecyclerViewListener() {
            @Override
            public void search(String keyworkd) {
                showMes("搜索");
                keyword=keyword;
                closeKeyboard();
                mpresenter.searchPatient(keyword);
            }

            @Override
            public void showTip(String mes) {
                showMes(mes);
            }

            @Override
            public void onItemClick(int position) {
                choseposition=position;
                Intent it=new Intent(mContext,Person_Info_Activity.class);
                startActivityForResult(it,MyPatients.CHOSEPATIENT);
            }
        });
        recyclerView.setAdapter(sa);
        closeKeyboard();
    }
    public void initLocalData(){


        for(int i=0;i<20;i++){
            Person p=new Person();
            p.setName("张三"+i);
            p.setImg("");
            source.add(p);
        }


    }

   /* @OnClick(R.id.action)//
    public void sharexinde(){
       *//* Intent it=new Intent(mContext,ShareXinde_Activity.class);
        startActivity(it);*//*
        mpresenter.login(Appstorage.getLoginUserName(mContext),Appstorage.getLoginUserPwd(mContext,Appstorage.getLoginUserName(mContext)));

    }*/
    public void loginresult(String result){
        showMes(result);
    }
    private void closeKeyboard() {
        View view = getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            if(requestCode== MyPatients.CHOSEPATIENT){
                //
                Intent it=new Intent();
                it.putExtra("info",source.get(choseposition).getName());
                setResult(RESULT_OK,it);
                finish();
            }
        }
    }

}
