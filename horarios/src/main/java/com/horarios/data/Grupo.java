package com.horarios.data;

public class Grupo {
    private final int groupId;
    private final int groupSize;
    private final int[] moduleIds;

    public Grupo(int groupId, int groupSize, int[] moduleIds){
        this.groupId = groupId;
        this.groupSize = groupSize;
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
}
