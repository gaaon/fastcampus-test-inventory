package com.grizz;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SimpleTest {
    @Test
    void onePlusTwoShouldReturnThree() {
        // when
        int result = 1 + 2;

        // then
        assertEquals(result, 3);
    }
}