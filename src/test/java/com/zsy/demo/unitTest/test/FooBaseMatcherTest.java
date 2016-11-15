package com.zsy.demo.unitTest.test;

import com.zsy.demo.unitTest.sample.Foo;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Test;

import static org.junit.Assert.assertThat;

/**
 * Created by zhuoshangyi on 2016/11/12.
 */
public class FooBaseMatcherTest {

    @Test
    public void numberIs42() {
        final Foo testee = new Foo();
        assertThat(testee, hasNumber(42));
    }

    private Matcher<Foo> hasNumber(final int i) {
        return new BaseMatcher<Foo>() {
            @Override
            public boolean matches(final Object item) {
                final Foo foo = (Foo) item;
                return i == foo.getNumber();
            }
            @Override
            public void describeTo(final Description description) {
                description.appendText("getNumber should return ").appendValue(i);
            }
            @Override
            public void describeMismatch(final Object item, final Description description) {
                description.appendText("was").appendValue(((Foo) item).getNumber());
            }
        };
    }

}
