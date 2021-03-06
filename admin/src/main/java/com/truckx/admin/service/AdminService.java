package com.truckx.admin.service;

import com.truckx.admin.entity.Event;
import com.truckx.admin.entity.Video;
import com.truckx.admin.repository.EventRepo;
import com.truckx.admin.repository.VideoRepo;
import com.truckx.admin.request.AlarmFilter;
import com.truckx.admin.request.SendCommand;
import com.truckx.admin.response.CommandRes;
import com.truckx.admin.response.EventRes;
import com.truckx.admin.response.VideoRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
@Slf4j
public class AdminService {
    @Autowired
    EventRepo eventRepo;
    @Autowired
    VideoRepo videoRepo;
    @Autowired
    RestTemplate restTemplate;

    @Value("${dashcam.base.url}")
    private String baseUrl;

    public List<EventRes> getAllAlarms(String imei) {
        List<Event> events = eventRepo.findByImei(imei);
        return EventRes.listToDto(events);
    }

    public List<EventRes> getAlarmWithFilter(AlarmFilter alarmFilter, String imei) {
        List<Event> events = eventRepo.findByImeiAndAlarmTypeAndAlarmTimeBetween(imei, alarmFilter.getAlarmType(),
                alarmFilter.getStartTime(),
                alarmFilter.getEndTime());
        return EventRes.listToDto(events);
    }

    public List<VideoRes> getAllVideos(String imei) {
        List<Video> videos = videoRepo.findByImei(imei);
        return VideoRes.listToDto(videos);
    }

    public CommandRes sendCommand(SendCommand command) {
        String url = baseUrl + command.getImei();
        try {
            ResponseEntity<CommandRes> responseEntity = restTemplate.exchange(
                    UriComponentsBuilder.fromHttpUrl(url).toUriString(),
                    HttpMethod.POST,
                    new HttpEntity<>(command, new HttpHeaders()),
                    CommandRes.class
            );
            return responseEntity.getBody();
        } catch (Exception e) {
            throw e;
        }
    }
}
