
import java.util.*;


/*
        Created by Artturi Suominen
        This program uses "brute force" to create a n*n magic square.
        This really straigthforward implementation is rather slow (When n>3 the program
        takes way too much time to finish). There are more sophisticated methods/algorithms
        to make magic squares but those are for some other time.

        The program shuffles numbers from 1 to n^2 in the square (matrix) until all rows
        and columns add up to the same sum. There is a special case where the diagonal
        also adds up to the same sum, but that isn't handled in any special
        way by the program.

 */



public class magisk implements Runnable {

        /*
        Starts the progress to find a n*n magic square with numbers from n to n^2
        */
        
    public void run() {
        try {
            long aloitusAika = System.currentTimeMillis();
            int n = 3;
            int sqr = n * n;
            int[] nums = new int[sqr];
            boolean onkoTaika = false;
            int M = n * ((n * n + 1) / 2);
            for (int i = 0; i < sqr; i++) {
                nums[i] = i + 1;
                System.out.println(nums[i]);
            }
            int kierros = 0;
            while (!onkoTaika) {
                int[][] tryOut = magicMaker(n, nums);


                int[] tulokset = new int[2 * n];
                int tuloksetKohta = -1;
                while (tuloksetKohta < tulokset.length / 2 - 1) {
                    for (int i = 0; i < n; i++) {
                        tuloksetKohta++;
                        for (int j = 0; j < n; j++) {
                            tulokset[tuloksetKohta] += tryOut[i][j];
                        }
                    }
                }
                while (tuloksetKohta < tulokset.length - 1) {
                    for (int i = 0; i < n; i++) {
                        tuloksetKohta++;
                        for (int j = 0; j < n; j++) {
                            tulokset[tuloksetKohta] += tryOut[j][i];
                        }
                    }
                }

                /*
                 *   Jos summat ovat ovat samat, printataan neliö
                 *   ja yritysten lkm
                 */
                if (areAllSame(tulokset, M)) {
                    System.out.println();
                    System.out.println();
                    System.out.println("Taikaneliö löytyi!");
                    System.out.println();
                    for (int[] aTryOut : tryOut) {
                        for (int j = 0; j < tryOut.length; j++) {
                            System.out.print(aTryOut[j] + " ");
                        }
                        System.out.println();
                    }
                    onkoTaika = true;
                    System.out.println();
                    System.out.println("Taikaneliötä yritettiin muodostaa " + kierros + " kertaa");
                    long loppuAika = System.currentTimeMillis() - aloitusAika;
                    System.out.println("aikaa tähän meni " + loppuAika + " ms");
                    System.out.println();
                    System.out.println();
                }
                kierros++;

            }

        }
        catch(Exception e){
            System.out.println("Virhe tapahtui");
        }
    }


    /*
    Palauttaa yritteen, eli satunnaistetun (neliö)matriisin.
    Ottaa parametreikseen
    Returns a matrix with numbers 1...n^2 shuffled randomly.
    */
        
    private static int[][] magicMaker(int m, int[] arr){
        Random asettelija = new Random();
        int[][] yrite = new int[m][m];
        int[] suffeli = new int[arr.length];
        boolean jep;
        for (int anArr : arr) {
            jep = false;
            while (!jep) {
                int kohta = asettelija.nextInt(arr.length);
                if (suffeli[kohta] == 0) {
                    suffeli[kohta] = anArr;
                    jep = true;
                }
            }
        }
        int suffelinKohta = 0;
        while (suffelinKohta < suffeli.length){
            for (int l = 0;l<m;l++) {
            for (int i = 0; i < m; i++) {
                    yrite[l][i] = suffeli[suffelinKohta];
                    suffelinKohta++;
                }
            }
        }
        return yrite;
    }

    /*
    Jos rivien tai sarakkeiden summasta löytyy yksikin "väärä" summa, palautetaan false, muuten true
    Checks whether all the integers in int array summat are the same number (taikaluku)
    */
        
    private static boolean areAllSame(int[] summat, int taikaLuku){
        for (int aSummat : summat) {
            if (aSummat != taikaLuku)
                return false;
        }
        return true;
    }
}
