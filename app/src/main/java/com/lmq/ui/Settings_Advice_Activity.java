package com.lmq.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.newapp.R;
import com.lmq.base.BaseActivity;
import com.lmq.common.CommonPresenter;
import com.lmq.common.CommonView;
import com.lmq.tool.DialogItem;
import com.lmq.tool.FileCache;
import com.lmq.tool.LmqTool;
import com.lmq.tool.PermisstionCheck;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class Settings_Advice_Activity extends BaseActivity implements CommonView {

    CommonPresenter mpresenter=new CommonPresenter(this,this);
    private static final int ACTIVITYRESULT_CHOSE_SYSIMG=2;
    private static final int ACTIVITYRESULT_CHOSE_TAKEIMG=3;
    private static final int ACTIVITYRESULT_CUTIMG=4;
    private File picpath,uploadFile;
    private Uri muri;
    private boolean haspermission=false;

    @Override
    protected int setContentView(){
        return R.layout.activity_settings_advice;
    }

    @Override
    protected void initBundleData() {

    }

    @Override
    protected void initView() {
        try {

            setTitle("意见建议");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT >= 23) {
            haspermission=   PermisstionCheck.checkAndRequestPermission(Settings_Advice_Activity.this) ;
        }else{
            haspermission=true;
        }
    }

    @BindView(R.id.inputtxt)EditText inputtxt;
    @BindView(R.id.addimg)ImageView addimg;

    @OnClick(R.id.action)//
    public void send(){//发送请求
        String mes=inputtxt.getText().toString().trim();
        if(mes.length()==0){
            showMes("请输入您对本软件的意见和建议！");
            return;
        }

        //发送请求
        mpresenter.advice(mes,uploadFile);

    }
    @Override
    public  void onResult(String result) {
        showMes("提交成功！");
        finish();
    }
    @OnClick(R.id.addimg)
    public void choseimg(){
        //选择图片
        if(haspermission)
        showChoseImg();
        else{
            showMes("请允许App所需要的权限！");
            if (Build.VERSION.SDK_INT >= 23) {
                haspermission = PermisstionCheck.checkAndRequestPermission(Settings_Advice_Activity.this);
            }
        }
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
            Glide.with(this).load(uploadFile).into(addimg);
            //asyncUpload(uploadFile);
            // 上传文件
            // upload_Photo(uploadFile);

        }
    }
}
