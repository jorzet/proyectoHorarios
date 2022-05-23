package com.horarios.data;

public class Aula {
    private final int roomId;
    private final String roomNumber;
    private final int capacity;

    public Aula(int roomId, String roomNumber, int capacity) {
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.capacity = capacity;
    }
    public int getRoomId() {
        return this.roomId;
    }
    public String getRoomNumber() { return this.roomNumber; }
    public int getRoomCapacity() {
        return this.capacity;
    }

}
