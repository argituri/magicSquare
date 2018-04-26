
import java.util.*;


/*
        Created by Artturi Suominen
        This program should initiate n number of threads each running magisk

 */



public class prosessi {
    public static void main(String[] args) {
        int[] n = new int[10];
        for (int i:n){
            new Thread(new magisk());
        }
    }
}
