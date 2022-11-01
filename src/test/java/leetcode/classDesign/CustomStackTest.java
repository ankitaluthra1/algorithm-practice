package leetcode.classDesign;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomStackTest {

    @Test
    void shouldIncrementLast3ValuesBy2() {

        CustomStack customStack = new CustomStack(3);
        customStack.push(1);
        customStack.push(2);
    //popIndex = 1
        assertEquals(2,customStack.pop());
//popIndex = 3
        customStack.push(2);
        customStack.push(3);
        customStack.push(4 );

        customStack.increment(5, 100);
        customStack.increment(2, 100);

        assertEquals(103,customStack.pop());
        assertEquals(202,customStack.pop());
        assertEquals(201,customStack.pop());
        assertEquals(-1,customStack.pop());

    }
}