package com.horarios.horariosapp.data;

public class Horario {
    private int timeslotId;
    private String timeslot;
    private String day;

    public Horario() {}
    public Horario(int timeslotId, String timeslot, String day){
        this.timeslotId = timeslotId;
        this.timeslot = timeslot;
        this.day = day;
    }

    public void setTimeslotId(int timeslotId) {
        this.timeslotId = timeslotId;
    }

    public int getTimeslotId(){
        return this.timeslotId;
    }

    public void setTimeslot(String timeslot) {
        this.timeslot = timeslot;
    }

    public String getTimeslot(){
        return this.timeslot;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDay() {
        return day;
    }
}
