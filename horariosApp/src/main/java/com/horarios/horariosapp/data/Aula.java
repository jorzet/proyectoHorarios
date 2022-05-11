package com.horarios.horariosapp.data;

public class Aula {
    private int roomId;
    private String roomNumber;
    private int capacity;

    public Aula() {}
    public Aula(int roomId, String roomNumber, int capacity) {
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.capacity = capacity;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }
    public int getRoomId() {
        return this.roomId;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomNumber() { return this.roomNumber; }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getRoomCapacity() {
        return this.capacity;
    }

}
