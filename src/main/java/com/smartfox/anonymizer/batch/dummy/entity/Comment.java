package com.smartfox.anonymizer.batch.dummy.entity;

import javax.persistence.Column;

import com.smartfox.anonymizer.batch.dummy.entity.base.AbstractIdentifiedEntity;

public class Comment extends AbstractIdentifiedEntity {

    public Comment() {
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Column(name = "comment")
    private String comment;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((comment == null) ? 0 : comment.hashCode());
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
        Comment other = (Comment) obj;
        if (comment == null) {
            if (other.comment != null)
                return false;
        } else if (!comment.equals(other.comment))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Comment [comment=" + comment + "]";
    }

}
