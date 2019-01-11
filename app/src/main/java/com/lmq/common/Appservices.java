package com.lmq.common;


import com.r.http.cn.RHttp;
import com.r.http.cn.callback.HttpCallback;
import com.r.http.cn.callback.UploadCallback;
import com.trello.rxlifecycle2.LifecycleProvider;

import org.json.JSONObject;

import java.io.File;
import java.util.Map;

/**
 * Created by Administrator on 2018/12/25 0025.
 */

public class Appservices {
    private final String LOGIN = "/app/login";
    private final String INITCONTENT="/app/news";
    private final String SHAREEXPLIST="/app/shareexperience_list";//获取所有用户心得列表
    private final String PATIENT_SHAREEXPLIST="/app/patient_shareexperience_list";//获取某一个患者心得列表
    private final String SHAREEXP_ZAN="/app/shareexperience_support";//心得点赞
    private final String SHAREEXP_COMMENT="/app/shareexperience_comment";//心得评论
    private final String SHAREEXP_NEW="/app/shareexperience_new";//发表心得
    private final String SEARCH_PATIENT="/app/search_patient";//搜索患者
    private final String SEARCH_DOCTOR="/app/ssearch_doctor";//搜索医生
    private final String DETAIL_PATIENT="/app/detail_patient";//患者信息详情
    private final String DETAIL_DOCTOR   ="/app/detail_doctor";//医生信息详情
    private final String ADVICE= "/app/advice";//软件意见建议

    private final String MESSAGE= "/app/message";//用户消息列表
    private final String HEALTHINFO= "/app/healthinfo";//用户健康档案
    private final String HEALTHINFO_EDIT_BASE= "/app/healthinfo_editbase";//保存用户健康档案基本信息
    private final String HEALTHINFO_EDIT_HOSPITAL= "/app/healthinfo_edithospital";//保存用户健康档案出入院信息
    private final String HEALTHINFO_DELETE_HOSPITAL= "/app/healthinfo_deletehospital";//删除用户健康档案出入院信息
    private final String HEALTHINFO_EDIT_PROBLEM= "/app/healthinfo_editproblem";//保存用户健康档案病史信息
    private final String HEALTHINFO_DELETE_PROBLEM= "/app/healthinfo_deleteproblem";//删除用户健康档案病史信息



    /**
     * 接口请求
     * @param api 请求方法
     * @param param 参数
     * @param lifecycle
     * @param callback
     */
    public void request(String api,JSONObject param,LifecycleProvider lifecycle, HttpCallback callback){
        try {


            String bodystring=param==null?"":String.valueOf(param);
            RHttp http = new RHttp.Builder()
                    .post()
                    .baseUrl(AppContact.getBaseUrl)
                    .apiUrl(api)
                    .setBodyString(bodystring, true)
                    .lifecycle(lifecycle)
                    .build();

            http.request(callback);
        }catch (Exception e){
            e.printStackTrace();
            callback.onError(1,e.getMessage());
        }
    }

    /**
     * 带文件上传的接口请求
     * @param api 请求方法
     * @param param json参数
     * @param fileMap 文件
     * @param lifecycle
     * @param callback
     */
    public void upload(String api,JSONObject param,Map<String, File> fileMap,LifecycleProvider lifecycle, UploadCallback callback){
        try {

            String bodystring=param==null?"":String.valueOf(param);
            /**
             * 发送请求
             */
            new RHttp.Builder()
                    .post()
                    .baseUrl(AppContact.getBaseUrl)
                    .apiUrl(api)

                    .setBodyString(bodystring, true)

                    .file(fileMap)
                    .lifecycle(lifecycle)
                    .build()
                    .upload(callback);
        }catch (Exception e){
            e.printStackTrace();
            callback.onError(1,e.getMessage());
        }
    }
    public void login(String username, String password, LifecycleProvider lifecycle, HttpCallback callback) {

      //  TreeMap<String, Object> request = new TreeMap<>();
        try {
            JSONObject param = new JSONObject();
            param.put("username", username);
            param.put("password", password);
            request(LOGIN,param,lifecycle,callback);


        }catch (Exception e){
            e.printStackTrace();
            callback.onError(1,e.getMessage());
        }

    }
    public void getinitContent(LifecycleProvider lifecycle, HttpCallback callback){
        request(INITCONTENT,null,lifecycle,callback);



    }

    /**
     * 获取类似患者分享心得列表
     * @param keyword 关键字
     * @param lifecycle
     * @param callback
     */
    public void getexpriencelist(String keyword,LifecycleProvider lifecycle, HttpCallback callback){
        try {
            JSONObject param = new JSONObject();
            param.put("keyword", keyword);
            request(SHAREEXPLIST,param,lifecycle,callback);

        }catch (Exception e){
            e.printStackTrace();
            callback.onError(1,e.getMessage());
        }
    }

