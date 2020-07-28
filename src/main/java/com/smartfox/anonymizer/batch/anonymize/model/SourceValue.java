package com.smartfox.anonymizer.batch.anonymize.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import com.github.javafaker.Faker;

public class SourceValue implements Serializable {

    private static final long serialVersionUID = 1L;
    private Object id;
    private Integer idType;
    private Object value;
    
    public SourceValue(Object id, String value) {
        super();
        this.id = id;
        this.value = value;
    }

    public SourceValue(Object id, String value, Integer idType) {
        super();
        this.id = id;
        this.idType = idType;
        this.value = value;
    }


	public Object getId() {
        return this.id;
    }

    public Integer getIdType() {
        return this.idType;
    }

    public Object getValue() {
        return this.value;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public void setIdType(Integer idType) {
        this.idType = idType;
    }

    public void setValue(Object value) {
        this.value = value;
    }
    

	@Override
	public String toString() {
		return "SourceValue [id=" + id + ", idType=" + idType + ", value=" + value + "]";
	}


}
