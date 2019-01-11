package com.lmq.ui;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.newapp.R;
import com.lmq.base.BaseActivity;
import com.lmq.ui.adapter.SettingsAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class Settings_Message_Activity extends BaseActivity {
    @Override
    protected int setContentView(){
        return R.layout.activity_settings_message;
    }

    @Override
    protected void initBundleData() {

    }

    @Override
    protected void initView() {
        try {

            setTitle("消息提醒");
            initListView();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @BindView(R.id.list)ListView listView;


    public void initListView(){

        final List<String> arr=new ArrayList<>();
        arr.add("训练提醒");
        arr.add("追踪警报");
        arr.add("社区消息");
        SettingsAdapter adapter = new SettingsAdapter(mContext, R.layout.listitem_settings, arr);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String title=arr.get(i);
                switch (title){
                    case "训练提醒":
                        Intent it=new Intent(mContext, MessageList_Activity.class);
                        it.putExtra("type",1);
                        startActivity(it);
                        break;
                    case "追踪警报":
                        Intent it2=new Intent(mContext, MessageList_Activity.class);
                        it2.putExtra("type",2);
                        startActivity(it2);
                        break;
                    case "社区消息":
                        Intent it3=new Intent(mContext, RecentMessage_Activity.class);
                        startActivity(it3);

                        break;

                }


            }
        });
    }


}
