package com.example.cashcard;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CashCardRepositoryTest {
    @Autowired
    private CashCardRepository cashCardRepository;
    @Test
    public void shouldSaveAndFindCashCard() {
        CashCard cashCard = new CashCard(99L, 123.45);
        cashCardRepository.save(cashCard);
        CashCard foundCashCard = cashCardRepository.findById(99L).get();
        
        assertThat(foundCashCard).isNotNull();
        assertThat(foundCashCard.getId()).isEqualTo(99L);
        assertThat(foundCashCard.getAmount()).isEqualTo(123.45);
    }
}
