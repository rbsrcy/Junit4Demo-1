package com.zsy.demo.unitTest.test;

import org.junit.Test;

import static com.zsy.demo.unitTest.matcher.IsNotANumber.notANumber;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by zhuoshangyi on 2016/11/12.
 */
public class HamcrestMatcherTest {
    @Test
    public void testNotANumber() {
        assertThat(1.0, is(notANumber()));
    }

    @Test
    public void testSquareRootOfMinusOneIsNotANumber() {
        assertThat(Math.sqrt(-1), is(notANumber()));
    }

}
