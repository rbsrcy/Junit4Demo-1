package com.zsy.demo.unitTest.test;

import com.zsy.demo.unitTest.sample.Account;
import com.zsy.demo.unitTest.sample.Transfer;
import org.junit.Test;

import static com.zsy.demo.unitTest.matcher.AccountMatcher.anAccountThat;
import static com.zsy.demo.unitTest.matcher.TransferMatcher.aTransferThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by zhuoshangyi on 2016/11/12.
 */
public class AccountSMOGMatcherTest {
    @Test
    public void canConstructAccount()
    {
        Account account = new Account("bob", 100);

        assertThat(account, is(anAccountThat().hasOwner("bob1").hasBalance(101).hasOverdrawn(false)));
    }

    @Test
    public void canGoOverdrawn()
    {
        Account account = new Account("bob", 100);

        account.withdraw(150);

        assertThat(account, is(anAccountThat().hasBalance(-50).hasOverdrawn(true)));
    }

    @Test
    public void ensureFundsAreTransferred()
    {
        Account fredsAccount = new Account("fred", 100);
        Account tracysAccount = new Account("tracy", 100);

        Transfer transfer = new Transfer(fredsAccount, tracysAccount, 50);

        assertThat(transfer, is(aTransferThat()
                .hasFromAccount(anAccountThat()
                        .hasOwner("fred")
                        .hasBalance(50))
                .hasToAccount(anAccountThat()
                        .hasOwner("tracy")
                        .hasBalance(150))
        ));
    }
}
