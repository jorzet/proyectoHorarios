package com.horarios.algorithm;

public class AlgoritmoGenetico {
    private int populationSize;
    private double mutationRate;
    private double crossoverRate;
    private int elitismCount;
    protected int tournamentSize;

    public AlgoritmoGenetico(int populationSize, double mutationRate, double crossoverRate, int elitismCount, int tournamentSize) {
        this.populationSize = populationSize;
        this.mutationRate = mutationRate;
        this.crossoverRate = crossoverRate;
        this.elitismCount = elitismCount;
        this.tournamentSize = tournamentSize;
    }
    public Poblacion initPopulation(EvaHorario timetable) {
        Poblacion population = new Poblacion(this.populationSize, timetable);
        return population;
    }
    public boolean isTerminationConditionMet(int generationsCount, int maxGenerations) {
        return (generationsCount > maxGenerations);
    }
    public boolean isTerminationConditionMet(Poblacion population) {
        return population.getFittest(0).getFitness() == 1.0;
    }
    public double calcFitness(Individual individual, EvaHorario timetable) {
        EvaHorario threadTimetable = new EvaHorario(timetable);
        threadTimetable.createClasses(individual);
        int clashes = threadTimetable.calcClashes();
        double fitness = 1 / (double) (clashes + 1);
        individual.setFitness(fitness);
        return fitness;
    }
    public void evalPopulation(Poblacion population, EvaHorario timetable) {
        double populationFitness = 0;
        for (Individual individual : population.getIndividuals()) {
            populationFitness += this.calcFitness(individual, timetable);
        }
        population.setPopulationFitness(populationFitness);
    }
    public Individual selectParent(Poblacion population) {
        Poblacion tournament = new Poblacion(this.tournamentSize);
        population.shuffle();
        for (int i = 0; i < this.tournamentSize; i++) {
            Individual tournamentIndividual = population.getIndividual(i);
            tournament.setIndividual(i, tournamentIndividual);
        }
        return tournament.getFittest(0);
    }
    public Poblacion mutatePopulation(Poblacion population, EvaHorario timetable) {
        Poblacion newPopulation = new Poblacion(this.populationSize);
        for (int populationIndex = 0; populationIndex < population.size(); populationIndex++) {
            Individual individual = population.getFittest(populationIndex);
            Individual randomIndividual = new Individual(timetable);
            for (int geneIndex = 0; geneIndex < individual.getChromosomeLength(); geneIndex++) {
                if (populationIndex > this.elitismCount) {
                    if (this.mutationRate > Math.random()) {
                        individual.setGene(geneIndex, randomIndividual.getGene(geneIndex));
                    }
                }
            }
            newPopulation.setIndividual(populationIndex, individual);
        }
        return newPopulation;
    }
    public Poblacion crossoverPopulation(Poblacion population) {
        Poblacion newPopulation = new Poblacion(population.size());
        for (int populationIndex = 0; populationIndex < population.size(); populationIndex++) {
            Individual parent1 = population.getFittest(populationIndex);
            if (this.crossoverRate > Math.random() && populationIndex >= this.elitismCount) {
                Individual offspring = new Individual(parent1.getChromosomeLength());
                Individual parent2 = selectParent(population);
                for (int geneIndex = 0; geneIndex < parent1.getChromosomeLength(); geneIndex++) {
                    if (0.5 > Math.random()) {
                        offspring.setGene(geneIndex, parent1.getGene(geneIndex));
                    } else {
                        offspring.setGene(geneIndex, parent2.getGene(geneIndex));
                    }
                }
                newPopulation.setIndividual(populationIndex, offspring);
            } else {
                newPopulation.setIndividual(populationIndex, parent1);
            }
        }
        return newPopulation;
    }
}
