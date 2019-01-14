package com.lmq.ui.adapter;

import android.content.Context;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.newapp.R;
import com.lmq.ui.entity.HealthInfo;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2018/12/28 0028.
 */

public class UserHealthInfoAdapter extends RecyclerView.Adapter {
    private static  final  int TYPE_BASE=0;//基本信息
    private static  final  int TYPE_HOSPITAL=1;//住院记录
    private static  final  int TYPE_PROBLEM=2;//病史
   public static interface OnRecyclerViewListener {
       void onBaseEdit();//基本信息编辑
       void onHospitalEdit();//出入院记录编辑
       void onProblemEdit();//病史编辑
      /* void onDianZanClick(int position);
        void onPinglunClick(int position);*/
       // void search(String keyworkd);
      //  void showTip(String mes);
    }

    private OnRecyclerViewListener onRecyclerViewListener;

    public void setOnRecyclerViewListener(OnRecyclerViewListener onRecyclerViewListener) {
        this.onRecyclerViewListener = onRecyclerViewListener;
    }

    private static final String TAG = UserHealthInfoAdapter.class.getSimpleName();
    private HealthInfo info;
    private Context mcontext;

    public UserHealthInfoAdapter(HealthInfo info, Context mcontext) {
        this.info = info;
        this.mcontext=mcontext;
    }

    @Override
    public int getItemViewType(int position) {

       // return super.getItemViewType(position);

        return position;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

            if(viewType==TYPE_BASE) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listitem_health_base, null);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                view.setLayoutParams(lp);
                return new BaseHolder(view);
           }
            else if(viewType==TYPE_HOSPITAL) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listitem_health_hospitalhistory, null);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                view.setLayoutParams(lp);
                return new HospitalHolder(view);
            }
           else{
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listitem_health_problemhistory, null);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                view.setLayoutParams(lp);
                return new ProblemHolder(view);
            }
         //   return null;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        try {
                 if(viewHolder instanceof BaseHolder){
                     BaseHolder holder = (BaseHolder) viewHolder;
                     holder.img.setImageResource(Integer.valueOf(info.getBase().getImg()));
                     holder.username.setText("姓名："+info.getBase().getName());
                     holder.user_sex.setText("性别："+info.getBase().getSex());
                     holder.user_birth.setText("年龄："+info.getBase().getAge());
                     holder.user_height.setText("身高："+info.getBase().getHeight());
                     holder.user_weight.setText("体重："+info.getBase().getWeight());
                     holder.user_phone.setText("联系电话："+info.getBase().getPhone());
                     holder.user_healthinfo.setText("当前健康状况:"+info.getBase().getHealthstatus());
                     holder.user_healthproblem.setText("当前健康问题："+info.getBase().getHealthproblem());
                     holder.edit.setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View v) {
                             if(onRecyclerViewListener!=null)
                             onRecyclerViewListener.onBaseEdit();
                         }
                     });
                 }
                 else  if(viewHolder instanceof HospitalHolder){
                     HospitalHolder holder = (HospitalHolder) viewHolder;
                     RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mcontext);
                     holder.list.setLayoutManager(layoutManager);
                     HospitalHistoryListAdapter sa=new HospitalHistoryListAdapter(info.getHealthhospitals());
                     holder.list.setAdapter(sa);

                     DividerItemDecoration divider = new DividerItemDecoration(mcontext,DividerItemDecoration.VERTICAL);
                     //  SpacesItemDecoration sp=new SpacesItemDecoration(2);
                     holder.list.addItemDecoration(divider);
                     holder.edit.setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View v) {
                             if(onRecyclerViewListener!=null)
                                 onRecyclerViewListener.onHospitalEdit();
                         }
                     });
                 }
                 else  if(viewHolder instanceof ProblemHolder){
                     ProblemHolder holder = (ProblemHolder) viewHolder;
                     RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mcontext);
                     holder.list.setLayoutManager(layoutManager);
                     ProblemListAdapter sa=new ProblemListAdapter(info.getHealthProblems());
                     holder.list.setAdapter(sa);

                     DividerItemDecoration divider = new DividerItemDecoration(mcontext,DividerItemDecoration.VERTICAL);
                   //  SpacesItemDecoration sp=new SpacesItemDecoration(2);
                     holder.list.addItemDecoration(divider);
                     holder.edit.setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View v) {
                             if(onRecyclerViewListener!=null)
                                 onRecyclerViewListener.onProblemEdit();
                         }
                     });
                 }


        }catch (Exception e){
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return 3;
    }

   class BaseHolder extends RecyclerView.ViewHolder{
        public BaseHolder(View view){
            super(view);
            ButterKnife.bind(this,view);
        }
       @BindView(R.id.user_image)CircleImageView img;
       @BindView(R.id.username) TextView username;
       @BindView(R.id.user_sex) TextView user_sex;
       @BindView(R.id.user_birth) TextView user_birth;
       @BindView(R.id.user_healthinfo) TextView user_healthinfo;
       @BindView(R.id.user_phone) TextView user_phone;
       @BindView(R.id.user_healthproblem) TextView user_healthproblem;
       @BindView(R.id.user_height) TextView user_height;
       @BindView(R.id.user_weight) TextView user_weight;
       @BindView(R.id.edit) TextView edit;

   }
    class ProblemHolder extends RecyclerView.ViewHolder{
        public ProblemHolder(View view){
            super(view);
            ButterKnife.bind(this,view);
        }
        @BindView(R.id.recyclerView)RecyclerView list;
        @BindView(R.id.edit) TextView edit;

    }
    class HospitalHolder extends RecyclerView.ViewHolder{
        public HospitalHolder(View view){
            super(view);
            ButterKnife.bind(this,view);
        }
        @BindView(R.id.recyclerView)RecyclerView list;
        @BindView(R.id.edit) TextView edit;

    }



}