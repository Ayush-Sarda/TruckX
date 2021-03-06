package com.truckx.admin.request;

import lombok.Data;

@Data
public class SendCommand {
    private String command;
    private String type;
    private String imei;
}
