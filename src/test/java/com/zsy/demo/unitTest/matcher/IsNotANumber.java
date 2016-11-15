package com.zsy.demo.unitTest.matcher;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * Created by zhuoshangyi on 2016/11/12.
 */
public class IsNotANumber extends TypeSafeMatcher<Double> {


    public void describeTo(Description description) { description.appendText("not a number"); }

    @Factory
    public static Matcher notANumber() { return new IsNotANumber(); }


    @Override
    protected boolean matchesSafely(Double number) {
        return Double.isNaN((Double) number);
    }
}
