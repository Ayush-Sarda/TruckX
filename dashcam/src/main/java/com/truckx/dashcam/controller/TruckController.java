package com.truckx.dashcam.controller;

import com.google.common.io.Files;
import com.truckx.dashcam.request.CreateEvent;
import com.truckx.dashcam.request.LoginMessage;
import com.truckx.dashcam.request.UploadLocation;
import com.truckx.dashcam.request.UploadVideo;
import com.truckx.dashcam.response.GenericResponse;
import com.truckx.dashcam.service.TruckService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Objects;

@Slf4j
@RestController
public class TruckController {
    @Autowired
    TruckService truckService;

    @PostMapping("/login")
    public ResponseEntity<GenericResponse> login(@RequestBody LoginMessage loginMessage) {
        truckService.login(loginMessage);
        return new ResponseEntity<>(new GenericResponse<>(true, null, null), HttpStatus.OK);
    }

    @PostMapping("/events")
    public ResponseEntity<GenericResponse> createEvent(@RequestBody CreateEvent createEvent) {
        truckService.createEvent(createEvent);
        return new ResponseEntity<>(new GenericResponse<>(true, null, null), HttpStatus.OK);
    }

    @PostMapping("/locations")
    public ResponseEntity<GenericResponse> uploadLocation(@RequestBody UploadLocation uploadLocation) {
        truckService.uploadLocation(uploadLocation);
        return new ResponseEntity<>(new GenericResponse<>(true, null, null), HttpStatus.OK);
    }


    @PostMapping("/videos")
    public ResponseEntity<GenericResponse> uploadVideo(@RequestParam(value = "imei") String imei,
                                                       @RequestParam(value = "fileName") String fileName,
                                                       @RequestParam(value = "video") MultipartFile videoFile) throws Exception {
        UploadVideo uploadVideo = new UploadVideo();
        uploadVideo.setFileName(fileName);
        uploadVideo.setImei(imei);
        uploadVideo.setVideoFile(multipartToFile(videoFile));
        try {
            truckService.uploadVideo(uploadVideo);
        } catch(Exception e) {
            return new ResponseEntity<>(new GenericResponse(true, "Video failed to upload", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(new GenericResponse<>(true, null, null), HttpStatus.OK);
    }

    private File multipartToFile(MultipartFile multipart) {
        if(!Objects.nonNull(multipart)){
            return null;
        }
        try {
            File convFile = createTempFile(multipart.getOriginalFilename());
            convFile.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(convFile);
            fileOutputStream.write(multipart.getBytes());
            fileOutputStream.close();
            return convFile;
        } catch (Exception e) {
            return null;
        }
    }

    private File createTempFile(String fileName) {

        return new File(Files.createTempDir(), fileName);

    }
}
