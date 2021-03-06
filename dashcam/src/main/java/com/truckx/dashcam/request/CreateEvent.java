package com.truckx.dashcam.request;

import lombok.Data;

import java.util.List;

@Data
public class CreateEvent {
    private String type;
    private String imei;
    private AlarmType alarmType;
    private String alarmTime;
    private Double latitude;
    private Double longitude;
    private List<String> fileList;
}
