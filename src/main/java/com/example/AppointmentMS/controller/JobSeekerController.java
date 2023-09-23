package com.example.AppointmentMS.controller;

import com.example.AppointmentMS.Util.VarList;
import com.example.AppointmentMS.dto.ConsultantDTO;
import com.example.AppointmentMS.dto.JobSeekerDTO;
import com.example.AppointmentMS.dto.ResponceDTO;
import com.example.AppointmentMS.service.JobSeekerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/jobseeker")
public class JobSeekerController {
    @Autowired
    private JobSeekerService jobSeekerService;
    @Autowired
    private ResponceDTO responceDTO;


    @PostMapping(value = "/savejobseeker")
    public ResponseEntity saveJobSeeker(@RequestBody JobSeekerDTO jobSeekerDTO) {
        try {
            String res = jobSeekerService.saveJobSeeker(jobSeekerDTO);
            if (res.equals("00")) {
                responceDTO.setCode(VarList.RSP_SUCCESS);
                responceDTO.setMessage("Data saved successfully.");
                responceDTO.setContent(jobSeekerDTO);
                return new ResponseEntity(responceDTO, HttpStatus.ACCEPTED);

            } else if (res.equals("06")) {
                responceDTO.setCode(VarList.RSP_DUPLICATED);
                responceDTO.setMessage("JobSeeker already exist.");
                responceDTO.setContent(jobSeekerDTO);
                return new ResponseEntity(responceDTO, HttpStatus.BAD_REQUEST);

            } else {
                responceDTO.setCode(VarList.RSP_FAIL);
                responceDTO.setMessage("Error.");
                responceDTO.setContent(null);
                return new ResponseEntity(responceDTO, HttpStatus.BAD_REQUEST);

            }
        } catch (Exception ex) {
            responceDTO.setCode(VarList.RSP_ERROR);
            responceDTO.setMessage(ex.getMessage());
            responceDTO.setContent(null);
            return new ResponseEntity(responceDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping ("/updatejobseeker")
    public ResponseEntity updateJobseeker(@RequestBody JobSeekerDTO jobSeekerDTO){
        try {
            String res = jobSeekerService.updateJobseeker(jobSeekerDTO);
            if (res.equals("00")){
                responceDTO.setCode(VarList.RSP_SUCCESS);
                responceDTO.setMessage("Success.");
                responceDTO.setContent(jobSeekerDTO);
                return new ResponseEntity(responceDTO, HttpStatus.ACCEPTED);

            }else if (res.equals("01")){
                responceDTO.setCode(VarList.RSP_DUPLICATED);
                responceDTO.setMessage("Not a registered JobSeeker");
                responceDTO.setContent(jobSeekerDTO);
                return new ResponseEntity(responceDTO, HttpStatus.BAD_REQUEST);

            }else{
                responceDTO.setCode(VarList.RSP_FAIL);
                responceDTO.setMessage("Error.");
                responceDTO.setContent(null);
                return new ResponseEntity(responceDTO, HttpStatus.BAD_REQUEST);

            }

        }catch (Exception ex){
            responceDTO.setCode(VarList.RSP_ERROR);
            responceDTO.setMessage(ex.getMessage());
            responceDTO.setContent(null);
            return new ResponseEntity(responceDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAllJobSeeker")
    public ResponseEntity getAllJobSeeker(){
        try{
            List<JobSeekerDTO> jobSeekerDTOList = jobSeekerService.getAllJobSeeker();
            responceDTO.setCode(VarList.RSP_SUCCESS);
            responceDTO.setMessage("Success.");
            responceDTO.setContent(jobSeekerDTOList);
            return new ResponseEntity(responceDTO, HttpStatus.ACCEPTED);

        }catch (Exception ex){
            responceDTO.setCode(VarList.RSP_ERROR);
            responceDTO.setMessage(ex.getMessage());
            responceDTO.setContent(null);
            return new ResponseEntity(responceDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/searchJobSeeker/{jsID}")
    public ResponseEntity serchJobSeeker(@PathVariable int jsID){
        try {
            JobSeekerDTO jobSeekerDTO = jobSeekerService.searchJobSeeker(jsID);
            if (jobSeekerDTO !=null){
                responceDTO.setCode(VarList.RSP_SUCCESS);
                responceDTO.setMessage("Success.");
                responceDTO.setContent(jobSeekerDTO);
                return new ResponseEntity(responceDTO, HttpStatus.ACCEPTED);

            }else{
                responceDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responceDTO.setMessage("No JobSeeker for this ID");
                responceDTO.setContent(null);
                return new ResponseEntity(responceDTO, HttpStatus.BAD_REQUEST);

            }
        }catch (Exception e){
            responceDTO.setCode(VarList.RSP_ERROR);
            responceDTO.setMessage(e.getMessage());
            responceDTO.setContent(e);
            return new ResponseEntity(responceDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteJobSeeker/{jsID}")
    public ResponseEntity deleteJobSeeker(@PathVariable int jsID){
        try {
            String res = jobSeekerService.deleteJobSeeker(jsID);
            if (res.equals("00")){
                responceDTO.setCode(VarList.RSP_SUCCESS);
                responceDTO.setMessage("Success.");
                responceDTO.setContent(null);
                return new ResponseEntity(responceDTO, HttpStatus.ACCEPTED);

            }else{
                responceDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responceDTO.setMessage("No JobSeeker for this ID");
                responceDTO.setContent(null);
                return new ResponseEntity(responceDTO, HttpStatus.BAD_REQUEST);

            }

        }catch (Exception e){
            responceDTO.setCode(VarList.RSP_ERROR);
            responceDTO.setMessage(e.getMessage());
            responceDTO.setContent(e);
            return new ResponseEntity(responceDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

