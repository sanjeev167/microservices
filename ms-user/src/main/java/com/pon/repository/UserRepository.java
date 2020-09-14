/**
 * 
 */
package com.pon.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pon.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}