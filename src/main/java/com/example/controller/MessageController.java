package com.example.controller;

import com.example.constant.EndPoints;
import com.example.domain.Message;
import com.example.services.MessageService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(EndPoints.Message.MESSAGE)
public class MessageController {

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public List<Message> list(@RequestParam(required = false) String text) {
        return StringUtils.isEmpty(text) ? messageService.findAll() : messageService.findByText(text);
    }

    @GetMapping(EndPoints.UrlParam.ID)
    public Message getOne(@PathVariable("id") Long id) {
        return messageService.findById(id);
    }

    @PostMapping
    public Message create(@RequestBody Message message) {
        Logger.getGlobal().log(Level.INFO, "REQUEST BODY IS: " + message);
        return messageService.save(message);
    }

    @PutMapping(EndPoints.UrlParam.ID)
    public Message update(@PathVariable("id") Message messageFromDB, @RequestBody Message message) {
        BeanUtils.copyProperties(message, messageFromDB, "id");
        return messageService.save(messageFromDB);
    }

    @DeleteMapping(EndPoints.UrlParam.ID)
    public void delete(@PathVariable("id") Message message) {
        messageService.delete(message);
    }

}
