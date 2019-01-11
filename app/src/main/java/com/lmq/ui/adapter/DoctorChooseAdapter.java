package com.lmq.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.newapp.R;
import com.lmq.ui.entity.Doctor;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2018/12/28 0028.
 */

public class DoctorChooseAdapter extends RecyclerView.Adapter {
   public static interface OnRecyclerViewListener {
        //void onHeadClick(int position);
      //  void onDianZanClick(int position);
      //  void onPinglunClick(int position);
     //   void search(String keyworkd);
        void showTip(String mes);
       //void onItemClick(int position);
    }

    private OnRecyclerViewListener onRecyclerViewListener;
   private boolean isSearch=false;

    public void setOnRecyclerViewListener(OnRecyclerViewListener onRecyclerViewListener) {
        this.onRecyclerViewListener = onRecyclerViewListener;
    }

    private static final String TAG = DoctorChooseAdapter.class.getSimpleName();
    private ArrayList<Doctor> source;
    private ArrayList<Doctor> tempsource=new ArrayList<>();
    private ArrayList<String> chooselist=new ArrayList<>();
    private Context mcontext;

    public DoctorChooseAdapter(ArrayList<Doctor> list, Context mcontext) {
        this.source = list;
        this.mcontext=mcontext;
        if(source==null)
            source=new ArrayList<>();
    }
    public ArrayList<String> getChooselist(){
        return chooselist;
    }
    public void chooseItem(String name){
        if(!chooselist.contains(name)) {//没有在列表中则选中
            chooselist.add(name);
            this.notifyDataSetChanged();
        }else{
            //已经选中则移除
            chooselist.remove(name);
            this.notifyDataSetChanged();
        }
    }
    public void removeItem(String name){
        chooselist.remove(name);
    }
    public void refreshData(ArrayList<Doctor> source){
        this.source=source;
        this.notifyDataSetChanged();
    }
    public void searchName(String name){
        tempsource=new ArrayList<>();
        for(int i=0;i<source.size();i++){
            if(source.get(i).getName().contains(name))
                tempsource.add(source.get(i));
        }
        if(tempsource.size()>0)
            isSearch=true;
        this.notifyDataSetChanged();

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {


            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listitem_person, null);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            view.setLayoutParams(lp);
            return new PersonHolder(view);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {

        try {


                final PersonHolder holder = (PersonHolder) viewHolder;
              //  holder.searchlinear.setVisibility(View.GONE);
                holder.contentlinear.setVisibility(View.VISIBLE);
                holder.position = i-1;

                holder.img.setImageResource(R.drawable.user1);
                holder.username.setText(source.get(i).getName());
                if(chooselist.contains(source.get(i).getName())){
                    holder.chosestatus.setBackgroundResource(R.drawable.nim_contact_checkbox_checked_green);
                }else{

                    holder.chosestatus.setBackgroundResource(R.drawable.nim_contact_checkbox_unchecked);
                }
                holder.chosestatus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        chooseItem(source.get(i).getName());
                    }
                });
                holder.contentlinear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       /* if(onRecyclerViewListener!=null)
                        onRecyclerViewListener.onItemClick( holder.position);*/
                        chooseItem(source.get(i).getName());
                    }
                });


        }catch (Exception e){
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return source.size();
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

       // @BindView(R.id.linear_search)LinearLayout searchlinear;
        @BindView(R.id.linear_content)LinearLayout  contentlinear;


        @BindView(R.id.chosestatus) TextView chosestatus;

        @Override
        public void onClick(View v) {
          /*  if (null != onRecyclerViewListener) {
                onRecyclerViewListener.onItemClick(position);
            }*/
        }


    }

}