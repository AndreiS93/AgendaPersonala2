package com.example.personalagenda;

import java.io.Serializable;
import java.util.List;

// clasa grup
public class MemoGroup implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// variabile pentru adagare in bundle
	public static final String GROUP_BUNDLE_KEY = "group_bundle_key";  
	
	// Id grup, generat aleator
	private String id;
	
	// titlu
	private String title;
	
	public MemoGroup(){
		
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
}
