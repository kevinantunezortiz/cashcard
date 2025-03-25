package com.example.cashcard;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

@JsonTest
public class CashCardJsonTest {

    @Autowired
    private JacksonTester<CashCard> json;

    @Test
    public void myFirstTest() {
        assertThat(42).isEqualTo(42);
    }

    @Test
    void testSerialize() throws IOException {
        CashCard cashCard = new CashCard(99L, 123.45);
        //read the expected JSON from a file
        String expectedJson = new String(Files.readAllBytes(Paths.get("src/test/resources/expected.json")));

        //verify that the JSON matches the expected JSON 
        assertThat(json.write(cashCard)).isEqualToJson(expectedJson);
        //verify that the JSON has a specific value
        assertThat(json.write(cashCard)).hasJsonPathNumberValue("@.id");
        //VERIFICA QUE EL VALOR DEl campo sea 99
        assertThat(json.write(cashCard)).extractingJsonPathNumberValue("@.id").isEqualTo(99);
        //VERIFICA QUE EL VALOR DEl campo sea 123.45 Y QUE EXISTA LA PROPIEDAD AMOUNT
        assertThat(json.write(cashCard)).hasJsonPathNumberValue("@.amount");
        assertThat(json.write(cashCard)).extractingJsonPathNumberValue("@.amount").isEqualTo(123.45);
    }

    @Test
    void cashCardDeserializationTest() throws IOException {
        //read the expected JSON from a file
        String expected = """
           {
               "id":99,
               "amount":123.45
           }
           """;
        //verify that the JSON matches the expected JSON
        assertThat(json.parse(expected))
                .isEqualTo(new CashCard(99L, 123.45));
        //verify that the JSON has a id field with a specific value equals 1000
        assertThat(json.parseObject(expected).id()).isEqualTo(99L);
        //verify that the JSON has a amount field with a specific value equals 67.89
        assertThat(json.parseObject(expected).amount()).isEqualTo(123.45);
    }
}
