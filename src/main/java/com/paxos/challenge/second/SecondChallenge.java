package com.paxos.challenge.second;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Created by rohit_mathew on 12/10/17.
 */
class Pair {
    int first;
    int last;

    public Pair(final int num1, final int num2) {
        this.first = num1;
        this.last = num2;
    }
}

public class SecondChallenge {
    ArrayList<Item> list = new ArrayList<Item>();

    class Item {
        String name;
        int price;

        public Item(final String entry) {
            String arr[] = entry.split(", ");
            this.name = arr[0];
            this.price = Integer.parseInt(arr[1]);
        }

        @Override
        public String toString(){
            return this.name + " " + this.price;
        }
    }

    public void addToList(final String entry) {
        this.list.add(new Item(entry));
    }

    /*
     * This solution will work only if there are no two items with the same price
     */
    public Pair findTwoPrices(Integer target, Integer first, Integer last) {
        if (list.size() < 2) {
            System.out.println("Duo not possible");
        }
        if ((list.get(first).price + list.get(first + 1).price > target)) {
            System.out.println("Duo not possible");
            return null;
        }
        //Keep a running max
        int localMax = -1;
        //Keep track of the indeces that gave the best sum so far
        int bestLow = -1, bestHigh = -1;
        while (first < last) {
            int sum = (list.get(first).price + list.get(last).price);
            if (sum < target) {
                if (sum > localMax) {
                    bestLow = first;
                    bestHigh = last;
                    localMax = sum;
                }
                first++;
            } else if (sum > target) {
                last--;
            } else {
                localMax = sum;
                bestLow = first;
                bestHigh = last;
                break;
            }
        }
        return new Pair(bestLow, bestHigh);
    }

    /**
     * Bonus question solved
     * Prints out the sum of items that is <= target
     */
    public void findThreePrices(Integer target) {
        int localMax = -1;
        int first = -1;
        int second = -1;
        int third = -1;
        if (list.size() < 3) {
            System.out.println("Trio not possible");
        }
        if ((list.get(0).price + list.get(1).price + list.get(2).price> target)) {
            System.out.println("Trio not possible");
        }
        for (int k = 0; k < list.size() - 2; k++) {
            Pair pair = findTwoPrices((target - list.get(k).price), k + 1, list.size() - 1);
            if (pair != null) {
                final int sum = list.get(pair.first).price + list.get(pair.last).price + list.get(k).price;
                if ((sum > localMax) && (sum <= target)) {
                    localMax = sum;
                    first = k;
                    second = pair.first;
                    third = pair.last;
                }
            }
        }
        System.out.print(list.get(first));
        System.out.print(", ");
        System.out.print(list.get(second));
        System.out.print(", ");
        System.out.println(list.get(third));
    }

    public static void main(String[] args) {
        try {
            File f = new File(args[0]);
            Integer target = Integer.parseInt(args[1]);
            Boolean isTwoPrices = Boolean.valueOf(args[2]);
            BufferedReader b = new BufferedReader(new FileReader(f));
            String readLine = "";
            SecondChallenge challenge = new SecondChallenge();
            while ((readLine = b.readLine()) != null) {
               challenge.addToList(readLine);
            }
            if(isTwoPrices) {
                Pair pair = challenge.findTwoPrices(target, 0, challenge.list.size() - 1);
                System.out.print(challenge.list.get(pair.first));
                System.out.print(", ");
                System.out.print(challenge.list.get(pair.last));
            } else {
                challenge.findThreePrices(target);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
