package com.example.personalagenda;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

// folosita pentru dialogurile de renuntare (Cancel)
public class MemoHandlerConfirmareDialogCancel {
	
	// dialog folosit in cazul in care datele nu au fost salvate
	public static void showConfirmCancelDialog(Activity sourceActivity){
		Dialog confirmCancelDialog;
		confirmCancelDialog = new AlertDialog.Builder(sourceActivity)
			.setIcon(android.R.drawable.ic_menu_help)
			.setTitle(R.string.memo_modif_memo_diag_tv_nu)
			.setPositiveButton(R.string.memo_modif_memo_diag_cancel_poz,
					new ConfirmareCancelCuDA(sourceActivity))
			.setNegativeButton(R.string.memo_modif_memo_diag_cancel_neg,
					new ConfirmareCancelCuNu())
			.create();
		confirmCancelDialog.show();
	}
	
	
	//clasa pentru a trata butonul DA
	private static class ConfirmareCancelCuDA implements OnClickListener{

		private Activity sourceActivity;
		
		// constructor
		public ConfirmareCancelCuDA(Activity sourceActivity){
			this.sourceActivity = sourceActivity;
		}
		
		@Override
		public void onClick(DialogInterface dialog, int which) {
			// daca DA, se termina activitatea
			sourceActivity.finish();
		}
		
	}
	
	// clasa pentru a trata butonul NU
	private static class ConfirmareCancelCuNu implements OnClickListener{

		@Override
		public void onClick(DialogInterface dialog, int which) {
			// daca NU, se inchide dialogul
			dialog.dismiss();
		}
		
	}
}
