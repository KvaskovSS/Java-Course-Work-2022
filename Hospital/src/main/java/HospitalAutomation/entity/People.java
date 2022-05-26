package HospitalAutomation.entity;

import HospitalAutomation.models.PeopleModel;

import javax.persistence.*;

@Entity
public class People {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;
    private String patherName;

    @ManyToOne
    @JoinColumn(name = "diagnosisId")
    private Diagnosis diagnosisId;

    @ManyToOne
    @JoinColumn(name = "wardId")
    private Ward wardId;

    public People(){

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

    public PeopleModel toModel(){
        return new PeopleModel(this.id, this.getFirstName(), this.getLastName(), this.getPatherName(), this.getDiagnosisId(),this.getWardId());
    }

    @Override
    public String toString() {
        return "People{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", patherName='" + patherName + '\'' +
                '}';
    }
}
