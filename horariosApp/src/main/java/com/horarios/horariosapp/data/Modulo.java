package com.horarios.horariosapp.data;

public class Modulo {
    private int moduleId;
    private String moduleCode;
    private String module;
    private int professorIds[];

    public Modulo() {}

    public Modulo(int moduleId, String moduleCode, String module, int professorIds[]){
        this.moduleId = moduleId;
        this.moduleCode = moduleCode;
        this.module = module;
        this.professorIds = professorIds;
    }

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }

    public int getModuleId(){
        return this.moduleId;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public String getModuleCode(){
        return this.moduleCode;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getModuleName(){
        return this.module;
    }

    public void setProfessorIds(int[] professorIds) {
        this.professorIds = professorIds;
    }

    public int getRandomProfessorId(){
        if (professorIds == null) return -1;
        return professorIds[(int) (professorIds.length * Math.random())];
    }
}
