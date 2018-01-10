package com.example.personalagenda;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

//clasa folosita pentru vizualizarea contactelor
public class ContacteViewContacts extends Activity implements OnClickListener {

	Button bBack;

	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.contacts_view_contacts);

		initButtons();
		//setare listener buton
		bBack.setOnClickListener(this);
		
		//afisare date in textview
		TextView tv = (TextView) findViewById(R.id.contacts_view_contacts_tv_SQLinfo);
		ContacteHandlerSQL info = new ContacteHandlerSQL(this);// pasam continutul aceste clase
		info.open();// deschidem HandleSQL
		String data = info.getData();// returnam string din getData
		info.close();// inchidem HandleSQL
		tv.setText(data);
	}

	//initializare butoane
	public void initButtons() {
		bBack = (Button) findViewById(R.id.contacts_view_contacts_b_back_button);
	}

	//tratare buton Inapoi
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		case R.id.contacts_view_contacts_b_back_button:
			Intent i = new Intent("com.example.personalagenda.Contacte");
			startActivity(i);
			break;

		}

	}
}
