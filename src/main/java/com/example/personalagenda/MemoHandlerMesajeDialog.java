package com.example.personalagenda;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

// folosita pentru administrarea mesajelor de tip dialog
public class MemoHandlerMesajeDialog {

	// activare dialog
	public static void showMessageDialog(Activity sourceActivity, String message){
		Dialog messageDialog;
		messageDialog = new AlertDialog.Builder(sourceActivity)
			.setIcon(android.R.drawable.ic_menu_info_details)
			.setTitle(message)
			.setPositiveButton("Da", new DialogMesajDA())
			.create();
		messageDialog.show();
	}

	// clasa pentru tratarea butonului DA
	private static class DialogMesajDA implements OnClickListener {

		@Override
		public void onClick(DialogInterface dialog, int which) {
			// TODO Auto-generated method stub
			dialog.dismiss();
		}

	}
}
