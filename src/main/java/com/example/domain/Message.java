package com.example.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "message")
@EqualsAndHashCode(of = {"id"})
//@ToString(of = {"id", "text"})
@Data
public class Message {

    public Message() {
    }

    public Message(String text) {
        this.text = text;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    private String text;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
