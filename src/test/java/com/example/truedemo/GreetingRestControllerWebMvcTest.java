package com.example.truedemo;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GreetingRestControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContactRepository contactRepository;

    @BeforeEach
    public void setUp() {
        Contact contact1 = Contact.builder().id("1").name("John Doe").build();
        Contact contact2 = Contact.builder().id("2").name("Peter Chan").build();
        when(contactRepository.findAll()).thenReturn(Arrays.asList(contact1, contact2));
        when(contactRepository.findById("1")).thenReturn(java.util.Optional.of(contact1));
        when(contactRepository.save(contact1)).thenReturn(contact1);
    }

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        mockMvc.perform(get("/greeting/Derrick"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, Derrick! How are you? Great! Yeah!!"));
    }

    @Test
    public void shouldReturnContactById() throws Exception {
        mockMvc.perform(get("/contacts/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":\"1\",\"name\":\"John Doe\"}"));
    }

    @Test
    public void shouldSaveContact() throws Exception {
        Contact c = Contact.builder().id("3").name("New Contact").build();
        when(contactRepository.save(c)).thenReturn(c);
        mockMvc.perform(post("/contacts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":\"3\",\"name\":\"New Contact\"}"))
                .andExpect(status().isOk())
                .andExpect(
                    content()
                    .json("{\"id\":\"3\",\"name\":\"New Contact\"}"));
    }

    @Test
    public void shouldReturnAllContacts() throws Exception {
        mockMvc.perform(get("/contacts"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":\"1\",\"name\":\"John Doe\"},{\"id\":\"2\",\"name\":\"Peter Chan\"}]"));
    }

}