    /**
     * 获取某个患者的心得列表
     * @param id_pat 患者id
     * @param lifecycle
     * @param callback
     */
    public void getPatientexpriencelist(String id_pat,LifecycleProvider lifecycle, HttpCallback callback){
        try {
            JSONObject param = new JSONObject();
            param.put("id_pat", id_pat);
            request(PATIENT_SHAREEXPLIST,param,lifecycle,callback);


        }catch (Exception e){
            e.printStackTrace();
            callback.onError(1,e.getMessage());
        }
    }
    /**
     * 心得点赞
     * @param id_ex 心得id
     * @param lifecycle
     * @param callback
     */

    public void supportExp(String id_ex,LifecycleProvider lifecycle, HttpCallback callback){
        try {
            JSONObject param = new JSONObject();
            param.put("id_ex", id_ex);
            request(SHAREEXP_ZAN,param,lifecycle,callback);


        }catch (Exception e){
            e.printStackTrace();
            callback.onError(1,e.getMessage());
        }
    }

    /**
     * 心得评论
     * @param id_ex 心得id
     * @param content 评论内容
     * @param lifecycle
     * @param callback
     */
    public void commentExp(String id_ex,String content,LifecycleProvider lifecycle, HttpCallback callback){
        try {
            JSONObject param = new JSONObject();
            param.put("id_ex", id_ex);
            request(SHAREEXP_COMMENT,param,lifecycle,callback);


        }catch (Exception e){
            e.printStackTrace();
            callback.onError(1,e.getMessage());
        }
    }
    /**
     * 发表心得
     * @param id_pat 用户id
     * @param content 心得内容
     * @param fileMap 上传文件
     * @param lifecycle
     * @param callback
     */
    public void shareEx(String id_pat,String content, Map<String, File> fileMap,LifecycleProvider lifecycle, UploadCallback callback) {
        try {
            JSONObject param = new JSONObject();
            param.put("id_pat", id_pat);
            param.put("cotent", content);
            upload(SHAREEXP_NEW,param,fileMap,lifecycle,callback);

        }catch (Exception e){
            e.printStackTrace();
            callback.onError(1,e.getMessage());
        }
    }

    /**
     * 搜索患者
     * @param keyword 患者关键字
     * @param lifecycle
     * @param callback
     */
    public void searchPatient(String keyword,LifecycleProvider lifecycle, HttpCallback callback){
        try {
            JSONObject param = new JSONObject();
            param.put("keyword", keyword);
            request(SEARCH_PATIENT,param,lifecycle,callback);


        }catch (Exception e){
            e.printStackTrace();
            callback.onError(1,e.getMessage());
        }
    }

    /**
     * 搜索医生
     * @param keyword
     * @param lifecycle
     * @param callback
     */
    public void searchDoctor(String keyword,LifecycleProvider lifecycle, HttpCallback callback){
        try {
            JSONObject param = new JSONObject();
            param.put("keyword", keyword);
            request(SEARCH_DOCTOR,param,lifecycle,callback);

        }catch (Exception e){
            e.printStackTrace();
            callback.onError(1,e.getMessage());
        }
    }

    /**
     * 获取患者详情
     * @param id_pat 患者id
     * @param lifecycle
     * @param callback
     */
    public void getPatientInfo(String id_pat,LifecycleProvider lifecycle, HttpCallback callback){
        try {
            JSONObject param = new JSONObject();
            param.put("id_pat", id_pat);
            request(DETAIL_PATIENT,param,lifecycle,callback);


        }catch (Exception e){
            e.printStackTrace();
            callback.onError(1,e.getMessage());
        }
    }

    /**
     * 获取医生详情
     * @param id_doc 医生id
     * @param lifecycle
     * @param callback
     */
    public void getDoctorInfo(String id_doc,LifecycleProvider lifecycle, HttpCallback callback){
        try {
            JSONObject param = new JSONObject();
            param.put("id_doc", id_doc);
            request(DETAIL_DOCTOR,param,lifecycle,callback);

        }catch (Exception e){
            e.printStackTrace();
            callback.onError(1,e.getMessage());
        }
    }

    /**
     * 软件意见建议
     * @param content 建议内容
     * @param fileMap 图片
     * @param lifecycle
     * @param callback
     */
    public void advice(String content, Map<String, File> fileMap,LifecycleProvider lifecycle, UploadCallback callback) {
        try {
            JSONObject param = new JSONObject();
            param.put("cotent", content);

            upload(ADVICE,param,fileMap,lifecycle,callback);

        }catch (Exception e){
            e.printStackTrace();
            callback.onError(1,e.getMessage());
        }
    }

