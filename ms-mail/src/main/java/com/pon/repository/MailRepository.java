/**
 * 
 */
package com.pon.repository;


import org.springframework.data.repository.CrudRepository;

import com.pon.entity.Mail;
/**
 * @author Sanjeev
 *
 */

public interface MailRepository extends CrudRepository<Mail, Long> {

}