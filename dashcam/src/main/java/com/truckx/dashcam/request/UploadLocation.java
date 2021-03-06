package com.truckx.dashcam.request;

import lombok.Data;

@Data
public class UploadLocation {
    private String type;
    private String imei;
    private String locationTime;
    private Double latitude;
    private Double longitude;
}
