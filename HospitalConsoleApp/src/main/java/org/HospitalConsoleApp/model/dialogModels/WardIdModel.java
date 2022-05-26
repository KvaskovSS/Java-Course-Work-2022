package org.HospitalConsoleApp.model.dialogModels;

public class WardIdModel {
    private Long id;
    private String name;
    private int maxCount;


    public WardIdModel(Long id, String name, int maxCount) {
        this.id = id;
        this.name = name;
        this.maxCount = maxCount;
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
}
