package HospitalAutomation.service;

import HospitalAutomation.entity.Diagnosis;
import HospitalAutomation.entity.Ward;
import HospitalAutomation.exceptions.WardIsAlreadyExistException;
import HospitalAutomation.exceptions.WardIsNotFoundException;
import HospitalAutomation.models.DiagnosisModel;
import HospitalAutomation.models.WardModel;
import HospitalAutomation.repository.WardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WardServiceIml implements WardService{

    @Autowired
    private WardRepository wardRepository;

    @Override
    public List<Ward> listWard() {
        return (List<Ward>) wardRepository.findAll();
    }

    @Override
    public List<WardModel> getAllWards() throws Exception {
        try {
            List<Ward> diagnosisList = (List<Ward>) wardRepository.findAll();
            return diagnosisList.stream().map(WardModel::toModel).collect(Collectors.toList());
        }catch (Exception exception){
            throw new Exception(exception.getMessage());
        }
    }

    @Override
    public Ward saveWard(Ward ward) throws WardIsAlreadyExistException {
        if(wardRepository.findByName(ward.getName()) != null){
            throw new WardIsAlreadyExistException("Ward is already exist");
        }
        return wardRepository.save(ward);
    }

    @Override
    public WardModel findWard(Long id) throws WardIsNotFoundException {
        Optional<Ward> optionalWard = wardRepository.findById(id);
        if(optionalWard.isPresent()){
            return WardModel.toModel(optionalWard.get());
        }else{
            throw new WardIsNotFoundException("Ward with this id is not exist");
        }
    }

    @Override
    public Long deleteWard(Long id) throws WardIsNotFoundException {
        Optional<Ward> optionalWard = wardRepository.findById(id);
        if(optionalWard.isPresent()) {
            wardRepository.deleteById(id);
            return id;
        }else{
            throw new WardIsNotFoundException("Ward with this id is not exist");
        }
    }

    @Override
    public Ward editWard(Long id, Ward ward){
        Ward editedWard = wardRepository.findById(id).get();
        editedWard.setMaxCount(ward.getMaxCount());
        editedWard.setName(ward.getName());

        return wardRepository.save(editedWard);
    }
}
