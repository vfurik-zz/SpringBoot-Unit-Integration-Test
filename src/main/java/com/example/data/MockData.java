package com.example.data;

import com.example.domain.Message;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MockData {

    final static public List<Message> messages = new ArrayList(Arrays.asList(
            new Message("message1"),
            new Message("message2"),
            new Message("message3"),
            new Message("message4"),
            new Message("message5"))
    );

}
