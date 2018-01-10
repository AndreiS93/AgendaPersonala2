package com.example.personalagenda;

import java.util.ArrayList;
import java.util.Calendar;

import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;


// clasa creeare , modificare Memo
public class MemoModifyMemo extends MemoGeneralKeepLayout {

	// VARIABLES DEFINED HERE

	// daca memo = null, creeare memo, altfel incarcare date din bundle
	private MemoMemo memo = null;

	private int EditSauAdaugareMemo;
	private final int EDIT_MEMO = 1;
	private final int ADAUGARE_MEMO = 2;

	private MemoHandlerSQL databaseAdapter;
	private Cursor cursorGrupuri;
	private SimpleCursorAdapter spinnerGrAdaptor;
	private Spinner spinnerGr;

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent;
		Bundle bundle;

		switch (item.getItemId()){

		// in caz de Renunta sau Acasa
		case R.id.modify_memo_actBar_renunta:
		case android.R.id.home:

			intent = new Intent();
			bundle = new Bundle();
			bundle.putSerializable(MemoMemo.MEMO_BUNDLE_KEY, this.memo);
			intent.putExtras(bundle);
			setResult(MemoViewMemoDetail.flag_cerere_de_edit_memo, intent);

			// dialog confirmare
			MemoHandlerConfirmareDialogCancel.showConfirmCancelDialog(this);
			return true;

			// salveaza
		case R.id.modify_memo_actBar_salveaza:

			// daca adaugare sau editare memo
			if(this.EditSauAdaugareMemo == ADAUGARE_MEMO){
				adaugareMemoNou();
			} else {
				editMemoExistent();

				intent = new Intent();
				bundle = new Bundle();
				bundle.putSerializable(MemoMemo.MEMO_BUNDLE_KEY, this.memo);
				intent.putExtras(bundle);
				setResult(MemoViewMemoDetail.flag_cerere_de_edit_memo, intent);

			}
			finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onBackPressed() {
		Intent resultIntent = new Intent();
		Bundle resultBundle = new Bundle();
		resultBundle.putSerializable(MemoMemo.MEMO_BUNDLE_KEY, this.memo);
		resultIntent.putExtras(resultBundle);
		setResult(MemoViewMemoDetail.flag_cerere_de_edit_memo, resultIntent);

		// dialog confirmare
		MemoHandlerConfirmareDialogCancel.showConfirmCancelDialog(this);
	}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.memo_modif_memo);

		spinnerGr = (Spinner) findViewById(R.id.memo_modif_memo_select_grup);

		databaseAdapter = new MemoHandlerSQL(this);
		databaseAdapter.open();

		this.initGroup();

