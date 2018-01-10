package com.example.personalagenda;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

// clasa creare grup
public class MemoModifyGroup extends MemoGeneralKeepLayout {

	// daca group=null, creeare grup nou, altfel se afiseaza grupurile din bundle
	private MemoGroup group = null;

	private MemoHandlerSQL databaseAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.memo_modif_group);
		// afisare grupuri
			putDataToForm();
		databaseAdapter = new MemoHandlerSQL(this);
		databaseAdapter.open();

	}

	// incarcare date din grup si afisare in edit text
	private void putDataToForm(){
		if (group != null){
			EditText titluGrupET = (EditText) findViewById(R.id.memo_modif_group_et_titlu_grup);
			titluGrupET.setText(this.group.getTitlu());
		}
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()){

		// in caz de Renunta sau Acasa, afisare Dialog
		case R.id.modify_group_actBar_renunta:
		case android.R.id.home:
			MemoHandlerConfirmareDialogCancel.showConfirmCancelDialog(this);
			return true;
		case R.id.modify_group_actBar_salveaza:
			insertGroup(); //salvare date
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	// inserare grup in baza de date
	private void insertGroup(){

		EditText titluGr = (EditText) findViewById(R.id.memo_modif_group_et_titlu_grup);
		databaseAdapter.insertGrup(databaseAdapter.getGrupNouID(), titluGr.getText().toString());
		this.finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_modify_group, menu);
		return true;
	}

}
