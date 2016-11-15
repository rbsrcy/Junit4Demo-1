package com.zsy.demo.unitTest.test;

import com.zsy.demo.unitTest.sample.Foo;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Test;

import static org.junit.Assert.assertThat;

/**
 * Created by zhuoshangyi on 2016/11/12.
 */
public class FooTypeSafteMatcherTest {

    @Test
    public void numberIs42() {
        final Foo testee = new Foo();
        assertThat(testee, hasNumber(42));
    }




    private Matcher<Foo> hasNumber(final int i) {
        return new TypeSafeMatcher<Foo>() {
            @Override
            public void describeTo(final Description description) {
                description.appendText("getNumber should return ").appendValue(i);
            }

            @Override
            protected void describeMismatchSafely(final Foo item, final
            Description mismatchDescription) {
                mismatchDescription.appendText(" was ").appendValue(item.getNumber());
            }

            @Override
            protected boolean matchesSafely(final Foo item) {
                return i == item.getNumber();
            }
        };
    }

}
