package com.example.AppointmentMS.controller;
import com.example.AppointmentMS.Util.VarList;
import com.example.AppointmentMS.dto.ConsultantDTO;
import com.example.AppointmentMS.dto.ResponceDTO;
import com.example.AppointmentMS.service.ConsultantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/consultant")
public class ConsultantController {

    @Autowired
    private ConsultantService consultantService;

    @Autowired
    private ResponceDTO responceDTO;

    @PostMapping("/saveConsultant")
    public ResponseEntity saveConsultant(@RequestBody ConsultantDTO consultantDTO){
        try {
            String res = consultantService.saveConsultant(consultantDTO);
            if (res.equals("00")){
                responceDTO.setCode(VarList.RSP_SUCCESS);
                responceDTO.setMessage("Data saved successfully.");
                responceDTO.setContent(consultantDTO);
                return new ResponseEntity(responceDTO, HttpStatus.ACCEPTED);

            }else if (res.equals("06")){
                responceDTO.setCode(VarList.RSP_DUPLICATED);
                responceDTO.setMessage("Consultant already exist.");
                responceDTO.setContent(consultantDTO);
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

    @PutMapping ("/updateConsultant")
    public ResponseEntity updateConsultant(@RequestBody ConsultantDTO consultantDTO){
        try {
            String res = consultantService.updateConsultant(consultantDTO);
            if (res.equals("00")){
                responceDTO.setCode(VarList.RSP_SUCCESS);
                responceDTO.setMessage("Success.");
                responceDTO.setContent(consultantDTO);
                return new ResponseEntity(responceDTO, HttpStatus.ACCEPTED);

            }else if (res.equals("01")){
                responceDTO.setCode(VarList.RSP_DUPLICATED);
                responceDTO.setMessage("Not a registered consultant");
                responceDTO.setContent(consultantDTO);
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

    @GetMapping("/getAllConsultant")
    public ResponseEntity getAllConsultant(){
        try{
            List<ConsultantDTO> consultantDTOList = consultantService.getAllConsultant();
            responceDTO.setCode(VarList.RSP_SUCCESS);
            responceDTO.setMessage("Success.");
            responceDTO.setContent(consultantDTOList);
            return new ResponseEntity(responceDTO, HttpStatus.ACCEPTED);

        }catch (Exception ex){
            responceDTO.setCode(VarList.RSP_ERROR);
            responceDTO.setMessage(ex.getMessage());
            responceDTO.setContent(null);
            return new ResponseEntity(responceDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/searchConsultant/{consultid}")
    public ResponseEntity serchConsultant(@PathVariable int consultid){
        try {
          ConsultantDTO consultantDTO = consultantService.searchConsultant(consultid);
            if (consultantDTO !=null){
                responceDTO.setCode(VarList.RSP_SUCCESS);
                responceDTO.setMessage("Success.");
                responceDTO.setContent(consultantDTO);
                return new ResponseEntity(responceDTO, HttpStatus.ACCEPTED);

            }else{
                responceDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responceDTO.setMessage("No Consultant for this ID");
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

    @DeleteMapping("/deleteConsultant/{consultid}")
    public ResponseEntity deleteConsultant(@PathVariable int consultid){
        try {
            String res = consultantService.deleteConsultant(consultid);
            if (res.equals("00")){
                responceDTO.setCode(VarList.RSP_SUCCESS);
                responceDTO.setMessage("Success.");
                responceDTO.setContent(null);
                return new ResponseEntity(responceDTO, HttpStatus.ACCEPTED);

            }else{
                responceDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responceDTO.setMessage("No Consultant for this ID");
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
