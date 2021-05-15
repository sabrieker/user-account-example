package com.sabri.account.service;

import java.util.List;
import java.util.Optional;

import com.sabri.account.dto.UserAccountDTO;
import com.sabri.account.entity.UserAccount;

public interface UserAccountQueryService {

	Optional<UserAccount> findOne(Long id);

	Optional<UserAccount> findByEmail(String email);

	List<UserAccountDTO> findAll(Integer pageNo, Integer pageSize, String sortBy);
}
