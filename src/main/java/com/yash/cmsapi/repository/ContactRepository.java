package com.yash.cmsapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yash.cmsapi.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long>{

}
