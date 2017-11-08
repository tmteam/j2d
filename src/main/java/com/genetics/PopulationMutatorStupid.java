package com.genetics;

public class PopulationMutatorStupid implements PopulationMutator {

    public PopulationMutatorStupid(){
    }

    @Override
    public Genom[] Mutate(GenomFitness[] previousPopulation) {
        //take best pius
        int populationSize = previousPopulation.length;
        Genom[] res = new Genom[populationSize];
        int generated = 0;

        for (int i = populationSize - 1; i > 0; i--) {
            GenomFitness genom = previousPopulation[i];

            double[] genom1 = genom.getGenomCopy();
            double[] genom2 = genom.getGenomCopy();

            new GausianMutator(0.05,0.5).mutate(genom1);
            new FatalMutator(0.05).mutate(genom2);

            if (generated >= populationSize)
                break;
            res[generated] = new Genom(genom1);
            generated++;
            if (generated >= populationSize)
                break;
            res[generated] = new Genom(genom2);
            generated++;
        }
        return res;
    }
}
