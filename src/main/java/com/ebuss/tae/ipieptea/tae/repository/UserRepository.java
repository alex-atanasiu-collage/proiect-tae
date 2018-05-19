package com.ebuss.tae.ipieptea.tae.repository;

import org.springframework.data.repository.CrudRepository;

import com.ebuss.tae.ipieptea.tae.entity.User;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<User, Long> {

}