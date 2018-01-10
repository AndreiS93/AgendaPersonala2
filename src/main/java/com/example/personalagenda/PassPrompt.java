package com.example.personalagenda;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/* This class promts the user to insert a password
 * and checks if input password matches the defined one. 
 */
public class PassPrompt extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.passprompt); //sets the layout to passpromt
		
		final EditText pass_in = (EditText)findViewById(R.id.etPassPrompt);// EditText, used for typing the password
		pass_in.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD); //sets the inputType to password type, input characters become *
		Button pass_press = (Button)findViewById(R.id.PassPromtButton); //OK button for entering the application
		
		//listener for the OK button
		pass_press.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent menu = new Intent("com.example.personalagenda.MENU");
				String check = pass_in.getText().toString(); //define the string for the password
				if (check.contentEquals("parola")) { //compares the input string with the defined one
					startActivity(menu); //if equal, start MENU activity
				} else {
					//try again
					pass_in.setInputType(InputType.TYPE_CLASS_TEXT);
					pass_in.setHint("Parola Incorecta");
					pass_in.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
				}
			}
		});
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish(); //finish PassPrompt activity
	}
	

}
