package com.grizz;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CalculatorTest {
    @Mock
    private Calculator calculator;

    @BeforeEach
    void setUp() {
        given(calculator.add(anyInt(), anyInt())).willAnswer(invocation -> {
            int a = invocation.getArgument(0);
            int b = invocation.getArgument(1);
            return a + b;
        });
    }

    @Test
    void onePlusTwoShouldReturnThree() {
        // when
        int a = 1;
        int b = 2;
        int result = calculator.add(a, b);

        // then
        assertEquals(result, 3);

        verify(calculator, only()).add(a, b);
    }
}