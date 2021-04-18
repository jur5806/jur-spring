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
@Table(name="resumeinfo_list")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class Resumeinfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resume_Id")
    private Integer resumeId;

    @Column(name = "recruit_Id")
    private Integer recruitId;

    @Column(name = "name")
    private String recommendedName;

    @Column(name = "tj_id")
    private int tjId;

    @Column(name = "native")
    private String recommendedProvince;

    @Column(name = "nation")
    private String recommendednation;

    @Column(name = "birth")
    private Date recommendedBirth;

    @Column(name = "sex")
    private Integer sex;

    @Column(name = "age")
    private Integer recommendedAge;

    @Column(name = "approval_state")
    private Integer approvalState;

    @Column(name = "ploltics")
    private String recommendedPolitical;

    @Column(name = "education")
    private Integer recommendedEducation;

    @Column(name = "marital")
    private String recommendedMarital;

    @Column(name = "idcard")
    private String recommendedIdcard;

    @Column(name = "start")
    private Date recommendedStartDate;

    @Column(name = "english")
    private String recommendedEnglish;

    @Column(name = "computer")
    private String recommendedComputer;

    @Column(name = "seniority")
    private String seniority;

    @Column(name = "telephone")
    private Integer recommendedTelephone;

    @Column(name = "address")
    private String recommendedAddress;

    @Column(name = "school")
    private String recommendedSchool;

    @Column(name = "major")
    private String recommendedMajor;

    @Column(name = "email")
    private String recommendedEmail;

    @Column(name = "attend")
    private String recommendedAttend;

    @Column(name = "final_date")
    private Date recommendedFinalDate;

    @Column(name = "self_evaluation")
    private String recommendedSelfEvaluation;

    @Column(name = "gain_certificate")
    private String recommendedGainCertificate;

    @Column(name = "program_info")
    private String recommendedProgramInfo;

    @Column(name = "work_info")
    private String recommendedWorkInfo;

    @Column(name = "professional_info")
    private String recommendedProfessionalInfo;

    @Column(name = "photo")
    private String recommendedPhoto;

    @Column(name = "resume_type")
    private Integer resumeType;

}
