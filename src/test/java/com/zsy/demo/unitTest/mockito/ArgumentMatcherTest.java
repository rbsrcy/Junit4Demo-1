package com.zsy.demo.unitTest.mockito;

import com.zsy.demo.unitTest.sample.Foo;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Created by zhuoshangyi on 2016/11/13.
 */
public class ArgumentMatcherTest {
    @Mock
    private List<String> mockedList;

    @Mock
    private Foo mock;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * 测试整数匹配器
     * @throws Exception
     */
    @Test
    public void testArgumentMatcher() throws Exception {
        when(mockedList.get(anyInt())).thenReturn("element");
        System.out.println(mockedList.get(999));
        verify(mockedList).get(anyInt());
    }

    /**
     * 测试空匹配器
     * @throws Exception
     */
    @Test
    public void testNullArgumentMatcher() throws Exception {
        // stubbing using anyBoolean() argument matcher
        when(mock.dryRun(anyBoolean())).thenReturn("state");

        // below the stub won't match, and won't return "state"
        mock.dryRun(null);

        // either change the stub
        when(mock.dryRun(isNull())).thenReturn("state");
        mock.dryRun(null); // ok

        // or fix the code ;)
        when(mock.dryRun(anyBoolean())).thenReturn("state");
        mock.dryRun(true); // ok

    }

    /**
     * 测试自定义 参数匹配器
     */
    @Test
    public void testCustomIntegerArgumentMatcher() {
        List list = mock(List.class);
        //匹配任意整数类型的参数
        when(list.get(anyInt())).thenReturn(1);
        //匹配符合自定义匹配器规则的参数
        when(list.contains(argThat(new IsValid()))).thenReturn(true);
        assertEquals(1, list.get(1));
        assertEquals(1, list.get(999));
        assertTrue(list.contains(1));
        assertTrue(!list.contains(3));
    }

    /**
     * 参数匹配器
     */
    private class IsValid implements ArgumentMatcher<Integer> {
        @Override
        public boolean matches(Integer argument) {
            return argument == 1 || argument == 2;
        }
    }

    /**
     * 测试自定义参数匹配器，并在不匹配时输出自定义内容
     * @throws Exception
     */
    @Test()
    public void testCustomListArgumentMatcher() throws Exception {
        when(mockedList.addAll(argThat(new ListOfTwoElements()))).thenReturn(true);


        mockedList.addAll(Arrays.asList("one", "two"));

        verify(mockedList).addAll(argThat(new ListOfTwoElements()));
//        java 8 支持如下语法
//        verify(mockedList).addAll(argThat(list -> list.size() == 2));

    }

    class ListOfTwoElements implements ArgumentMatcher<List> {
        private List list;

        public boolean matches(List list) {
            this.list=list;
            return list.size() == 2;
        }
        public String toString() {
            //printed in verification errors
            return list.toString();
        }
    }
}
