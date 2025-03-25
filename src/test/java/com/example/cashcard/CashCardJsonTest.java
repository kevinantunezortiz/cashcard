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
        String expectedJson = new String(Files.readAllBytes(Paths.get("src/test/resources/expected.json")));
        assertThat(json.write(cashCard)).isEqualToJson(expectedJson);
        assertThat(json.write(cashCard)).hasJsonPathNumberValue("@.id");
        assertThat(json.write(cashCard)).extractingJsonPathNumberValue("@.id").isEqualTo(99);
        assertThat(json.write(cashCard)).hasJsonPathNumberValue("@.amount");
        assertThat(json.write(cashCard)).extractingJsonPathNumberValue("@.amount").isEqualTo(123.45);
    }
}
