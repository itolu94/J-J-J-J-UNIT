package com.programming.juni5;


import org.junit.jupiter.api.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class JUnitTest {
    // @BeforeAll must be static and is ran before all other methods are executed.
    // @AfterAll is the same as @BeforeAll but is ran after all test have finished executing.
    @BeforeAll
    static void beforeAll() {
        System.out.println("beforeAll() will only run once");
    }

    //@BeforeEach runs before each test.
    //@AfterEach runs after each test .
    @BeforeEach
    void beforeEach(TestInfo info) {
        //TestInfo class will contain test unit being ran.
        System.out.println("beforeEach() is ran before " + info.getDisplayName());

    }

    //@Test is used to create a new test.
    @Test
    void test() {
        int length = "ABCD".length();
         assertEquals(4, length);
        /*
            Code under test (CUT) is everything prior to assertions.
                "Absence of failure = success"
                without checking (using assertions) for success, test will pass
         */
    }

    //@Nested groups a class of test together.
    // Nested classes can have their own @BeforeEach, @BeforeAll, @AfterEach, and @AfterAll
    @Nested
    class BasicTest {

        @Test
        void minTest(){
            int a = Math.min(322, 3453);
            int b = 322;
            assertEquals(b, a);
        }

        @Test
        void maxTest() {
            int c = Math.max(322, 3453);
            assertEquals(3453, c);
        }

        @Test
        void toUpperCase(){
            String str = "monday";
            String result = str.toUpperCase();
            assertEquals("MONDAY", result);
        }
    }


    @Test
    void nullTest() {
        String d = null;
        assertNull(d);
        d = "Hello World";
        assertNotNull(d);
    }

    //@DisplayName allows you specify how the test will be labeled.
    @Test
    @DisplayName("String contains ays, and should fail, and mon, and should pass")
    void contains_basics(){
        String str = "monday";
        Boolean results = str.contains("ays");
        assertFalse(results);
        results =  str.contains("mon");
        assertTrue(results);
    }


    @Test
    void split_basic() {
        String str = "abc def ghi";
        String results [] = str.split(" ");
        String  expectedResults[] = new String []{"abc", "def", "ghi"};
        assertArrayEquals(expectedResults, results);
    }


    @Test
    void exception  (){
        // When code is being tested to see if throws an Exception,
        // it has to be written within a lambda function.
        assertThrows(ArithmeticException.class,
                 () -> {
                    int a = 1/0;
                });
    }


    //@RepeatedTest allows for a test to be ran multiple times
    @Test
    @RepeatedTest(2)
    void repeat() {
        System.out.println("repeat test");
        assertTrue(Math.random() > 0.1);
    }

    @Test
    void performanceTest(){
        //assertTimeout let us test performance/how fast a test runs/maximum amount of time test should take.
        assertTimeout(Duration.ofMillis(10),
                () ->{
                    int i;
                    for(i = 1; i <= 1000; i = i * 10)
                        System.out.println("Condition not met yet");
                    assertTrue(i >= 1000);
                });
    }


    //@Disabled is used to disable a test from running
    @Test
    @Disabled
    void disabled(){
        System.out.println("Test is never ran");
        assertTrue(true);
    }
//    @ParameterizedTest
//    @ValueSource(strings = {"abc", "cd", "def"});
//    void paramtest(String value) {
//        assertTrue(value.length()>0);
    }



