package com.passwordStorage.demo.controller;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class FileModel {

    public FileModel() {
        super();
    }
    public FileModel(String name, String type, byte[] bytes) {
        this.name = name;
        this.type = type;
        this.bytes = bytes;
    }
    private String name;
    private String type;
    private byte[] bytes;

    public FileModel(MultipartFile multipartFile) throws IOException {
        this.name = multipartFile.getOriginalFilename();
        this.bytes = multipartFile.getBytes();
        this.type = multipartFile.getContentType();
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public byte[] getBytes() {
        return bytes;
    }
    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}
