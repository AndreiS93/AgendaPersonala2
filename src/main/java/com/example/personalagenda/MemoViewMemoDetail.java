package com.example.personalagenda;

import java.util.Calendar;
import java.util.Locale;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

//vizualizare detalii memo
public class MemoViewMemoDetail extends MemoGeneralKeepLayout {

	// memo de afisat, incarcat din bundle
	private MemoMemo memo;

	// flag ce indica apasarea butonului de editare
	public static final int flag_cerere_de_edit_memo = 1;

	// Database Adapter for interacting with database
	private MemoHandlerSQL databaseAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.memo_view_memo_detail);

		databaseAdapter = new MemoHandlerSQL(this);
		databaseAdapter.open();

		Bundle bundleDetaliiMemo = this.getIntent().getExtras();
		try{
			this.memo = (MemoMemo) bundleDetaliiMemo.getSerializable(MemoMemo.MEMO_BUNDLE_KEY);
		} catch (Exception ex){
			ex.printStackTrace();
		}

		if(this.memo == null){
			this.finish();
		} else {
			// afisare in pagina
			this.afisareDetaliiMemo();
		}

	}

	// extragere date din memo si afisare
	@SuppressLint("NewApi")
	private void afisareDetaliiMemo(){

		if(this.memo == null){
			this.finish();
		} else {
			// initializare campuri
			// titlu
			TextView titluMemo = (TextView) findViewById(R.id.memo_view_memo_detail_tv_continut_memo);
			titluMemo.setText(this.memo.getTitlu());
			// data
			TextView dataMemo = (TextView) findViewById(R.id.memo_view_memo_detail_tv_continut_data);
			Calendar data = this.memo.getData();
			String dueDateString = data.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.US) + " "
					+ data.get(Calendar.DATE) + " "
					+ data.get(Calendar.YEAR);
			dataMemo.setText(dueDateString);
			// note
			TextView noteMemo = (TextView) findViewById(R.id.memo_view_memo_detail_tv_continut_notite);
			noteMemo.setText(this.memo.getNote());
			// prioritate
			TextView prioritateMemo = (TextView) findViewById(R.id.memo_view_memo_detail_tv_continut_prioritate);
			String prioritate;
			switch (this.memo.getNivelPrioritate()){
			case MemoMemo.PR_RIDICATA:
				prioritate = this.getString(R.string.memo_modif_memo_spinner_prior_ridicata);
				break;
			case MemoMemo.PR_MEDIE:
				prioritate = this.getString(R.string.memo_modif_memo_spinner_prior_medie);
				break;
			default:
				prioritate = this.getString(R.string.memo_modif_memo_spinner_prior_scazuta);
				break;
			}
			prioritateMemo.setText(prioritate);
			// grad finalizare
			TextView finalizareMemo = (TextView) findViewById(R.id.memo_view_memo_detail_tv_continut_statut);
			String finalizare;
			if(this.memo.setNivelPrioritate() == MemoMemo.MEMO_EFECTUAT){
				finalizare = getString(R.string.memo_modif_memo_spinner_efectuat);
			} else {
				finalizare = getString(R.string.memo_modif_memo_spinner_in_derulare);
			}
			finalizareMemo.setText(finalizare);
			// grup
			TextView grup = (TextView) findViewById(R.id.memo_view_memo_detail_tv_continut_grup);
			grup.setText(this.memo.getGroup().getTitlu());
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_view_memo_detail, menu);
		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		if(requestCode == flag_cerere_de_edit_memo){
			this.memo = (MemoMemo) data.getExtras().getSerializable(MemoMemo.MEMO_BUNDLE_KEY);
			this.afisareDetaliiMemo();
		}
	}
	
	public void editareMemoExistent(MemoMemo memo){
		Intent intentEditareMemo = new Intent("com.example.personalagenda.MemoModifyMemo");
		// incarcare memo de editat in bundle
		Bundle bundleMemo = new Bundle();
		bundleMemo.putSerializable(MemoMemo.MEMO_BUNDLE_KEY, memo);
		// incarcare bundle in intent
		intentEditareMemo.putExtras(bundleMemo);
		startActivityForResult(intentEditareMemo, MemoViewMemoDetail.flag_cerere_de_edit_memo);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()){
		case R.id.memo_detail_actBar_edit:
			editareMemoExistent(this.memo);
			return true;
		case R.id.memo_detail_actBar_delete:
			MemoHandlerConfirmareDialogDelete.showConfirmDeleteDialogForMemo(this, this.memo, this.databaseAdapter);
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	

}
