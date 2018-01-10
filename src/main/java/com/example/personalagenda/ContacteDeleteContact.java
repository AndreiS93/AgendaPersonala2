package com.example.personalagenda;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;

//clasa ce se ocupa cu stergerea unui contact
public class ContacteDeleteContact extends Activity implements OnClickListener {

	Button sqlDelete, sqlBack;
	EditText sqlRow;
	TextView tvx;
	ContacteHandlerSQL info = new ContacteHandlerSQL(this);

	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.contacts_delete_contact);
		initButtons();
		// setare listeneri pentru butoane
		sqlDelete.setOnClickListener(this);
		sqlBack.setOnClickListener(this);
		
		//afisam intr-un textview contactele existente
		tvx = (TextView) findViewById(R.id.contacts_delete_contact_tv_SQLinfo);
		
		info.open();// deschidem HandleSQL
		String data = info.getDataOnlyId();// returnam string ce contine ID, nume, prenume
		info.close();// inchidem HandleSQL
		tvx.setText(data);
	}

	//initializare butoane
	public void initButtons() {

		sqlDelete = (Button) findViewById(R.id.contacts_delete_contact_b_DeleteContact);
		sqlBack = (Button) findViewById(R.id.contacts_delete_contact_b_Back_delete_contact);
		sqlRow = (EditText) findViewById(R.id.contacts_delete_contact_et_RowNumber);
	}

	@Override
	// determina ce buton a fost apasat
	public void onClick(View arg0) {
		switch (arg0.getId()) {

		//pentru butonul Inapoi, ne intoarcem in meniul anterior
		case R.id.contacts_delete_contact_b_Back_delete_contact:
			Intent i = new Intent("com.example.personalagenda.Contacte");
			startActivity(i);
			break; 

		//pentru buton stergere contact
		case R.id.contacts_delete_contact_b_DeleteContact:
			boolean didItWork = true;//succes sau esec
			try {
				
				//se preia intregul introdus in campul ID
				String sRow3 = sqlRow.getText().toString();
				//contertire in long
				long lRow3 = Long.parseLong(sRow3);

				//instanta a clasei ContacteHandlerSQL
				ContacteHandlerSQL db3 = new ContacteHandlerSQL(this);
				db3.open();
				db3.deleteEntry(lRow3); //se sterge contactul corespunzator ID-ului introdus
				db3.close();
				//in caz de exeptie, afisam mesaj
			} catch (Exception e) {
				didItWork = false;
				String error = e.toString();
				Dialog d = new Dialog(this);
				d.setTitle("Eroare!");
				TextView tv = new TextView(this);
				tv.setText(error);
				d.setContentView(tv);
				d.show();
			} finally {
				// afisam dialog in caz de succes
				if (didItWork) {
					Dialog d = new Dialog(this);
					d.setTitle("Contact sters!");
					TextView tv = new TextView(this);
					tv.setText("Succes");
					d.setContentView(tv);
					d.show();
					
					info.open();// deschidem HandleSQL
					String data = info.getDataOnlyId();// returnam string ce contine ID, nume, prenume
					info.close();// inchidem HandleSQL
					tvx.setText(data);
					
				}

			}

		}

	}
}