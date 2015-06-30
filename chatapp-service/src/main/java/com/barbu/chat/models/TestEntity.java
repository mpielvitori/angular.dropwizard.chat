package com.barbu.chat.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import org.joda.time.LocalDate;

@Entity
@Table(name = "Testentities")
public class TestEntity {

    @Id
    @GeneratedValue
    private long id;

    
    @NotNull
    @Column(name = "name")
    
    
    
    
    
    private String name;
    
    @NotNull
    @Column(name = "time")
    
    
    
    
    
    private LocalDate time;
    

    public long getId() {
        return id;
    }

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }
    

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TestEntity)) return false;
        TestEntity that = (TestEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
