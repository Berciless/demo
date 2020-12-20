package com.passwordStorage.demo.service;

import org.ghost4j.converter.ConverterException;
import org.ghost4j.converter.PDFConverter;
import org.ghost4j.converter.PSConverter;
import org.ghost4j.document.DocumentException;
import org.ghost4j.document.PDFDocument;
import org.ghost4j.document.PSDocument;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.FileUtils;

import javax.imageio.ImageIO;
import javax.xml.bind.JAXBException;

import java.awt.image.BufferedImage;
import java.io.*;



@Service
public class CleaningService {

    public byte[] clean(MultipartFile file) throws IOException {
        try {
            if (file.getContentType().startsWith("image")) {
                return cleanImage(file);
            }
            if (file.getContentType().endsWith("pdf")) {
                return cleanPdf(file);
            }
            if (file.getContentType().endsWith("officedocument.spreadsheetml.sheet")){
                return cleanExcel(file);
            }
            return cleanTxt(file);
        } catch (NullPointerException | ConverterException | DocumentException e){
            return null;
        }
    }

    private byte[] cleanTxt(MultipartFile file) throws IOException {
        File txtOutput = new File("clean.txt");
        OutputStream os = new FileOutputStream(txtOutput);
        os.write(file.getBytes());
        BufferedReader br = new BufferedReader(new FileReader(txtOutput));
        String line;
        StringBuilder text = new StringBuilder();
        while ((line = br.readLine()) != null) {
            text.append(line);
            text.append("\n");
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

    public byte[] cleanPdf(MultipartFile file) throws IOException, ConverterException, DocumentException {
        FileOutputStream outstreamFile = new FileOutputStream(new File("temp.ps"));
        try{
            PDFDocument pdfDocument = new PDFDocument();
            pdfDocument.load(file.getInputStream());
            PSConverter converter = new PSConverter();
            converter.convert(pdfDocument, outstreamFile);
            outstreamFile.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        PSDocument psDocument = new PSDocument();
        psDocument.load(new File("temp.ps"));
        File cleanPdf = new File("clean.pdf");
        FileOutputStream pdfOutput = new FileOutputStream(cleanPdf);
        PDFConverter pdfConverter = new PDFConverter();
        pdfConverter.setPDFSettings(PDFConverter.OPTION_PDFSETTINGS_PREPRESS);
        pdfConverter.convert(psDocument, pdfOutput);
        return FileUtils.readFileToByteArray(cleanPdf);
    }

    public byte[] cleanExcel(MultipartFile fiile) {



        return "".getBytes();
    }

}


