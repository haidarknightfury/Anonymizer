package com.smartfox.anonymizer.batch.dummy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.smartfox.anonymizer.batch.dummy.entity.base.AbstractIdentifiedEntity;

@Entity
@Table(name = "email", schema = "person_ref")
@SequenceGenerator(name = "default_gen", sequenceName = "email_seq", allocationSize = 1)
public class Email extends AbstractIdentifiedEntity {

    public Email() {
    }

    @Column(name = "email")
    private String email;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((email == null) ? 0 : email.hashCode());
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
        Email other = (Email) obj;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        return true;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Email(String email) {
        super();
        this.email = email;
    }

    @Override
    public String toString() {
        return "Email [email=" + email + "]";
    }

}
