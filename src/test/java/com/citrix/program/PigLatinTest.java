package com.citrix.program;
import org.junit.Test;
import org.junit.Assert;

/*
    This is the Unit test class to , test functionality of PigLatin
 */
public class PigLatinTest {

    @Test
    public void testIsPunctuation(){
        Assert.assertTrue(PigLatin.isPunctuation('\''));
    }

    @Test
    public void testApplyCapitalization(){
        Assert.assertEquals("Two",PigLatin.applyCapitalization("One","two"));
    }

    @Test
    public void testApplyVowelCheck(){
        Assert.assertEquals("appleway",PigLatin.applyVowelCheck("apple"));
        Assert.assertEquals("Ellohay",PigLatin.applyVowelCheck("Hello"));
        Assert.assertEquals("stairway",PigLatin.applyVowelCheck("stairway"));
    }

    @Test
    public void testHandleHyphens(){
        Assert.assertEquals("histay-hingtay",PigLatin.handleHyphens("this-thing"));
    }

    @Test
    public void testHandlePunctuations(){
        Assert.assertEquals("antca’y",PigLatin.handlePunctuations("can’t","ant’cay"));
        Assert.assertEquals("endway.",PigLatin.handlePunctuations("end.","end.way"));
        Assert.assertEquals("hello",PigLatin.handlePunctuations("hello","hello"));
    }

    @Test
    public void testTransformInputString(){
        Assert.assertEquals("histay-hingtay",PigLatin.transformInputString("this-thing"));
    }

    @Test
    public void testPigLatinMain(){
        //PigLatin.main(new String[] {"arg1"});
    }

}

