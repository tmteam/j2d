package com.genetics;

import java.util.ArrayList;
import java.util.Dictionary;

public class CompactDMXGenomRepresentation{
    private final int resolution;
    double[] mxs;
    double[] dxs;
    public CompactDMXGenomRepresentation(double[] genomSequence, int resolution){
        this.resolution = resolution;
        double alpha = 10;
        //задача подобрать такую альфу, чтобы вычислив распределение генома, сделать распределение нормализованных чисел максимально равномерным
        //это значит, что гистограмма по оригинальным числам, в результате растягивания по альфе должна стать равномерной
        //1. построим гистограмму для модулей чисел
        //2. А дальше хз... Забьём и подберём альфа методом тыка ;)

        double[] normalizedGenome = new double[genomSequence.length];
        for (int i = 0; i < genomSequence.length; i++) {
            normalizedGenome[i] = Tools.normalize(genomSequence[i],alpha);
        }
        int sectorLength = (int)Math.round(normalizedGenome.length/(double)resolution);
        mxs = new double[resolution];
        dxs = new double[resolution];

        for (int sectorN = 0; sectorN < resolution; sectorN++) {

            //sectorLength*sectorN
            int startSectorPostion = sectorLength * sectorN;
            int endSectorPosition = sectorLength*(sectorN+1);

            boolean isLast = sectorN== resolution-1;
            if(isLast){
                endSectorPosition = normalizedGenome.length-1;
            }

            int currentSectorLength = endSectorPosition-startSectorPostion;
            if(currentSectorLength<=0)
                break;

            double mx = 0;

            for (int i = startSectorPostion; i < endSectorPosition; i++)
                mx+=normalizedGenome[i];
            mx =  mx/currentSectorLength;

            double sd = 0;
            for (int i = startSectorPostion; i < endSectorPosition; i++) {
                sd+= Math.abs (mx- normalizedGenome[i]);
            }
            sd = sd/currentSectorLength;

            mxs[sectorN] = mx;
            dxs[sectorN] = sd;
        }
    }

    public String toString(){
        ArrayList<String> mAlphabet = new ArrayList<>();
        mAlphabet.add("A");      //        mAlphabet.add("a");
        mAlphabet.add("B");      //        mAlphabet.add("b");
        mAlphabet.add("C");      //        mAlphabet.add("c");
        mAlphabet.add("D");      //        mAlphabet.add("d");
        mAlphabet.add("E");      //        mAlphabet.add("e");
        mAlphabet.add("F");      //        mAlphabet.add("f");
        mAlphabet.add("G");      //        mAlphabet.add("g");
        mAlphabet.add("H");      //        mAlphabet.add("h");
        mAlphabet.add("I");      //        mAlphabet.add("i");
        mAlphabet.add("J");      //        mAlphabet.add("j");
        mAlphabet.add("K");      //        mAlphabet.add("k");
        mAlphabet.add("L");      //        mAlphabet.add("l");
        mAlphabet.add("M");      //        mAlphabet.add("m");
        mAlphabet.add("N");      //        mAlphabet.add("n");
        mAlphabet.add("O");      //        mAlphabet.add("o");
        mAlphabet.add("P");      //        mAlphabet.add("p");
        mAlphabet.add("Q");      //        mAlphabet.add("q");
        mAlphabet.add("R");      //        mAlphabet.add("r");
        mAlphabet.add("S");      //        mAlphabet.add("s");
        mAlphabet.add("T");      //        mAlphabet.add("t");
        mAlphabet.add("U");      //        mAlphabet.add("u");
        mAlphabet.add("V");      //        mAlphabet.add("v");
        mAlphabet.add("W");      //        mAlphabet.add("w");
        mAlphabet.add("X");      //        mAlphabet.add("x");
        mAlphabet.add("Y");      //        mAlphabet.add("y");
        mAlphabet.add("Z");      //        mAlphabet.add("z");





        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < resolution; i++) {
            int mIndex = (int)Math.round(mxs[i]*(mAlphabet.size()-1));
          //  int dIndex = (int)Math.round(dxs[i]*(mAlphabet.size()-1));

            sb.append(mAlphabet.get(mIndex));
            //sb.append(mAlphabet.get(dIndex));
           if(i!=resolution-1 && i>0 && i%5==0)
                sb.append("-");
        }
        return sb.toString();
    }
    public String toNumericString(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < resolution; i++) {
            sb.append(Math.round(mxs[i]*10));
            sb.append(Math.round(dxs[i]*10));
            if(i!=resolution-1)
                sb.append("-");
        }
        return sb.toString();
    }
    public double[] getMxs(){
        return mxs;
    }
    public double[] getDxs(){
        return dxs;
    }
    public int getResolution(){
        return resolution;
    }

}
