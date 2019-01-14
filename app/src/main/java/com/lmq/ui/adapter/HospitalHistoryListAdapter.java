package com.lmq.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.newapp.R;
import com.lmq.ui.entity.HospitalHistory;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/12/28 0028.
 */

public class HospitalHistoryListAdapter extends RecyclerView.Adapter {


    private static final String TAG = HospitalHistoryListAdapter.class.getSimpleName();
    private ArrayList<HospitalHistory> source;


    public HospitalHistoryListAdapter(ArrayList<HospitalHistory> list) {
        this.source = list;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {


            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listitem_health_hospitalhistory_item, null);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            view.setLayoutParams(lp);
            return new MyHolder(view);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        try {

            MyHolder holder = (MyHolder) viewHolder;

                holder.intime.setText("入院时间："+source.get(position).getIntime());
                holder.outtime.setText("出院时间："+source.get(position).getOuttime());
                holder.doctor.setText("主治医生："+source.get(position).getDoctor());
                 holder.chufang.setText("医生处方："+source.get(position).getChufang());


        }catch (Exception e){
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return source.size();
    }

    class MyHolder extends RecyclerView.ViewHolder
    {
        public int position;
        public MyHolder(View view){
            super(view);
            ButterKnife.bind(this,view);
        }

        @BindView(R.id.hospitalmhistory_intime) TextView intime;
        @BindView(R.id.hospitalmhistory_outtime) TextView outtime;
        @BindView(R.id.hospitalmhistory_doctor) TextView doctor;
        @BindView(R.id.hospitalmhistory_chufang) TextView chufang;

    }

}