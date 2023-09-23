package com.example.AppointmentMS.service;

import com.example.AppointmentMS.Util.VarList;
import com.example.AppointmentMS.dto.ConsultantDTO;
import com.example.AppointmentMS.dto.JobSeekerDTO;
import com.example.AppointmentMS.entity.Consultant;
import com.example.AppointmentMS.entity.JobSeeker;
import com.example.AppointmentMS.repo.JobSeekerRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional

public class JobSeekerService {
    @Autowired
    private JobSeekerRepo jobSeekerRepo;

    @Autowired
    private ModelMapper modelMapper;
public String saveJobSeeker(JobSeekerDTO jobSeekerDTO) {
    if (jobSeekerRepo.existsById(jobSeekerDTO.getJsID())) {
        return VarList.RSP_DUPLICATED;
    } else {
        jobSeekerRepo.save(modelMapper.map(jobSeekerDTO, JobSeeker.class));
        return VarList.RSP_SUCCESS;

    }
}
    public String updateJobseeker (JobSeekerDTO jobSeekerDTO){
        if (jobSeekerRepo.existsById(jobSeekerDTO.getJsID())){
            jobSeekerRepo.save((modelMapper.map(jobSeekerDTO, JobSeeker.class)));
            return VarList.RSP_SUCCESS;
        }else {
            return VarList.RSP_NO_DATA_FOUND;
        }
    }

    public List<JobSeekerDTO> getAllJobSeeker(){
        List<JobSeeker> jobSeekerList = jobSeekerRepo.findAll();
        return modelMapper.map(jobSeekerList, new TypeToken<ArrayList<JobSeekerDTO>>(){
        }.getType());
    }
    public JobSeekerDTO searchJobSeeker(int jsID){
        if(jobSeekerRepo.existsById(jsID)){
            JobSeeker jobSeeker =  jobSeekerRepo.findById(jsID).orElse(null);
            return modelMapper.map(jobSeeker, JobSeekerDTO.class);
        }else {
            return null;
        }
    }

    public String deleteJobSeeker(int jsID){
        if (jobSeekerRepo.existsById(jsID)){
            jobSeekerRepo.deleteById(jsID);
            return VarList.RSP_SUCCESS;
        }else {
            return  VarList.RSP_NO_DATA_FOUND;
        }
    }
}

