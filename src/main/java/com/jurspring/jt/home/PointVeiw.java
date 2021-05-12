package com.jurspring.jt.home;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "points_record")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class PointVeiw {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "points_id")
    private int pointsId;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "event_type")
    private int eventType;


    private String name;
    private String phone;

    @Column(name = "points_num")
    private int pointsNum;

    @Column(name = "change_type")
    private int changeType;

    @Column(name = "event_time")
    private Date eventTime;

    private int dealer;

    private int resumeId;



}
