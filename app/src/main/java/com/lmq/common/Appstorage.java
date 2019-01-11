package com.lmq.common;

import android.content.Context;

import com.ddmeng.preferencesprovider.provider.PreferencesStorageModule;
import com.example.newapp.News;
import com.lmq.tool.LmqTool;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/12/25 0025.
 */


//test modify the same file.

public class Appstorage  {
    //xxxx 修改时间：2018-12-27-17：11


    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        Appstorage.context = context.getApplicationContext();
    }

    private static Context context;

    public static PreferencesStorageModule myModule=null;
    public static void initModel(Context context){
        if(myModule==null)
            myModule = new PreferencesStorageModule(context, "appModule");
    }

    public static void setRememberPsdState(Context context, boolean isRememberPsd){
        initModel(context);
        myModule.put("user.isRememberPsd", isRememberPsd);
    }

    public static boolean getRememberPsdState(Context context){
        initModel(context);
        return myModule.getBoolean("user.isRememberPsd", false);
    }

    public static void setLoginUsernameAndPwd(Context context,String username,String pwd){

        initModel(context);
        myModule.put("user.username", username);
        myModule.put("user.pwd_"+username, pwd);//密码需要加密保存，与服务端协商，AES

    }

    public static String getLoginUserName(Context context){

        initModel(context);
        return myModule.getString("user.username", "");
    }
    public static void setLoginUserPwd(Context context,String username,String pwd){

        initModel(context);

        myModule.put("user.pwd_"+username, pwd);
    }
    public static String getLoginUserPwd(Context context,String username){

        return myModule.getString("user.pwd_"+username, "");
    }
    //登陆状态, true为已登陆,false为未登陆.需要跳转到登陆页面
    public  static void setLoginState(Context mcontext,boolean islogin){
        initModel(mcontext);
        myModule.put("user.loginstate",islogin);
    }
    public static boolean getLoginState(Context mcontext){
        initModel(mcontext);
        return myModule.getBoolean("user.loginstate",false);
    }

   public static void saveNewsList(Context mcontext, String newsStr){
       initModel(mcontext);
       myModule.put("newsStr", newsStr);
   }

   public static ArrayList<News> getNewsList(Context mcontext){
       initModel(mcontext);
       String newsData = myModule.getString("newsStr","");
       if (newsData.length() == 0) return null;
       ArrayList<News> arrayList = LmqTool.jsonToArrayList(newsData, News.class);
       return arrayList;
   }
    public  static void setMessageState(Context mcontext,boolean ison){
        initModel(mcontext);
        myModule.put("user.messagestate",ison);
    }
    public static boolean getMessageState(Context mcontext){
        initModel(mcontext);
        return myModule.getBoolean("user.messagestate",false);
    }
    public  static void setIMUser_Account(Context mcontext,String acc,String pwd){
        initModel(mcontext);
        myModule.put("user.imaccount",acc);
        myModule.put("user.impwd",pwd);
    }
    public static String getIMUser_Account_Acc(Context mcontext){
        initModel(mcontext);
        return myModule.getString("user.imaccount","");
    }
    public static  String getAccount(Context mcontext){
        initModel(mcontext);
        return myModule.getString("user.imaccount","");
    }
    public static String getIMUser_Account_Pwd(Context mcontext){
        initModel(mcontext);
        return myModule.getString("user.impwd","");
    }




    public static void saveList(Context mcontext, String listName, String ListStr){
        initModel(mcontext);
        myModule.put(listName, ListStr);
    }

    public static <T> ArrayList<T> getList(Context mcontext, Class<T> clazz, String listName){
        initModel(mcontext);
        String newsData = myModule.getString(listName,"");
        if (newsData.length() == 0) return null;
        ArrayList<T> arrayList = LmqTool.jsonToArrayList(newsData, clazz);
        return arrayList;
    }

    public static void setStr(Context mcontext, String strKey, String str){
        initModel(mcontext);
        myModule.put(strKey, str);
    }






    public static String getStr(Context mcontext, String strKey){
        initModel(mcontext);
        return myModule.getString(strKey, "");
    }





}
