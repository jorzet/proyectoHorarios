package com.horarios.horariosapp.data;

import java.util.ArrayList;

public class Grupo {
    private  int groupId;
    private String groupName;
    private  int groupSize;
    private boolean matutino;
    private ArrayList<Match> matches;

    public Grupo() {}
    public Grupo(int groupId, int groupSize, ArrayList<Match> matches){
        this.groupId = groupId;
        this.groupSize = groupSize;
        this.matches = matches;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public void setGroupSize(int groupSize) {
        this.groupSize = groupSize;
    }

    public void setMatches(ArrayList<Match> matches) {
        this.matches = matches;
    }

    public int getGroupId(){
        return this.groupId;
    }
    public int getGroupSize(){
        return this.groupSize;
    }

    public ArrayList<Match> getMatches() {
        return this.matches;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setMatutino(boolean matutino) {
        this.matutino = matutino;
    }

    public boolean isMatutino() {
        return matutino;
    }
}
