package HospitalAutomation.service;

import HospitalAutomation.entity.Diagnosis;
import HospitalAutomation.entity.People;
import HospitalAutomation.entity.Ward;
import HospitalAutomation.exceptions.*;
import HospitalAutomation.models.PeopleModel;
import HospitalAutomation.repository.DiagnosisRepository;
import HospitalAutomation.repository.PeopleRepository;
import HospitalAutomation.repository.WardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PeopleServiceIml implements PeopleService{

    @Autowired
    private PeopleRepository peopleRepository;
    @Autowired
    private DiagnosisRepository diagnosisRepository;
    @Autowired
    private WardRepository wardRepository;

    @Override
    public People writePerson(People people, Long diagId, Long wardId) throws WardIsOwerFlowException, DiagnosisIsNotFoundException, WardIsNotFoundException, PeopleIsAlreadyExistException {
        Optional<Diagnosis> optionalDiagnosis = diagnosisRepository.findById(diagId);
        if(optionalDiagnosis.isEmpty()){
            throw new DiagnosisIsNotFoundException("Diagnosis with this id is not exist");
        }
        Optional<Ward> optionalWard = wardRepository.findById(wardId);
        if(optionalWard.isEmpty()){
            throw new WardIsNotFoundException("Ward with this id is not exist");
        }
        if(peopleRepository.findByFirstNameAndLastNameAndPatherName(
                people.getFirstName(),
                people.getLastName(),
                people.getPatherName()) != null){
            throw new PeopleIsAlreadyExistException("Patient with this Name is already in hospital");
        }

        Diagnosis diagnosis = optionalDiagnosis.get();
        Ward ward = optionalWard.get();
        if(ward.getMaxCount() == ward.getPatients().size()){
            throw new WardIsOwerFlowException("This ward is already full");
        }
        people.setDiagnosisId(diagnosis);
        people.setWardId(ward);
        return peopleRepository.save(people);
    }

    @Override
    public List<People> listPeople() {
        return (List<People>) peopleRepository.findAll();
    }

    @Override
    public String getAllPatients(){
        List<People> peopleList;
        peopleList = (List<People>) peopleRepository.findAll();
        StringBuilder exitString = new StringBuilder();

        for(int i = 0; i < peopleList.size(); i++){
            People temp = peopleList.get(i);
            temp.toString();
            exitString.append(temp);
        }
        return exitString.toString();
    }

    @Override
    public People findPatient(Long id) throws PeopleIsNotFoundException {
        Optional<People> optionalPeople = peopleRepository.findById(id);
        if(optionalPeople.isPresent()){
            return optionalPeople.get();
        }else{
            throw new PeopleIsNotFoundException("Patient with this id is not exist");
        }
    }

    @Override
    public Long deletePerson(Long id) throws PeopleIsNotFoundException {
        Optional<People> optionalPeople = peopleRepository.findById(id);
        if(optionalPeople.isPresent()) {
            peopleRepository.deleteById(id);
            return id;
        }else{
            throw new PeopleIsNotFoundException("Patient with this id is not exist");
        }
    }


    @Override
    public List<PeopleModel> findByWard(String wardName){

        return peopleRepository.findByWardName(wardName).stream().map(People::toModel).collect(Collectors.toList());

    }

    @Override
    public People editPatient(Long id, People details){
        People editedPeople = peopleRepository.findById(id).get();
        editedPeople.setFirstName(details.getFirstName());
        editedPeople.setLastName(details.getLastName());
        editedPeople.setPatherName(details.getPatherName());

        return peopleRepository.save(editedPeople);
    }

    @Override
    public List<PeopleModel> findByDiagnosis(String diagnosisName){
        return peopleRepository.findByDiagnosis(diagnosisName).stream().map(People::toModel).collect(Collectors.toList());
    }

    @Override
    public List<PeopleModel> findByFirstName(String firstName){
        return peopleRepository.findByFirstName(firstName).stream().map(People::toModel).collect(Collectors.toList());
    }

    @Override
    public List<PeopleModel> findByLastName(String lastName){
        return peopleRepository.findByLastName(lastName).stream().map(People::toModel).collect(Collectors.toList());
    }

    @Override
    public List<PeopleModel> findByPatherName(String patherName){
        return peopleRepository.findByPatherName(patherName).stream().map(People::toModel).collect(Collectors.toList());
    }

}
