package HospitalAutomation.models;

import HospitalAutomation.entity.Diagnosis;
import HospitalAutomation.entity.People;

import java.util.List;
import java.util.stream.Collectors;

public class DiagnosisModel {
    private Long id;
    private String name;
//    private List<PeopleModel> people;

    public static DiagnosisModel toModel(Diagnosis diagnosis){
        DiagnosisModel diagnosisModel = new DiagnosisModel();
        diagnosisModel.setId(diagnosis.getId());
        diagnosisModel.setName(diagnosis.getName());
//        diagnosisModel.setPeople(diagnosis.getPatients().stream().map(PeopleModel::toModel).collect(Collectors.toList()));
        return diagnosisModel;
    }

    public DiagnosisModel(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public List<PeopleModel> getPeople() {
//        return people;
//    }
//
//    public void setPeople(List<PeopleModel> people) {
//        this.people = people;
//    }
}
