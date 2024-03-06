package com.example.truedemo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, String>{

    List<Contact> findAllByName(String name);
}
