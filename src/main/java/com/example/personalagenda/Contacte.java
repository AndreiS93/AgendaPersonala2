package com.example.personalagenda;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;

//clasa ce se ocupa cu tratarea contactelor
//contine butoane pentru adaugare, cautare, stergere, vizualizare contacte si intoarcere in meniu
public class Contacte extends Activity implements OnClickListener {

	ImageView cMenu_add, cMenu_delete, cMenu_see, cMenu_search;
	Button menu;

	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.contacts_menu);
		initButtons();
		
		
	}

	// initializare butoane si adaugare listeneri pentru acestea
	public void initButtons() {

		cMenu_add = (ImageView) findViewById(R.id.contacts_menu_imgAddContact);
		cMenu_delete = (ImageView) findViewById(R.id.contacts_menu_imgDeleteContact);
		cMenu_search = (ImageView) findViewById(R.id.contacts_menu_imgSearchContact);
		cMenu_see = (ImageView) findViewById(R.id.contacts_menu_imgSeeContact);
		menu = (Button) findViewById(R.id.contacts_menu_MenuList);
		cMenu_add.setOnClickListener(this);
		cMenu_delete.setOnClickListener(this);
		cMenu_search.setOnClickListener(this);
		cMenu_see.setOnClickListener(this);
		menu.setOnClickListener(this);
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_contacts_importexport, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items
		switch (item.getItemId()) {
		case R.id.contacts_saveDb:
			boolean didItWork = true;//succes sau esec
			try{
			exportDB();
			} catch (Exception e) {
				// TODO: handle exception
				String error = e.toString();
				Dialog d = new Dialog(this);
				d.setTitle("Eroare!");
				
				TextView tv = new TextView(this);
				tv.setText(error);
				d.setContentView(tv);
				d.show();
			}finally {
				// afisam dialog in caz de succes
				if (didItWork) {
					Dialog d = new Dialog(this);
					TextView tv = new TextView(this);
					tv.setText("Baza de date a fost exportata.");	
					d.setContentView(tv);
					d.setTitle("Contacte exportate. "); 
					d.show();
				}

			}
			return true;
		case R.id.contacts_getDb:
			boolean didItWork2 = true;//succes sau esec
			try {
			importDB();
			}catch (Exception e) {
				didItWork2 = false;
				String error = e.toString();
				Dialog d = new Dialog(this);
				d.setTitle("Eroare!");
				TextView tv = new TextView(this);
				tv.setText(error);
				d.setContentView(tv);
				d.show();
			} finally {
				// afisam dialog in caz de succes
				if (didItWork2) {
					Dialog d = new Dialog(this);
					d.setTitle("Contacte importate. ");
					TextView tv = new TextView(this);
					tv.setText("Baza de date a fost importata.");
					d.setContentView(tv);
					d.show();
				}

			}
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	// se trateaza evenimentul corespunzatorul butonului
	@Override
	public void onClick(View v) {
		// activare intenturi
		// TODO Auto-generated method stub
		switch (v.getId()) {

		// pentru adaugare contact
		case R.id.contacts_menu_imgAddContact:
			Intent i = new Intent(
					"com.example.personalagenda.ContacteAdaugareContact");
			startActivity(i);
			break;
		// pentru vizualizare contacte
		case R.id.contacts_menu_imgSeeContact:
			Intent i1 = new Intent(
					"com.example.personalagenda.ContacteViewContacts");
			startActivity(i1);
			break;
		// pentru cautare contact
		case R.id.contacts_menu_imgSearchContact:
			Intent i2 = new Intent(
					"com.example.personalagenda.ContacteGetContactInfo");
			startActivity(i2);
			break;
		// pentru stergere contact
		case R.id.contacts_menu_imgDeleteContact:
			Intent i3 = new Intent(
					"com.example.personalagenda.ContacteDeleteContact");
			startActivity(i3);
			break;
		// pentru intoarcere in meniul principal
		case R.id.contacts_menu_MenuList:
			Intent i4 = new Intent("com.example.personalagenda.MENU");
			startActivity(i4);
			break;

		}

	}
	
	//creeare locatie pentru import, export
	public void initDirectory(){
		File direct = new File(Environment.getExternalStorageDirectory() + "/BackupContactDB");

        if(!direct.exists())
         {
             if(direct.mkdir()) 
               {
                //directory is created;
               }

         }
	}
	//import baza de date
    public void importDB() {
        // TODO Auto-generated method stub

        try {
            File sd = Environment.getExternalStorageDirectory();
            File data  = Environment.getDataDirectory();

            if (sd.canWrite()) {
                String  currentDBPath= "//data//" + "com.example.personalagenda"
                        + "//databases//" + "ContactsDB";
                String backupDBPath  = "/BackupContactDB/ContactsDB";
                File  backupDB= new File(data, currentDBPath);
                File currentDB  = new File(sd, backupDBPath);

                FileChannel src = new FileInputStream(currentDB).getChannel();
                FileChannel dst = new FileOutputStream(backupDB).getChannel();
                dst.transferFrom(src, 0, src.size());
                src.close();
                dst.close();
                Toast.makeText(getBaseContext(), backupDB.toString(),Toast.LENGTH_LONG).show();

            }
        } catch (Exception e) {

            Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG)
                    .show();

        }
    }
    
  //export  baza de date
    public void exportDB() {
        // TODO Auto-generated method stub

        try {
            File sd = Environment.getExternalStorageDirectory();
            File data = Environment.getDataDirectory();

            if (sd.canWrite()) {
                String  currentDBPath= "//data//" + "com.example.personalagenda"
                        + "//databases//" + "ContactsDB";
                String backupDBPath  = "/BackupContactDB/ContactsDB";
                File currentDB = new File(data, currentDBPath);
                File backupDB = new File(sd, backupDBPath);

                FileChannel src = new FileInputStream(currentDB).getChannel();
                FileChannel dst = new FileOutputStream(backupDB).getChannel();
                dst.transferFrom(src, 0, src.size());
                src.close();
                dst.close();
                Toast.makeText(getBaseContext(), backupDB.toString(),Toast.LENGTH_LONG).show();

            }
        } catch (Exception e) {

            Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG)
                    .show();

        }
    }
}
