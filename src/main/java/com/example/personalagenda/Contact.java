package com.example.personalagenda;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;
 //contact dezvoltator
public class Contact extends Activity implements OnClickListener {

	Button meniu_principal;

	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.contact_dezvoltator);
		initButtons();
	}

	// initializare butoane si adaugare listeneri pentru acestea
	public void initButtons() {

		meniu_principal = (Button) findViewById(R.id.contact_dezvoltator_b_meniu_principal);
		meniu_principal.setOnClickListener(this);
	}

	// se trateaza evenimentul corespunzatorul butonului
	@Override
	public void onClick(View v) {
		// activare intenturi
		// TODO Auto-generated method stub
		switch (v.getId()) {

		case R.id.contact_dezvoltator_b_meniu_principal:
			Intent i = new Intent(
					"com.example.personalagenda.MENU");
			startActivity(i);
			break;
		}

	}
}
