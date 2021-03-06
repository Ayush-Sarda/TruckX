package com.truckx.admin.repository;

import com.truckx.admin.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepo extends JpaRepository<Video, Integer> {
    List<Video> findByImei(String imei);
}
