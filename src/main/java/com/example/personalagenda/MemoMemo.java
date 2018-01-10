package com.example.personalagenda;

import java.io.Serializable;
import java.util.Calendar;

// clasa memo
public class MemoMemo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// prioritate memo
	public static final int PR_RIDICATA = 0;
	public static final int PR_MEDIE = 1;
	public static final int PR_SCAZUTA = 2;
	
	// finalizare
	public static final int MEMO_EFECTUAT = 0;
	public static final int MEMO_IN_DERULARE = 1;
	
	public static final String MEMO_BUNDLE_KEY = "memo_bundle_key";  
	
	private String id;
	
	private String title;
	
	private Calendar data;
	
	private String note;

	private int nivelPrioritate;

	private MemoGroup group;
	
	private int gradFinalizareMemo;
	
	public MemoMemo(){
		this.id = "";
		this.title = "";
		this.data = Calendar.getInstance();
		this.note = "";
		this.nivelPrioritate = PR_RIDICATA;
		this.group = new MemoGroup();
		this.gradFinalizareMemo = MEMO_EFECTUAT;
	}
	
	public MemoMemo(String id, String title, Calendar data, String note,
			int nivelPrioritate, MemoGroup group,
			int gradFinalizare) {
		super();
		this.id = id;
		this.title = title;
		this.data = data;
		this.note = note;
		this.nivelPrioritate = nivelPrioritate;
		this.group = group;
		this.gradFinalizareMemo = gradFinalizare;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitlu() {
		return title;
	}

	public void setTitlu(String title) {
		this.title = title;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getNivelPrioritate() {
		return nivelPrioritate;
	}

	public void setNivelPrioritate(int nivPr) {
		this.nivelPrioritate = nivPr;
	}

	public MemoGroup getGroup() {
		return group;
	}

	public void setGroup(MemoGroup group) {
		this.group = group;
	}

	public int setNivelPrioritate() {
		return gradFinalizareMemo;
	}

	public void setGradCompletare(int gradF) {
		this.gradFinalizareMemo = gradF;
	}
	
}
