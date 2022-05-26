package org.HospitalConsoleApp.model.getModels;

public class People {
    private String firstName;
    private String lastName;
    private String patherName;
    private Diagnosis diagnosisId;
    private Ward wardId;


    public People(String firstName, String lastName, String patherName, Diagnosis diagnosisId, Ward wardId) {
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
