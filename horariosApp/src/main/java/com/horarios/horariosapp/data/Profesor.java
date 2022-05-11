package com.horarios.horariosapp.data;

public class Profesor {
    private int professorId;
    private String professorName;
    private String profesorFirstLastName;
    private String profesorSecondtLastName;

    public Profesor() {}
    public Profesor(int professorId, String professorName){
        this.professorId = professorId;
        this.professorName = professorName;
    }

    public void setProfessorId(int professorId) {
        this.professorId = professorId;
    }
    public int getProfessorId(){
        return this.professorId;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

    public String getProfessorName(){
        return this.professorName;
    }

    public void setProfesorFirstLastName(String profesorFirstLastName) {
        this.profesorFirstLastName = profesorFirstLastName;
    }

    public String getProfesorFirstLastName() {
        return profesorFirstLastName;
    }

    public void setProfesorSecondtLastName(String profesorSecondtLastName) {
        this.profesorSecondtLastName = profesorSecondtLastName;
    }

    public String getProfesorSecondtLastName() {
        return profesorSecondtLastName;
    }
}
