package com.example.AppointmentMS.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ConsultantDTO {
    private int consultID;
    private String consultname;
    private String consultfield;
    private String consultemail;
    private String consultusername;
    private String consultpassword;
}
