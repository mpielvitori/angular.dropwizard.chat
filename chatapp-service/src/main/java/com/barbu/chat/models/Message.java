package com.barbu.chat.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import org.joda.time.DateTime;

@Entity
@Table(name = "Messages")
public class Message {

    @Id
    @GeneratedValue
    private long id;


    @NotNull
    @Column(name = "message")





    private String message;

    @NotNull
    @Column(name = "nickname")





    private String nickname;

    @NotNull
    @Column(name = "room")

    @Min(value = 1)



    private Integer room;

    @NotNull
    @Column(name = "time")





    private DateTime time;


    public long getId() {
        return id;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getRoom() {
        return room;
    }

    public void setRoom(Integer room) {
        this.room = room;
    }

    public DateTime getTime() {
        return time;
    }

    public void setTime(DateTime time) {
        this.time = time;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message)) return false;
        Message that = (Message) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
