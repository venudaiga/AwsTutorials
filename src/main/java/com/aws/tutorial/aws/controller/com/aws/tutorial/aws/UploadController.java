package com.aws.tutorial.aws.controller.com.aws.tutorial.aws;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
public class UploadController{

    public void uploadFile(@RequestParam MultipartFile file){




    }


}
