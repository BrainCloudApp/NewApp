package com.lmq.common;

import com.lmq.base.BaseActivity;
import com.lmq.base.BasePresenter;
import com.lmq.http.CommonAllHttpCallback;
import com.lmq.http.CommonUploadCallback;
import com.r.http.cn.utils.LogUtils;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.io.File;
import java.util.Map;
import java.util.TreeMap;

public class CommonPresenter extends BasePresenter<CommonView, LifecycleProvider> {
    private final String TAG = CommonPresenter.class.getSimpleName();
    public CommonPresenter(CommonView view, LifecycleProvider activity) {
        super(view, activity);
    }
    public CommonAllHttpCallback getCommHttpCallback(){
        if (getView() != null)
            // getView().showLoading();
            ((BaseActivity)getActivity()).showLoading();

        CommonAllHttpCallback httpCallback = new CommonAllHttpCallback<String>() {

            @Override
            public String convert(String data) {
                return data;
                // return new Gson().fromJson(data, String.class);
            }

            @Override
            public void onSuccess(String object) {
                if (getView() != null) {

                    ((BaseActivity)getActivity()).closeLoading();
                    if(object!=null) {

                        getView().onResult(object);
                    }
                }
            }

            @Override
            public void onError(int code, String desc) {
                if (getView() != null) {

                    ((BaseActivity)getActivity()).showError(desc);
                }
            }

            @Override
            public void onCancel() {
                LogUtils.e("请求取消了");
                if (getView() != null) {

                    ((BaseActivity)getActivity()).closeLoading();
                }
            }
        };

            return httpCallback;
    }
    public CommonUploadCallback getUploadHttpCallback(){
        if (getView() != null)
            // getView().showLoading();
            ((BaseActivity)getActivity()).showLoading();
        CommonUploadCallback uploadCallback=  new CommonUploadCallback<String>() {
            @Override
            public String convert(String data) {

                return data;
                //return new Gson().toJson(data);
            }

            @Override
            public void onProgress(File file, long currentSize, long totalSize, float progress, int currentIndex, int totalFile) {
                // progressBar.setProgress((int) (progress * 100));
                //tvIndex.setText("正在上传第:  " + currentIndex + "  张，总共:  " + totalFile + "   " + (progress * 100) + "%");
                // tvResult.setText("正在上传中\n文件名称:" + file.getName() + "\n已上传:" + currentSize + "  总大小:" + totalSize + "\n当前文件序号:" + currentIndex + "  文件总数:" + totalFile);
                //tvResult.setText("正在上传中\n文件名称:" + file.getName() + "\n已上传:" + currentSize + "  总大小:" + totalSize);

            }

            @Override
            public void onSuccess(String value) {
                //tvResult.setText("上传成功\n" + value);
                if (getView() != null) {

                    ((BaseActivity)getActivity()).closeLoading();
                    if(value!=null) {

                        getView().onResult(value);
                    }
                }

            }

            @Override
            public void onError(int code, String desc) {

                if (getView() != null) {

                    ((BaseActivity)getActivity()).showError(desc);
                }
            }

            @Override
            public void onCancel() {

                if (getView() != null) {

                    ((BaseActivity)getActivity()).closeLoading();
                }
            }
        };
        return uploadCallback;
    }
    public void getPatientExprienceList(String id_pat) {

        new Appservices().getPatientexpriencelist(id_pat,getActivity(), getCommHttpCallback());

    }
    public void getExprienceList(String keyword) {
        new Appservices().getexpriencelist(keyword,getActivity(), getCommHttpCallback());

    }
    public void getDoctorInfo(String id_doc) {

        new Appservices().getDoctorInfo(id_doc,getActivity(),  getCommHttpCallback());

    }
    public void getPersonInfo(String id_pat) {

        new Appservices().getPatientInfo(id_pat,getActivity(), getCommHttpCallback());

    }
    public void advice(String content,File uploadfile){

        Map<String, File> fileMap = new TreeMap<>();
        fileMap.put("img", uploadfile);
        new Appservices().advice(content,fileMap,getActivity(), getUploadHttpCallback());
    }
    public void share(String id_pat,String content,File uploadfile){

        Map<String, File> fileMap = new TreeMap<>();
        fileMap.put("img", uploadfile);

        new Appservices().shareEx(id_pat,content,fileMap,getActivity(), getUploadHttpCallback());
    }
    public void share_comment(String id_ex,String content){

        new Appservices().commentExp(id_ex,content,getActivity(), getUploadHttpCallback());
    }
    public void searchDoctor(String keyword) {



        new Appservices().searchDoctor(keyword,getActivity(), getCommHttpCallback());

    }
    public void searchPatient(String keyword) {



        new Appservices().searchPatient(keyword,getActivity(), getCommHttpCallback());

    }
    public void getUserMessage(String id_pat,int type) {



        new Appservices().getUserMessage(id_pat,type,getActivity(), getCommHttpCallback());

    }
    public void getUserHealthInfo(String id_pat) {


        new Appservices().getUserHealthInfo(id_pat,getActivity(), getCommHttpCallback());

    }
    public void editHealthinfo_Base(String id_pat,String username,String sex,String age,String phone,String height,String weight,String healthinfo,String healthproblem, File uploadfile) {



        Map<String, File> fileMap = new TreeMap<>();
        if(uploadfile!=null)
        fileMap.put("img", uploadfile);


        new Appservices().editHealthinfo_Base( id_pat, username, sex, age, phone, height, weight, healthinfo, healthproblem, fileMap,getActivity(), getUploadHttpCallback());
    }
    public void editHealthinf_Hospital(String id_hospital,String intime,String  outtime,String doctor,String chufang) {


        new Appservices().editHealthinf_Hospital( id_hospital, intime,  outtime, doctor, chufang,getActivity(), getCommHttpCallback());

    }
    public void deleteHealthinf_Hospital(String id_hospital) {




        new Appservices().deleteHealthinf_Hospital( id_hospital, getActivity(), getCommHttpCallback());

    }
    public void editHealthinfo_Problem(String  id_problem,String time,String  name,String result,String beizhu) {


        new Appservices().editHealthinfo_Problem( id_problem, time,  name, result, beizhu,getActivity(), getCommHttpCallback());

    }
    public void deleteHealthinfo_Problem(String id_problem) {




        new Appservices().deleteHealthinfo_Problem( id_problem, getActivity(), getCommHttpCallback());

    }
    public void addMypatient(String id_pat) {




        new Appservices().addMypatient( id_pat, getActivity(), getCommHttpCallback());

    }
    public void getMypatient(String id_u) {




        new Appservices().getMypatients( id_u, getActivity(), getCommHttpCallback());

    }
    }
