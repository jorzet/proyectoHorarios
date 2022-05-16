package com.horarios.horariosapp.algorithm;

import com.horarios.horariosapp.data.Grupo;
import com.horarios.horariosapp.data.Horario;
import com.horarios.horariosapp.data.Modulo;
import com.horarios.horariosapp.repository.Dao;

import java.util.ArrayList;

public class Individual {
    private int[] chromosome;
    private double fitness = -1;

    public Individual(EvaHorario timetable) {
        int numClasses = timetable.getNumClasses();
        int chromosomeLength = numClasses * 3;
        int newChromosome[] = new int[chromosomeLength];
        ArrayList<Horario> times = new Dao().getAllTimes();
        int chromosomeIndex = 0;
        for (Grupo group : timetable.getGroupsAsArray()) {
            for (int moduleId : group.getModuleIds()) {
                int timeslotId = timetable.getRandomTimeslot().getTimeslotId();
                newChromosome[chromosomeIndex] = timeslotId;
                chromosomeIndex++;
                /*if (chromosomeIndex == 0){
                    newChromosome[chromosomeIndex] = timeslotId;
                    chromosomeIndex++;
                } else {
                    int chromosomeIndexAux = chromosomeIndex;
                    if (newChromosome[chromosomeIndex] < times.size()) {
                        Horario classHorarioA = times.get(newChromosome[chromosomeIndex]);
                        for (int i = 0; i < chromosomeIndexAux - 1; i++) {
                            if (newChromosome[i] < times.size()) {
                                Horario classHorarioB = times.get(newChromosome[i]);
                                String[] timeA = classHorarioA.getTimeslot().replace(" ", "").split("-")[0].split(":");
                                String[] timeB = classHorarioB.getTimeslot().replace(" ", "").split("-")[0].split(":");
                                double doubleTimeA = Double.parseDouble(timeA[0]) + Double.parseDouble(timeA[1]) / 60;
                                double doubleTimeB = Double.parseDouble(timeB[0]) + Double.parseDouble(timeB[1]) / 60;
                                if (classHorarioA.getDay().equals(classHorarioB.getDay()) &&
                                        Math.abs(doubleTimeA - doubleTimeB) < 2) {
                                    newChromosome[chromosomeIndex] = timeslotId;
                                    chromosomeIndex++;
                                    i = chromosomeIndexAux - 1;
                                }
                            }
                        }
                    }
                }*/

                int roomId = timetable.getRandomRoom().getRoomId();
                newChromosome[chromosomeIndex] = roomId;
                chromosomeIndex++;
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
}
