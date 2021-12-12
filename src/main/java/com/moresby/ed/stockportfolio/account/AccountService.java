package com.moresby.ed.stockportfolio.account;

import com.moresby.ed.stockportfolio.exception.InsufficientAmount;
import com.moresby.ed.stockportfolio.user.User;

public interface AccountService {
    Account withdrawal(User user, Long amount) throws IllegalArgumentException, InsufficientAmount;
    Account deposit(User user, Long amount);
    Account findExistingAccountByUserId(Long userId);
}
