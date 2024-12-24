package com.yash.cmsapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yash.cmsapi.entity.Contact;
import com.yash.cmsapi.service.ContactService;
import com.yash.cmsapi.service.MapValidationErrorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/contacts")
@CrossOrigin(origins = "http://localhost:3000") 
public class ContactController {
	
	@Autowired
	private ContactService contactService;
	
	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	
	@PostMapping("/save")
    public ResponseEntity<?> createNewContact(@Valid @RequestBody Contact contact, BindingResult result) {
      
        ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(result);
        if (errorMap != null) return errorMap;

        Contact newContact = contactService.saveOrUpdate(contact);
        return new ResponseEntity<>(newContact, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public Iterable<Contact> getAllContacts() {
        return contactService.getAllContacts();
    }
    
    @GetMapping("/{contactId}")
    public ResponseEntity<?> getContactById(@PathVariable Long contactId) {
        Contact contact = contactService.findContactById(contactId);
        return new ResponseEntity<>(contact, HttpStatus.OK);
    }

    @DeleteMapping("/{contactId}")
    public ResponseEntity<?> deleteContact(@PathVariable Long contactId) {
        contactService.deleteContactById(contactId);
        return new ResponseEntity<>("Contact with ID: " + contactId + " was deleted", HttpStatus.OK);
    }

    @PutMapping("/{contactId}")
    public ResponseEntity<?> updateContact(@Valid @RequestBody Contact contact, 
                                         @PathVariable Long contactId, 
                                         BindingResult result) {
        ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(result);
        if (errorMap != null) return errorMap;

        contact.setId(contactId);
        Contact updatedContact = contactService.saveOrUpdate(contact);
        return new ResponseEntity<>(updatedContact, HttpStatus.OK);
    }
    
    //need getAll whose created date is last year

}
