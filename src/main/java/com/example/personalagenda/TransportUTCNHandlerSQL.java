package com.example.personalagenda;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//baza de date pentru orarul autobuzelor
public class TransportUTCNHandlerSQL {

	// variabile statice finale
	public static final String KEY_ROWID = "_id";
	public static final String KEY_LINE = "_busline";
	public static final String KEY_TYPE = "_bustype";
	public static final String KEY_SOURCE = "_sourcestation";
	public static final String KEY_DEST = "_destinationstation";
	public static final String KEY_DIST = "_distance";
	public static final String KEY_DURATION = "_duration";
	public static final String KEY_WD_HOURS1 = "_wdHours1";
	public static final String KEY_W_HOURS1 = "_wHours1";
	public static final String KEY_WD_HOURS2 = "_wdHours2";
	public static final String KEY_W_HOURS2 = "_wHours2";

	// setare baza de date: nume db, nume tabel, versiune
	private static final String DATABASE_NAME = "TransportUtcnSQL";
	private static final String DATABASE_TABLE = "busesTable";
	private static final int DATABASE_VERSION = 1;

	// instanta clasa
	public DbHelper helper;

	// context clasa
	private final Context context;

	// instanta clasa SQLiteDatabase
	private SQLiteDatabase database;

	// clasa interioara ce construieste baza de date
	private static class DbHelper extends SQLiteOpenHelper {

