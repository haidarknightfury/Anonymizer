package com.smartfox.anonymizer.batch.dummy.entity;

import com.smartfox.anonymizer.batch.dummy.entity.base.AbstractIdentifiedEntity;

public class Dob extends AbstractIdentifiedEntity {

    public Dob() {
    }

    private String dob;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((dob == null) ? 0 : dob.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Dob other = (Dob) obj;
        if (dob == null) {
            if (other.dob != null)
                return false;
        } else if (!dob.equals(other.dob))
            return false;
        return true;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public Dob(String dob) {
        super();
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "Dob [dob=" + dob + "]";
    }

}
