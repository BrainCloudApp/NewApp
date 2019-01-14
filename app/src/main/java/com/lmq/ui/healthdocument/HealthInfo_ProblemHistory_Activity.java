package com.lmq.ui.healthdocument;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.newapp.R;
import com.lmq.base.BaseActivity;
import com.lmq.common.CommonPresenter;
import com.lmq.common.CommonView;
import com.lmq.tool.CustormDialog;
import com.lmq.ui.adapter.ProblemList_EditAdapter;
import com.lmq.ui.entity.HealthProblem;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class HealthInfo_ProblemHistory_Activity extends BaseActivity implements CommonView {
    CommonPresenter mpresenter=new CommonPresenter(this,this);
    ArrayList<HealthProblem> source;
    int selectedindex=-1;
    @Override
    protected int setContentView(){
        return R.layout.activity_health_hospitalhistory;
    }

    @Override
    protected void initBundleData() {

    }

    @Override
    protected void initView() {

        try {
            setTitle("病史");
            source = (ArrayList<HealthProblem>) getIntent().getSerializableExtra("info");
            if (source == null)
                source = new ArrayList<>();
            initdata();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void onResult(String result) {
        showMes("删除成功！");
     /*   Intent it=new Intent();
        it.putExtra("data",info);
        setResult(RESULT_OK,it);*/
        //finish();
    }

    @Override
    public void goback() {
        try {
            Intent it = new Intent();
            it.putExtra("info", source);
            setResult(RESULT_OK, it);

        }catch (Exception e){
            e.printStackTrace();

        }
        super.goback();
    }

    @BindView(R.id.recyclerView)RecyclerView list;
    @OnClick(R.id.action)
    public void addHistory(){
        //添加记录
        selectedindex=-1;
        Intent it=new Intent(mContext,HealthInfo_ProblemHistory_Item_Activity.class);
        startActivityForResult(it,1);
    }
    ProblemList_EditAdapter sa;
    public void initdata(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext);
        list.setLayoutManager(layoutManager);

         sa=new ProblemList_EditAdapter(source);
         sa.setOnRecyclerViewListener(new ProblemList_EditAdapter.OnRecyclerViewListener() {
             @Override
             public void onEdit(int position) {
                 //跳转到item详情编辑
                 selectedindex=position;
                 Intent it=new Intent(mContext,HealthInfo_ProblemHistory_Item_Activity.class);
                 it.putExtra("info",source.get(position));
                 startActivityForResult(it,1);
             }

             @Override
             public void onDelete(int position) {
                //删除提示
               //  selectedindex=-1;
                 showDeleteAlert(position);
             }
         });
        list.setAdapter(sa);


        DividerItemDecoration divider = new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL);
        //  SpacesItemDecoration sp=new SpacesItemDecoration(2);
        list.addItemDecoration(divider);

    }

    public CustormDialog custormDialog=null;
    public void showDeleteAlert(final int position){
        custormDialog=new CustormDialog(HealthInfo_ProblemHistory_Activity.this,new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                custormDialog.dismiss();
                try {
                    source.remove(position);
                    sa.notifyDataSetChanged();
                  //mpresenter.deleteHealthinf_Hospital(source.get(position));

                }catch (Exception e){
                    e.printStackTrace();
                }

                // dialog.dismiss();
            }
        });
        custormDialog.showPopupWindow("您确定要删除该条记录吗？","取消","确定");
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (resultCode == RESULT_OK) {
                switch (requestCode) {
                    case 1:
                        //刷新数据
                        if (selectedindex == -1) {
                            //添加
                            source.add(0, (HealthProblem) data.getSerializableExtra("info"));

                        }else{
                            //修改
                            source.set(selectedindex,(HealthProblem) data.getSerializableExtra("info"));
                        }
                        sa.notifyDataSetChanged();
                        break;

                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
