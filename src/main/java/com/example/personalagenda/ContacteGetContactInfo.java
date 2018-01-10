package com.example.personalagenda;

import java.util.Locale;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;

//clasa ce se ocupa cu preluarea contactelor si afisarea acestora
public class ContacteGetContactInfo extends Activity implements OnClickListener {

	Button sqlGetInfo, sqlModify, sqlBack;
	EditText sqlNume, sqlPrenume, sqlNb_Mobil, sqlNb_Home, sqlNb_Work,
			sqlEmail, sqlFax, sqlAddress, sqlNotes, sqlRow;

	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.contacts_get_contact_info);
		initButtons();
		//setare listeneri pentru butoane
		sqlGetInfo.setOnClickListener(this);
		sqlModify.setOnClickListener(this);
		sqlBack.setOnClickListener(this);
		
		//setare dialog informational
		Dialog d = new Dialog(this);
		d.setTitle("Utilizare");
		TextView tv = new TextView(this);
		tv.setText("Completati unul dintre campuri si apasati butonul Informatii pentru a extrage datele despre contact. Daca doriti editati datele si salvati apasand butonul Editeaza.");
		d.setContentView(tv);
		d.show();
	}

	//initializare butoane
	public void initButtons() {

		sqlGetInfo = (Button) findViewById(R.id.contacts_get_contact_info_b_getInfo);
		sqlModify = (Button) findViewById(R.id.contacts_get_contact_info_b_SQLmodify);
		sqlBack = (Button) findViewById(R.id.contacts_get_contact_info_b_Back);
		sqlNume = (EditText) findViewById(R.id.contacts_get_contact_info_et_SQLNume);
		sqlPrenume = (EditText) findViewById(R.id.contacts_get_contact_info_et_SQLPrenume);
		sqlNb_Mobil = (EditText) findViewById(R.id.contacts_get_contact_info_et_SQLphoneNumberMobile);
		sqlNb_Home = (EditText) findViewById(R.id.contacts_get_contact_info_et_SQLphoneNumberHome);
		sqlNb_Work = (EditText) findViewById(R.id.contacts_get_contact_info_et_SQLphoneNumberWork);
		sqlEmail = (EditText) findViewById(R.id.contacts_get_contact_info_et_SQLemail);
		sqlFax = (EditText) findViewById(R.id.contacts_get_contact_info_et_SQLFax);
		sqlAddress = (EditText) findViewById(R.id.contacts_get_contact_info_et_SQLadresa);
		sqlNotes = (EditText) findViewById(R.id.contacts_get_contact_info_et_SQLNotes);
		sqlRow = (EditText) findViewById(R.id.contacts_get_contact_info_et_RowNumber);
	}
	
	

	@Override
	// determina ce buton a fost apasat
	public void onClick(View arg0) {
		switch (arg0.getId()) {

		//in cazul butonului Inapoi, ne intoarcem in meniul anterior
		case R.id.contacts_get_contact_info_b_Back:
			Intent i = new Intent("com.example.personalagenda.Contacte");
			startActivity(i);
			break;
		//in cazul butonului Informatii, se extrag datele din baza de date in conformitate cu unul dintre campurile alese pentru comparare
		case R.id.contacts_get_contact_info_b_getInfo:
			try {		
				String nNume = sqlNume.getText().toString();
				String nPrenume = sqlPrenume.getText().toString();
				String nNrMobil = sqlNb_Mobil.getText().toString();
				String nNrHome = sqlNb_Home.getText().toString();
				String nNrWork = sqlNb_Work.getText().toString();
				String nEmail = sqlEmail.getText().toString();
				String nAddr = sqlAddress.getText().toString();
				String nRow = sqlRow.getText().toString();
				
				ContacteHandlerSQL db1 = new ContacteHandlerSQL(this);
				db1.open();

				//extragere in functie de nume
				if( !nNume.matches("") & nPrenume.matches("") & nNrMobil.matches("") & nNrHome.matches("") & nNrWork.matches("") & nEmail.matches("") & nAddr.matches("") & nRow.matches(""))
				{	
				String returnedNume = db1.getNume(nNume, ContacteHandlerSQL.KEY_NUME);
				String returnedPrenume = db1.getPrenume(nNume, ContacteHandlerSQL.KEY_NUME);
				String returnedNbMobil = db1.getNbMobil(nNume, ContacteHandlerSQL.KEY_NUME);
				String returnedNbHome = db1.getNbHome(nNume, ContacteHandlerSQL.KEY_NUME);
				String returnedNbWork = db1.getNbWork(nNume, ContacteHandlerSQL.KEY_NUME);
				String returnedEmail = db1.getEmail(nNume, ContacteHandlerSQL.KEY_NUME);
				String returnedFax = db1.getFax(nNume, ContacteHandlerSQL.KEY_NUME);
				String returnedAddress = db1.getAddress(nNume, ContacteHandlerSQL.KEY_NUME);
				String returnedNotes = db1.getNotes(nNume, ContacteHandlerSQL.KEY_NUME);
				String returnedRow = db1.getRow(nNume, ContacteHandlerSQL.KEY_NUME);
				db1.close();
				sqlNume.setText(returnedNume);
				sqlPrenume.setText(returnedPrenume);
				sqlNb_Mobil.setText(returnedNbMobil);
				sqlNb_Home.setText(returnedNbHome);
				sqlNb_Work.setText(returnedNbWork);
				sqlEmail.setText(returnedEmail);
				sqlFax.setText(returnedFax);
				sqlAddress.setText(returnedAddress);
				sqlNotes.setText(returnedNotes);
				sqlRow.setText(returnedRow);
				}
				
				//extragere in functie de prenume
				else if(nNume.matches("") & !nPrenume.matches("") & nNrMobil.matches("") & nNrHome.matches("") & nNrWork.matches("") & nEmail.matches("") & nAddr.matches("") & nRow.matches(""))
				{	
				String returnedNume = db1.getNume(nPrenume, ContacteHandlerSQL.KEY_PRENUME);
				String returnedPrenume = db1.getPrenume(nPrenume, ContacteHandlerSQL.KEY_PRENUME);
				String returnedNbMobil = db1.getNbMobil(nPrenume, ContacteHandlerSQL.KEY_PRENUME);
				String returnedNbHome = db1.getNbHome(nPrenume, ContacteHandlerSQL.KEY_PRENUME);
				String returnedNbWork = db1.getNbWork(nPrenume, ContacteHandlerSQL.KEY_PRENUME);
				String returnedEmail = db1.getEmail(nPrenume, ContacteHandlerSQL.KEY_PRENUME);
				String returnedFax = db1.getFax(nPrenume, ContacteHandlerSQL.KEY_PRENUME);
				String returnedAddress = db1.getAddress(nPrenume, ContacteHandlerSQL.KEY_PRENUME);
				String returnedNotes = db1.getNotes(nPrenume, ContacteHandlerSQL.KEY_PRENUME);
				String returnedRow = db1.getRow(nPrenume, ContacteHandlerSQL.KEY_PRENUME);
				db1.close();
				sqlNume.setText(returnedNume);
				sqlPrenume.setText(returnedPrenume);
				sqlNb_Mobil.setText(returnedNbMobil);
				sqlNb_Home.setText(returnedNbHome);
				sqlNb_Work.setText(returnedNbWork);
				sqlEmail.setText(returnedEmail);
				sqlFax.setText(returnedFax);
				sqlAddress.setText(returnedAddress);
				sqlNotes.setText(returnedNotes);
				sqlRow.setText(returnedRow);
				}
				
				//extragere in functie de numarul mobil
				else if(nNume.matches("") & nPrenume.matches("") & !nNrMobil.matches("") & nNrHome.matches("") & nNrWork.matches("") & nEmail.matches("") & nAddr.matches("") & nRow.matches(""))
				{	
				String returnedNume = db1.getNume(nNrMobil, ContacteHandlerSQL.KEY_NR_MOBIL);
				String returnedPrenume = db1.getPrenume(nNrMobil, ContacteHandlerSQL.KEY_NR_MOBIL);
				String returnedNbMobil = db1.getNbMobil(nNrMobil, ContacteHandlerSQL.KEY_NR_MOBIL);
				String returnedNbHome = db1.getNbHome(nNrMobil, ContacteHandlerSQL.KEY_NR_MOBIL);
				String returnedNbWork = db1.getNbWork(nNrMobil, ContacteHandlerSQL.KEY_NR_MOBIL);
				String returnedEmail = db1.getEmail(nNrMobil, ContacteHandlerSQL.KEY_NR_MOBIL);
				String returnedFax = db1.getFax(nNrMobil, ContacteHandlerSQL.KEY_NR_MOBIL);
				String returnedAddress = db1.getAddress(nNrMobil, ContacteHandlerSQL.KEY_NR_MOBIL);
				String returnedNotes = db1.getNotes(nNrMobil, ContacteHandlerSQL.KEY_NR_MOBIL);
				String returnedRow = db1.getRow(nNrMobil, ContacteHandlerSQL.KEY_NR_MOBIL);
				db1.close();
				sqlNume.setText(returnedNume);
				sqlPrenume.setText(returnedPrenume);
				sqlNb_Mobil.setText(returnedNbMobil);
				sqlNb_Home.setText(returnedNbHome);
				sqlNb_Work.setText(returnedNbWork);
				sqlEmail.setText(returnedEmail);
				sqlFax.setText(returnedFax);
				sqlAddress.setText(returnedAddress);
				sqlNotes.setText(returnedNotes);
				sqlRow.setText(returnedRow);
				}
				
				//extragere in functie de numarul de acasa
				else if(nNume.matches("") & nPrenume.matches("") & nNrMobil.matches("") & !nNrHome.matches("") & nNrWork.matches("") & nEmail.matches("") & nAddr.matches("") & nRow.matches(""))
				{	
				String returnedNume = db1.getNume(nNrHome, ContacteHandlerSQL.KEY_NR_HOME);
				String returnedPrenume = db1.getPrenume(nNrHome, ContacteHandlerSQL.KEY_NR_HOME);
				String returnedNbMobil = db1.getNbMobil(nNrHome, ContacteHandlerSQL.KEY_NR_HOME);
				String returnedNbHome = db1.getNbHome(nNrHome, ContacteHandlerSQL.KEY_NR_HOME);
				String returnedNbWork = db1.getNbWork(nNrHome, ContacteHandlerSQL.KEY_NR_HOME);
				String returnedEmail = db1.getEmail(nNrHome, ContacteHandlerSQL.KEY_NR_HOME);
				String returnedFax = db1.getFax(nNrHome, ContacteHandlerSQL.KEY_NR_HOME);
				String returnedAddress = db1.getAddress(nNrHome, ContacteHandlerSQL.KEY_NR_HOME);
				String returnedNotes = db1.getNotes(nNrHome, ContacteHandlerSQL.KEY_NR_HOME);
				String returnedRow = db1.getRow(nNrHome, ContacteHandlerSQL.KEY_NR_HOME);
				db1.close();
				sqlNume.setText(returnedNume);
				sqlPrenume.setText(returnedPrenume);
				sqlNb_Mobil.setText(returnedNbMobil);
				sqlNb_Home.setText(returnedNbHome);
				sqlNb_Work.setText(returnedNbWork);
				sqlEmail.setText(returnedEmail);
				sqlFax.setText(returnedFax);
				sqlAddress.setText(returnedAddress);
				sqlNotes.setText(returnedNotes);
				sqlRow.setText(returnedRow);
				}
				
				//extragere in functie de numarul de la munca
				else if(nNume.matches("") & nPrenume.matches("") & nNrMobil.matches("") & nNrHome.matches("") & !nNrWork.matches("") & nEmail.matches("") & nAddr.matches("") & nRow.matches(""))
				{	
				String returnedNume = db1.getNume(nNrWork, ContacteHandlerSQL.KEY_NR_WORK);
				String returnedPrenume = db1.getPrenume(nNrWork, ContacteHandlerSQL.KEY_NR_WORK);
				String returnedNbMobil = db1.getNbMobil(nNrWork, ContacteHandlerSQL.KEY_NR_WORK);
				String returnedNbHome = db1.getNbHome(nNrWork, ContacteHandlerSQL.KEY_NR_WORK);
				String returnedNbWork = db1.getNbWork(nNrWork, ContacteHandlerSQL.KEY_NR_WORK);
				String returnedEmail = db1.getEmail(nNrWork, ContacteHandlerSQL.KEY_NR_WORK);
				String returnedFax = db1.getFax(nNrWork, ContacteHandlerSQL.KEY_NR_WORK);
				String returnedAddress = db1.getAddress(nNrWork, ContacteHandlerSQL.KEY_NR_WORK);
				String returnedNotes = db1.getNotes(nNrWork, ContacteHandlerSQL.KEY_NR_WORK);
				String returnedRow = db1.getRow(nNrWork, ContacteHandlerSQL.KEY_NR_WORK);
				db1.close();
				sqlNume.setText(returnedNume);
				sqlPrenume.setText(returnedPrenume);
				sqlNb_Mobil.setText(returnedNbMobil);
				sqlNb_Home.setText(returnedNbHome);
				sqlNb_Work.setText(returnedNbWork);
				sqlEmail.setText(returnedEmail);
				sqlFax.setText(returnedFax);
				sqlAddress.setText(returnedAddress);
				sqlNotes.setText(returnedNotes);
				sqlRow.setText(returnedRow);
				}
				
				//extragere in functie de email
				else if(nNume.matches("") & nPrenume.matches("") & nNrMobil.matches("") & nNrHome.matches("") & nNrWork.matches("") & !nEmail.matches("") & nAddr.matches("") & nRow.matches(""))
				{	
				String returnedNume = db1.getNume(nEmail, ContacteHandlerSQL.KEY_EMAIL);
				String returnedPrenume = db1.getPrenume(nEmail, ContacteHandlerSQL.KEY_EMAIL);
				String returnedNbMobil = db1.getNbMobil(nEmail, ContacteHandlerSQL.KEY_EMAIL);
				String returnedNbHome = db1.getNbHome(nEmail, ContacteHandlerSQL.KEY_EMAIL);
				String returnedNbWork = db1.getNbWork(nEmail, ContacteHandlerSQL.KEY_EMAIL);
				String returnedEmail = db1.getEmail(nEmail, ContacteHandlerSQL.KEY_EMAIL);
				String returnedFax = db1.getFax(nEmail, ContacteHandlerSQL.KEY_EMAIL);
				String returnedAddress = db1.getAddress(nEmail, ContacteHandlerSQL.KEY_EMAIL);
				String returnedNotes = db1.getNotes(nEmail, ContacteHandlerSQL.KEY_EMAIL);
				String returnedRow = db1.getRow(nEmail, ContacteHandlerSQL.KEY_EMAIL);
				db1.close();
				sqlNume.setText(returnedNume);
				sqlPrenume.setText(returnedPrenume);
				sqlNb_Mobil.setText(returnedNbMobil);
				sqlNb_Home.setText(returnedNbHome);
				sqlNb_Work.setText(returnedNbWork);
				sqlEmail.setText(returnedEmail);
				sqlFax.setText(returnedFax);
				sqlAddress.setText(returnedAddress);
				sqlNotes.setText(returnedNotes);
				sqlRow.setText(returnedRow);
				}
				
				//extragere in functie de adresa
				else if(nNume.matches("") & nPrenume.matches("") & nNrMobil.matches("") & nNrHome.matches("") & nNrWork.matches("") & nEmail.matches("") & !nAddr.matches("") & nRow.matches(""))
				{	
				String returnedNume = db1.getNume(nAddr, ContacteHandlerSQL.KEY_ADDRESS);
				String returnedPrenume = db1.getPrenume(nAddr, ContacteHandlerSQL.KEY_ADDRESS);
				String returnedNbMobil = db1.getNbMobil(nAddr, ContacteHandlerSQL.KEY_ADDRESS);
				String returnedNbHome = db1.getNbHome(nAddr, ContacteHandlerSQL.KEY_ADDRESS);
				String returnedNbWork = db1.getNbWork(nAddr, ContacteHandlerSQL.KEY_ADDRESS);
				String returnedEmail = db1.getEmail(nAddr, ContacteHandlerSQL.KEY_ADDRESS);
				String returnedFax = db1.getFax(nAddr, ContacteHandlerSQL.KEY_ADDRESS);
				String returnedAddress = db1.getAddress(nAddr, ContacteHandlerSQL.KEY_ADDRESS);
				String returnedNotes = db1.getNotes(nAddr, ContacteHandlerSQL.KEY_ADDRESS);
				String returnedRow = db1.getRow(nAddr, ContacteHandlerSQL.KEY_ADDRESS);
				db1.close();
				sqlNume.setText(returnedNume);
				sqlPrenume.setText(returnedPrenume);
				sqlNb_Mobil.setText(returnedNbMobil);
				sqlNb_Home.setText(returnedNbHome);
				sqlNb_Work.setText(returnedNbWork);
				sqlEmail.setText(returnedEmail);
				sqlFax.setText(returnedFax);
				sqlAddress.setText(returnedAddress);
				sqlNotes.setText(returnedNotes);
				sqlRow.setText(returnedRow);
				}
				
				//extragere in functie de id
				else if(nNume.matches("") & nPrenume.matches("") & nNrMobil.matches("") & nNrHome.matches("") & nNrWork.matches("") & nEmail.matches("") & nAddr.matches("") & !nRow.matches(""))
				{	
				String returnedNume = db1.getNume(nRow, ContacteHandlerSQL.KEY_ROWID);
				String returnedPrenume = db1.getPrenume(nRow, ContacteHandlerSQL.KEY_ROWID);
				String returnedNbMobil = db1.getNbMobil(nRow, ContacteHandlerSQL.KEY_ROWID);
				String returnedNbHome = db1.getNbHome(nRow, ContacteHandlerSQL.KEY_ROWID);
				String returnedNbWork = db1.getNbWork(nRow, ContacteHandlerSQL.KEY_ROWID);
				String returnedEmail = db1.getEmail(nRow, ContacteHandlerSQL.KEY_ROWID);
				String returnedFax = db1.getFax(nRow, ContacteHandlerSQL.KEY_ROWID);
				String returnedAddress = db1.getAddress(nRow, ContacteHandlerSQL.KEY_ROWID);
				String returnedNotes = db1.getNotes(nRow, ContacteHandlerSQL.KEY_ROWID);
				String returnedRow = db1.getRow(nRow, ContacteHandlerSQL.KEY_ROWID);
				db1.close();
				sqlNume.setText(returnedNume);
				sqlPrenume.setText(returnedPrenume);
				sqlNb_Mobil.setText(returnedNbMobil);
				sqlNb_Home.setText(returnedNbHome);
				sqlNb_Work.setText(returnedNbWork);
				sqlEmail.setText(returnedEmail);
				sqlFax.setText(returnedFax);
				sqlAddress.setText(returnedAddress);
				sqlNotes.setText(returnedNotes);
				sqlRow.setText(returnedRow);
				}
				//daca nu a fost completat nici un camp sau mai mult de un camp, se afiseaza mesaj
				else{
					Dialog d = new Dialog(this);
					d.setTitle("Eroare!");
					TextView tv = new TextView(this);
					tv.setText("Nu a fost completat nici un camp sau a fost completat mai mult de un camp!");
					d.setContentView(tv);
					d.show();
				}
				//pentru date introduse gresit
			} catch (Exception e) {
				Dialog d = new Dialog(this);
				d.setTitle("Eroare!");
				TextView tv = new TextView(this);
				tv.setText("Data inexistenta sau introdusa gresit.");
				d.setContentView(tv);
				d.show();
			}
			break;
		
		//in cazul butonului de editare contact
		case R.id.contacts_get_contact_info_b_SQLmodify:
			boolean didItWork = true;
			try {
				String mNume = sqlNume.getText().toString();
				String mPrenume = sqlPrenume.getText().toString();
				String mNbMobil = sqlNb_Mobil.getText().toString();
				String mNbHome = sqlNb_Home.getText().toString();
				String mNbWork = sqlNb_Work.getText().toString();
				String mEmail = sqlEmail.getText().toString();
				String mFax = sqlFax.getText().toString();
				String mAddress = sqlAddress.getText().toString();
				String mNotes = sqlNotes.getText().toString();
				String sRow2 = sqlRow.getText().toString();
				
				//editarea se face in functie de id rand
				long lRow2 = Long.parseLong(sRow2);

				ContacteHandlerSQL db2 = new ContacteHandlerSQL(this);
				db2.open();
				//updatare intrare
				db2.updateEntry(lRow2, mNume, mPrenume, mNbMobil, mNbHome,
						mNbWork, mEmail, mFax, mAddress, mNotes);
				db2.close();
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
				// afisam dialog pentru succes
				if (didItWork) {
					Dialog d = new Dialog(this);
					d.setTitle("Contact modificat!");
					TextView tv = new TextView(this);
					tv.setText("Succes");
					d.setContentView(tv);
					d.show();
				}
			}
			break;

		}

	}

}
