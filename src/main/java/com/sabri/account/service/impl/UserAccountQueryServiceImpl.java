package com.sabri.account.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sabri.account.dto.UserAccountDTO;
import com.sabri.account.entity.UserAccount;
import com.sabri.account.service.UserAccountQueryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserAccountQueryServiceImpl implements UserAccountQueryService {
	private final UserAccountRepository userAccountRepository;
	private final ModelMapper mapper;

	@Override
	public List<UserAccountDTO> findAll(Integer pageNo, Integer pageSize, String sortBy) {
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		
		return userAccountRepository.findAll(paging)
									.stream().map(var -> mapper.map(var, UserAccountDTO.class))
									.collect(Collectors.toList());
	}

	
	@Override
	public Optional<UserAccount> findOne(Long id) {
		return userAccountRepository.findById(id);
	}

	@Override
	public Optional<UserAccount> findByEmail(String email) {
		return userAccountRepository.findByEmail(email);
	}
}
