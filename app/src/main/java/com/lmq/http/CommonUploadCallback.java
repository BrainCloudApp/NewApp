package com.lmq.http;

import com.r.http.cn.callback.UploadCallback;

import java.io.File;

/**
 * 根据业务进一步封装
 *
 * @author ZhongDaFeng
 */
public abstract class CommonUploadCallback<T> extends UploadCallback<T> {

    @Override
    public T onConvert(String data) {
        /**
         * 接口响应数据格式如@Response
         * 将result转化给success
         * 这里处理通过错误
         */
     /*   T t = null;
        try {
            JSONObject response = new JSONObject(data);
            String error = response.getString("status");

            if (error.equalsIgnoreCase("success")){

                return convert(data);
            }else {
                String errormes = response.has("failinfo") ? response.getString("failinfo") : "未知错误！";
                onError(-1,errormes);
            }



        }catch (Exception e){
            e.printStackTrace();
        }
        return t;*/
        return convert(data);
    }

    /**
     * 数据转换/解析
     *
     * @param data
     * @return
     */
    public abstract T convert(String data);

    /**
     * 上传回调
     *
     * @param file
     * @param currentSize
     * @param totalSize
     * @param progress
     * @param currentIndex
     * @param totalFile
     */
    public abstract void onProgress(File file, long currentSize, long totalSize, float progress, int currentIndex, int totalFile);


    /**
     * 成功回调
     *
     * @param value
     */
    public abstract void onSuccess(T value);

    /**
     * 失败回调
     *
     * @param code
     * @param desc
     */
    public abstract void onError(int code, String desc);

    /**
     * 取消回调
     */
    public abstract void onCancel();

}