    /**
     * 获取用户消息
     * @param id_pat 用户id
     * @param type 类别 1训练提醒 2 追踪警报
     * @param lifecycle
     * @param callback
     */
    public void getUserMessage(String id_pat,int type,LifecycleProvider lifecycle, HttpCallback callback){

        try {
            JSONObject param = new JSONObject();
            param.put("id_pat", id_pat);
            param.put("type", type);
            request(MESSAGE,param,lifecycle,callback);
        }catch (Exception e){
            e.printStackTrace();
            callback.onError(1,e.getMessage());
        }
    }

    /**
     * 获取用户健康档案
     * @param id_pat 用户id
     * @param lifecycle
     * @param callback
     */
    public void getUserHealthInfo(String id_pat,LifecycleProvider lifecycle, HttpCallback callback){

        try {
            JSONObject param = new JSONObject();
            param.put("id_pat", id_pat);
            request(HEALTHINFO,param,lifecycle,callback);
        }catch (Exception e){
            e.printStackTrace();
            callback.onError(1,e.getMessage());
        }
    }

    /**
     * 保存用户健康档案基本信息
     * @param id_pat 用户id
     * @param username 姓名
     * @param sex 性别
     * @param age 年龄
     * @param phone 手机号
     * @param height 身高
     * @param weight 体重
     * @param healthinfo 健康状况
     * @param healthproblem 健康问题
     * @param fileMap 头像
     * @param lifecycle
     * @param callback
     */
    public void editHealthinfo_Base(String id_pat,String username,String sex,String age,String phone,String height,String weight,String healthinfo,String healthproblem, Map<String, File> fileMap,LifecycleProvider lifecycle, UploadCallback callback) {
        try {
            JSONObject param = new JSONObject();
            param.put("id_ex", id_pat);
            param.put("username", username);
            param.put("sex", sex);
            param.put("age", age);
            param.put("phone", phone);
            param.put("height", height);
            param.put("weight", weight);
            param.put("healthinfo", healthinfo);
            param.put("healthproblem", healthproblem);



            upload(HEALTHINFO_EDIT_BASE,param,fileMap,lifecycle,callback);

        }catch (Exception e){
            e.printStackTrace();
            callback.onError(1,e.getMessage());
        }
    }

    /**
     * 保存单个出入院记录
     * @param id_hospital 记录id 如果为0则是新增其他为修改
     * @param intime 入院时间
     * @param outtime 出院时间
     * @param doctor 主治医生
     * @param chufang 医生处方
     * @param lifecycle
     * @param callback
     */
    public void editHealthinf_Hospital(String id_hospital,String intime,String  outtime,String doctor,String chufang,LifecycleProvider lifecycle, HttpCallback callback){

        try {
            JSONObject param = new JSONObject();
            param.put("id_hospital", id_hospital);
            param.put("intime", intime);
            param.put("outtime", outtime);
            param.put("doctor", doctor);
            param.put("chufang", chufang);
            request(HEALTHINFO_EDIT_HOSPITAL,param,lifecycle,callback);
        }catch (Exception e){
            e.printStackTrace();
            callback.onError(1,e.getMessage());
        }
    }
    public void deleteHealthinf_Hospital(String id_hospital,LifecycleProvider lifecycle, HttpCallback callback){

        try {
            JSONObject param = new JSONObject();
            param.put("id_hospital", id_hospital);
            request(HEALTHINFO_DELETE_HOSPITAL,param,lifecycle,callback);
        }catch (Exception e){
            e.printStackTrace();
            callback.onError(1,e.getMessage());
        }
    }

    /**
     * 编辑健康档案信息-病史
     * @param id_problem 病史id 如果为0则是新增其他为修改
     * @param time 时间
     * @param name 疾病名称
     * @param result 治疗效果
     * @param beizhu 备注
     * @param lifecycle
     * @param callback
     */
    public void editHealthinfo_Problem(String id_problem,String time,String  name,String result,String beizhu,LifecycleProvider lifecycle, HttpCallback callback){

        try {
            JSONObject param = new JSONObject();
            param.put("id_problem", id_problem);
            param.put("time", time);
            param.put("name", name);
            param.put("result", result);
            param.put("beizhu", beizhu);
            request(HEALTHINFO_EDIT_PROBLEM,param,lifecycle,callback);
        }catch (Exception e){
            e.printStackTrace();
            callback.onError(1,e.getMessage());
        }
    }
    public void deleteHealthinfo_Problem(String id_problem,LifecycleProvider lifecycle, HttpCallback callback){

        try {
            JSONObject param = new JSONObject();
            param.put("id_problem", id_problem);
            request(HEALTHINFO_DELETE_PROBLEM,param,lifecycle,callback);
        }catch (Exception e){
            e.printStackTrace();
            callback.onError(1,e.getMessage());
        }
    }
}
