package com.lmq.ui.healthdocument;

import android.content.Intent;
import android.widget.EditText;

import com.example.newapp.R;
import com.lmq.base.BaseActivity;
import com.lmq.common.CommonPresenter;
import com.lmq.common.CommonView;
import com.lmq.ui.entity.HospitalHistory;

import butterknife.BindView;
import butterknife.OnClick;

public class HospitalHistory_Item_Activity extends BaseActivity implements CommonView {
    CommonPresenter mpresenter=new CommonPresenter(this,this);
    HospitalHistory info;
    @Override
    protected int setContentView(){
        return R.layout.activity_health_hospitalhistory_item;
    }

    @Override
    protected void initBundleData() {

    }

    @Override
    protected void initView() {
        setTitle("出入院记录");
        info=(HospitalHistory)getIntent().getSerializableExtra("info");
        if(info==null){
            info=new HospitalHistory();
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
        String intimestr=intime.getText().toString().trim();
        String outtimestr=outtime.getText().toString().trim();
        String doctorstr=doctor.getText().toString().trim();
        String chufangstr=chufang.getText().toString().trim();
        if(intimestr.length()==0){
            showMes("请输入入院时间！");
            return;
        }
        if(outtimestr.length()==0){
            showMes("请输入出院时间！");
            return;
        }
        if(doctorstr.length()==0){
            showMes("请输入主治医生！");
            return;
        }
        if(chufangstr.length()==0){
            showMes("请输入处方信息！");
            return;
        }
        info.setIntime(intimestr);
        info.setOuttime(outtimestr);
        info.setDoctor(doctorstr);
        info.setChufang(chufangstr);
        mpresenter.editHealthinf_Hospital(info.getId(),info.getIntime(),info.getOuttime(),info.getDoctor(),info.getChufang());
        Intent it=new Intent();
        it.putExtra("info",info);
        setResult(RESULT_OK,it);
        finish();

    }

    @BindView(R.id.hospitalmhistory_intime)
    EditText intime;
    @BindView(R.id.hospitalmhistory_outtime) EditText outtime;
    @BindView(R.id.hospitalmhistory_doctor) EditText doctor;
    @BindView(R.id.hospitalmhistory_chufang) EditText chufang;
    public void initData(){
        intime.setText(info.getIntime());
        outtime.setText(info.getOuttime());
        doctor.setText(info.getDoctor());
        chufang.setText(info.getChufang());
    }
}
