package com.example.personalagenda;

import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class DailyPlanner extends Activity implements View.OnClickListener{

	Button chkCmd;
	Button passTog;
	EditText input;
	TextView display;
	int flag = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.text);
		initButtons();		
		
		passTog.setOnClickListener(this);
		chkCmd.setOnClickListener(this);
	}
	private void initButtons() {
		// TODO Auto-generated method stub
		chkCmd = (Button)findViewById(R.id.Bresults);
		passTog = (ToggleButton)findViewById(R.id.tbPassword);
		input = (EditText)findViewById(R.id.etComands);
		display = (TextView)findViewById(R.id.tvResults);
		
	}
	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch(view.getId()){
		case R.id.Bresults:
			// TODO Auto-generated method stub
			String check = input.getText().toString();
			display.setText(check);
			if (check.contentEquals("left")){
				display.setGravity(Gravity.LEFT);
			}else if(check.contentEquals("center")){
				display.setGravity(Gravity.CENTER);
			}else if(check.contentEquals("blue")){
				display.setTextColor(Color.BLUE);
			}else if(check.contains("WTF")){
				Random crazy = new Random();
				display.setText("WTF!!!!");
				display.setTextSize(crazy.nextInt(75));
				display.setTextColor(Color.rgb(crazy.nextInt(255), crazy.nextInt(255), crazy.nextInt(255)));
				switch(crazy.nextInt(3)){
				case 0:
					display.setGravity(Gravity.LEFT);
					break;
				case 1:
					display.setGravity(Gravity.CENTER);
					break;
				case 2:
					display.setGravity(Gravity.RIGHT);
					break;
				}
			}else{
				display.setText("Invalid");
				display.setGravity(Gravity.CENTER);
				display.setTextColor(Color.WHITE);
			}
			break;
		case R.id.tbPassword:
			// TODO Auto-generated method stub
			if (((CompoundButton) passTog).isChecked()){
				flag = 1;
				Intent i = new Intent( getApplicationContext(),Splash.class);
				Bundle extras = new Bundle();
				extras.putString("FLAG", "1");
				i.putExtras(extras);
				startActivity(i);

			}else{
				flag = 1;
				Intent i = new Intent( getApplicationContext(),Splash.class);
				Bundle extras = new Bundle();
				extras.putString("FLAG", "0");
				i.putExtras(extras);
				startActivity(i);
			}
			break;
			
		}
	}
}
