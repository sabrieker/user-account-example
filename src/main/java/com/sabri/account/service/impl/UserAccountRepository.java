package com.sabri.account.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sabri.account.entity.UserAccount;

@Repository
interface UserAccountRepository extends JpaRepository<UserAccount, Long>,PagingAndSortingRepository<UserAccount, Long>{
	List<UserAccount> findByName(String name);
	Optional<UserAccount> findByEmail(String email);
}
