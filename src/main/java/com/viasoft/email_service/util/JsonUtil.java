package com.viasoft.email_service.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

    private static final ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();

    public static void printJson(String title, Object obj) {
        try {
            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
            System.out.println("=== " + title + " ===");
            System.out.println(json);
        } catch (JsonProcessingException e) {
            System.err.println("Erro ao serializar objeto para JSON: " + e.getMessage());
        }
    }
}