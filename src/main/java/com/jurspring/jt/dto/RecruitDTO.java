package com.jurspring.jt.dto;

import com.jurspring.jt.dto.base.OutputConverter;
import com.jurspring.jt.home.Recruit;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RecruitDTO implements OutputConverter<RecruitDTO, Recruit> {


    /**
     * User status.
     */
    private boolean enabled;

}
