package com.horarios.horariosapp.data;

public class GroupModule {
    private int idGroup;
    private int idModule;
    private int idTeacher;

    private int times;

    public int getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(int idGroup) {
        this.idGroup = idGroup;
    }

    public void setIdModule(int idModule) {
        this.idModule = idModule;
    }

    public int getIdModule() {
        return idModule;
    }

    public void setIdTeacher(int idTeacher) {
        this.idTeacher = idTeacher;
    }

    public int getIdTeacher() {
        return idTeacher;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public int getTimes() {
        return times;
    }
}
