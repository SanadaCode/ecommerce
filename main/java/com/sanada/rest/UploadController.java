package com.sanada.rest;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("upload")
public class UploadController {
	//Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "C:\\Users\\davide.borgato\\Documents\\";


    @PostMapping("/upload") // //new annotation since 4.3
    @CrossOrigin(origins="http://localhost:4200")
    public void singleFileUpload(@RequestBody() MultipartFile file) {
    	
    	System.out.println(file.getContentType());
        if (file.isEmpty()) {
           System.out.println("file vuoto");
        }

        try {
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            
            System.out.println(path);
            Files.write(path, bytes);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }  
    
    @PostMapping("/test") // //new annotation since 4.3
    @CrossOrigin(origins="http://localhost:4200")
    public void test(@RequestBody() String test) {
    	
    	
    	String[] base64= test.split(",");
    	
    	byte[] enc = Base64.decodeBase64(base64[1]);
    	Path path = Paths.get(UPLOADED_FOLDER + "test");
    	try {
    		ByteArrayInputStream bis = new ByteArrayInputStream(enc);
    		BufferedImage image =ImageIO.read(new ByteArrayInputStream(enc));
    		File outputfile = new File(UPLOADED_FOLDER + "test1");
    		ImageIO.write(image, ".jpg", outputfile);
    		BufferedImage image2 = ImageIO.read(outputfile);
    		ByteArrayOutputStream bos = new ByteArrayOutputStream();
    		System.out.println(ImageIO.write(image2, "jpeg", bos));
    		System.out.println(bos.size());
    		byte [] data = bos.toByteArray();
    		System.out.println("qui: " + Base64.encodeBase64String(data));
    		
		} catch (Exception e) {
			System.out.println( e);
		}
    }
    
    @GetMapping("/test2") // //new annotation since 4.3
    @CrossOrigin(origins="http://localhost:4200")
    public String test2() {
    	
    	
    	
    	String base64=null;
    	Path path = Paths.get(UPLOADED_FOLDER + "test");
    	try {
    		File outputfile = new File(UPLOADED_FOLDER + "test1");
    		BufferedImage image2 = ImageIO.read(outputfile);
    		ByteArrayOutputStream bos = new ByteArrayOutputStream();
    		ImageIO.write(image2, "jpeg", bos);
    		byte [] data = bos.toByteArray();
    		base64 = Base64.encodeBase64String(data);
    	} catch (Exception e) {
    		System.out.println( e);
    	}
    	return base64;
    }
}
