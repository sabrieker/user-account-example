package com.sabri.account.service.impl;

import org.springframework.stereotype.Service;

import com.sabri.account.entity.UserAccount;
import com.sabri.account.service.UserAccountCommandService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserAccountCommandServiceImpl implements UserAccountCommandService {
	private final UserAccountRepository userAccountRepository;
	
	@Override
	public void save(UserAccount userAccount) {
		userAccountRepository.save(userAccount);
	}

	@Override
	public void update(UserAccount userAccount) {
		userAccountRepository.save(userAccount);
	}

	@Override
	public void delete(Long id) {
		userAccountRepository.deleteById(id);
	}
	
	
}
