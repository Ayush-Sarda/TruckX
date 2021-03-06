package com.truckx.admin.repository;

import com.truckx.admin.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface EventRepo extends JpaRepository<Event, Integer> {
    List<Event> findByImeiAndAlarmTypeAndAlarmTimeBetween(String imei, String alarmType, String startTime, String endTime);
    List<Event> findByImei(String imei);
}
