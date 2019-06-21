package com.sanada.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
}
