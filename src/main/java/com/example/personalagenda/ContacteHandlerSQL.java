package com.example.personalagenda;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//baza de date pentru contacte
public class ContacteHandlerSQL {
	// ca un spreadsheet, e mai usor sa managezi data
	// mai usor de referentiat
	// nu vrem sa schimbam numele coloanelor
	// folosim string pt toate variabilele bazei de date

	// static final - variabilele nu se schimba
	public static final String KEY_ROWID = "_id";
	public static final String KEY_NUME = "person_nume";
	public static final String KEY_PRENUME = "person_prenume";
	public static final String KEY_NR_MOBIL = "person_mobile_number";
	public static final String KEY_NR_HOME = "person_home_number";
	public static final String KEY_NR_WORK = "person_work_number";
	public static final String KEY_EMAIL = "person_email";
	public static final String KEY_FAX = "person_fax";
	public static final String KEY_ADDRESS = "person_address";
	public static final String KEY_NOTES ="person_notes";

	// setare baza de date
	// private - clasa o poate accesa
	private static final String DATABASE_NAME = "ContactsDB";
	private static final String DATABASE_TABLE = "peopleTable";
	private static final int DATABASE_VERSION = 1;

	// instanta pentru aceasta clasa
	public DbHelper ourHelper;
	// context pentru clasa
	private final Context ourContext; // contructorul pentru clasa SQLHandle
										// va fi seatat pentru var privata
	// instanta pentru clasa SQLiteDatabase
	private SQLiteDatabase ourDatabase;

	// clasa interioara ce construieste baza de date
	private static class DbHelper extends SQLiteOpenHelper {

