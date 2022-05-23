package com.horarios.horariosapp.algorithm;

import com.horarios.horariosapp.data.Grupo;
import com.horarios.horariosapp.data.Horario;
import com.horarios.horariosapp.data.Match;
import com.horarios.horariosapp.data.Modulo;
import com.horarios.horariosapp.repository.Dao;

import java.util.ArrayList;
import java.util.HashMap;

public class Individual {
    private int[] chromosome;
    private double fitness = -1;

    public Individual(EvaHorario timetable) {
        int numClasses = timetable.getNumClasses();
        int chromosomeLength = numClasses * 3;
        int newChromosome[] = new int[chromosomeLength];
        int chromosomeIndex = 0;
        for (Grupo group : timetable.getGroupsAsArray()) {
            int[] moduleIds = EvaHorario.getModuleIds(group);
            int lastTime = -1;
            int lastRoom = -1;
            int lastProfessorId = -1;
            for (int i = 0; i < moduleIds.length; i++) {
                int moduleId = moduleIds[i];
                int times = getTimesByModule(group, moduleId);

                int left = -1;
                int right = -1;
                if (times >= 2) {
                    if ((i+1) < moduleIds.length -1 && moduleId == moduleIds[i+1]) {
                        right = moduleIds[i+1];
                    }

                    if ((i-1) >= 0 && moduleId == moduleIds[i-1]) {
                        left = moduleIds[i-1];
                    }
                }
                int timeslotId = timetable.getRandomTimeslot(
                        group.isMatutino(),
                        lastTime,
                        moduleId,
                        left,
                        right
                ).getTimeslotId();
                lastTime = timeslotId;
                boolean isCorrectTime = false;
                while (!isCorrectTime) {
                    boolean hasSameTime = false;
                    for (int h = 0; h < chromosomeLength/3; h+=3) {
                        if (timeslotId == newChromosome[h]) {
                            // horario
                            hasSameTime = true;
                        }
                    }

                    if (!hasSameTime) {
                        newChromosome[chromosomeIndex] = timeslotId;
                        chromosomeIndex++;
                        isCorrectTime = true;
                    } else {
                        timeslotId = timetable.getRandomTimeslot(
                                group.isMatutino(),
                                lastTime,
                                moduleId,
                                left,
                                right
                        ).getTimeslotId();
                        lastTime = timeslotId;
                    }

                }

                int roomId = timetable.getRandomRoom().getRoomId();
                boolean isCorrectRoom = false;
                while (!isCorrectRoom) {
                    if ( lastRoom != roomId) {
                        newChromosome[chromosomeIndex] = roomId;
                        chromosomeIndex++;
                        isCorrectRoom = true;
                    } else {
                        roomId = timetable.getRandomRoom().getRoomId();
                    }
                }
                lastRoom = roomId;

                // materia
                Modulo module = timetable.getModule(moduleId);
                newChromosome[chromosomeIndex] = module.getRandomProfessorId();
                chromosomeIndex++;

            }
        }
        this.chromosome = newChromosome;
    }
    public Individual(int chromosomeLength) {
        int[] individual;
        individual = new int[chromosomeLength];
        for (int gene = 0; gene < chromosomeLength; gene++) {
            individual[gene] = gene;
        }

        this.chromosome = individual;
    }
    public Individual(int[] chromosome) {
        this.chromosome = chromosome;
    }
    public int[] getChromosome() {
        return this.chromosome;
    }
    public int getChromosomeLength() {
        return this.chromosome.length;
    }
    public void setGene(int offset, int gene) {
        this.chromosome[offset] = gene;
    }
    public int getGene(int offset) {
        return this.chromosome[offset];
    }
    public void setFitness(double fitness) {
        this.fitness = fitness;
    }
    public double getFitness() {
        return this.fitness;
    }
    public String toString() {
        String output = "";
        for (int gene = 0; gene < this.chromosome.length; gene++) {
            output += this.chromosome[gene] + ",";
        }
        return output;
    }
    public boolean containsGene(int gene) {
        for (int i = 0; i < this.chromosome.length; i++) {
            if (this.chromosome[i] == gene) {
                return true;
            }
        }
        return false;
    }

    private int getTimesByModule(Grupo grupo, int moduleId) {
        int times = 0;
        for (Match match: grupo.getMatches()) {
            if (match.getModuleId() == moduleId)
                times = match.getTimes();
        }
        return times;
    }
}
