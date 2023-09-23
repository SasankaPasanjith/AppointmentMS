package com.example.AppointmentMS.service;

import com.example.AppointmentMS.Util.VarList;
import com.example.AppointmentMS.dto.ConsultantDTO;
import com.example.AppointmentMS.entity.Consultant;
import com.example.AppointmentMS.repo.ConsultantRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional

public class ConsultantService {

    @Autowired
    private ConsultantRepo consultantRepo;

    @Autowired
    private ModelMapper modelMapper;
    public String saveConsultant(ConsultantDTO consultantDTO){
        if (consultantRepo.existsById(consultantDTO.getConsultID())){
            return VarList.RSP_DUPLICATED;
        }else {
            consultantRepo.save(modelMapper.map(consultantDTO, Consultant.class));
            return VarList.RSP_SUCCESS;
        }
    }

    public String updateConsultant (ConsultantDTO consultantDTO){
        if (consultantRepo.existsById(consultantDTO.getConsultID())){
            consultantRepo.save((modelMapper.map(consultantDTO, Consultant.class)));
            return VarList.RSP_SUCCESS;
        }else {
            return VarList.RSP_NO_DATA_FOUND;
        }
    }

    public List<ConsultantDTO> getAllConsultant(){
        List<Consultant> consultantList = consultantRepo.findAll();
        return modelMapper.map(consultantList, new TypeToken<ArrayList<ConsultantDTO>>(){
        }.getType());
    }

    public ConsultantDTO searchConsultant(int consultid){
        if(consultantRepo.existsById(consultid)){
           Consultant consultant =  consultantRepo.findById(consultid).orElse(null);
           return modelMapper.map(consultant, ConsultantDTO.class);
        }else {
            return null;
        }
    }

    public String deleteConsultant(int consultid){
        if (consultantRepo.existsById(consultid)){
            consultantRepo.deleteById(consultid);
            return VarList.RSP_SUCCESS;
        }else {
            return  VarList.RSP_NO_DATA_FOUND;
        }
    }
}