		public DbHelper(Context context) {
			// name - nume baza de date (HandleSQL)
			// string - nume baza de date
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		// va crea baza de date
		// cand se apeleaza onCreate(SQLiteDatabase db) ii se paseaza db,
		// care va accesa baza de date si va executa cod SQL
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			// KEY_ROWID chiar daca a fost definit STRING, in baza de date el este un INT
			// KEY_NUMBER chiar daca a fost definit INT, in baza de date el este un STRING (mai usor de lucru)
			db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" + KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " 
					+ KEY_NUME + " COLLATE NOCASE, " 
					+ KEY_PRENUME + " COLLATE NOCASE, "
					+ KEY_NR_MOBIL + " TEXT NOT NULL, "
					+ KEY_NR_HOME + " TEXT NOT NULL, "
					+ KEY_NR_WORK + " TEXT NOT NULL, "
					+ KEY_EMAIL + " COLLATE NOCASE, "
					+ KEY_FAX + " TEXT NOT NULL, "
					+ KEY_ADDRESS + " COLLATE NOCASE, "
					+ KEY_NOTES + " COLLATE NOCASE);");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			// daca tabelul exista, drop si apel onCreate
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			onCreate(db);
		}

	}

	// contructor
	public ContacteHandlerSQL(Context c) {
		ourContext = c; // pasat din Contacte
	}

	// metode pentru clasa HandleSQL
	// deschidem + inchidem baza de date
	// o referientam, ii dam contextul ce va fi pasat
	public ContacteHandlerSQL open() throws SQLException {
		// setam variabilele private
		ourHelper = new DbHelper(ourContext);
		// deschide baza de date prin ourHelper
		ourDatabase = ourHelper.getWritableDatabase(); // daca putem scrie in
														// ea, o putem si citi
		return this;
	}

	public void close() {
		ourHelper.close(); // inchide clasa SQLiteDatabase
	}

	// folosita pentru a scrie in baza de date
	// se lucreaza ca si cu Bundles
	public long createEntry(String nume, String prenume, String nb_mobil, String nb_home, String nb_work, String email, String fax, String address, String notes) {
		// TODO Auto-generated method stub
		// setare valoare content - ca si un bundle
		ContentValues cv = new ContentValues();
		cv.put(KEY_NUME, nume);
		cv.put(KEY_PRENUME, prenume);
		cv.put(KEY_NR_MOBIL, nb_mobil);
		cv.put(KEY_NR_HOME, nb_home);
		cv.put(KEY_NR_WORK, nb_work);
		cv.put(KEY_EMAIL,email);
		cv.put(KEY_FAX, fax);
		cv.put(KEY_ADDRESS, address);
		cv.put(KEY_NOTES, notes);
		// vrem sa returneze ce inseram
		return ourDatabase.insert(DATABASE_TABLE, null, cv);// insereaza in tabel

	}

	//extragere date
	public String getData() {
		// TODO Auto-generated method stub
		String[] columns = new String[] { KEY_ROWID, KEY_NUME, KEY_PRENUME, KEY_NR_MOBIL, KEY_NR_HOME, 
										  KEY_NR_WORK, KEY_EMAIL, KEY_FAX, KEY_ADDRESS, KEY_NOTES};
		// citim info prin Cursor
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null, null, KEY_NUME+" ASC");
		String result = "";

		// vrem sa citim toate intrarile din baza, se uita dupa coloane
		int iNume = c.getColumnIndex(KEY_NUME);
		int iPrenume = c.getColumnIndex(KEY_PRENUME);
		int iNrMobil = c.getColumnIndex(KEY_NR_MOBIL);
		int iNrHome = c.getColumnIndex(KEY_NR_HOME);
		int iNrWork = c.getColumnIndex(KEY_NR_WORK);
		int iEmail = c.getColumnIndex(KEY_EMAIL);
		int iFax = c.getColumnIndex(KEY_FAX);
		int iAddress = c.getColumnIndex(KEY_ADDRESS);
		int iNotes = c.getColumnIndex(KEY_NOTES);

		// for care va seta cursorul la inceput si va parcurge toata baza
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			//primim stringul de la rand, de la care va lua numele, numarul, etc.
			
			result = result 
					+ c.getString(iNume) + " " 
					+ c.getString(iPrenume) + " \n" 
					+ "Nr. Mobil:" + c.getString(iNrMobil) + "\n" 
					+ "Nr. Acasa:" + c.getString(iNrHome) + "\n" 
					+ "Nr. Munca:" + c.getString(iNrWork) + "\n" 
					+ "Email:" + c.getString(iEmail) + "\n" 
					+ "Fax:" + c.getString(iFax) + "\n" 
					+ "Adresa:" + c.getString(iAddress) + "\n" 
					+ "Note:" + c.getString(iNotes) + "\n"
					+ "__________________________________" +"\n";
		}

		return result;
	}
	
	//returneaza doar un string cu ID, nume, prenume
	public String getDataOnlyId() {
		// TODO Auto-generated method stub
		String[] columns = new String[] { KEY_ROWID, KEY_NUME, KEY_PRENUME, KEY_NR_MOBIL, KEY_NR_HOME, 
										  KEY_NR_WORK, KEY_EMAIL, KEY_FAX, KEY_ADDRESS, KEY_NOTES};
		// citim info prin Cursor
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null, null, KEY_NUME+" ASC");
		String result = "";

		// vrem sa citim toate intrarile din baza
		int iRow = c.getColumnIndex(KEY_ROWID);// se uita dupa coloana ID
		int iNume = c.getColumnIndex(KEY_NUME);// se uita dupa coloana NAME
		int iPrenume = c.getColumnIndex(KEY_PRENUME);// se uita dupa coloana

		// for care va seta cursorul la inceput si va parcurge toata baza
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
		
			//primim stringul de la rand, de la care va lua Id, numele, prenumele.
			
			result = result 
					+ c.getString(iRow) + "  "
					+ c.getString(iNume) + "   " 
					+ c.getString(iPrenume) + "\n"; 
		}

		return result;
	}
	//metode getter
	@SuppressLint("DefaultLocale")
	
	// get nume
	public String getNume(String nume, String searchAfter)throws SQLException {
		// TODO Auto-generated method stub
		String s = "SELECT "+KEY_NUME+" FROM "+DATABASE_TABLE+" WHERE "+searchAfter+"='"+nume+"' COLLATE NOCASE;";
		Cursor c = ourDatabase.rawQuery(s, null);
		if (c!=null){
			c.moveToFirst();
			String p = c.getString(c.getColumnIndex(KEY_NUME));
			return p;
		}
		return null;
	}

	//get prenume
	public String getPrenume(String prenume, String searchAfter)throws SQLException {
		// TODO Auto-generated method stub
		String s = "SELECT "+KEY_PRENUME+" FROM "+DATABASE_TABLE+" WHERE "+searchAfter+"='"+prenume+"';";
		Cursor c = ourDatabase.rawQuery(s, null);
		if (c!=null){
			c.moveToFirst();
			String p = c.getString(c.getColumnIndex(KEY_PRENUME));
			return p;
		}
		return null;
	}
	
	//get nr tel mobil
	public String getNbMobil(String nbMobil, String searchAfter)throws SQLException {
		// TODO Auto-generated method stub
				String s = "SELECT "+KEY_NR_MOBIL+" FROM "+DATABASE_TABLE+" WHERE "+searchAfter+"='"+nbMobil+"';";
				Cursor c = ourDatabase.rawQuery(s, null);
				if (c!=null){
					c.moveToFirst();
					String p = c.getString(c.getColumnIndex(KEY_NR_MOBIL));
					return p;
				}
				return null;
	}
	
	//get nr tel acasa
	public String getNbHome(String nbHome, String searchAfter)throws SQLException {
		// TODO Auto-generated method stub
		String s = "SELECT "+KEY_NR_HOME+" FROM "+DATABASE_TABLE+" WHERE "+searchAfter+"='"+nbHome+"';";
		Cursor c = ourDatabase.rawQuery(s, null);
		if (c!=null){
			c.moveToFirst();
			String p = c.getString(c.getColumnIndex(KEY_NR_HOME));
			return p;
		}
		return null;
	}
	
	//get nr tel munca
	public String getNbWork(String nbWork, String searchAfter)throws SQLException {
		// TODO Auto-generated method stub
				String s = "SELECT "+KEY_NR_WORK+" FROM "+DATABASE_TABLE+" WHERE "+searchAfter+"='"+nbWork+"';";
				Cursor c = ourDatabase.rawQuery(s, null);
				if (c!=null){
					c.moveToFirst();
					String p = c.getString(c.getColumnIndex(KEY_NR_WORK));
					return p;
				}
				return null;
	}
	
	//get email
	public String getEmail(String email, String searchAfter)throws SQLException {
		// TODO Auto-generated method stub
		String s = "SELECT "+KEY_EMAIL+" FROM "+DATABASE_TABLE+" WHERE "+searchAfter+"='"+email+"';";
		Cursor c = ourDatabase.rawQuery(s, null);
		if (c!=null){
			c.moveToFirst();
			String p = c.getString(c.getColumnIndex(KEY_EMAIL));
			return p;
		}
		return null;
	}
	
	//get fax
	public String getFax(String fax, String searchAfter)throws SQLException {
		// TODO Auto-generated method stub
				String s = "SELECT "+KEY_FAX+" FROM "+DATABASE_TABLE+" WHERE "+searchAfter+"='"+fax+"';";
				Cursor c = ourDatabase.rawQuery(s, null);
				if (c!=null){
					c.moveToFirst();
					String p = c.getString(c.getColumnIndex(KEY_FAX));
					return p;
				}
				return null;
	}
	
	//get adresa
	public String getAddress(String addr, String searchAfter)throws SQLException {
		// TODO Auto-generated method stub
		String s = "SELECT "+KEY_ADDRESS+" FROM "+DATABASE_TABLE+" WHERE "+searchAfter+"='"+addr+"';";
		Cursor c = ourDatabase.rawQuery(s, null);
		if (c!=null){
			c.moveToFirst();
			String p = c.getString(c.getColumnIndex(KEY_ADDRESS));
			return p;
		}
		return null;
	}
	
	//get notite
	public String getNotes(String notes, String searchAfter)throws SQLException {
		// TODO Auto-generated method stub
				String s = "SELECT "+KEY_NOTES+" FROM "+DATABASE_TABLE+" WHERE "+searchAfter+"='"+notes+"';";
				Cursor c = ourDatabase.rawQuery(s, null);
				if (c!=null){
					c.moveToFirst();
					String p = c.getString(c.getColumnIndex(KEY_NOTES));
					return p;
				}
				return null;
	}
	
	//get id
	public String getRow(String rowID, String searchAfter)throws SQLException {
		// TODO Auto-generated method stub
				String s = "SELECT "+KEY_ROWID+" FROM "+DATABASE_TABLE+" WHERE "+searchAfter+"='"+rowID+"';";
				Cursor c = ourDatabase.rawQuery(s, null);
				if (c!=null){
					c.moveToFirst();
					String p = c.getString(c.getColumnIndex(KEY_ROWID));
					return p;
				}
				return null;
	}
	
	//sterge contactul dupa ID
	public void deleteEntry(long lRow)throws SQLException {
		// TODO Auto-generated method stub
		ourDatabase.delete(DATABASE_TABLE, KEY_ROWID + "=" + lRow, null);
		
	}

	//updatare baza de date, contact
	public void updateEntry(long lRow, String mNume, String mPrenume, String mNb_mobil, String mNb_home, String mNb_work, String mEmail, String mFax, String mAddress, String mNotes)throws SQLException {
		// TODO Auto-generated method stub
		ContentValues cvUpdate = new ContentValues();
		cvUpdate.put(KEY_NUME, mNume);
		cvUpdate.put(KEY_PRENUME, mPrenume);
		cvUpdate.put(KEY_NR_MOBIL, mNb_mobil);
		cvUpdate.put(KEY_NR_HOME, mNb_home);
		cvUpdate.put(KEY_NR_WORK, mNb_work);
		cvUpdate.put(KEY_EMAIL,mEmail);
		cvUpdate.put(KEY_FAX, mFax);
		cvUpdate.put(KEY_ADDRESS, mAddress);
		cvUpdate.put(KEY_NOTES, mNotes);
		ourDatabase.update(DATABASE_TABLE, cvUpdate, KEY_ROWID + "=" + lRow, null);
		
	}

	
}
