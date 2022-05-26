package HospitalAutomation.service;

import HospitalAutomation.entity.Diagnosis;
import HospitalAutomation.exceptions.DiagnosisAlreadyExistException;
import HospitalAutomation.exceptions.DiagnosisIsNotFoundException;
import HospitalAutomation.models.DiagnosisModel;
import HospitalAutomation.repository.DiagnosisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DiagnosisServiceIml implements DiagnosisService{

    @Autowired
    private DiagnosisRepository diagnosisRepository;

    @Override
    public List<Diagnosis> listDiagnosis() {
        return (List<Diagnosis>) diagnosisRepository.findAll();
    }

    @Override
    public List<DiagnosisModel> getAllDiagnosis() throws Exception {
        try {
            List<Diagnosis> diagnosisList = (List<Diagnosis>) diagnosisRepository.findAll();
            return diagnosisList.stream().map(DiagnosisModel::toModel).collect(Collectors.toList());
        }catch (Exception exception){
            throw new Exception(exception.getMessage());
        }
    }

    @Override
    public Diagnosis saveDiagnosis(Diagnosis diagnosis) throws DiagnosisAlreadyExistException {
        if(diagnosisRepository.findByName(diagnosis.getName()) != null){
            throw new DiagnosisAlreadyExistException("Diagnosis is already exist");
        }
        return diagnosisRepository.save(diagnosis);
    }

    @Override
    public DiagnosisModel findDiagnosis(Long id) throws DiagnosisIsNotFoundException {
        Optional<Diagnosis> optionalDiagnosis = diagnosisRepository.findById(id);
        if(optionalDiagnosis.isPresent()){
            return DiagnosisModel.toModel(optionalDiagnosis.get()) ;
        }else {
            throw new DiagnosisIsNotFoundException("Diagnosis with this id is not exist");
        }
    }

    @Override
    public Long deleteDiagnosis(Long id) throws DiagnosisIsNotFoundException {
        Optional<Diagnosis> optionalDiagnosis = diagnosisRepository.findById(id);
        if(optionalDiagnosis.isPresent()) {
            diagnosisRepository.deleteById(id);
            return id;
        }else{
            throw new DiagnosisIsNotFoundException("Diagnosis with this id is not exist");
        }
    }

    @Override
    public Diagnosis editDiagnosis(Long id, Diagnosis diagnosis){
        Diagnosis editedDiagnosis = diagnosisRepository.findById(id).get();
        editedDiagnosis.setName(diagnosis.getName());
        return diagnosisRepository.save(editedDiagnosis);
    }
}
