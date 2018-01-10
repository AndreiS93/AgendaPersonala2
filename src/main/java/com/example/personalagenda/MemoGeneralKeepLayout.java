package com.example.personalagenda;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;

//Adaugare elemente la ActionBar
@SuppressLint("NewApi")
public class MemoGeneralKeepLayout extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		// icoana back
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()){
		case android.R.id.home:
			this.finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	
	
}
