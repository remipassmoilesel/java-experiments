package org.remipassmoilesel.lightplayground.losttime;

import lombok.val;

import java.util.*;
import java.io.*;
import java.math.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * The while loop represents the game.
 * Each iteration represents a turn of the game
 * where you are given inputs (the heights of the mountains)
 * and where you have to print an output (the index of the mountain to fire on)
 * The inputs you are given are automatically updated according to your last actions.
 **/
class CodingGameDestroyMountains {

    public static class Mountain {
        final Integer id;
        final Integer height;

        Mountain(Integer id, Integer height) {
            this.id = id;
            this.height = height;
        }

        @Override
        public String toString() {
            return "Mountain{" +
                    "height=" + id +
                    ", height=" + height +
                    '}';
        }
    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        // game loop
        while (true) {

            List<Mountain> sortedMountain = IntStream.range(0, 8)
                    .boxed()
                    .map(id -> {
                        val height = in.nextInt();
                        return new Mountain(id, height);
                    })
                    .sorted((m1, m2) -> {
                        if (m1.height.equals(m2.height)) {
                            return 0;
                        } else {
                            return m1.height > m2.height ? -1 : +1;
                        }
                    })
                    .collect(Collectors.toList());

            Integer firstId = sortedMountain.get(0).id;
            System.out.println(firstId);
        }
    }
}
