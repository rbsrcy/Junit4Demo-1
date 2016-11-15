package com.zsy.demo.unitTest.test;

import com.zsy.demo.unitTest.sample.Foo;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Test;

import static org.hamcrest.core.CombinableMatcher.both;
import static org.junit.Assert.assertThat;

/**
 * Created by zhuoshangyi on 2016/11/12.
 */
public class FooCombineMatcherTest {

    @Test
    public void numberIs42() {
        final Foo testee = new Foo();
        assertThat(testee, both(hasName("Foo")).and(hasNumber(42)));

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

    private Matcher<Foo> hasName(final String name) {
        return new TypeSafeMatcher<Foo>() {
            @Override
            public void describeTo(final Description description) {
                description.appendText("getName should return ").appendValue(name);
            }

            @Override
            protected void describeMismatchSafely(final Foo item, final
            Description mismatchDescription) {
                mismatchDescription.appendText(" was ").appendValue(item.getName());
            }

            @Override
            protected boolean matchesSafely(final Foo item) {
                return (name==null&&item.getName()==null)||(name.equals(item.getName()));
            }
        };
    }
}
