package by.skopinau.transactional.outbox.demo.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.AttributeConverter;
import java.io.IOException;
import java.util.Map;

@Slf4j
public class JsonToMapConverter implements AttributeConverter<Map<String, Object>, String> {

    private static final String JSON_WRITING_ERROR = "JSON writing error";
    private static final String JSON_READING_ERROR = "JSON reading error";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Map<String, Object> payload) {
        String payloadJson = null;
        try {
            payloadJson = objectMapper.writeValueAsString(payload);
        } catch (final JsonProcessingException e) {
            log.error(JSON_WRITING_ERROR, e);
        }

        return payloadJson;
    }

    @Override
    public Map<String, Object> convertToEntityAttribute(String payloadJSON) {
        Map<String, Object> payload = null;
        try {
            payload = objectMapper.readValue(payloadJSON, Map.class);
        } catch (IOException e) {
            log.error(JSON_READING_ERROR, e);
        }

        return payload;
    }
}
