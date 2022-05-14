package com.horarios.horariosapp.data;

public class Grupo {
    private  int groupId;
    private String groupName;
    private  int groupSize;
    private boolean matutino;
    private  int[] moduleIds;

    public Grupo() {}
    public Grupo(int groupId, int groupSize, int[] moduleIds){
        this.groupId = groupId;
        this.groupSize = groupSize;
        this.moduleIds = moduleIds;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public void setGroupSize(int groupSize) {
        this.groupSize = groupSize;
    }

    public void setModuleIds(int[] moduleIds) {
        this.moduleIds = moduleIds;
    }

    public int getGroupId(){
        return this.groupId;
    }
    public int getGroupSize(){
        return this.groupSize;
    }
    public int[] getModuleIds() {
        return this.moduleIds;
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
