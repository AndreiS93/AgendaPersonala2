package com.example.personalagenda;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

public class Memo extends Activity implements OnClickListener {

	private ListView menuLV;
	Button meniuPrincipal;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.memo_main);

		// Listener
		meniuPrincipal = (Button)findViewById(R.id.memo_main_meniu_b_principal);
		meniuPrincipal.setOnClickListener(this);
		menuLV = (ListView) findViewById(R.id.memo_main_lv_opt);
		menuLV.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapterView, View listView, int selectedItemId,
					long arg4) {
				// TODO Auto-generated method stub
				mainListViewOnItemClickHandler(adapterView, listView, selectedItemId, arg4);
			}
		});
	}

	// handler click
	private void mainListViewOnItemClickHandler(AdapterView<?> adapterView, View listView, int selectedItemId,long arg4) {
		//selectare item
		String selectedItem = (String) menuLV.getItemAtPosition(selectedItemId);
		
		if(selectedItem.equals(getString(R.string.memo_main_lv_vezi_grupuri))){
			Intent intentVeziGrupuri = new Intent("com.example.personalagenda.MemoViewGroups");
			startActivity(intentVeziGrupuri);
		}
		if(selectedItem.equals(getString(R.string.memo_main_lv_vezi_memouri))){
			Intent intentVeziMemouri = new Intent("com.example.personalagenda.MemoViewMemos");
			startActivity(intentVeziMemouri);
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		//in cazul butonului meniu principal, ne intoarcem in meniul anterior
		case R.id.memo_main_meniu_b_principal:
			Intent i = new Intent("com.example.personalagenda.MENU");
			startActivity(i);
			break;
		
	}

}
}
