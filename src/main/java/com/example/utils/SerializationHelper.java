package com.example.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;
import org.springframework.test.web.servlet.ResultActions;

import java.io.IOException;

@UtilityClass
public class SerializationHelper {

    public static <T> T deserialize(ResultActions resultActions, ObjectMapper objectMapper, Class<T> clazz) throws IOException {
        return objectMapper.readValue(resultActions.andReturn().getResponse().getContentAsString(), clazz);
    }

}
