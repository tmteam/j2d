package com.genetics;

import java.util.Random;

public class SingleGenomCombinedMutator implements IMutator {

    Random r = new Random();
    @Override
    public void mutate(double[] genom) {
        switch (r.nextInt(6)){
            case 0:
                new SoftGausianMutator(0.05,0.1).mutate(genom);
            break;
            case 1:
                new FatalMutator(0.01).mutate(genom);
            break;
            case 2:
                new SingleGenomCombinedMutator().mutate(genom);
            case 4:
                mutate(genom);
            break;
        }
    }
}
