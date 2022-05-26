package HospitalAutomation.service;

import HospitalAutomation.entity.Diagnosis;
import HospitalAutomation.exceptions.DiagnosisAlreadyExistException;
import HospitalAutomation.exceptions.DiagnosisIsNotFoundException;
import HospitalAutomation.models.DiagnosisModel;

import java.util.List;

public interface DiagnosisService {
    List<Diagnosis> listDiagnosis();
    DiagnosisModel findDiagnosis(Long id) throws DiagnosisIsNotFoundException;
    Diagnosis saveDiagnosis(Diagnosis diagnosis) throws DiagnosisAlreadyExistException;
    Long deleteDiagnosis(Long id) throws DiagnosisIsNotFoundException;
    List<DiagnosisModel> getAllDiagnosis() throws Exception;
    Diagnosis editDiagnosis(Long id, Diagnosis diagnosis);
}