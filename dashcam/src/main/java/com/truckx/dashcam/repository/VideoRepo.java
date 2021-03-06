package com.truckx.dashcam.repository;

import com.truckx.dashcam.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepo extends JpaRepository<Video, Integer> {
    List<Video> findByImei(String imei);
}
