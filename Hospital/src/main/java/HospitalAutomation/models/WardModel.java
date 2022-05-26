package HospitalAutomation.models;

import HospitalAutomation.entity.Diagnosis;
import HospitalAutomation.entity.Ward;

import java.util.List;
import java.util.stream.Collectors;

public class WardModel {
    private Long id;
    private String name;
    private int maxCount;
    //private List<PeopleModel> people;

    public static WardModel toModel(Ward ward){
        WardModel wardModel = new WardModel();
        wardModel.setId(ward.getId());
        wardModel.setName(ward.getName());
        wardModel.setMaxCount(ward.getMaxCount());
       // wardModel.setPeople(ward.getPatients().stream().map(PeopleModel::toModel).collect(Collectors.toList()));

        return wardModel;
    }

    public WardModel(){

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

    public int getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }

//    public List<PeopleModel> getPeople() {
//        return people;
//    }
//
//    public void setPeople(List<PeopleModel> people) {
//        this.people = people;
//    }
}
