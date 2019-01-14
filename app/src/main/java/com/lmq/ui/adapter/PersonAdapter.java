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
import com.lmq.ui.entity.Person;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2018/12/28 0028.
 */

public class PersonAdapter extends RecyclerView.Adapter {
   public static interface OnRecyclerViewListener {
        //void onHeadClick(int position);
      //  void onDianZanClick(int position);
      //  void onPinglunClick(int position);
        void search(String keyworkd);
        void showTip(String mes);
       void onItemClick(int position);
    }

    private OnRecyclerViewListener onRecyclerViewListener;

    public void setOnRecyclerViewListener(OnRecyclerViewListener onRecyclerViewListener) {
        this.onRecyclerViewListener = onRecyclerViewListener;
    }

    private static final String TAG = PersonAdapter.class.getSimpleName();
    private ArrayList<Person> source;
    private Context mcontext;

    public PersonAdapter(ArrayList<Person> list, Context mcontext) {
        this.source = list;
        this.mcontext=mcontext;
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
            if(i==0) {
                final PersonHolder holder = (PersonHolder) viewHolder;
                holder.searchlinear.setVisibility(View.VISIBLE);
                holder.contentlinear.setVisibility(View.GONE);

                holder.keyword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if (actionId == EditorInfo.IME_ACTION_SEARCH||actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {//EditorInfo.IME_ACTION_SEARCH、EditorInfo.IME_ACTION_SEND等分别对应EditText的imeOptions属性
                            //TODO回车键按下时要执行的操作
                            if (TextUtils.isEmpty(holder.keyword.getText().toString().trim())){

                                onRecyclerViewListener.showTip("请输入搜索关键字！");
                                return true;
                            }else {
                                onRecyclerViewListener.search(holder.keyword.getText().toString().trim());
                            }
                            return true;
                        }
                        return false;
                    }
                });


                if(holder.keyword.getText().toString().trim().length()>0){
                    holder.cancle.setVisibility(View.VISIBLE);
                }else
                    holder.cancle.setVisibility(View.GONE);

            }else {

                final PersonHolder holder = (PersonHolder) viewHolder;
                holder.searchlinear.setVisibility(View.GONE);
                holder.contentlinear.setVisibility(View.VISIBLE);
                holder.position = i-1;

                holder.img.setImageResource(R.drawable.user1);
                holder.username.setText(source.get(i - 1).getName());
                holder.contentlinear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onRecyclerViewListener.onItemClick(holder.position);
                    }
                });
            }

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

        @BindView(R.id.linear_search)LinearLayout searchlinear;
        @BindView(R.id.linear_content)LinearLayout  contentlinear;

        @BindView(R.id.keyword)EditText keyword;
        @BindView(R.id.cancle)Button cancle;
        @Override
        public void onClick(View v) {
            if (null != onRecyclerViewListener) {
                onRecyclerViewListener.onItemClick(position);
            }
        }


    }

}