package com.truckx.admin.request;

import lombok.Data;


@Data
public class AlarmFilter {
    private String startTime;
    private String endTime;
    private String alarmType;
}
