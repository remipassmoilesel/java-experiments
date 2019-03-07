/*
 * Copyright (c) 2014, Oracle America, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.sample;

import io.vavr.collection.List;
import io.vavr.collection.Stream;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Warmup;

import java.util.ArrayList;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class VavrBenchmark {

    public static final int ITEMS = 5000;

    @Benchmark
    @Warmup(iterations = 20, time = 1000, timeUnit = MILLISECONDS)
    @Measurement(iterations = 20, time = 1000, timeUnit = MILLISECONDS)
    public void arrays() {

        int[] items = new int[ITEMS];
        for (int i = 0; i < ITEMS; i++) {
            items[i] = i;
        }

        for (int i = 0; i < ITEMS; i++) {
            int[] copy = items.clone();
        }

    }

    @Benchmark
    @Warmup(iterations = 20, time = 1000, timeUnit = MILLISECONDS)
    @Measurement(iterations = 20, time = 1000, timeUnit = MILLISECONDS)
    public void lists() {

        ArrayList<Integer> items = new ArrayList<>();
        for (int i = 0; i < ITEMS; i++) {
            items.add(i);
        }

        for (int i = 0; i < ITEMS; i++) {
            ArrayList copy = (ArrayList) items.clone();
        }

    }

    @Benchmark
    @Warmup(iterations = 20, time = 1000, timeUnit = MILLISECONDS)
    @Measurement(iterations = 20, time = 1000, timeUnit = MILLISECONDS)
    public void vavrLists() {

        List<Integer> list = Stream.from(0).take(ITEMS).map(i -> i).toList();

        for (int i = 0; i < ITEMS; i++) {
            List<Integer> copy = list.toList();
        }

    }

    @Benchmark
    @Warmup(iterations = 20, time = 1000, timeUnit = MILLISECONDS)
    @Measurement(iterations = 20, time = 1000, timeUnit = MILLISECONDS)
    public void vavrListToJavaList() {

        List<Integer> list = Stream.from(0).take(ITEMS).map(i -> i).toList();

        for (int i = 0; i < ITEMS; i++) {
            java.util.List<Integer> copy = list.toJavaList();
        }

    }

}
