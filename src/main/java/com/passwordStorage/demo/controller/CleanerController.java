package com.passwordStorage.demo.controller;

import com.passwordStorage.demo.service.CleaningService;
import org.ghost4j.converter.ConverterException;
import org.ghost4j.document.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class CleanerController {

    CleaningService cleaningService;

    public CleanerController(CleaningService cleaningService){
        this.cleaningService=cleaningService;
    }

    @GetMapping("/test")
    public ResponseEntity<String> test(){
        return ResponseEntity.status(HttpStatus.OK).body("text");
    }

    @PostMapping("/upload")
    public FileModel unload(@RequestParam("file") MultipartFile file) throws IOException {
        return new FileModel(file.getOriginalFilename(),file.getContentType(),cleaningService.clean(file));
    }



}
