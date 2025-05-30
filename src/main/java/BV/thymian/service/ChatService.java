package BV.thymian.service;

import lombok.RequiredArgsConstructor;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ObjectMapper objectMapper;
    private static final String FASTAPI_BASE_URL = "http://127.0.0.1:8000";

    public Map<String, Object> processChat(String message) throws IOException {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost request = new HttpPost(FASTAPI_BASE_URL + "/chat");
            request.setEntity(new StringEntity(
                    objectMapper.writeValueAsString(Map.of("message", message)),
                    ContentType.APPLICATION_JSON));

            return client.execute(request,
                    response -> objectMapper.readValue(response.getEntity().getContent(), Map.class));
        }
    }

    public Map<String, Object> generateImage(String inputText) throws IOException {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost request = new HttpPost(FASTAPI_BASE_URL + "/generate");
            request.setEntity(new StringEntity(
                    objectMapper.writeValueAsString(Map.of("input_text", inputText)),
                    ContentType.APPLICATION_JSON));

            return client.execute(request,
                    response -> objectMapper.readValue(response.getEntity().getContent(), Map.class));
        }
    }

    public String downloadImage(String url) {
        return url;
    }
}