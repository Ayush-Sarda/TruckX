package com.truckx.dashcam.request;

import lombok.Data;

import java.io.File;

@Data
public class UploadVideo {
    private String imei;
    private String fileName;
    private File videoFile;
}
