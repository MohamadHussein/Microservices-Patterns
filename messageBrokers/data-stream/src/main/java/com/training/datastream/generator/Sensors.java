package com.aspire.datastream.generator;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

enum Sensor {
     HEAT,
     VIBRATION;

    private static final List<Sensor> VALUES =
            Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static Sensor getRand()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
