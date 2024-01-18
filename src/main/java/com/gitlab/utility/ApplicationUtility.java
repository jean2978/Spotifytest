package com.gitlab.utility;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ApplicationUtility {

    public String encodeBase64(String input) {
        byte[] encodedBytes = Base64.getEncoder().encode(input.getBytes(StandardCharsets.UTF_8));
        return new String(encodedBytes, StandardCharsets.UTF_8);
    }
}
