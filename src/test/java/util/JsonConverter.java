package util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonConverter {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    // Пример метода для преобразования объекта в JSON строку
    public static String asJsonString(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
