package com.truckx.dashcam.service;

import com.truckx.dashcam.client.AmazonS3Client;
import com.truckx.dashcam.entity.Event;
import com.truckx.dashcam.entity.Location;
import com.truckx.dashcam.entity.Truck;
import com.truckx.dashcam.entity.Video;
import com.truckx.dashcam.repository.EventRepo;
import com.truckx.dashcam.repository.LocationRepo;
import com.truckx.dashcam.repository.TruckRepo;
import com.truckx.dashcam.repository.VideoRepo;
import com.truckx.dashcam.request.CreateEvent;
import com.truckx.dashcam.request.LoginMessage;
import com.truckx.dashcam.request.UploadLocation;
import com.truckx.dashcam.request.UploadVideo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
public class TruckService {
    @Autowired
    LocationRepo locationRepo;
    @Autowired
    EventRepo eventRepo;
    @Autowired
    TruckRepo truckRepo;
    @Autowired
    VideoRepo videoRepo;
    @Autowired
    AmazonS3Client s3Client;

    @Value("${aws.s3_bucket.endpoint}")
    private String s3BucketEndpoint;
    @Value("${aws.s3_bucket.name}")
    private String s3BucketName;

    private String videoS3Path = "videos/%s/%s";

    public void login(LoginMessage loginMessage) {
        Truck truck = new Truck();
        truck.setImei(loginMessage.getImei());
        truckRepo.save(truck);
    }

    public void createEvent(CreateEvent createEvent) {
        Event event = new Event();
        event.setAlarmTime(createEvent.getAlarmTime());
        event.setAlarmType(createEvent.getAlarmType().toString());
        event.setFileList(createEvent.getFileList());
        event.setImei(createEvent.getImei());
        event.setLatitude(createEvent.getLatitude());
        event.setLongitude(createEvent.getLongitude());
        eventRepo.save(event);
    }

    public void uploadLocation(UploadLocation uploadLocation) {
        Location location = new Location();
        location.setImei(uploadLocation.getImei());
        location.setLatitude(uploadLocation.getLatitude());
        location.setLongitude(uploadLocation.getLongitude());
        location.setLocationTime(uploadLocation.getLocationTime());
        locationRepo.save(location);
    }

    public void uploadVideo(UploadVideo uploadVideo) throws Exception {
        Video video = new Video();
        video.setFileName(uploadVideo.getFileName());
        video.setImei(uploadVideo.getImei());
        if(!Objects.isNull(uploadVideo.getVideoFile())) {
            String fileUrl = uploadDocument(uploadVideo.getImei(),
                    uploadVideo.getVideoFile());
            video.setUrl(fileUrl);
        }
        videoRepo.save(video);
    }

    private String uploadDocument(String imei, File file) throws Exception {
        String destinationPath = String.format(videoS3Path, imei, file.getName());
        boolean isUploaded = false;
        try {
            isUploaded = s3Client.saveFile(s3BucketName, file.getName(), file);
        } catch (Exception e) {
//            System.out.println("Video not uploaded");
            throw new Exception("Video not uploaded");
        }
        if(!isUploaded) {
//            System.out.println("Video not uploaded");
            throw new Exception("Video not uploaded");
        }
        return s3BucketEndpoint + "/" + destinationPath;
    }
}
