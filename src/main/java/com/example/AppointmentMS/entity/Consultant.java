package com.example.AppointmentMS.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "Consultant")
public class Consultant {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int consultID;
    private String consultname;
    private String consultfield;
    private String consultemail;
    private String consultusername;
    private String consultpassword;
}
