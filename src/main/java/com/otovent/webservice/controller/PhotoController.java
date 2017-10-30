package com.otovent.webservice.controller;

import com.otovent.webservice.entity.Cars;
import com.otovent.webservice.entity.User;
import com.otovent.webservice.entity.enums.PhotosDependency;
import com.otovent.webservice.entity.response.BaseResponse;
import com.otovent.webservice.service.CarsService;
import com.otovent.webservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(value = "*")
@RequestMapping(value = "image")
public class PhotoController {
    @Autowired
    UserService userService;
    @Autowired
    CarsService carsService;
    @Autowired
    Environment environment;

    // TODO Controller for Upload
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse uploadFile(@RequestParam("uploadfile")MultipartFile uploadFile,
                                        @RequestHeader Long id,
                                        @RequestHeader PhotosDependency typeUpload) throws IOException {
        if (!uploadFile.getContentType().toLowerCase().contains("jpg")
                && !uploadFile.getContentType().toLowerCase().contains("jpeg")) {
            return BaseResponse.builder().httpStatus(HttpStatus.EXPECTATION_FAILED).message("File must be jpg").result(null).build();
        }
        String keyName= "";
        Date uploadedDate = new Date();
        String locationResource = environment.getProperty("resource.host");

        if (typeUpload.equals(PhotosDependency.ADS)){}
        else if (typeUpload.equals(PhotosDependency.CARS)){
            Cars carUploaded = carsService.getOneCar(id);
            keyName = String.valueOf(carUploaded.hashCode())+uploadedDate.getTime()+
                    PhotosDependency.CARS.hashCode()+".jpg";
            carsService.updateLinkImageCar(id,locationResource+keyName);
        }
        else if (typeUpload.equals(PhotosDependency.EVENTS)){}
        else if (typeUpload.equals(PhotosDependency.POSTS)){}
        // Photo Profile
        else if (typeUpload.equals(PhotosDependency.USERS)){
            User userTarget = userService.getDetailOneUser(id);
            keyName = String.valueOf(userTarget.hashCode())+uploadedDate.getTime()+
                    PhotosDependency.USERS.hashCode()+".jpg";
            userService.updatePhotoProfile(id,locationResource+keyName);
        }

        try {
            Path path = Paths.get("scontent/");
            Files.copy(uploadFile.getInputStream(), path.resolve(keyName));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return BaseResponse.builder().httpStatus(HttpStatus.BAD_REQUEST).message(ex.toString()).result(null).build();
        }
        return BaseResponse.builder().httpStatus(HttpStatus.OK).message("Success Upload").result(null).build();
    }

    // TODO To Get Image From Root Project / Uploaded ( scontent )
    @RequestMapping(value = "/get/url", method = RequestMethod.GET)
    public BaseResponse getImage(@RequestHeader Long id, @RequestHeader String key) throws IOException {
        String keyName = id+key+".jpg";
        Path path = Paths.get("scontent/");
        Path file = path.resolve(keyName);
        Resource resource = new UrlResource(file.toUri());
        System.out.println(resource.getURL());
        System.out.println(resource.getURI());
        List<String> result = new ArrayList<>();
        result.add(resource.getURI().toString());
        return BaseResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Success Get Image")
                .result(result).build();
    }
}
