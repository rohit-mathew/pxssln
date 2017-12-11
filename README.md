# Second Challenge

##Setup IDE (Idea)
```
./gradlew idea
open pxssln.ipr
```

## Big O Notation

n --> Length of the array

Solution's time complexity is O(n) as we go over every element in the array only once

## Big O notation (Bonus Question)
Solved in com.paxos.challenge.second.SecondChallenge
Solution's time complexity is O(n^2) as we go over the whole array for every element in the array

## How to build and run
```
//build
./gradlew fatJarSecond
//For a pair of prices
java -jar build/libs/challenge2-1.0.jar ~/Desktop/price.txt 3000 true
//For a trio of prices
//Ignore the "Duo not possible" messages
java -jar build/libs/challenge2-1.0.jar ~/Desktop/price.txt 10000 false

```

# Third Challenge

## Big O Notation

n --> Number of x's in the input string
m --> Length of the string

There are 2^n total possible outcome and printing costs O(m)

Then total time complexity of this solution is O(m*2^n)

There is no way to do this with lower time complexity, considering that we have to print every possible solution.

## How to build and run

```
./gradlew fatJarThird
java -jar build/libs/challenge3-1.0.jar XXX
```
