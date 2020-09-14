/**
 * 
 */
package com.pon.service;

import com.pon.entity.User;

/**
 * @author Sanjeev
 *
 */


public interface UserService {

	User registerUser(User input);
    Iterable<User> findAll();
}
