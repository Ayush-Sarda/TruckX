package com.truckx.dashcam.request;

import lombok.Data;

@Data
public class LoginMessage {
    private String type;
    private String imei;
}
