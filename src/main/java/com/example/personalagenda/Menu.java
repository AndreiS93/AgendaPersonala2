package com.example.personalagenda;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

// Clasa MENU defineste  o lista cu activitatile care vor putea fi rulate de aceasta aplicatie
public class Menu extends ListActivity{
	
	String classes[] = {"Contacte", "Memo", "TransportUTCN",  "Contact"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(Menu.this, android.R.layout.simple_list_item_1, classes));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		
		super.onListItemClick(l, v, position, id);
		String class_nb = classes[position];
		try{
		Class<?> ourClass = Class.forName("com.example.personalagenda." + class_nb);
		//start activitate selectata
		Intent ourIntent = new Intent(Menu.this, ourClass);
		startActivity(ourIntent);
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}

	
	
	

}
