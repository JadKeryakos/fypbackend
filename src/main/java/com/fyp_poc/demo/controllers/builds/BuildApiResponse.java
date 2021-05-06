package com.fyp_poc.demo.controllers.builds;

import lombok.*;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class BuildApiResponse {

    private long id;
    private String buildName;
    private Date createDate;
}
