package com.trade_accounting.services.impl.Stubs.model;

import com.trade_accounting.models.Contact;

public class ContactModelStubs {

    public static Contact getContact(Long id){
        return Contact.builder()
                .id(id)
                .fullName("Contact fullName")
                .position("position")
                .phone("89998887766")
                .email("contact@mail.com")
                .comment("Comment"+id)
                .build();
    }
}
