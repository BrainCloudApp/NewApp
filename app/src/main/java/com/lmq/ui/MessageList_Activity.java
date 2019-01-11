package com.lmq.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.newapp.R;
import com.lmq.base.BaseActivity;
import com.lmq.common.CommonPresenter;
import com.lmq.common.CommonView;
import com.lmq.tool.SpacesItemDecoration;
import com.lmq.ui.adapter.MessageListAdapter;
import com.lmq.ui.entity.AppMessage;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/12/28 0028.
 */

public class MessageList_Activity extends BaseActivity implements CommonView {

    ArrayList<AppMessage> source=new ArrayList<>();
    CommonPresenter mpresenter=new CommonPresenter(this,this);
    private static  final int TAG_REFRESH=0;
    private static  final int TAG_LOADMORE=1;
    private  int requestTag=0;//0刷新，1 加载更多
    String id_pat="";//患者id
    int type=1;//
    @Override
    protected int setContentView(){
        return R.layout.activity_messagelist;
    }

    @Override
    protected void initBundleData() {

    }

    @Override
    protected void initView() {
        try {
            setTitle("消息提醒");
            id_pat=getIntent().getStringExtra("id");
            if(id_pat==null)
                id_pat="";
            type=getIntent().getIntExtra("type",1);
            initLocalData();
            initrefresh();
            setListView();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void onResult(String result){
        switch (requestTag){
            case TAG_REFRESH:
                refreshLayout.finishRefresh(true);
                break;
            case TAG_LOADMORE:
                refreshLayout.finishLoadMore(true);
                break;
        }

    }

    MessageListAdapter sa;

    @BindView(R.id.refreshLayout)SmartRefreshLayout refreshLayout;
    @BindView(R.id.recyclerView)RecyclerView recyclerView;


    public void initrefresh(){//上拉加载下拉刷新
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                //refreshlayout.finishRefresh(2000/**,false*/);//传入false表示刷新失败
                mpresenter.getPatientExprienceList(id_pat);
            }
        });
      /*  refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadMore(2000*//**,false*//*);//传入false表示加载失败
            }
        });*/
        refreshLayout.setRefreshHeader(new ClassicsHeader(this));
       // refreshLayout.setRefreshFooter(new ClassicsFooter(this));
    }
    public void setListView(){
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext);

        recyclerView.setLayoutManager(layoutManager);


        /*DividerItemDecoration divider = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        //divider.setDrawable(new ColorDrawable(Color.rgb(204,204,204)));
        divider.setDrawable(ContextCompat.getDrawable(this,R.drawable.transparentdrawable));

        recyclerView.addItemDecoration(divider);*/

        recyclerView.addItemDecoration(new SpacesItemDecoration(15));



        sa = new MessageListAdapter(source,mContext);

        recyclerView.setAdapter(sa);

    }
    public void initLocalData(){

        AppMessage message=new AppMessage();
        message.setTime("2019-01-03");
        message.setTitle("今日训练");
        message.setContent("开始训练啦！！");
        source.add(message);
        AppMessage message2=new AppMessage();
        message2.setTime("2019-01-03");
        message2.setTitle("训练警告");
        message2.setContent("训练失误请联系您的医生！！");
        source.add(message2);
        AppMessage message3=new AppMessage();
        message3.setTime("2019-01-03");
        message3.setTitle("今日训练");
        message3.setContent("开始训练啦！！");
        source.add(message3);
        AppMessage message4=new AppMessage();
        message4.setTime("2019-01-03");
        message4.setTitle("今日训练");
        message4.setContent("开始训练啦！！");
        source.add(message4);


    }


}
