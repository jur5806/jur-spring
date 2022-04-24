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
@Table(name="integral_seting")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class Integeral {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "integral_seting_id")
    private int integralSetingId;

    @Column(name = "resume_pass_name")
    private String resumePassName;

    @Column(name = "resume_pass_value")
    private int resumePassValue;

    @Column(name = "resume_number_limited")
    private int resumeNumberLimited;

    @Column(name = "view_pass_name")
    private String viewPassName;

    @Column(name = "view_pass_value")
    private int viewPassValue;

    @Column(name = "view_number_limited")
    private int viewNumberLimited;

    @Column(name = "success_in_name")
    private String successInName;

    @Column(name = "success_in_value")
    private int successInValue;

    @Column(name = "success_in_limited")
    private int successInLimited;
}
