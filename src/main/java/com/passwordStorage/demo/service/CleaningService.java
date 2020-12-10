package com.passwordStorage.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.FileUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Scanner;


@Service
public class CleaningService {

    public byte[] clean(MultipartFile file) throws IOException {
        try {
            if (file.getContentType().startsWith("image")) {
                return cleanImage(file);
            }
            return cleanTxt(file);
        } catch (NullPointerException e){
            return null;
        }
    }

    private byte[] cleanTxt(MultipartFile file) throws IOException {
        File txtOutput = new File("clean.txt");
        OutputStream os = new FileOutputStream(txtOutput);
        os.write(file.getBytes());
        BufferedReader br = new BufferedReader(new FileReader(txtOutput));
        String line = null;
        StringBuilder text = new StringBuilder();
        while ((line = br.readLine()) != null) {
            text.append(line);
        }
        return text.toString().getBytes();
    }

    private byte[] cleanImage(MultipartFile file) throws IOException {
        File res = new File("clean.jpg");
        InputStream pic = new ByteArrayInputStream(file.getBytes());
        BufferedImage img = ImageIO.read(pic);
        try {
            ImageIO.write(img, "jpg", res);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return FileUtils.readFileToByteArray(res);
    }

}


