package com.example.services_test;

import com.example.domain.Message;
import com.example.repo.MessageRepo;
import com.example.services.MessageServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class MessageServiceTest {

    @Mock
    private MessageRepo messageRepo;

    @InjectMocks
    private MessageServiceImpl messageService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllMessagesTest() {
        List<Message> messages = new ArrayList<>(Arrays.asList(
                new Message("message1"),
                new Message("message2"),
                new Message("message3")));

        when(messageRepo.findAll()).thenReturn(messages);
        assertThat(messageService.findAll().size()).isEqualTo(3);
    }

    @Test
    public void getMessagesByTextTest() {
        String message = "message";
        List<Message> messages = new ArrayList<>(Arrays.asList(
                new Message(message)));

        when(messageRepo.findByText(message)).thenReturn(messages);
        List<Message> byText = messageService.findByText(message);

        assertThat(byText.size()).isEqualTo(1);
        assertThat(byText.get(0).getText()).isEqualTo(message);
    }

    @Test
    public void getMessageById() {
        String byId = "byId";
        long id = 1;
        when(messageRepo.findById(id)).thenReturn(java.util.Optional.of(new Message("byId")));
        assertThat(messageService.findById(id).getText()).isEqualTo(byId);
    }

}
