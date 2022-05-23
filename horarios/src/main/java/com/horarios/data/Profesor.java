package com.horarios.data;

public class Profesor {
    private final int professorId;
    private final String professorName;

    public Profesor(int professorId, String professorName){
        this.professorId = professorId;
        this.professorName = professorName;
    }
    public int getProfessorId(){
        return this.professorId;
    }
    public String getProfessorName(){
        return this.professorName;
    }
}
