package com.barbu.chat.models;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "Rooms")
public class Room {

    @Id
    @GeneratedValue
    private long id;


    @NotNull
    @Column(name = "name")





    private String name;


    @Column(name = "messages")
    private String messages;


    public long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Deprecated
    public String getMessages() {
        return messages;
    }

    @Deprecated
    public void setMessages(String messages) {
        this.messages = messages;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room)) return false;
        Room that = (Room) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
