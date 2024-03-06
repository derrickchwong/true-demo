package com.example.truedemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class GreetingRestController {

    private ContactRepository contactRepository;

    public GreetingRestController(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @GetMapping("/greeting/{name}")
    public String greeting(@PathVariable String name) {
        log.info("Greeting {}", name);
        return "Hello, " + name + "! How are you? Great! Yeah!!";
    }

    @GetMapping("/contacts/{id}")
    public Contact getContact(@PathVariable String id) {
        return contactRepository.findById(id).orElse(null);
    }

    @PostMapping("/contacts")
    public Contact saveContact(@RequestBody Contact contact) {
        return contactRepository.save(contact);
    }

    @GetMapping("/contacts")
    public Iterable<Contact> getContacts() {
        return contactRepository.findAll();
    }

    @GetMapping("/contacts/search/{name}")
    public Iterable<Contact> searchContacts(@PathVariable String name) {
        return contactRepository.findAllByName(name);
    }
}
