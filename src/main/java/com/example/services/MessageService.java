package com.example.services;

import com.example.domain.Message;

import java.util.List;

public interface MessageService {

    List<Message> findAll();

    List<Message> findByText(String text);

    Message findById(Long id);

    Message save(Message message);

    void delete(Message message);
}
