package org.HospitalConsoleApp.model.dialogModels;

import org.HospitalConsoleApp.model.getModels.Diagnosis;
import org.HospitalConsoleApp.model.getModels.Ward;

public class PeopleIdModel {
    private Long id;
    private String firstName;
    private String lastName;
    private String patherName;
    private Diagnosis diagnosisId;
    private Ward wardId;

    public PeopleIdModel(Long id, String firstName, String lastName, String patherName, Diagnosis diagnosisId, Ward wardId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patherName = patherName;
        this.diagnosisId = diagnosisId;
        this.wardId = wardId;
    }

    public String getWardName(){
        return wardId.getName();
    }

    public String getDiagnosisName(){
        return diagnosisId.getName();
    }

    public Diagnosis getDiagnosisId() {
        return diagnosisId;
    }

    public void setDiagnosisId(Diagnosis diagnosisId) {
        this.diagnosisId = diagnosisId;
    }

    public Ward getWardId() {
        return wardId;
    }

    public void setWardId(Ward wardId) {
        this.wardId = wardId;
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
