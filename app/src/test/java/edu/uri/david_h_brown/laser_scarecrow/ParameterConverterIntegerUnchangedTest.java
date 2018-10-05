package edu.uri.david_h_brown.laser_scarecrow;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParameterConverterIntegerUnchangedTest {
    private ParameterConverterIntegerUnchanged me;
    @Before
    public void init(){
        me=new ParameterConverterIntegerUnchanged();
    }

    @Test
    public void testZero(){
        assertEquals("0",me.convert("0"));
    }
    @Test
    public void test10bit(){
        assertEquals("1023",me.convert("1023"));
    }
    @Test (expected = NumberFormatException.class)
    public void invalid(){
        me.convert("this is not a number!");
    }
}