package com.truckx.admin.entity;

import com.vladmihalcea.hibernate.type.array.ListArrayType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@TypeDefs({
        @TypeDef(name = "list-array", typeClass = ListArrayType.class),
})
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "imei")
    private String imei;

    @Column(name = "alarm_type")
    private String alarmType;

    @Column(name = "alarm_time")
    private String alarmTime;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Type(type = "list-array")
    @Column(name = "file_list")
    private List<String> fileList;
}
