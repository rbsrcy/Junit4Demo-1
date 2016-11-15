package com.zsy.demo.unitTest.mockito;

import org.junit.Test;

import static org.mockito.Mockito.*;

/**
 * Created by zhuoshangyi on 2016/11/13.
 */
public class CallRealMethodTest {

    /**
     * 使用 doCallRealMethod 方法可以精确控制那些方法，没有使用的方法会进行stub 调用
     */
    @Test
    public void callRealMethodTest() {
        Jerry jerry = mock(Jerry.class);
        doCallRealMethod().when(jerry).goHome();
        doCallRealMethod().when(jerry).doSomeThingB();
        jerry.goHome();
        verify(jerry).doSomeThingA();
        verify(jerry).doSomeThingB();
    }

    class Jerry {
        public void goHome() {
            doSomeThingA();
            doSomeThingB();
        }

        // real invoke it.
        public void doSomeThingB() {
            System.out.println("good day");
        }

        // auto mock method by mockito
        public void doSomeThingA() {
            System.out.println("you should not see this message.");
        }
    }
}
