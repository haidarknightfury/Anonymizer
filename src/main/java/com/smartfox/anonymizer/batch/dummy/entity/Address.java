package com.smartfox.anonymizer.batch.dummy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.smartfox.anonymizer.batch.dummy.entity.base.AbstractIdentifiedEntity;

@Entity
@Table(name = "address", schema = "person_ref")
@SequenceGenerator(name = "default_gen", sequenceName = "address_seq", allocationSize = 1)
public class Address extends AbstractIdentifiedEntity {

    public Address() {
    }

    @Column(name = "address")
    private String address;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((address == null) ? 0 : address.hashCode());
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
        Address other = (Address) obj;
        if (address == null) {
            if (other.address != null)
                return false;
        } else if (!address.equals(other.address))
            return false;
        return true;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Address(String address) {
        super();
        this.address = address;
    }

    @Override
    public String toString() {
        return "Address [address=" + address + "]";
    }

}
