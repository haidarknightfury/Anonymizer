package com.smartfox.anonymizer.batch.dummy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.smartfox.anonymizer.batch.dummy.entity.base.AbstractIdentifiedEntity;

@Entity
@Table(name = "person", schema = "person_ref")
@SequenceGenerator(name = "default_gen", sequenceName = "person_seq", allocationSize = 1)
public class Person extends AbstractIdentifiedEntity {

    @Column(name = "city")
    private String city;

    @Column(name = "first")
    private String first;
    @Column(name = "gender")
    private String gender;

    @Column(name = "last")
    private String last;

    @Column(name = "street")
    private String street;

    public Person() {
        super();
    }

    public Person(String gender, String first, String last, String city, String street) {
        super();
        this.gender = gender;
        this.first = first;
        this.last = last;
        this.city = city;
        this.street = street;
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
        Person other = (Person) obj;
        if (this.city == null) {
            if (other.city != null) {
                return false;
            }
        } else if (!this.city.equals(other.city)) {
            return false;
        }
        if (this.first == null) {
            if (other.first != null) {
                return false;
            }
        } else if (!this.first.equals(other.first)) {
            return false;
        }
        if (this.gender == null) {
            if (other.gender != null) {
                return false;
            }
        } else if (!this.gender.equals(other.gender)) {
            return false;
        }
        if (this.last == null) {
            if (other.last != null) {
                return false;
            }
        } else if (!this.last.equals(other.last)) {
            return false;
        }
        if (this.street == null) {
            if (other.street != null) {
                return false;
            }
        } else if (!this.street.equals(other.street)) {
            return false;
        }
        return true;
    }

    public String getCity() {
        return this.city;
    }

    public String getFirst() {
        return this.first;
    }

    public String getGender() {
        return this.gender;
    }

    public String getLast() {
        return this.last;
    }

    public String getStreet() {
        return this.street;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (this.city == null ? 0 : this.city.hashCode());
        result = prime * result + (this.first == null ? 0 : this.first.hashCode());
        result = prime * result + (this.gender == null ? 0 : this.gender.hashCode());
        result = prime * result + (this.last == null ? 0 : this.last.hashCode());
        result = prime * result + (this.street == null ? 0 : this.street.hashCode());
        return result;
    }

    public void setCity(String location) {
        this.city = location;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public void setStreet(String street) {
        this.street = street;
    }
    
    public String getAddress()
    {
    	return this.street + ", " + this.city; 
    }

    @Override
    public String toString() {
        return "Person [gender=" + this.gender + ", first=" + this.first + ", last=" + this.last + ", city=" + this.city + ", street=" + this.street + "]";
    }

}