		// constructor
		public DbHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		// la apelare onCreate se executa cod SQL
		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" + KEY_ROWID
					+ " INTEGER PRIMARY KEY AUTOINCREMENT, " 
					+ KEY_LINE + " TEXT NOT NULL, " 
					+ KEY_TYPE + " TEXT NOT NULL, "
					+ KEY_SOURCE + " TEXT NOT NULL, " 
					+ KEY_DEST + " TEXT NOT NULL, " 
					+ KEY_DIST + " TEXT NOT NULL, "
					+ KEY_DURATION + " TEXT NOT NULL, " 
					+ KEY_WD_HOURS1 + " TEXT NOT NULL, "
					+ KEY_W_HOURS1 + " TEXT NOT NULL, " 
					+ KEY_WD_HOURS2 + " TEXT NOT NULL, " 
					+ KEY_W_HOURS2 + " TEXT NOT NULL);");

		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME);
			onCreate(db);
		}
	}

	// constructor
	public TransportUTCNHandlerSQL(Context c) {
		context = c;
	}

	// metode prelucrare baza de date

	// deschidere baza de date pentru citire - scriere
	public TransportUTCNHandlerSQL open() throws SQLException {

		// setam variabilele private
		helper = new DbHelper(context);
		// deschidere baza de date
		database = helper.getWritableDatabase();
		return this;
	}

	// inchidere baza de date
	public void close() {
		helper.close();
	}

	// scriere in baza de date
	public long createEntry(String linie, String tip, String statieSursa, String statieDestinatie,
			String distanta, String durata, String WDore1, String Wore1, String WDore2, String Wore2) {

		// bundle like :)
		ContentValues cv = new ContentValues();

		cv.put(KEY_LINE, linie);
		cv.put(KEY_TYPE, tip);
		cv.put(KEY_SOURCE, statieSursa);
		cv.put(KEY_DEST, statieDestinatie);
		cv.put(KEY_DIST, distanta);
		cv.put(KEY_DURATION, durata);
		cv.put(KEY_WD_HOURS1, WDore1);
		cv.put(KEY_W_HOURS1, Wore1);
		cv.put(KEY_WD_HOURS2, WDore2);
		cv.put(KEY_W_HOURS2, Wore2);

		return database.insert(DATABASE_TABLE, null, cv);
	}
	
	//stergere baza de date
	public void delete() throws SQLException{
		context.deleteDatabase(DATABASE_NAME);
	}

	// extragere date din baza de date - metode getter

	// tip
	public String getBusType(String linie, String searchAfter) throws SQLException {
		String rez_hours = "SELECT " + KEY_TYPE + " FROM " + DATABASE_TABLE
				+ " WHERE " + searchAfter + "='" + linie + "';";
		Cursor cursor = database.rawQuery(rez_hours, null);
		if (cursor != null) {
			cursor.moveToFirst();
			String rez = cursor.getString(cursor.getColumnIndex(KEY_TYPE));
			return rez;
		}
		
		return null;
	}
	
	// source
	public String getsSource(String linie, String searchAfter) throws SQLException {
		String rez_hours = "SELECT " + KEY_SOURCE + " FROM " + DATABASE_TABLE
				+ " WHERE " + searchAfter + "='" + linie + "';";
		Cursor cursor = database.rawQuery(rez_hours, null);
		if (cursor != null) {
			cursor.moveToFirst();
			String rez = cursor.getString(cursor.getColumnIndex(KEY_SOURCE));
			return rez;
		}
		
		return null;
	}

	// destination
	public String getDestination(String linie, String searchAfter) throws SQLException {
		String rez_hours = "SELECT " + KEY_DEST + " FROM " + DATABASE_TABLE
				+ " WHERE " + searchAfter + "='" + linie + "';";
		Cursor cursor = database.rawQuery(rez_hours, null);
		if (cursor != null) {
			cursor.moveToFirst();
			String rez = cursor.getString(cursor.getColumnIndex(KEY_DEST));
			return rez;
		}
		
		return null;
	}

	// distance
	public String getDistance(String linie, String searchAfter) throws SQLException {
		String rez_hours = "SELECT " + KEY_DIST + " FROM " + DATABASE_TABLE
				+ " WHERE " + searchAfter + "='" + linie + "';";
		Cursor cursor = database.rawQuery(rez_hours, null);
		if (cursor != null) {
			cursor.moveToFirst();
			String rez = cursor.getString(cursor.getColumnIndex(KEY_DIST));
			return rez;
		}
		
		return null;
	}

	// duration
		public String getDuration(String linie, String searchAfter) throws SQLException {
			String rez_hours = "SELECT " + KEY_DURATION + " FROM " + DATABASE_TABLE
					+ " WHERE " + searchAfter + "='" + linie + "';";
			Cursor cursor = database.rawQuery(rez_hours, null);
			if (cursor != null) {
				cursor.moveToFirst();
				String rez = cursor.getString(cursor.getColumnIndex(KEY_DURATION));
				return rez;
			}
			
			return null;
		}

	// working days hours 1
	public String getWDHours1(String linie, String searchAfter) throws SQLException {
		String rez_hours = "SELECT " + KEY_WD_HOURS1 + " FROM " + DATABASE_TABLE
				+ " WHERE " + searchAfter + "='" + linie + "';";
		Cursor cursorH = database.rawQuery(rez_hours, null);
		if (cursorH != null) {
			cursorH.moveToFirst();
			String rezH = cursorH.getString(cursorH.getColumnIndex(KEY_WD_HOURS1));
			return rezH;
		}
		
		return null;
	}
	
	// weekend hours 1
		public String getWHours1(String linie, String searchAfter) throws SQLException {
			String rez_hours = "SELECT " + KEY_W_HOURS1 + " FROM " + DATABASE_TABLE
					+ " WHERE " + searchAfter + "='" + linie + "';";
			Cursor cursorH = database.rawQuery(rez_hours, null);
			if (cursorH != null) {
				cursorH.moveToFirst();
				String rezH = cursorH.getString(cursorH.getColumnIndex(KEY_W_HOURS1));
				return rezH;
			}
			
			return null;
		}
		
		// working days hours 2
		public String getWDHours2(String linie, String searchAfter) throws SQLException {
			String rez_hours = "SELECT " + KEY_WD_HOURS2 + " FROM " + DATABASE_TABLE
					+ " WHERE " + searchAfter + "='" + linie + "';";
			Cursor cursorH = database.rawQuery(rez_hours, null);
			if (cursorH != null) {
				cursorH.moveToFirst();
				String rezH = cursorH.getString(cursorH.getColumnIndex(KEY_WD_HOURS2));
				return rezH;
			}
			
			return null;
		}
		
		// weekend hours 2
			public String getWHours2(String linie, String searchAfter) throws SQLException {
				String rez_hours = "SELECT " + KEY_W_HOURS2 + " FROM " + DATABASE_TABLE
						+ " WHERE " + searchAfter + "='" + linie + "';";
				Cursor cursorH = database.rawQuery(rez_hours, null);
				if (cursorH != null) {
					cursorH.moveToFirst();
					String rezH = cursorH.getString(cursorH.getColumnIndex(KEY_W_HOURS2));
					return rezH;
				}
				
				return null;
			}

}
