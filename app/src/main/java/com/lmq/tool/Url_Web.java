package com.lmq.tool;

import android.net.http.SslError;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newapp.R;
import com.lmq.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class Url_Web extends BaseActivity {
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK)) {


			if(wv.canGoBack())
				wv.goBack();
			else {


				Url_Web.this.finish();
			}
			return true;
		}else {
			return super.onKeyDown(keyCode, event);
		}

	}
	@Override
	protected  int setContentView(){
		return R.layout.info_web;
	}
	@Override
	protected  void initBundleData(){

	}
	@Override
	protected  void initView(){
		init();
		initWebView();
		String url=getIntent().getStringExtra("url");

			wv.loadUrl(url);

	}
	@OnClick(R.id.back)
	public void goback(){
		finish();
	}
	




	@BindView(R.id.webkitWebView1)
	WebView wv ;
	@BindView(R.id.progress) 	 ProgressBar myprogress;

	@BindView(R.id.title)TextView titlebt;

	private boolean gologin=false;
	
	
	public void init(){
		
	
		  String title=getIntent().getStringExtra("title");
		
		  if(title!=null&&title.length()>0)
		  titlebt.setText(title);

	}

	public void initWebView(){
		try{


		        wv.setWebViewClient(new WebViewClient(){    
		        	
		            public void onReceivedSslError(WebView view,
							SslErrorHandler handler, SslError error) {
						// TODO Auto-generated method stub

						handler.proceed();
					}
		       


					public boolean shouldOverrideUrlLoading(WebView view, String url) {


						if(url.startsWith("app://login")){
						/*	Intent it31=new Intent(mContext,Login.class);
							startActivity(it31);
							gologin=true;*/

							return true;
						}
						return false;
		            } 
		            public void onPageFinished(WebView view, String url) {//页面加载完成后做的工作
		              
						super.onPageFinished(view, url);
					     myprogress.setVisibility(View.GONE);
						
					}
		            // 加载错误时要做的工作
					public void onReceivedError(WebView view, int errorCode,
							String description, String failingUrl) {
						//Log.d(TAG, "error=" + description);
						//titlelinearlayout.setVisibility(View.VISIBLE);
						Toast.makeText(mContext,
								errorCode + "/" + description, Toast.LENGTH_LONG)
								.show();

					}
					
		              
		            });
		    
		        wv.getSettings().setJavaScriptEnabled(true);
		        wv.requestFocus();
		        wv.getSettings().setDefaultTextEncodingName("UTF-8");
			/*if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){

				wv.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);

			}*/
		        //设置可以访问文件     
		        wv.getSettings().setAllowFileAccess(true);    
		    
		        //设置支持缩放     
		        wv.getSettings().setBuiltInZoomControls(true); 
		        wv.getSettings().setSavePassword(false);//设置是否保存密码
		        wv.getSettings().setDomStorageEnabled(true);
		        
		        wv.getSettings().setDisplayZoomControls(false);  
		        myprogress.setVisibility(View.GONE);
		      
		      
		       // wv.loadUrl(getIntent().getStringExtra("url"));
				}catch(Exception e){
					e.printStackTrace();
				}
			}



    /*** 
     * 获取url 指定name的value; 
     * @param url 
     * @param name 
     * @return 
     */  
    public static String getValueByName(String url, String name) {  
        String result = "";  
        int index = url.indexOf("?");  
        String temp = url.substring(index + 1);  
        String[] keyValue = temp.split("\\&");  
        for (String str : keyValue) {  
            if (str.contains(name)) {  
                result = str.replace(name + "=", "");  
            }  
        }  
        return result;  
    }  

	// 关闭进度框
	void closeProgress() {
		hidProDialog();
	}


}
