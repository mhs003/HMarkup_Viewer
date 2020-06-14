package com.mhs.htmlviewer.fs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.*;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.app.*;
import android.widget.*;
import android.view.*;
import android.net.*;

public class MainActivity extends Activity 
{
	private String mUrl;
    private WebView mWebView;
	private Intent facebook;
	private Intent setting;
	
	private class ChromeClient extends WebChromeClient {
        private ChromeClient() {
        }

        public void onReceivedTitle(WebView view, String title) {
            if (!getIntent().hasExtra("android.intent.extra.TITLE")) {
                setTitle(title);
                getActionBar().setTitle(title);
            }
        }
		
		
    }
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		
		this.mWebView = (WebView) findViewById(R.id.webview);
		this.mWebView.setWebChromeClient(new ChromeClient());
		
		setWebviewSettings();
		
		Intent intent = getIntent();
        if (intent.hasExtra("android.intent.extra.TITLE")) {
            setTitle(intent.getStringExtra("android.intent.extra.TITLE"));
        }
		
		this.mUrl = String.valueOf(intent.getData());
        if (ActivityCompat.checkSelfPermission(this, "android.permission.READ_EXTERNAL_STORAGE") == 0) {
            this.mWebView.loadUrl(this.mUrl);
            return;
        }
        ActivityCompat.requestPermissions(this, new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, 2000);
		
		
		
    }
	
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        this.mWebView.loadUrl(this.mUrl);
    }
	
	protected void setWebviewSettings(){
		WebSettings s = this.mWebView.getSettings();
		
		boolean p;
		
		p = (MyString.getInt(getApplicationContext(), "js") == 1) ? true : false;
		s.setJavaScriptEnabled(p);
		
		p = (MyString.getInt(getApplicationContext(), "vp") == 1) ? true : false;
		s.setUseWideViewPort(p);
		
		p = (MyString.getInt(getApplicationContext(), "zoom") == 1) ? true : false;
		s.setSupportZoom(p);
		s.setBuiltInZoomControls(p);
		
		p = (MyString.getInt(getApplicationContext(), "zoomCtrl") == 1) ? true : false;
        s.setDisplayZoomControls(p);
		
		p = (MyString.getInt(getApplicationContext(), "blockn") == 1) ? true : false;
        s.setBlockNetworkLoads(p);
		
		if(MyString.getInt(getApplicationContext(), "enc") == 1){
			s.setDefaultTextEncodingName("utf-8");
		}
	}
	
	@Override 
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_menu, menu); 
		return true; 
	}
	
	@Override 
	public boolean onOptionsItemSelected(MenuItem item) { 
		switch(item.getItemId()){ 
				case R.id.setting: 
					setting = new Intent(getApplicationContext(),SettingActivity.class);
					startActivity(setting);
					break; 
				case R.id.about:
					ViewDialogDV dialogDV = new ViewDialogDV();
					dialogDV.showDialog(this);
					break; 
				case R.id.exit: 
					finish(); 
					break; 
		} 
		return super.onOptionsItemSelected(item); 
	} 

    protected void onDestroy() {
        super.onDestroy();
        this.mWebView.destroy();
    }

	@Override
	protected void onResume()
	{
		super.onResume();
		setWebviewSettings();
		this.mWebView.reload();
	}
	
	@Override
	public void onBackPressed()
	{
		if(this.mWebView.canGoBack()){
			this.mWebView.goBack();
		} else {
			finish();
		}
	}
	
	public class ViewDialogDV {
		public void showDialog(Activity activity){
			final Dialog dialogDV = new Dialog(activity);
			dialogDV.requestWindowFeature(Window.FEATURE_NO_TITLE);
			dialogDV.setCancelable(true);
			dialogDV.setContentView(R.layout.about);

			Button dialogButton = (Button) dialogDV.findViewById(R.id.btn_my_fb);
			dialogButton.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						dialogDV.dismiss();
						facebook = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/7ox1c.7"));
						startActivity(facebook);
					}
				});
			dialogDV.show();
		}
	}
}
