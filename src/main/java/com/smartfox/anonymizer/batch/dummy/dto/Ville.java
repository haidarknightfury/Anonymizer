package com.smartfox.anonymizer.batch.dummy.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Ville {

	private String Name;

	public String getvName() {
		return Name;
	}

	public void setvName(String vName) {
		this.Name = vName;
	}
	
	@Override
    public String toString() {
        return  this.Name;
    }
}
