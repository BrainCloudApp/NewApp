package com.lmq.tool;

import android.content.Context;

import com.lmq.common.AppContact;

import java.io.File;
import java.net.URLEncoder;

public class FileCache {

	private File cacheDir;

	public FileCache(Context context) {
		// 如果有SD卡则在SD卡中建一个LazyList的目录存放缓存的图片
		// 没有SD卡就放在系统的缓存目录中
		try{
		if (android.os.Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED)) {

			File root=new File(android.os.Environment.getExternalStorageDirectory(),
					AppContact.FileStorePath);
			if(!root.exists())
				root.mkdir();
			cacheDir = new File(
					android.os.Environment.getExternalStorageDirectory(),
					AppContact.ImageFileStorePath);
		}
		else {
			cacheDir =new File( context.getCacheDir().getPath()+"/imgs/");

		}

		if (!cacheDir.exists())
			cacheDir.mkdir();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("创建图标缓存文件夹失败");
			e.printStackTrace();
		}
	}

	public String getFileName(String url){
		//String filename = String.valueOf(url.hashCode());
		// Another possible solution
		 String filename = URLEncoder.encode(url);
		return cacheDir.getPath() +"/"+filename;
//		File f = new File(cacheDir, "/"+url);
//		return f.getAbsolutePath();
	}
	public File getFile(String url) {
		// 将url的hashCode作为缓存的文件名
		//String filename = String.valueOf(url.hashCode());
		// Another possible solution
		// String filename = URLEncoder.encode(url);
		 String filename = URLEncoder.encode(url);
		// filename= cacheDir.getPath() +"/"+filename;
		File f = new File(cacheDir, "/"+filename);
		return f;

	}

	public void clear() {
		File[] files = cacheDir.listFiles();
		if (files == null)
			return;
		for (File f : files)
			f.delete();
	}

}
