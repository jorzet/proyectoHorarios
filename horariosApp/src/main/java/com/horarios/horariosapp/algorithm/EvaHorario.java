package com.horarios.horariosapp.algorithm;

import com.horarios.horariosapp.data.*;
import com.horarios.horariosapp.data.Class;

import java.util.ArrayList;
import java.util.HashMap;

public class EvaHorario {
    private final HashMap<Integer, Aula> rooms;
    private final HashMap<Integer, Profesor> professors;
    private final HashMap<Integer, Modulo> modules;
    private final HashMap<Integer, Grupo> groups;
    private final HashMap<Integer, Horario> timeslots;
    private Class classes[];
    private int numClasses = 0;

    public EvaHorario() {
        this.rooms = new HashMap<Integer, Aula>();
        this.professors = new HashMap<Integer, Profesor>();
        this.modules = new HashMap<Integer, Modulo>();
        this.groups = new HashMap<Integer, Grupo>();
        this.timeslots = new HashMap<Integer, Horario>();
    }
    public EvaHorario(EvaHorario cloneable) {
        this.rooms = cloneable.getRooms();
        this.professors = cloneable.getProfessors();
        this.modules = cloneable.getModules();
        this.groups = cloneable.getGroups();
        this.timeslots = cloneable.getTimeslots();
    }
    private HashMap<Integer, Grupo> getGroups() {
        return this.groups;
    }
    private HashMap<Integer, Horario> getTimeslots() {
        return this.timeslots;
    }
    private HashMap<Integer, Modulo> getModules() {
        return this.modules;
    }
    private HashMap<Integer, Profesor> getProfessors() {
        return this.professors;
    }
    public void addRoom(int roomId, String roomName, int capacity) {
        this.rooms.put(roomId, new Aula(roomId, roomName, capacity));
    }
    public void addProfessor(int professorId, String professorName) {
        this.professors.put(professorId, new Profesor(professorId, professorName));
    }
    public void addModule(int moduleId, String moduleCode, String module, int professorIds[]) {
        this.modules.put(moduleId, new Modulo(moduleId, moduleCode, module, professorIds));
    }
    public void addGroup(int groupId, String groupName, int groupSize, boolean isMatutino, ArrayList<Match> matches) {
        this.groups.put(groupId, new Grupo(groupId, groupName, groupSize, isMatutino, matches));
        this.numClasses = 0;
    }
    public void addTimeslot(int timeslotId, String timeslot, String day) {
        this.timeslots.put(timeslotId, new Horario(timeslotId, timeslot, day));
    }
    public void createClasses(Individual individual) {
        Class classes[] = new Class[this.getNumClasses()];
        int chromosome[] = individual.getChromosome();
        int chromosomePos = 0;
        int classIndex = 0;
        for (Grupo group : this.getGroupsAsArray()) {

            int moduleIds[] = getModuleIds(group);

            for (int moduleId : moduleIds) {
                classes[classIndex] = new Class(classIndex, group.getGroupId(), moduleId);
                Horario horario = timeslots.get(chromosome[chromosomePos]);
                classes[classIndex].addTimeslot(chromosome[chromosomePos]);
                chromosomePos++;
                classes[classIndex].setRoomId(chromosome[chromosomePos]);
                chromosomePos++;
                classes[classIndex].addProfessor(chromosome[chromosomePos]);
                chromosomePos++;
                classIndex++;
            }
        }
        this.classes = classes;
    }
    public Aula getRoom(int roomId) {
        if (!this.rooms.containsKey(roomId)) {
            System.out.println("Rooms doesn't contain key " + roomId);
        }
        return (Aula) this.rooms.get(roomId);
    }
    public HashMap<Integer, Aula> getRooms() {
        return this.rooms;
    }
    public Aula getRandomRoom() {
        Object[] roomsArray = this.rooms.values().toArray();
        Aula room = (Aula) roomsArray[(int) (roomsArray.length * Math.random())];
        return room;
    }
    public Profesor getProfessor(int professorId) {
        return (Profesor) this.professors.get(professorId);
    }
    public Modulo getModule(int moduleId) { return (Modulo) this.modules.get(moduleId); }
    public Grupo getGroup(int groupId) {
        return (Grupo) this.groups.get(groupId);
    }
    public Grupo[] getGroupsAsArray() {
        return (Grupo[]) this.groups.values().toArray(new Grupo[this.groups.size()]);
    }
    public Horario getTimeslot(int timeslotId) { return (Horario) this.timeslots.get(timeslotId); }

