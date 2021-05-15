package com.sabri.account.service;

import com.sabri.account.entity.UserAccount;

public interface UserAccountCommandService {

	void save(UserAccount userAccount);
	void update(UserAccount userAccount);
	void delete(Long id);
}
