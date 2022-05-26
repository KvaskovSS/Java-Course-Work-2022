package HospitalAutomation.service;

import HospitalAutomation.entity.People;
import HospitalAutomation.exceptions.*;
import HospitalAutomation.models.PeopleModel;

import java.util.List;

public interface PeopleService {
    List<People> listPeople();
    People findPatient(Long id) throws PeopleIsNotFoundException;
    People writePerson(People people, Long diagId, Long wardId) throws WardIsOwerFlowException, DiagnosisIsNotFoundException, WardIsNotFoundException, PeopleIsAlreadyExistException;
    Long deletePerson(Long id) throws PeopleIsNotFoundException;
    String getAllPatients();
    List<PeopleModel> findByWard(String wardName);
    List<PeopleModel> findByDiagnosis(String diagnosisName);
    List<PeopleModel> findByFirstName(String firstName);
    List<PeopleModel> findByLastName(String lastName);
    List<PeopleModel> findByPatherName(String patherName);
    People editPatient(Long id, People details);
}
