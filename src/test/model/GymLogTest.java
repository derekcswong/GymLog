package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


public class GymLogTest {
    private GymLog gymLog1;
    private final static LocalDate d0 = LocalDate.parse("1999-12-11");
    private final static LocalDate d1 = LocalDate.parse("2000-10-17");
    private final static LocalDate d2 = LocalDate.parse("2019-10-10");
    private final static LocalDate d3 = LocalDate.parse("2020-10-11");


    @BeforeEach
    public void runBefore(){
        gymLog1 = new GymLog();
    }

    @Test
    public void testSize() {
        assertEquals(gymLog1.size(), 0);

        gymLog1.addLogEntry(d0, "cat");
        gymLog1.addLogEntry(d1, "cat");
        gymLog1.addLogEntry(d2, "cat");
        gymLog1.addLogEntry(d3, "cat");

        assertEquals(gymLog1.size(), 4);
    }


}
