package com.truckx.dashcam.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "videos")
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "imei")
    private String imei;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "video_data")
    private String videoData;

    @Column(name = "url")
    private String url;
}
