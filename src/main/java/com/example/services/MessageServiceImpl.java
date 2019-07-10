package com.example.services;

import com.example.domain.Message;
import com.example.exceptions.NotFoundException;
import com.example.repo.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepo messageRepo;

    @Override
    public List<Message> findAll() {
        return messageRepo.findAll();
    }

    @Override
    public List<Message> findByText(String text) {
        return messageRepo.findByText(text);
    }

    @Override
    public Message findById(Long id) {
        return messageRepo.findById(id).orElseThrow(() -> new NotFoundException());
    }

    @Override
    public Message save(Message message) {
        return messageRepo.save(message);
    }

    @Override
    public void delete(Message message) {
        messageRepo.delete(message);
    }
}
