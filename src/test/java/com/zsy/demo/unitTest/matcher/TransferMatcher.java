package com.zsy.demo.unitTest.matcher;

import com.zsy.demo.unitTest.sample.Account;
import com.zsy.demo.unitTest.sample.Transfer;
import com.mistraltech.smog.core.CompositePropertyMatcher;
import com.mistraltech.smog.core.MatchAccumulator;
import com.mistraltech.smog.core.PropertyMatcher;
import com.mistraltech.smog.core.ReflectingPropertyMatcher;
import com.mistraltech.smog.core.annotation.Matches;
import org.hamcrest.Matcher;

import static org.hamcrest.CoreMatchers.equalTo;

@Matches(Transfer.class)
public final class TransferMatcher extends CompositePropertyMatcher<Transfer> {
    private static final String MATCHED_OBJECT_DESCRIPTION = "an Transfer";
    private final PropertyMatcher<Account> toAccountMatcher = new ReflectingPropertyMatcher<Account>("toAccount", this);
    private final PropertyMatcher<Integer> amountMatcher = new ReflectingPropertyMatcher<Integer>("amount", this);
    private final PropertyMatcher<Account> fromAccountMatcher = new ReflectingPropertyMatcher<Account>("fromAccount", this);

    private TransferMatcher(final String matchedObjectDescription, final Transfer template) {
        super(matchedObjectDescription);
        if (template != null) {
            hasToAccount(template.getToAccount());
            hasAmount(template.getAmount());
            hasFromAccount(template.getFromAccount());
        }
    }

    public static TransferMatcher aTransferThat() {
        return new TransferMatcher(MATCHED_OBJECT_DESCRIPTION, null);
    }

    public static TransferMatcher aTransferLike(final Transfer template) {
        return new TransferMatcher(MATCHED_OBJECT_DESCRIPTION, template);
    }

    public TransferMatcher hasToAccount(final Account toAccount) {
        return hasToAccount(equalTo(toAccount));
    }

    public TransferMatcher hasToAccount(final Matcher<? super Account> toAccountMatcher) {
        this.toAccountMatcher.setMatcher(toAccountMatcher);
        return this;
    }

    public TransferMatcher hasAmount(final int amount) {
        return hasAmount(equalTo(amount));
    }

    public TransferMatcher hasAmount(final Matcher<? super Integer> amountMatcher) {
        this.amountMatcher.setMatcher(amountMatcher);
        return this;
    }

    public TransferMatcher hasFromAccount(final Account fromAccount) {
        return hasFromAccount(equalTo(fromAccount));
    }

    public TransferMatcher hasFromAccount(final Matcher<? super Account> fromAccountMatcher) {
        this.fromAccountMatcher.setMatcher(fromAccountMatcher);
        return this;
    }

    @Override
    protected void matchesSafely(final Transfer item, final MatchAccumulator matchAccumulator) {
        super.matchesSafely(item, matchAccumulator);
    }
}
