package com.example.personalagenda;

import android.os.Bundle;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MemoViewGroups extends MemoGeneralKeepLayout {
	
	private MemoHandlerSQL databaseAdapter;
	
	// cursor grupuri
	private Cursor cGrupuri;
	
	// adaptor listview
	private SimpleCursorAdapter lv_adaptor_Grupuri;
	
	// listview grupuri
	private ListView lvGrupuri;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.memo_view_group);
		
		// incarcare listview cu grupuri
		this.lvGrupuri = (ListView) findViewById(R.id.memo_view_group2_lv_grupuri);

		databaseAdapter = new MemoHandlerSQL(this);
		databaseAdapter.open();
		afisareGrupuriInLV();
		
	}
	
	
	// preluare grupuri din baza de date si afisare in list view
	private void afisareGrupuriInLV(){
		
		if(this.databaseAdapter != null){
			// preluare grupuri
			cGrupuri = databaseAdapter.getGrupuri();
			startManagingCursor(cGrupuri);
			// preluare date de pe coloana
			String[] from = new String[]{MemoHandlerSQL.GROUP_TABLE_COULMN_TITLE};
			// incarcare date in listview
			int[] to = new int[]{R.id.memo_view_group_lv_grupuri};

			lv_adaptor_Grupuri = new SimpleCursorAdapter(this,R.layout.memo_view_group_listview, cGrupuri, from, to);
			// setare adaptor pentru listview
			this.lvGrupuri.setAdapter(lv_adaptor_Grupuri);
		}
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_view_gr, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()){
		case R.id.view_gr_actBar_addGr:
			afisareGrupuriInLV();
			Intent intentAdaugaGrup = new Intent("com.example.personalagenda.MemoModifyGroup");
			startActivity(intentAdaugaGrup);
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
