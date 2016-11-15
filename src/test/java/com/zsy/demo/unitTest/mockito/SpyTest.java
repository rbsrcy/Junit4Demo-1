package com.zsy.demo.unitTest.mockito;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

/**
 * Created by zhuoshangyi on 2016/11/13.
 * spy 测试用例
 */
public class SpyTest {

    /***
     * 对spy 打桩，调用实际方法，返回桩设定的值
     * @throws Exception
     */
    @Test
    public void testSpyWithStub() throws Exception {
        List<String> list = new LinkedList();
        List<String> spy = spy(list);
        when(spy.size()).thenReturn(100);
        spy.add("one");
        spy.add("two");
        assertThat(spy.get(0), is("one"));
        assertThat(spy.size(), is(100));
    }

    /**
     * 没有对spy 打桩，调用实际方法，返回实际的值
     * @throws Exception
     */
    @Test
    public void testSpyWithoutStub() throws Exception {
        List<String> list = new LinkedList();
        List<String> spy = spy(list);
        spy.add("one");
        spy.add("two");
        assertThat(spy.get(0), is("one"));
        assertThat(spy.size(), is(2));
    }

    /**
     * spy 模拟仍然会调用实际方法，只是返回stub 的值
     */
    @Test
    public void testSpyWhenStubIfCallRealMethod() {
        Jack spyJack = spy(new Jack());
        when(spyJack.go()).thenReturn(false);
        assertFalse(spyJack.go());
    }

    class Jack {
        public boolean go() {
            System.out.println("I say go go go!!");
            return true;
        }
    }

    /**
     * 测试当使用doReturn 进行打桩时，不会调用真实的方法，但是会返回桩设定的返回值
     * @throws Exception
     */
    @Test
    public void testUsingDoReturnStubNotCallRealMethod() throws Exception {
        Jack spyJack = spy(new Jack());
        doReturn(false).when(spyJack).go();
        assertFalse(spyJack.go());

    }
}
