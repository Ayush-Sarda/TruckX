package com.truckx.admin.response;

import com.truckx.admin.entity.Event;
import com.truckx.admin.request.AlarmType;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class EventRes {
    private String imei;
    private AlarmType alarmType;
    private String alarmTime;
    private Double latitude;
    private Double longitude;
    private List<String> fileList;

    public static List<EventRes> listToDto(List<Event> events) {
        List<EventRes> eventsResList = new ArrayList<>();
        for(Event event : events) {
            EventRes eventRes = new EventRes();
            eventRes.setAlarmTime(event.getAlarmTime());
            eventRes.setAlarmType(AlarmType.valueOf(event.getAlarmType()));
            eventRes.setFileList(event.getFileList());
            eventRes.setImei(event.getImei());
            eventRes.setLatitude(event.getLatitude());
            eventRes.setLongitude(event.getLongitude());
            eventsResList.add(eventRes);
        }
        return eventsResList;
    }
}
