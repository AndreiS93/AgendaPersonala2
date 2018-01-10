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

//clasa ce se ocupa cu adaugarea contactelor
public class ContacteAdaugareContact extends Activity implements OnClickListener {

	Button sqlUpdate, sqlBack;
	EditText sqlNume, sqlPrenume, sqlNb_Mobil, sqlNb_Home, sqlNb_Work, sqlEmail, sqlFax, sqlAddress, sqlNotes;

	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.contacts_add_contact);
		initButtons();
		//setare listeneri pentru butoane
		sqlUpdate.setOnClickListener(this);
		sqlBack.setOnClickListener(this);
	}
	
	//initializare butoane
	public void initButtons(){

		sqlUpdate = (Button) findViewById(R.id.contacts_add_contact_b_SQLUpdate);
		sqlBack = (Button) findViewById(R.id.contacts_add_contact_b_Add_Cont_Back);
		sqlNume = (EditText)findViewById(R.id.contacts_add_contact_et_SQLNume);
		sqlPrenume = (EditText)findViewById(R.id.contacts_add_contact_et_SQLPrenume);
		sqlNb_Mobil = (EditText)findViewById(R.id.contacts_add_contact_et_SQLphoneNumberMobile);
		sqlNb_Home = (EditText)findViewById(R.id.contacts_add_contact_et_SQLphoneNumberHome);
		sqlNb_Work = (EditText)findViewById(R.id.contacts_add_contact_et_SQLphoneNumberWork);
		sqlEmail = (EditText)findViewById(R.id.contacts_add_contact_et_SQLemail);
		sqlFax = (EditText)findViewById(R.id.contacts_add_contact_et_SQLFax);
		sqlAddress = (EditText)findViewById(R.id.contacts_add_contact_et_SQLadresa);
		sqlNotes = (EditText)findViewById(R.id.contacts_add_contact_et_SQLNotes);
	}

	@Override
	// determina ce buton a fost apasat
	public void onClick(View arg0) {
		switch (arg0.getId()) {	
		case R.id.contacts_add_contact_b_SQLUpdate:
			// cand apasam Update textul va fi preluat si setat pe String
			boolean didItWork = true;
			try {
				String nume = sqlNume.getText().toString();
				String prenume = sqlPrenume.getText().toString();
				String nb_mobile = sqlNb_Mobil.getText().toString();
				String nb_home = sqlNb_Home.getText().toString();
				String nb_work = sqlNb_Work.getText().toString();
				String email = sqlEmail.getText().toString();
				String fax = sqlFax.getText().toString();
				String addr = sqlAddress.getText().toString();
				String notes = sqlNotes.getText().toString();

				// referinta la clasa HandleSQL
				ContacteHandlerSQL entry = new ContacteHandlerSQL(ContacteAdaugareContact.this);

				//operatii in baza de date
				entry.open();
				//adaugare contact
				entry.createEntry(nume, prenume, nb_mobile, nb_home, nb_work, email, fax, addr, notes);
				entry.close();
				//in caz de eroare afisam mesaj
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
				// afisam dialog in cazul adaugarii cu succes a contactului
				if (didItWork) {
					Dialog d = new Dialog(this);
					d.setTitle("Contact adaugat!");
					TextView tv = new TextView(this);
					tv.setText("Succes");
					d.setContentView(tv);
					d.show();
				}
			}
			break;
		//caz de tratare pentru intoarcerea in meniul anterior	
		case R.id.contacts_add_contact_b_Add_Cont_Back:
			Intent i = new Intent("com.example.personalagenda.Contacte");
			startActivity(i);
			break;
		}

	}

}
