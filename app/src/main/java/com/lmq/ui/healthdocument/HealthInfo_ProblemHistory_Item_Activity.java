package com.lmq.ui.healthdocument;

import android.content.Intent;
import android.widget.EditText;

import com.example.newapp.R;
import com.lmq.base.BaseActivity;
import com.lmq.common.CommonPresenter;
import com.lmq.common.CommonView;
import com.lmq.ui.entity.HealthProblem;

import butterknife.BindView;
import butterknife.OnClick;

public class HealthInfo_ProblemHistory_Item_Activity extends BaseActivity implements CommonView {
    CommonPresenter mpresenter=new CommonPresenter(this,this);
    HealthProblem info;
    @Override
    protected int setContentView(){
        return R.layout.activity_healthinfo_problemhistory_item;
    }

    @Override
    protected void initBundleData() {

    }

    @Override
    protected void initView() {
        setTitle("病史");
        info=(HealthProblem)getIntent().getSerializableExtra("info");
        if(info==null){
            info=new HealthProblem();
        }
        initData();
    }
    @Override
    public void onResult(String result) {
        showMes("保存成功！");
     /*   Intent it=new Intent();
        it.putExtra("data",info);
        setResult(RESULT_OK,it);*/
        finish();
    }
    @OnClick(R.id.action)
    public void saceHistory(){
        //添加记录
        String timestr=time.getText().toString().trim();
        String namestr=name.getText().toString().trim();
        String resultstr=result.getText().toString().trim();
        String beizhustr=beizhu.getText().toString().trim();
        if(timestr.length()==0){
            showMes("请输入疾病时间！");
            return;
        }
        if(namestr.length()==0){
            showMes("请输入疾病名称！");
            return;
        }
        if(resultstr.length()==0){
            showMes("请输入治疗效果！");
            return;
        }
        if(beizhustr.length()==0){
            showMes("请输入备注！");
            return;
        }
        info.setTime(timestr);
        info.setName(namestr);
        info.setResult(resultstr);
        info.setBeizhu(beizhustr);
       // mpresenter.editHealthinf_Hospital(info.getId(),info.getIntime(),info.getOuttime(),info.getDoctor(),info.getChufang());
        Intent it=new Intent();
        it.putExtra("info",info);
        setResult(RESULT_OK,it);
        finish();

    }



    @BindView(R.id.problemhistory_time)
    EditText time;
    @BindView(R.id.problemhistory_name) EditText name;
    @BindView(R.id.problemhistory_result) EditText result;
    @BindView(R.id.problemhistory_beizhu) EditText beizhu;

    public void initData(){
        time.setText(info.getTime());
        name.setText(info.getName());
        result.setText(info.getResult());
        beizhu.setText(info.getBeizhu());
    }
}
