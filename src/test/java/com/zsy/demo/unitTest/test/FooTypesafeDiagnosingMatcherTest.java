package com.zsy.demo.unitTest.test;

import com.zsy.demo.unitTest.sample.Foo;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.junit.Test;

import static org.junit.Assert.assertThat;

/**
 * Created by zhuoshangyi on 2016/11/12.
 */
public class FooTypesafeDiagnosingMatcherTest {

    @Test
    public void numberIs42() {
        final Foo testee = new Foo();
        assertThat(testee, hasNumber(42));
    }

    private Matcher<Foo> hasNumber(final int i) {
        return new TypeSafeDiagnosingMatcher<Foo>() {
            @Override
            public void describeTo(final Description description) {
                description.appendText("getNumber should return ").appendValue(i);
            }

            @Override
            protected boolean matchesSafely(final Foo item, final
            Description mismatchDescription) {
                System.out.println("matchesSafely been called.");
                mismatchDescription.appendText(" was ").appendValue(item.getNumber());
                return i == item.getNumber();
            }
        };
    }

}
