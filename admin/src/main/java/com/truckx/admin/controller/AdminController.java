package com.truckx.admin.controller;

import com.truckx.admin.request.AlarmFilter;
import com.truckx.admin.request.SendCommand;
import com.truckx.admin.response.CommandRes;
import com.truckx.admin.response.EventRes;
import com.truckx.admin.response.GenericResponse;
import com.truckx.admin.response.VideoRes;
import com.truckx.admin.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@Slf4j
public class AdminController {
    @Autowired
    AdminService adminService;

    @GetMapping("/admin/alarms")
    public ResponseEntity<GenericResponse> getAllAlarms(@RequestParam String imei) {
        List<EventRes> eventRes = adminService.getAllAlarms(imei);
        String message = (Objects.isNull(eventRes) || eventRes.size() == 0) ? "Not found any data" : "Success";
        return new ResponseEntity<>(new GenericResponse(true, message, eventRes), HttpStatus.OK);
    }

    @PostMapping("/admin/alarms-filter")
    public ResponseEntity<GenericResponse> getAlarmsWithFilter(@RequestParam String imei,
                                                               @RequestBody AlarmFilter alarmFilter) {
        List<EventRes> eventRes = adminService.getAlarmWithFilter(alarmFilter, imei);
        String message = (Objects.isNull(eventRes) || eventRes.size() == 0) ? "Not found any data" : "Success";
        return new ResponseEntity<>(new GenericResponse(true, message, eventRes), HttpStatus.OK);
    }

    @GetMapping("/admin/videos")
    public ResponseEntity<GenericResponse> getVideos(@RequestParam String imei) {
        List<VideoRes> videoRes = adminService.getAllVideos(imei);
        String message = (Objects.isNull(videoRes) || videoRes.size() == 0) ? "Not found any data" : "Success";
        return new ResponseEntity<>(new GenericResponse(true, message, videoRes), HttpStatus.OK);
    }

    @PostMapping("/admin/command")
    public ResponseEntity<GenericResponse> sendCommandToDashCam(@RequestBody SendCommand sendCommand) {
        try {
            CommandRes commandRes = adminService.sendCommand(sendCommand);
            String message = Objects.isNull(commandRes) ? "Not found any data" : "Success";
            return new ResponseEntity<>(new GenericResponse(true, message, ""), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(new GenericResponse(true, "Command not sent", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
