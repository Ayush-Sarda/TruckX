package com.truckx.dashcam.client;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Objects;

@Component
@Slf4j
public class AmazonS3Client {
    @Qualifier("s3_client")
    @Autowired
    private AmazonS3 s3Client;

    public boolean saveFile(String bucket, String fileName, File file) {
        if (!Objects.isNull(bucket) && !Objects.isNull(fileName) && !Objects.isNull(file)) {
            try {
                CannedAccessControlList cannedAccessControlList = CannedAccessControlList.PublicRead;
                s3Client.putObject(new PutObjectRequest(bucket, fileName, file)
                        .withCannedAcl(cannedAccessControlList));
                return true;
            } catch (Exception e) {
                System.out.println("Video not uploaded");
            }
        }
        return false;
    }
}
