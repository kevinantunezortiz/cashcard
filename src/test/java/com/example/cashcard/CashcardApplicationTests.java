package com.example.cashcard;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

// inicia el servidor de spring boot con un puerto aleatorio
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CashcardApplicationTests {

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    CashCardRepository repository;

    @Test
    void shouldReturnACashCardWhenDataIsSaved() {
        CashCard cashCard = new CashCard(99L, 123.45);
        repository.save(cashCard);

        ResponseEntity<String> response = restTemplate.getForEntity("/cashcards/99", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        DocumentContext documentContext = JsonPath.parse(response.getBody());
        Number id = documentContext.read("$.id");
        assertThat(id).isEqualTo(99);
        Double amount = documentContext.read("$.amount");
        assertThat(amount).isEqualTo(123.45);
    }
	@Test
	void shouldNotReturnACashCardWithAnUnknownId() {
		//petición get a /cashcards/1000 que no existe en la base de datos 
	  ResponseEntity<String> response = restTemplate.getForEntity("/cashcards/1000", String.class);
	
	  assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	  assertThat(response.getBody()).isBlank();
	}

}
