package com.example.personalagenda;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

// folosita pentru dialogurile de stergere (Delete)
public class MemoHandlerConfirmareDialogDelete{

	// dialog Sterge memo
	public static void showConfirmDeleteDialogForMemo(Activity sourceActivity, MemoMemo memo, MemoHandlerSQL databaseAdapter){
		Dialog confirmCancelDialog;
		confirmCancelDialog = new AlertDialog.Builder(sourceActivity)
		.setIcon(android.R.drawable.ic_menu_help)
		.setTitle("Doriti sa stergeti?")
		.setPositiveButton("Da",
				new ConfirmareDeleteCuDA(sourceActivity, memo, databaseAdapter))
		.setNegativeButton("Nu",
				new ConfirmareDeleteCuNU())
		.create();
		confirmCancelDialog.show();
	}


	// clasa pentru a trata butonu DA
	private static class ConfirmareDeleteCuDA implements OnClickListener{

		private Activity sourceActivity;
		
		// memo de sters
		private MemoMemo memo;
		
		private MemoHandlerSQL databaseAdapter;

		// constructor
		public ConfirmareDeleteCuDA(Activity sourceActivity, MemoMemo memo, MemoHandlerSQL databaseAdapter){
			this.sourceActivity = sourceActivity;
			this.memo = memo;
			this.databaseAdapter = databaseAdapter;
		}

		@Override
		public void onClick(DialogInterface dialog, int which) {
			// sterge memo
			databaseAdapter.deleteMemo(memo);
			
			// daca DA, se termina activitatea
			sourceActivity.finish();
		}

	}

	// clasa pentru a trata butonul NU
	private static class ConfirmareDeleteCuNU implements OnClickListener{

		@Override
		public void onClick(DialogInterface dialog, int which) {
			// daca NU, inchide dialogul
			dialog.dismiss();
		}

	}
}
