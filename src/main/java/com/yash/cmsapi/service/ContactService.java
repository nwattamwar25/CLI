package com.yash.cmsapi.service;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.cmsapi.entity.Contact;
import com.yash.cmsapi.exception.ContactIdException;
import com.yash.cmsapi.repository.ContactRepository;

@Service
public class ContactService {
	
	@Autowired
	private ContactRepository contactRepository;
	
	
	public Contact saveOrUpdate(Contact contact) {
        try {
//            contact.setCreated_At(new Date(System.currentTimeMillis()));
            contact.setUpdated_At(new Date(System.currentTimeMillis()));
//            
//            // Additional validation
            if(contact.getName() == null || contact.getName().trim().isEmpty()) {
                throw new ContactIdException("Contact Name cannot be empty");
            }
            if(contact.getEmail() == null || contact.getEmail().trim().isEmpty()) {
                throw new ContactIdException("Contact Email cannot be empty");
            }
            if(contact.getPhone() == null || contact.getPhone().trim().isEmpty()) {
                throw new ContactIdException("Contact Phone cannot be empty");
            }
            if(contact.getcGroup() == null || contact.getcGroup().trim().isEmpty()) {
                throw new ContactIdException("Contact Group cannot be empty");
            }

            return contactRepository.save(contact);
        } catch (Exception ex) {
            throw new ContactIdException("Error saving contact: " + ex.getMessage());
        }
    }

	
	public Iterable<Contact> getAllContacts (){
		return contactRepository.findAll();
		
	}
	
	public Contact findContactById(Long id) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new ContactIdException("Contact ID '" + id + "' does not exist"));
        return contact;
    }


    public void deleteContactById(Long id) {
        Contact contact = findContactById(id);
        contactRepository.delete(contact);
    }

}
