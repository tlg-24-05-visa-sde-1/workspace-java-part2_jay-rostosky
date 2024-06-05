package com.entertainment.client;

import com.entertainment.Television;
import java.util.HashSet;
import java.util.Set;

class TelevisionClient {

    public static void main(String[] args) {

        // examine == and the equals() method
        Television tvA = new Television("Sony", 50);
        Television tvB = new Television("Sony", 50);

        System.out.println(tvA.hashCode());
        System.out.println(tvB.hashCode());

        System.out.println("tvA == tvB: "      + (tvA == tvB));     // false (of course)
        System.out.println("tvA.equals(tvB): " + tvA.equals(tvB));  // true now
        System.out.println();

        Set<Television> tvs = new HashSet<>();
        tvs.add(tvA);
        tvs.add(tvB);
        System.out.println("The size of the Set is: " + tvs.size());  // should be 1
    }
}