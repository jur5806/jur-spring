package com.jurspring.jt.home;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name="reason_list")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class Reason {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reason_id")
    private int reasonId;

    @Column(name = "reason_name")
    private String reasonName;


    @Column(name = "reason_number")
    private int reasonNumber;
}
