package com.janja.myTwitter.REST;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
public class UploadAvatar {

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public void uploadAvatar(
            @RequestParam("file")MultipartFile file
            ) throws IOException {
        File dir = new File("resources/avatars");
        dir.mkdirs();
        File newFile = new File(dir, file.getOriginalFilename());
        newFile.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(newFile);
        fileOutputStream.write(file.getBytes());
        fileOutputStream.close();
    }
}
