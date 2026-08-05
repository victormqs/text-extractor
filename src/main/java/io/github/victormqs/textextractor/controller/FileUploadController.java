package io.github.victormqs.textextractor.controller;

import io.github.victormqs.textextractor.service.TextExtractorService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class FileUploadController {

    private final TextExtractorService textExtractorService;

    public FileUploadController(TextExtractorService textExtractorService) {
        this.textExtractorService = textExtractorService;
    }

    @GetMapping("/")
    public String index() {
        return "uploadForm";
    }

    @PostMapping("/upload")
    public String upload (@RequestParam("file")MultipartFile file) throws IOException {

        String text = textExtractorService.extractText(file);

        System.out.println(text);

        return "uploadForm";
    }

}
