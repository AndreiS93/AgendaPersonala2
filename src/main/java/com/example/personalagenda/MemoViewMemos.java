package com.example.personalagenda;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleCursorAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MemoViewMemos extends MemoGeneralKeepLayout {

	private ListView memo_lv;

	private MemoHandlerSQL databaseAdapter;

	private Cursor cursorMemo;

	private SimpleCursorAdapter adaptorLVMemo;

	public static final int flag_cerere_memo_nou = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.memo_view_memo);

		memo_lv = (ListView) findViewById(R.id.memo_view_memo_lv_memouri);
		memo_lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				handlerClickMemoDinLV(arg0, arg1, arg2);
			}
		});
		databaseAdapter = new MemoHandlerSQL(this);
		databaseAdapter.open();
		afisareMemoInListView();
	}

	// afisare Memo in ListView
	public void afisareMemoInListView() {

		if (this.databaseAdapter != null) {
			// preluare memo
			cursorMemo = databaseAdapter.getMemo();
			startManagingCursor(cursorMemo);
			// preluare date din coloana
			String[] from = new String[] { MemoHandlerSQL.MEMO_TABLE_COLUMN_TITLE };
			// incarcare date in list view
			int[] to = new int[] { R.id.memo_view_group_lv_grupuri };
			// adaptor list view
			adaptorLVMemo = new SimpleCursorAdapter(this,R.layout.memo_view_group_listview, cursorMemo, from, to);
			this.memo_lv.setAdapter(adaptorLVMemo);
		}
	}

	// click item din listview
	private void handlerClickMemoDinLV(AdapterView<?> adapterView,
			View listView, int selectedItemId) {

		MemoMemo memoSelectat = new MemoMemo();

		cursorMemo.moveToFirst();
		cursorMemo.move(selectedItemId);
		// setari pentru memoSelectat
		//id
		memoSelectat.setId(cursorMemo.getString(cursorMemo.getColumnIndex(MemoHandlerSQL.MEMO_TABLE_COLUMN_ID)));
		// titlu
		memoSelectat.setTitlu(cursorMemo.getString(cursorMemo.getColumnIndex(MemoHandlerSQL.MEMO_TABLE_COLUMN_TITLE)));
		// data
		memoSelectat.getData().setTimeInMillis(cursorMemo.getLong(cursorMemo.getColumnIndex(MemoHandlerSQL.MEMO_TABLE_COLUMN_DATA)));
		// note
		memoSelectat.setNote(cursorMemo.getString(cursorMemo.getColumnIndex(MemoHandlerSQL.MEMO_TABLE_COLUMN_NOTE)));
		// prioritate
		memoSelectat.setNivelPrioritate(cursorMemo.getInt(cursorMemo.getColumnIndex(MemoHandlerSQL.MEMO_TABLE_COLUMN_PRIORITY)));
		// grad finalizare
		memoSelectat.setGradCompletare(cursorMemo.getInt(cursorMemo.getColumnIndex(MemoHandlerSQL.MEMO_TABLE_COLUMN_COMPLETION_STATUS)));
		// grup
		memoSelectat.setGroup(this.getGrupDupaMemo(cursorMemo.getString(cursorMemo.getColumnIndex(MemoHandlerSQL.MEMO_TABLE_COLUMN_GROUP))));

		Intent intentVeziDetaliiMemo = new Intent("com.example.personalagenda.MemoViewMemoDetail");
		// incarcare memo in bundle
		Bundle bundleVeziDetaliiMemo = new Bundle();
		bundleVeziDetaliiMemo.putSerializable(MemoMemo.MEMO_BUNDLE_KEY, (MemoMemo) memoSelectat);
		// incarcare bundle in intent
		intentVeziDetaliiMemo.putExtras(bundleVeziDetaliiMemo);
		startActivity(intentVeziDetaliiMemo);
	}

	// preluare grup dupa  memo
	private MemoGroup getGrupDupaMemo(String groupId) {
		MemoGroup group = new MemoGroup();
		
		Cursor groupCursor = this.databaseAdapter.getGrupDupaID(groupId);
		groupCursor.moveToFirst();
		group.setId(groupCursor.getString(groupCursor.getColumnIndex(MemoHandlerSQL.GROUP_TABLE_COLUMN_ID)));
		group.setTitlu(groupCursor.getString(groupCursor.getColumnIndex(MemoHandlerSQL.GROUP_TABLE_COULMN_TITLE)));

		return group;
	}
	public static void adaugaMemo(Activity sourceActivity, MemoHandlerSQL databaseAdapter){
		// preluare grupuri din baza de date
		Cursor cursorGrupuri = databaseAdapter.getGrupuri();
		if(cursorGrupuri.getCount() == 0){
			// daca nu sunt grupuri, cere sa se creeze unul
			MemoHandlerMesajeDialog.showMessageDialog(sourceActivity, "Nu exista grupuri!\nAdaugati un grup!");
		} else {
			// adauga memo
			Intent intentAdaugareMemo = new Intent(sourceActivity, MemoModifyMemo.class);
			sourceActivity.startActivityForResult(intentAdaugareMemo, MemoViewMemos.flag_cerere_memo_nou);
		}
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.view_memo_actBar_addMemo:
			adaugaMemo(this, this.databaseAdapter);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_view_memo, menu);
		return true;
	}

}