		// creeare sau editare memo
		Bundle bundleModificaMemo = this.getIntent().getExtras();
		try {
			this.memo = (MemoMemo) bundleModificaMemo.getSerializable(MemoMemo.MEMO_BUNDLE_KEY);
		} catch (Exception ex){
			ex.printStackTrace();
		}
		if (memo != null){
			// editare Memo
			this.EditSauAdaugareMemo = this.EDIT_MEMO;
			// adaugare data
			putDataToForm();
		} else {
			this.memo = new MemoMemo();
			this.EditSauAdaugareMemo = this.ADAUGARE_MEMO;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_modify_memo, menu);
		return true;
	}

	// editare memo
	private void editMemoExistent(){
		updateMemo();
		databaseAdapter.editMemoExistent(this.memo);
	}

	// updatare Memo
	private void updateMemo(){
		//setare titlu
		String titluMemo = ((EditText)findViewById(R.id.memo_modif_memo_et_titlu_memo)).getText().toString();
		this.memo.setTitlu(titluMemo);
		//setare data
		DatePicker memoData = (DatePicker) findViewById(R.id.memo_modif_memo_dp_data);
		this.memo.getData().set(Calendar.DATE, memoData.getDayOfMonth());
		this.memo.getData().set(Calendar.MONTH, memoData.getMonth());
		this.memo.getData().set(Calendar.YEAR, memoData.getYear());
		//setare notite
		String noteMemo = ((EditText)findViewById(R.id.memo_modif_memo_et_notite)).getText().toString();
		this.memo.setNote(noteMemo);
		//setare prioritate
		int nivelPrioritate = ((Spinner)findViewById(R.id.memo_modif_memo_sp_prioritate)).getSelectedItemPosition();
		this.memo.setNivelPrioritate(nivelPrioritate);
		//setare nivel finalizare
		int gradFinalizare = ((Spinner)findViewById(R.id.memo_modif_memo_select_status)).getSelectedItemPosition();
		this.memo.setGradCompletare(gradFinalizare);
		//setare grup
		Spinner grup = (Spinner) findViewById(R.id.memo_modif_memo_select_grup);
		this.memo.getGroup().setId(idGrupDupaPozitie(cursorGrupuri, grup.getSelectedItemPosition()));
	}

	// adaugare Memo
	private void adaugareMemoNou(){
		updateMemo();
		String id = databaseAdapter.getMemoNouID();
		this.memo.setId(id);
		this.databaseAdapter.insertMemo(this.memo);
	}

	// preluare Grupuri si inseare in Spinner
	private void initGroup(){
		
		if(this.databaseAdapter != null){
			// grupuri
			cursorGrupuri = databaseAdapter.getGrupuri();
			startManagingCursor(cursorGrupuri);
			String[] from = new String[]{MemoHandlerSQL.GROUP_TABLE_COULMN_TITLE};
			int[] to = new int[]{R.id.memo_view_group_lv_grupuri};
			//spinner
			this.spinnerGrAdaptor = new SimpleCursorAdapter(this,R.layout.memo_view_group_listview, cursorGrupuri, from, to);
			spinnerGr.setAdapter(spinnerGrAdaptor);
		} else {
			finish();
		}
	}
	
	// afisare date
	private void putDataToForm(){
		// editare memo
		if (this.EditSauAdaugareMemo == this.EDIT_MEMO){
			// setare titlu
			EditText titluMemo = (EditText) findViewById(R.id.memo_modif_memo_et_titlu_memo);
			titluMemo.setText(this.memo.getTitlu());

			// setare data
			DatePicker dataMemo = (DatePicker) findViewById(R.id.memo_modif_memo_dp_data);
			dataMemo.updateDate(this.memo.getData().get(Calendar.YEAR),
					this.memo.getData().get(Calendar.MONTH),
					this.memo.getData().get(Calendar.DATE));

			// setare notite
			EditText memoNotite = (EditText) findViewById(R.id.memo_modif_memo_et_notite);
			memoNotite.setText(this.memo.getNote());

			// setare prioritate
			Spinner prioritateMemo = (Spinner) findViewById(R.id.memo_modif_memo_sp_prioritate);
			prioritateMemo.setSelection(this.memo.getNivelPrioritate());

			// setare grup
			spinnerGr.setSelection(this.getPozGrupInCursor(cursorGrupuri, this.memo.getGroup().getId()));

			// setare grad de finalizare
			Spinner gradFinalizare = (Spinner) findViewById(R.id.memo_modif_memo_select_status);
			gradFinalizare.setSelection(this.memo.setNivelPrioritate());
		}
	}

	// idGrupDupaPozitie
	private String idGrupDupaPozitie(Cursor cursor, int position){
		String groupId = null;
		cursor.moveToFirst();
		cursor.move(position);
		groupId = cursor.getString(cursor.getColumnIndex(MemoHandlerSQL.GROUP_TABLE_COLUMN_ID));
		return groupId;
	}

	// get pozitie grup in cursor
	private int getPozGrupInCursor(Cursor cursor, String groupId){
		int position = -1;
		cursor.moveToFirst();
		while(!cursor.isAfterLast()){
			String currentGroupId = cursor.getString(cursor.getColumnIndex(MemoHandlerSQL.GROUP_TABLE_COLUMN_ID));
			if(currentGroupId.equals(groupId)){
				position = cursor.getPosition();
			}
			cursor.moveToNext();
		}
		return position;
	}

}
