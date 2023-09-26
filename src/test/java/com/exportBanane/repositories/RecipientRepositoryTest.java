package com.exportBanane.repositories;


import com.exportBanane.models.Recipient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RecipientRepositoryTest {

    @Autowired
    RecipientRepository recipientRepository;

    private Recipient recipient;


    @BeforeEach
    public void setup() {

        recipient = Recipient.builder().id(1).name("Test1").city("Antony").address("123 rue abc").build();
    }

    @DisplayName("Junit test for save recipient operation")
    @Test
    public void givenRecipientObject_whenSave_thenReturnSavedRecipient() {
        //given - precondition or setup

        // when - action or the behaviour that we are going test
        Recipient savedRecipient = recipientRepository.save(recipient);

        // then - verify the output
        assertThat(savedRecipient).isNotNull();
        assertThat(savedRecipient.getId()).isGreaterThan(0);

    }

}
