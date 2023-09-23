package com.example.AppointmentMS.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JobSeekerDTO {
    private int jsID;
    private String jstname;
    private String jsfield;
    private String jsemail;
    private String jsusername;
    private String jspassword;
}
