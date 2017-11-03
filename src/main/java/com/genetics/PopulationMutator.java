package com.genetics;

public interface PopulationMutator {
    Genom[] Mutate(GenomFitness[] previousPopulation);
}
