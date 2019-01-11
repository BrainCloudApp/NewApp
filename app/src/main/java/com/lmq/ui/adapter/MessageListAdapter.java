package com.lmq.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.newapp.R;
import com.lmq.ui.entity.AppMessage;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/12/28 0028.
 */

public class MessageListAdapter extends RecyclerView.Adapter {


    private static final String TAG = MessageListAdapter.class.getSimpleName();
    private ArrayList<AppMessage> source;
    private Context mcontext;

    public MessageListAdapter(ArrayList<AppMessage> list, Context mcontext) {
        this.source = list;
        this.mcontext=mcontext;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {


            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listitem_message, null);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            view.setLayoutParams(lp);
            return new MessageHolder(view);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        try {

            MessageHolder holder = (MessageHolder) viewHolder;

                holder.time.setText(source.get(position).getTime());
                holder.title.setText(source.get(position).getTitle());
                 holder.content.setText(source.get(position).getContent());


        }catch (Exception e){
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return source.size();
    }

    class MessageHolder extends RecyclerView.ViewHolder
    {
        public int position;
        public MessageHolder(View view){
            super(view);
            ButterKnife.bind(this,view);
        }

        @BindView(R.id.item_time) TextView time;
        @BindView(R.id.item_title) TextView title;
        @BindView(R.id.item_content) TextView content;

    }

}