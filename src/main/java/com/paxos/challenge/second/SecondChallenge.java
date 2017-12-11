package com.paxos.challenge.second;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Created by rohit_mathew on 12/10/17.
 */
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

        public void print(){
            System.out.print(this.name + " " + this.price);
        }
    }

    public void addToList(final String entry) {
        this.list.add(new Item(entry));
    }

    /*
     * This solution will work only if there are no two items with the same price
     */
    public int findTwoPrices(Integer target, Integer first, Integer last) {
        if ((list.get(first).price + list.get(first + 1).price > target)) {
            System.out.println("Not possible");
            return -1;
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
        list.get(bestLow).print();
        System.out.print(", ");
        list.get(bestHigh).print();
        return localMax;
    }

    public void findThreePrices(Integer target) {
        int localMax = -1;
        for (int k = 0; k < list.size() - 2; k++) {
            localMax = Math.max(localMax, list.get(k).price + findTwoPrices((target - list.get(k).price), k + 1, list.size() - 1));
        }
        System.out.println("Maximum of 3 numbers " + localMax);
    }

    public static void main(String[] args) {
        try {
            File f = new File(args[0]);
            Integer target = Integer.parseInt(args[1]);
            BufferedReader b = new BufferedReader(new FileReader(f));
            String readLine = "";
            SecondChallenge challenge = new SecondChallenge();
            while ((readLine = b.readLine()) != null) {
               challenge.addToList(readLine);
            }
            challenge.findTwoPrices(target, 0, challenge.list.size() - 1);
//            challenge.findThreePrices(10000); Bonus
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
