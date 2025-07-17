package com.JING.openai.controller;

import com.JING.openai.dto.OpenAiRequest;
import com.JING.openai.service.OpenAiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/openai")
@RequiredArgsConstructor
public class OpenAiController {

    private  final OpenAiService openAiService;


    @PostMapping
    public ResponseEntity<String> ask(@RequestBody OpenAiRequest request){
        String response = openAiService.askOpenAi(request.getPrompt());
        return ResponseEntity.ok(response);
    }
}
