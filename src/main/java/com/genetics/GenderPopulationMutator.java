package com.genetics;

import java.util.*;

public class GenderPopulationMutator implements PopulationMutator {
    Random r = new Random();
    @Override
    public Genom[] Mutate(GenomFitness[] previousPopulation) {
        List<GenomFitness> list=  new ArrayList(Arrays.asList(previousPopulation));

        list.sort(new Comparator<GenomFitness>() {
            public int compare(GenomFitness o1, GenomFitness o2) {
                return o1.fitness > o2.fitness ? 1 : o1.fitness == o2.fitness ? 0 : -1;
            }
        });

        Kill(list);

        List<Genom> newPopulation = new ArrayList<>(previousPopulation.length);
        while (newPopulation.size()<previousPopulation.length){
            double bestFitness= list.get(list.size()-1).fitness;

            GenomFitness father =getRandomParent(list, bestFitness);
            GenomFitness mother =getRandomParent(list, bestFitness);

            double[] g1 = null;
            double[] g2 = null;

            int rndChoice = r.nextInt(3);
            if(rndChoice == 0){
                Crosover_1 c1 =  new Crosover_1();
                c1.calc(father.genom,mother.genom);
                g1 = c1.getChild1();
                g2 = c1.getChild2();

            }
            else if(rndChoice==1){
                Crosover_2 c2 =  new Crosover_2();
                c2.calc(father.genom,mother.genom);
                g1 = c2.getChild1();
                g2 = c2.getChild2();
            }
            else {
                g1 = mother.getGenomCopy();
                g2 = father.getGenomCopy();
            }

           // new GausianMutator(0.01).mutate(g1);
            new SoftGausianMutator(0.05,0.1).mutate(g2);

            new FatalMutator(0.01).mutate(g2);

            newPopulation.add(new Genom(g1));
            newPopulation.add(new Genom(g2));

        }

        return newPopulation.toArray(new Genom[previousPopulation.length]);
    }
    private GenomFitness getRandomParent(List<GenomFitness> fitnesses, double bestFitness){
        while (true) {
            int fatherId = r.nextInt(fitnesses.size());
            GenomFitness father = fitnesses.get(fatherId);
            double deathP= father.fitness/bestFitness;
            if(r.nextDouble()<deathP){
               return father;
            }
        }
    }
    private void Kill( List<GenomFitness> list) {
        double killPercentage = 0.6;
        int aliveCount =  (int)(list.size()*killPercentage);
        double bestFitness= list.get(list.size()-1).fitness;

        for (int i =  0; list.size()>aliveCount; i++) {
             double deathP= 1- list.get(i).fitness/bestFitness;
             if(r.nextDouble()<deathP){
                 list.remove(i);
             }
            if(i>=list.size()-1){
                 i = 0;
            }
        }
    }

}
