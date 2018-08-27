package org.remipassmoilesel;

import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.*;

/**
 * Created by remipassmoilesel on 22/02/17.
 * <p>
 * It is also possible to catch arguments of method with ArgumentCaptor (@Captor)
 * <p>
 * Source: http://www.vogella.com/tutorials/Mockito/article.html
 * Forms: https://www.petrikainulainen.net/programming/spring-framework/integration-testing-of-spring-mvc-applications-forms/
 */

public class MockitoExperiments {


    public static class MyClass {

        private int uniqueId = 55;
        private int testInt;

        public MyClass(int uniqueId) {
            this.uniqueId = uniqueId;
        }

        public int getUniqueId() {
            return uniqueId;
        }

        public void testInt(int i) {
            testInt = i;
        }

        public void testString(String s) {

        }
    }

    @Test
    public void test() {

        // create a mock from class
        MyClass test = Mockito.mock(MyClass.class);

        // define return value for method getUniqueId()
        // multiple values can be defined
        when(test.getUniqueId()).thenReturn(43).thenReturn(53);

        // use mock in test
        assertEquals(test.getUniqueId(), 43);
        assertEquals(test.getUniqueId(), 53);
    }

    // this test demonstrates how to return values based on the input
    @Test
    public void testReturnValueDependentOnMethodParameter() {
        Comparable c = mock(Comparable.class);
        when(c.compareTo("Mockito")).thenReturn(1);
        when(c.compareTo("Eclipse")).thenReturn(2);
        //assert
        assertEquals(1, c.compareTo("Mockito"));
    }

    // this test demonstrates how to return values independent of the input value

    @Test
    public void testReturnValueInDependentOnMethodParameter() {
        Comparable c = mock(Comparable.class);
        when(c.compareTo(anyInt())).thenReturn(-1);
        //assert
        assertEquals(-1, c.compareTo(9));
    }


    // return a value based on the type of the provide parameter
    @Test
    public void testReturnValueInDependentOnMethodParameter2() {
        Comparable c = mock(Comparable.class);
        when(c.compareTo(isA(MyClass.class))).thenReturn(0);
        //assert
        MyClass todo = new MyClass(25);
        assertEquals(todo, c.compareTo(new MyClass(1)));
    }

    // this test demonstrates how use doThrow

    @Test(expected = IOException.class)
    public void testForIOException() throws IOException {
        // create an configure mock
        OutputStream mockStream = mock(OutputStream.class);
        doThrow(new IOException()).when(mockStream).close();

        // use mock
        OutputStreamWriter streamWriter = new OutputStreamWriter(mockStream);
        streamWriter.close();
    }

    @Test
    public void testVerify() {

        // create and configure mock
        MyClass test = Mockito.mock(MyClass.class);
        when(test.getUniqueId()).thenReturn(43);

        // call method testing on the mock with parameter 12
        test.testInt(12);
        test.getUniqueId();
        test.getUniqueId();


        // now check if method testing was called with the parameter 12
        verify(test).testInt(Matchers.eq(12));

        // was the method called twice?
        verify(test, times(2)).getUniqueId();

        // other alternatives for verifiying the number of method calls for a method
        verify(test, never()).testString("never called");
        verify(test, atLeastOnce()).testString("called at least once");
        verify(test, atLeast(2)).testString("called at least twice");
        verify(test, times(5)).testString("called five times");
        verify(test, atMost(3)).testString("called at most 3 times");
    }

    /**
     * Spy objects can be added to real objects to monitor method calls
     */
    public void addSpy() {
        // Lets mock a LinkedList
        List list = new LinkedList();
        List spy = spy(list);

        //You have to use doReturn() for stubbing
        doReturn("foo").when(spy).get(0);

        // this would not work
        // real method is called so spy.get(0)
        // throws IndexOutOfBoundsException (list is still empty)
        when(spy.get(0)).thenReturn("foo");
    }


}
