package com.zsy.demo.unitTest.matcher;

import com.zsy.demo.unitTest.sample.Account;
import com.mistraltech.smog.core.CompositePropertyMatcher;
import com.mistraltech.smog.core.MatchAccumulator;
import com.mistraltech.smog.core.PropertyMatcher;
import com.mistraltech.smog.core.ReflectingPropertyMatcher;
import com.mistraltech.smog.core.annotation.Matches;
import org.hamcrest.Matcher;

import static org.hamcrest.CoreMatchers.equalTo;

@Matches(Account.class)
public final class AccountMatcher extends CompositePropertyMatcher<Account> {
    private static final String MATCHED_OBJECT_DESCRIPTION = "an Account";
    private final PropertyMatcher<String> ownerMatcher = new ReflectingPropertyMatcher<String>("owner", this);
    private final PropertyMatcher<Integer> balanceMatcher = new ReflectingPropertyMatcher<Integer>("balance", this);
    private final PropertyMatcher<Boolean> overdrawnMatcher = new ReflectingPropertyMatcher<Boolean>("overdrawn", this);

    private AccountMatcher(final String matchedObjectDescription, final Account template) {
        super(matchedObjectDescription);
        if (template != null) {
            hasOwner(template.getOwner());
            hasBalance(template.getBalance());
            hasOverdrawn(template.isOverdrawn());
        }
    }

    public static AccountMatcher anAccountThat() {
        return new AccountMatcher(MATCHED_OBJECT_DESCRIPTION, null);
    }

    public static AccountMatcher anAccountLike(final Account template) {
        return new AccountMatcher(MATCHED_OBJECT_DESCRIPTION, template);
    }

    public AccountMatcher hasOwner(final String owner) {
        return hasOwner(equalTo(owner));
    }

    public AccountMatcher hasOwner(final Matcher<? super String> ownerMatcher) {
        this.ownerMatcher.setMatcher(ownerMatcher);
        return this;
    }

    public AccountMatcher hasBalance(final int balance) {
        return hasBalance(equalTo(balance));
    }

    public AccountMatcher hasBalance(final Matcher<? super Integer> balanceMatcher) {
        this.balanceMatcher.setMatcher(balanceMatcher);
        return this;
    }

    public AccountMatcher hasOverdrawn(final boolean overdrawn) {
        return hasOverdrawn(equalTo(overdrawn));
    }

    public AccountMatcher hasOverdrawn(final Matcher<? super Boolean> overdrawnMatcher) {
        this.overdrawnMatcher.setMatcher(overdrawnMatcher);
        return this;
    }

    @Override
    protected void matchesSafely(final Account item, final MatchAccumulator matchAccumulator) {
        super.matchesSafely(item, matchAccumulator);
    }
}
