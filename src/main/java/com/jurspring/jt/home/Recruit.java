package com.jurspring.jt.home;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name="recruit_list")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class Recruit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recruit_Id")
    private int recruitId;

    @Column(name = "station_name")
    private String stationName;

    @Column(name = "department_name")
    private String departmentName;

    @Column(name = "department_state")
    private int departmentState;

    @Column(name = "work_place")
    private String workPlace;

    @Column(name = "rc_start_time")
    private Date rcStartTime;

    @Column(name = "rc_end_time")
    private Date rcEndTime;

    @Column(name = "rc_department_num")
    private int rcDepartmentNum;

    @Column(name = "rc_describe")
    private String rcDescribe;

    @Column(name = "rc_salary_welfare")
    private String rcSalaryWelfare;

    @Column(name = "station_title")
    private String stationTitle;

    @Column(name = "max_age")
    private int maxAge;

    @Column(name = "min_age")
    private int minAge;

    @Column(name = "position_type_id")
    private int positionTypeId;

    @Column(name = "eduction_class")
    private int eductionClass;

    @Column(name = "resume_num")
    private int resumeNum = 0;

    /**
     * User status.
     */
    @Column(name = "enabled")
    private boolean enabled=true;

    @Column(name = "hr_id")
    private int hrId;

}
