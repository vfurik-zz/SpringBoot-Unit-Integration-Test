package com.example.jpa_tests;

import com.example.domain.Message;
import com.example.repo.MessageRepo;
import com.example.utils.RandomGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MessageRepositoryTest {

    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void verifyGetMessagesByText() {
        String textMessage = RandomGenerator.getRandomString();
        testEntityManager.persist(new Message(textMessage));

        List<Message> byText = messageRepo.findByText(textMessage);
        assertThat(byText.size()).isEqualTo(1);
        assertThat(byText.get(0).getText()).isEqualTo(textMessage);
    }

    @Test
    public void verifyDeleteMessage() {
        Message message = messageRepo.findById(1l).get();
        messageRepo.delete(message);
        assertThat(messageRepo.findAll()).doesNotContain(message);
    }

    @Test
    public void verifyPostMessage() {
        Message message = new Message("POST Message");
        messageRepo.save(message);
        assertThat(messageRepo.findAll()).extracting(Message::getText).contains(message.getText());
    }

    @Test
    public void verifyPutMessage() {
        String updatedText = RandomGenerator.getRandomString();
        Message message = messageRepo.findById(1l).get();
        message.setText(updatedText);
        messageRepo.save(message);
        assertThat(messageRepo.findAll()).extracting(Message::getText).contains(updatedText);
    }

}
