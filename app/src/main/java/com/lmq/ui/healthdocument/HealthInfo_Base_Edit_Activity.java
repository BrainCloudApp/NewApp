package com.lmq.ui.healthdocument;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bumptech.glide.Glide;
import com.example.newapp.R;
import com.lmq.base.BaseActivity;
import com.lmq.common.CommonPresenter;
import com.lmq.common.CommonView;
import com.lmq.tool.DialogItem;
import com.lmq.tool.FileCache;
import com.lmq.tool.LmqTool;
import com.lmq.tool.PermisstionCheck;
import com.lmq.ui.entity.Health_Base;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class HealthInfo_Base_Edit_Activity extends BaseActivity implements CommonView {
    CommonPresenter mpresenter=new CommonPresenter(this,this);
    String selectedsex="";
    Health_Base info;

    private static final int ACTIVITYRESULT_CHOSE_SYSIMG=2;
    private static final int ACTIVITYRESULT_CHOSE_TAKEIMG=3;
    private static final int ACTIVITYRESULT_CUTIMG=4;
    private File picpath,uploadFile;
    private Uri muri;
    private boolean haspermission=false;
    @Override
    protected int setContentView(){
        return R.layout.activity_health_base_edit;
    }

    @Override
    protected void initBundleData() {

    }

    @Override
    protected void initView() {
        try {
            setTitle("健康档案编辑");
            info = (Health_Base) getIntent().getSerializableExtra("info");
            if (info == null)
                info = new Health_Base();
            initData();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT >= 23) {
            haspermission=   PermisstionCheck.checkAndRequestPermission(HealthInfo_Base_Edit_Activity.this) ;
        }else{
            haspermission=true;
        }
    }
    @Override
    public void onResult(String result) {
        showMes("保存成功！");
        Intent it=new Intent();
        it.putExtra("data",info);
        setResult(RESULT_OK,it);
        finish();
    }
    @OnClick(R.id.action)
    public void save(){//保存
        String usernamestr=username.getText().toString().trim();
        String user_birthstr=user_birth.getText().toString().trim();
        String user_healthinfostr=user_healthinfo.getText().toString().trim();
        String user_phonestr=user_phone.getText().toString().trim();
        String user_healthproblemstr=user_healthproblem.getText().toString().trim();
        String user_heightstr=user_height.getText().toString().trim();
        String user_weightstr=user_weight.getText().toString().trim();
        if(usernamestr.length()==0){
            showMes("请输入姓名！");
            return;
        }
        if(selectedsex.length()==0){
            showMes("请选择性别！");
            return;
        }if(user_birthstr.length()==0){
            showMes("请输入年龄！");
            return;
        }if(user_healthinfostr.length()==0){
            showMes("请输入当前健康状况！");
            return;
        }if(user_phonestr.length()==0){
            showMes("请输入手机号！");
            return;
        }if(user_healthproblemstr.length()==0){
            showMes("请输入当前健康问题！");
            return;
        }if(user_heightstr.length()==0){
            showMes("请输入身高！");
            return;
        }
       if(user_weightstr.length()==0){
        showMes("请输入体重！");
        return;
        }
        info.setName(usernamestr);
        info.setSex(selectedsex);
        info.setAge(user_birthstr);
        info.setPhone(user_phonestr);
        info.setHeight(user_heightstr);
        info.setWeight(user_weightstr);
        info.setHealthstatus(user_healthinfostr);
        info.setHealthproblem(user_healthproblemstr);

        Intent it=new Intent();
        it.putExtra("data",info);
        setResult(RESULT_OK,it);
        finish();
     //   mpresenter.editHealthinfo_Base(info.getId(),usernamestr,selectedsex,user_birthstr,user_phonestr,user_heightstr,user_weightstr,user_healthinfostr,user_healthproblemstr,uploadFile);
    }
    @OnClick(R.id.user_image)
    public void  choseImg(){
        //选择头像
        if(haspermission)
            showChoseImg();
        else{
            showMes("请允许App所需要的权限！");
            if (Build.VERSION.SDK_INT >= 23) {
                haspermission = PermisstionCheck.checkAndRequestPermission(HealthInfo_Base_Edit_Activity.this);
            }
        }
    }
    @BindView(R.id.user_image)CircleImageView img;
    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.user_sex)
    RadioGroup user_sex;
    @BindView(R.id.user_birth) EditText user_birth;
    @BindView(R.id.user_healthinfo) EditText user_healthinfo;
    @BindView(R.id.user_phone) EditText user_phone;
    @BindView(R.id.user_healthproblem) EditText user_healthproblem;
    @BindView(R.id.user_height) EditText user_height;
    @BindView(R.id.user_weight) EditText user_weight;
    @BindView(R.id.male)RadioButton mal;
    @BindView(R.id.female)RadioButton female;
    public void inisex(){
        user_sex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.male){
                    //男
                    selectedsex="男";
                }else{
                    //女
                    selectedsex="女";
                }
            }
        });
        String sex=info.getSex();
        if(sex==null||sex.length()==0||sex.equalsIgnoreCase("男")){
            mal.setChecked(true);
        }else{
            female.setChecked(true);
        }

    }
    public void initData(){
        img.setImageResource(Integer.valueOf(info.getImg()));
        inisex();
        username.setText(info.getName());
        user_birth.setText(info.getAge());
        user_height.setText(info.getHeight());
        user_weight.setText(info.getWeight());
        user_phone.setText(info.getPhone());
        user_healthinfo.setText(info.getHealthstatus());
        user_healthproblem.setText(info.getHealthproblem());
    }

    public void showChoseImg(){
        ArrayList<DialogItem> items = new ArrayList<DialogItem>();
        //items.add(new DialogItem("请选择操作", R.layout.custom_dialog_title));
        items.add(new DialogItem("拍照", R.layout.custom_dialog_normal2){
            @Override
            public void onClick() {
                // TODO Auto-generated method stub
                Intent it = new Intent("android.media.action.IMAGE_CAPTURE");
                FileCache fc=new FileCache(mContext);

                String filepath =fc.getFileName("imagTemp");

                String date = (new SimpleDateFormat("MMddhhmmss")).format(new java.util.Date());
                //   thepicpath= filepath+"MyDownload/"+date+"_img.jpg";
                picpath = new File(filepath+date+"_img.jpg");
                it.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(picpath));
                startActivityForResult(it, ACTIVITYRESULT_CHOSE_TAKEIMG);
            }
        });
        items.add(new DialogItem("相册选取", R.layout.custom_dialog_normal2){

            @Override
            public void onClick() {
                // TODO Auto-generated method stub
                Intent intent = new Intent(Intent.ACTION_PICK,null);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, ACTIVITYRESULT_CHOSE_SYSIMG);
            }

        });

        items.add(new DialogItem("取消", R.layout.custom_dialog_normal2));

        LmqTool.createCustomDialog(mContext, items, R.style.CustomDialogNew);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //
        if (resultCode == Activity.RESULT_OK) {
            if(requestCode==ACTIVITYRESULT_CHOSE_SYSIMG){
                muri=null;
                if(data==null)
                    return;
                muri= data.getData();

                startPicCut(muri);

            }

            else if(requestCode==ACTIVITYRESULT_CHOSE_TAKEIMG){
                muri=null;
                if(picpath!=null&&picpath.length()>0){
                    muri=Uri.fromFile(picpath);
                    startPicCut(muri);
                    //startPicCut(muri);
                    //  asyncUpload(picpath);
                }
            }
            else if(requestCode==ACTIVITYRESULT_CUTIMG){
                SaveBitMap(data);
            }

        }

    }
    /**
     * 裁剪图片的方法
     * @param uri
     */
    public void startPicCut(Uri uri){
        Intent intentCarema = new Intent("com.android.camera.action.CROP");
        intentCarema.setDataAndType(uri, "image/*");
        intentCarema.putExtra("crop", true);
        //intentCarema.putExtra("scale", false);
        //intentCarema.putExtra("noFaceDetection", true);//不需要人脸识别功能
        //intentCarema.putExtra("circleCrop", "");//设定此方法选定区域会是圆形区域
        //aspectX aspectY是宽高比例
        if(Build.MODEL.contains("HUAWEI"))
        {//华为特殊处理 不然会显示圆
            intentCarema.putExtra("aspectX", 9998);
            intentCarema.putExtra("aspectY", 9999);
        }
        else
        {
            intentCarema.putExtra("aspectX", 1);
            intentCarema.putExtra("aspectY", 1);
        }

        //outputX outputY	是裁剪图片的宽高
        intentCarema.putExtra("outputX", 300);
        intentCarema.putExtra("outputY", 300);
        intentCarema.putExtra("return-data", true);
        startActivityForResult(intentCarema, ACTIVITYRESULT_CUTIMG);
    }

    /**
     * 保存裁剪后的图片
     * @param picData
     */
    private void SaveBitMap(Intent picData){
        Bundle bundle = picData.getExtras();
        if(bundle != null){
            Bitmap photo = bundle.getParcelable("data");
            FileCache fc=new FileCache(mContext);

            String filepath =fc.getFileName("imagTemp");

            String date = (new SimpleDateFormat("MMddhhmmss")).format(new java.util.Date());
            String	mfilepath = filepath+date+"_img.jpg";
            File picpath = new File(mfilepath);
            try{
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(picpath));
                photo.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            }catch(IOException e){
                e.printStackTrace();
            }
            uploadFile =picpath;
            // addimg.setBackground(new BitmapDrawable());
            Glide.with(this).load(uploadFile).into(img);
            //asyncUpload(uploadFile);
            // 上传文件
            // upload_Photo(uploadFile);

        }
    }
}