    public Horario getRandomTimeslot(boolean isMatutino, int lastTime, int currentModuleId, int left, int right) {
        Object[] timeslotArray = this.timeslots.values().toArray();
        Horario timeslot = (Horario) timeslotArray[(int) (timeslotArray.length * Math.random())];
        if (currentModuleId == left && currentModuleId != right
                && lastTime != -1 && (lastTime + 1) < timeslots.size()) {
            //return timeslots.get(lastTime + 1);
        }
        boolean isCorrectTime = false;
        while (!isCorrectTime) {
            String[] time = timeslot.getTimeslot().replace(" ", "").split("-")[1].split(":");
            double doubleTime = Double.parseDouble(time[0]) + Double.parseDouble(time[1]) / 60;

            if (((isMatutino && doubleTime <= 14) || (!isMatutino && doubleTime >14))) {
                isCorrectTime = true;
            } else
                timeslot = (Horario) timeslotArray[(int) (timeslotArray.length * Math.random())];
        }
        return timeslot;
    }
    public Class[] getClasses() {
        return this.classes;
    }
    public int getNumClasses() {
        if (this.numClasses > 0) {
            return this.numClasses;
        }
        int numClasses = 0;
        Grupo groups[] = this.groups.values().toArray(new Grupo[this.groups.size()]);
        for (Grupo group : groups) {
            for (Match match: group.getMatches())
                numClasses += match.getTimes();
        }
        this.numClasses = numClasses;
        return this.numClasses;
    }
    public int calcClashes() {
        int clashes = 0;
        for (Class classA : this.classes) {
            if (classA != null) {
                int roomCapacity = this.getRoom(classA.getRoomId()).getRoomCapacity();
                int groupSize = this.getGroup(classA.getGroupId()).getGroupSize();
                if (roomCapacity < groupSize) {
                    clashes++;
                }
                for (Class classB : this.classes) {
                    if (classB != null &&  classA.getRoomId() == classB.getRoomId() && classA.getTimeslotId() == classB.getTimeslotId()
                            && classA.getClassId() != classB.getClassId()) {
                        clashes++;
                        break;
                    }
                }
                for (Class classB : this.classes) {
                    if (classB != null &&  classA.getProfessorId() == classB.getProfessorId() && classA.getTimeslotId() == classB.getTimeslotId()
                            && classA.getClassId() != classB.getClassId()) {
                        clashes++;
                        break;
                    }
                }
                for (Class classB : this.classes) {
                    if (classB != null && classA.getRoomId() == classB.getRoomId() && classA.getProfessorId() == classB.getProfessorId()
                            && classA.getClassId() != classB.getClassId()) {
                        clashes++;
                        break;
                    }
                }

                /*for (Class classB : this.classes) {
                    if (classB != null) {
                        Horario classHorarioA = timeslots.get(classA.getTimeslotId());
                        Horario classHorarioB = timeslots.get(classB.getTimeslotId());
                        String dayA = classHorarioA.getDay();
                        String dayB = classHorarioB.getDay();
                        String[] timeA = classHorarioA.getTimeslot().replace(" ", "").split("-")[0].split(":");
                        String[] timeB = classHorarioB.getTimeslot().replace(" ", "").split("-")[0].split(":");
                        double doubleTimeA = Double.parseDouble(timeA[0]) + Double.parseDouble(timeA[1]) / 60;
                        double doubleTimeB = Double.parseDouble(timeB[0]) + Double.parseDouble(timeB[1]) / 60;

                        if (dayA.equals(dayB) && Math.abs(doubleTimeB - doubleTimeA) < 2 &&
                                classA.getGroupId() == classB.getGroupId()
                                && classA.getClassId() != classB.getClassId()) {
                            clashes++;
                            break;
                        }
                    }
                }*/

            /*for (Class classB : this.classes) {
                Horario classHorarioA =  timeslots.get(classA.getTimeslotId());
                Horario classHorarioB =  timeslots.get(classB.getTimeslotId());
                String dayA = classHorarioA.getDay();
                String dayB = classHorarioB.getDay();

                if (dayA.equals(dayB) && classA.getModuleId() != classB.getModuleId() &&
                        classA.getClassId() != classB.getClassId()) {
                    clashes++;
                    break;
                }
            }*/
            }
        }
        return clashes;
    }

    public static int[] getModuleIds(Grupo group) {
        ArrayList<Integer> moduleIdsArray = new ArrayList<>();
        for (int i = 0; i < group.getMatches().size(); i++) {
            for (int times = 0; times < group.getMatches().get(i).getTimes(); times++)
                moduleIdsArray.add(group.getMatches().get(i).getModuleId());
        }

        int moduleIds[] = new int[moduleIdsArray.size()];
        for (int i = 0; i < moduleIdsArray.size(); i++) {
            moduleIds[i] = moduleIdsArray.get(i);
        }
        return moduleIds;
    }
}
