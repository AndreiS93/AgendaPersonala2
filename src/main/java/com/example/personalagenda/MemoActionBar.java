package com.example.personalagenda;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MemoActionBar extends MemoGeneralKeepLayout {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()){
		case R.id.setting_actBar_renunta:
			this.finish();
		default:
			return super.onOptionsItemSelected(item);
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_setting, menu);
		return true;
	}

}
