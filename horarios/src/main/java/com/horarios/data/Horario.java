package com.horarios.data;

public class Horario {
    private final int timeslotId;
    private final String timeslot;

    public Horario(int timeslotId, String timeslot){
        this.timeslotId = timeslotId;
        this.timeslot = timeslot;
    }
    public int getTimeslotId(){
        return this.timeslotId;
    }
    public String getTimeslot(){
        return this.timeslot;
    }
}
