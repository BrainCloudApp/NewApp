package com.lmq.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.newapp.R;
import com.lmq.ui.entity.Patient;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2018/12/28 0028.
 */

public class PatientHealthAdapter extends RecyclerView.Adapter {
   public static interface OnRecyclerViewListener {

       void onItemClick(int position);
    }

    private OnRecyclerViewListener onRecyclerViewListener;

    public void setOnRecyclerViewListener(OnRecyclerViewListener onRecyclerViewListener) {
        this.onRecyclerViewListener = onRecyclerViewListener;
    }

    private static final String TAG = PatientHealthAdapter.class.getSimpleName();
    private ArrayList<Patient> source;
    private Context mcontext;

    public PatientHealthAdapter(ArrayList<Patient> list, Context mcontext) {
        this.source = list;
        this.mcontext=mcontext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {


            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listitem_patient_health, null);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            view.setLayoutParams(lp);
            return new PersonHolder(view);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {

        try {

                final PersonHolder holder = (PersonHolder) viewHolder;

                holder.contentlinear.setVisibility(View.VISIBLE);
                holder.position = i;
                final  int position=i;
                holder.img.setImageResource(R.drawable.user1);
                holder.username.setText(source.get(i).getName());
               /* holder.gochat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (null != onRecyclerViewListener) {
                            onRecyclerViewListener.onChatClick(position);
                        }
                    }
                });*/

             holder.contentlinear.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     if (null != onRecyclerViewListener) {
                         onRecyclerViewListener.onItemClick(position);
                     }
                 }
             });


        }catch (Exception e){
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return source.size()+1;
    }


    class PersonHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public int position;
        public PersonHolder(View view){
            super(view);
            ButterKnife.bind(this,view);
        }
        @BindView(R.id.username) TextView username;
        @BindView(R.id.user_image)CircleImageView img;

        @BindView(R.id.linear_content)LinearLayout  contentlinear;
        @BindView(R.id.gochat) TextView gochat;/*
        @BindView(R.id.gophone) TextView gophone;*/

        @Override
        public void onClick(View v) {
            if (null != onRecyclerViewListener) {
                onRecyclerViewListener.onItemClick(position);
            }
        }


    }

}