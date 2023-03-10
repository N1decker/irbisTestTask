package com.nidecker.testtask.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@ToString
@AllArgsConstructor
@Table(name = "source")
public class Source extends BaseEntity{
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Source source = (Source) o;
        return id != null && Objects.equals(id, source.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
