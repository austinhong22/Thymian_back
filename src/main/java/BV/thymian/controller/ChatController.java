package BV.thymian.controller;

import BV.thymian.dto.ChatRequest;
import BV.thymian.dto.GenerateRequest;
import BV.thymian.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ChatController {

    private final ChatService chatService;

    @PostMapping("/chat")
    public ResponseEntity<?> chat(@RequestBody ChatRequest request) {
        if (request.getMessage() == null || request.getMessage().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("빈 메시지는 허용되지 않습니다.");
        }
        try {
            return ResponseEntity.ok(chatService.processChat(request.getMessage()));
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("서버 내부 오류 발생");
        }
    }

    @PostMapping("/generate")
    public ResponseEntity<?> generateImage(@RequestBody GenerateRequest request) {
        if (request.getInputText() == null || request.getInputText().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Input text cannot be empty");
        }
        try {
            return ResponseEntity.ok(chatService.generateImage(request.getInputText()));
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("이미지 생성 요청 처리 중 오류 발생");
        }
    }

    @GetMapping("/download")
    public ResponseEntity<?> downloadImage(@RequestParam String url) {
        if (url == null || url.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Missing url query parameter");
        }
        return ResponseEntity.ok().body(chatService.downloadImage(url));
    }

    @GetMapping("/")
    public ResponseEntity<?> healthCheck() {
        return ResponseEntity.ok("Spring Boot 서버가 정상 작동중입니다.");
    }
}