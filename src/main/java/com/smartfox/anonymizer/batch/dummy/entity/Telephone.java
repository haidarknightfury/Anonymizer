package com.smartfox.anonymizer.batch.dummy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.smartfox.anonymizer.batch.dummy.entity.base.AbstractIdentifiedEntity;



@Entity
@Table(name = "telephone", schema = "person_ref")
@SequenceGenerator(name = "default_gen", sequenceName = "tlp_seq", allocationSize = 1)
public class Telephone extends AbstractIdentifiedEntity {

	@Column(name = "telephone")
	private String phone;

	public Telephone() {

	}

	public Telephone(String phone) {
		this.phone = phone;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		Telephone other = (Telephone) obj;
		if (this.phone == null) {
			if (other.phone != null) {
				return false;
			}
		} else if (!this.phone.equals(other.phone)) {
			return false;
		}
		return true;
	}

	public String getPhone() {
		return this.phone;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (this.phone == null ? 0 : this.phone.hashCode());
		return result;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Telephone [phone=" + this.phone + "]";
	}


}
