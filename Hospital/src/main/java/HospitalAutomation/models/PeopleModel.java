package HospitalAutomation.models;

import HospitalAutomation.entity.Diagnosis;
import HospitalAutomation.entity.People;
import HospitalAutomation.entity.Ward;

public class PeopleModel {
    private Long id;
    private String firstName;
    private String lastName;
    private String patherName;
    private Diagnosis diagnosisName;
    private Ward ward;


    public static PeopleModel toModel(People people){
        PeopleModel peopleModel = new PeopleModel();
        peopleModel.setId(people.getId());
        peopleModel.setFirstName(people.getFirstName());
        peopleModel.setLastName(people.getLastName());
        peopleModel.setPatherName(people.getPatherName());
        peopleModel.setDiagnosisName(people.getDiagnosisId());
        peopleModel.setWard(people.getWardId());
        return peopleModel;
    }

    public PeopleModel(Long id, String firstName, String lastName, String patherName, Diagnosis diagnosisName, Ward ward) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patherName = patherName;
        this.diagnosisName = diagnosisName;
        this.ward = ward;
    }

    public PeopleModel(){

    }

    public Diagnosis getDiagnosisName() {
        return diagnosisName;
    }

    public void setDiagnosisName(Diagnosis diagnosisName) {
        this.diagnosisName = diagnosisName;
    }

    public Ward getWard() {
        return ward;
    }

    public void setWard(Ward ward) {
        this.ward = ward;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatherName() {
        return patherName;
    }

    public void setPatherName(String patherName) {
        this.patherName = patherName;
    }

}
