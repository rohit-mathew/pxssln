package com.paxos.challenge.third;

/**
 * Created by rohit_mathew on 12/10/17.
 */
public class ThirdChallenge {

    public void printAllCombinations(final String str) {
        char chrArray[] = str.toCharArray();
        //Find the number of x's
        int count = 0;
        for (int i = 0 ; i < str.length(); i++){
            if ((chrArray[i] == 'X') || (chrArray[i] == 'x')) {
                count++;
            }
        }
        //Generate all the binary possibilities for just the x's
        for (int j = 0; j < Math.pow(2, count); j++) {
            int localItr = j;
            for (int i = 0 ; i < str.length(); i++){
                if ((chrArray[i] == 'X') || (chrArray[i] == 'x')) {
                    System.out.print(localItr % 2);
                    localItr /= 2;
                } else {
                    System.out.print(chrArray[i]);
                }
            }
            System.out.println("");
        }
    }

    public static void main(String[] args) {
        new ThirdChallenge().printAllCombinations(args[0]);
    }
}
