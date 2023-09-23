package com.example.AppointmentMS.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "JobSeeker")
public class JobSeeker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int jsID;
    private String jstname;
    private String jsfield;
    private String jsemail;
    private String jsusername;
    private String jspassword;
}
