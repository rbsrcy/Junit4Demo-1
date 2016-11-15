package com.zsy.demo.unitTest.test;

import com.zsy.demo.unitTest.sample.Foo;
import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by zhuoshangyi on 2016/11/12.
 */
public class FooFeatureMatcherTest {

    @Test
    public void numberIs42() {
        final Foo testee = new Foo();
        assertThat(testee, hasNumberFeatureMatcher(42));
    }




    private Matcher<Foo> hasNumberFeatureMatcher(final Integer i) {
        return new FeatureMatcher<Foo, Integer>(equalTo(i), "number", "number") {
            @Override
            protected Integer featureValueOf(final Foo actual) {
                return actual.getNumber();
            }
        };
    }
}
