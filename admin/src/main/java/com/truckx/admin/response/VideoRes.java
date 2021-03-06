package com.truckx.admin.response;

import com.truckx.admin.entity.Video;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class VideoRes {
    private String url;
    private String fileName;

    public static List<VideoRes> listToDto(List<Video> videos) {
        List<VideoRes>  videoResList = new ArrayList<>();
        for(Video video : videos) {
            VideoRes videoRes = new VideoRes();
            videoRes.setFileName(video.getFileName());
            videoRes.setUrl(video.getUrl());
            videoResList.add(videoRes);
        }
        return videoResList;
    }
}
