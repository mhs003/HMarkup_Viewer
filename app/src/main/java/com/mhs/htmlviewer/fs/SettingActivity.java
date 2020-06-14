package com.mhs.htmlviewer.fs;

import android.app.*;
import android.os.*;
import android.support.v4.app.*;
import android.view.*;
import android.widget.*;

public class SettingActivity extends Activity
{
	private ActionBar actionBar;
	private Switch s_js, s_vp, s_zoom, s_zoomCtrl, s_blkn, s_enc;
	
	@Override
	protected void onCreate(Bundle saveInstanceState)
	{
		super.onCreate(saveInstanceState);
		setContentView(R.layout.settings);
		
		actionBar = getActionBar();
		actionBar.setTitle("Settings");
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setHomeButtonEnabled(true);
		
		s_js = (Switch) findViewById(R.id.s_javascript);
		s_vp = (Switch) findViewById(R.id.s_viewport);
		s_zoom = (Switch) findViewById(R.id.s_zoom);
		s_zoomCtrl = (Switch) findViewById(R.id.s_zoomCtrl);
		s_blkn = (Switch) findViewById(R.id.s_blockn);
		s_enc = (Switch) findViewById(R.id.s_enc);
		
		if(MyString.getInt(SettingActivity.this.getApplicationContext(), "js") == 1){
			s_js.setChecked(true);
		} else {
			s_js.setChecked(false);
		}
		
		if(MyString.getInt(SettingActivity.this.getApplicationContext(), "vp") == 1){
			s_vp.setChecked(true);
		} else {
			s_vp.setChecked(false);
		}
		
		if(MyString.getInt(SettingActivity.this.getApplicationContext(), "zoom") == 1){
			s_zoom.setChecked(true);
		} else {
			s_zoom.setChecked(false);
		}
		
		if(MyString.getInt(SettingActivity.this.getApplicationContext(), "zoomCtrl") == 1){
			s_zoomCtrl.setChecked(true);
		} else {
			s_zoomCtrl.setChecked(false);
		}
		
		if(MyString.getInt(SettingActivity.this.getApplicationContext(), "blockn") == 1){
			s_blkn.setChecked(true);
		} else {
			s_blkn.setChecked(false);
		}
		
		if(MyString.getInt(SettingActivity.this.getApplicationContext(), "enc") == 1){
			s_enc.setChecked(true);
		} else {
			s_enc.setChecked(false);
		}
		
		s_js.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
			@Override
			public void onCheckedChanged(CompoundButton p1, boolean p2)
			{
				if(p2){
					MyString.setInt(SettingActivity.this.getApplicationContext(), "js", 1);
				} else {
					MyString.setInt(SettingActivity.this.getApplicationContext(), "js", 0);
				}
			}
		});
		
		s_vp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
			@Override
			public void onCheckedChanged(CompoundButton p1, boolean p2)
			{
				if(p2){
					MyString.setInt(SettingActivity.this.getApplicationContext(), "vp", 1);
				} else {
					MyString.setInt(SettingActivity.this.getApplicationContext(), "vp", 0);
				}
			}
		});
		
		s_zoom.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
			@Override
			public void onCheckedChanged(CompoundButton p1, boolean p2)
			{
				if(p2){
					MyString.setInt(SettingActivity.this.getApplicationContext(), "zoom", 1);
				} else {
					s_zoomCtrl.setChecked(false);
					MyString.setInt(SettingActivity.this.getApplicationContext(), "zoom", 0);
				}
			}
		});
		
		s_zoomCtrl.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
			@Override
			public void onCheckedChanged(CompoundButton p1, boolean p2)
			{
				if(p2){
					s_zoom.setChecked(true);
					MyString.setInt(SettingActivity.this.getApplicationContext(), "zoomCtrl", 1);
				} else {
					MyString.setInt(SettingActivity.this.getApplicationContext(), "zoomCtrl", 0);
				}
			}
		});
		
		s_blkn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
			@Override
			public void onCheckedChanged(CompoundButton p1, boolean p2)
			{
				if(p2){
					MyString.setInt(SettingActivity.this.getApplicationContext(), "blockn", 1);
				} else {
					MyString.setInt(SettingActivity.this.getApplicationContext(), "blockn", 0);
				}
			}
		});
		
		s_enc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
			@Override
			public void onCheckedChanged(CompoundButton p1, boolean p2)
			{
				if(p2){
					MyString.setInt(SettingActivity.this.getApplicationContext(), "enc", 1);
				} else {
					MyString.setInt(SettingActivity.this.getApplicationContext(), "enc", 0);
				}
			}
		});
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		finish();
		return super.onOptionsItemSelected(item);
	} 
	
	@Override
	public void onBackPressed()
	{
		super.onBackPressed();
		finish();
	}
}
