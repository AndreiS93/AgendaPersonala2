package com.example.personalagenda;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

/* Ecran ce contine logoul aplicatiei si adauga un sunet
 */
public class Splash extends Activity{
	
	MediaPlayer ourSong; //sunet
	
	@Override
	protected void onCreate(Bundle splash) {
		// TODO Auto-generated method stub
		super.onCreate(splash);
		setContentView(R.layout.splash); // setare layout corespunzator
		ourSong = MediaPlayer.create(Splash.this, R.raw.music); //sunet
		ourSong.start(); //start sunet
		
		//se defineste un Thread pentru a permite rularea Splash-ului 5 secunde
		Thread timer = new Thread(){
			public void run(){
				try{
					sleep(2000);
				} catch (InterruptedException e){
					e.printStackTrace();
				} finally {
					Intent openMainActivtity = new Intent("com.example.personalagenda.MENU");
					startActivity(openMainActivtity);
				}
			}
		};
		timer.start();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		ourSong.release(); //oprire muzica
		finish(); //incheiere activitate
	}
	
	
}
