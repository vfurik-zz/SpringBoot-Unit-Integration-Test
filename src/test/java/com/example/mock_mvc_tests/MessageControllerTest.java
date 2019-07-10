package com.example.mock_mvc_tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.constant.EndPoints;
import com.example.domain.Message;
import com.example.utils.SerializationHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void verifyFindAllMessages() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(EndPoints.Message.MESSAGE)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    public void verifyFindMessagesByText() throws Exception {
        String expectedValue = "message5";
        ResultActions text = mockMvc.perform(MockMvcRequestBuilders.get(EndPoints.Message.MESSAGE)
                .accept(MediaType.APPLICATION_JSON)
                .param("text", expectedValue))
                .andExpect(MockMvcResultMatchers.status().is(200));

        text.andExpect(MockMvcResultMatchers.jsonPath("$[0].text").value(expectedValue))
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)));
    }

    @Test
    public void verifyDeleteMessage() throws Exception {
        long id = 3;
        mockMvc.perform(MockMvcRequestBuilders.delete(EndPoints.Message.MESSAGE_BY_ID, id)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));


        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.get("/message")
                .accept(MediaType.APPLICATION_JSON));

        Message[] messages = SerializationHelper.deserialize(perform, objectMapper, Message[].class);

        List<Long> collect = Arrays.stream(messages).map(e -> e.getId()).collect(Collectors.toList());

        assertThat(collect).doesNotContain(id);

    }

    @Test
    public void verifyGetById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(EndPoints.Message.MESSAGE_BY_ID, 2)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2));
    }

    @Test
    public void verifyGetByIncorrectId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(EndPoints.Message.MESSAGE_BY_ID, 100500)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(404));
    }

    @Test
    public void verifyPostMessage() throws Exception {

        String testMessage = "POST message";
        mockMvc.perform(MockMvcRequestBuilders.post(EndPoints.Message.MESSAGE)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"text\" : \"" + testMessage + "\"}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.text").value(testMessage));

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(EndPoints.Message.MESSAGE)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andDo(print());

        Message[] messages = SerializationHelper.deserialize(resultActions, objectMapper, Message[].class);
        assertThat(messages[messages.length - 1]).hasFieldOrPropertyWithValue("text", testMessage);

    }

    @Test
    public void verifyPutMessage() throws Exception {
        String testMessage = "PUT message";

        mockMvc.perform(MockMvcRequestBuilders.put(EndPoints.Message.MESSAGE_BY_ID, 2)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"text\" : \"" + testMessage + "\"}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.text").value(testMessage));

        mockMvc.perform(MockMvcRequestBuilders.get(EndPoints.Message.MESSAGE_BY_ID, 2)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.text").value(testMessage));
    }


}
