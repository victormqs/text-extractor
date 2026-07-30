package io.github.victormqs.textextractor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class FileUploadController {
    @GetMapping("/")
    public String index() {
        return "uploadForm";
    }

    @PostMapping("/upload")
    public String upload (@RequestParam("file")MultipartFile file) throws IOException {
        byte[] fileBytes = file.getBytes();

        String textExtracted = new String(fileBytes);

        System.out.println(textExtracted);

        return "uploadForm";
    }

}
