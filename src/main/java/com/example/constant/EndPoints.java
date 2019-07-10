package com.example.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EndPoints {

    public class Message {
        public static final String MESSAGE = "/message";
        public static final String MESSAGE_BY_ID = MESSAGE + UrlParam.ID;
    }

    public class UrlParam {
        public static final String ID = "/{id}";
    }

}
