package pl.ray.camelcase.persons;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Log4j2
@ActiveProfiles("test")
@SpringBootTest(properties = {"database.quote:true"})
class PersonRepositoryWithQuoteTest {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    DatabaseClient databaseClient;

    @AfterEach
    void clean() {
        databaseClient.execute("DROP SCHEMA public CASCADE; CREATE SCHEMA public;").then().block();
    }

    @Test
    void shouldReturnPersonFromDB() {

        //when
        final var person = personRepository.findAll().blockLast();

        //Person is returned but every property is null
        log.info("Person from db: " + person);

        //then
        assertNotNull(person.getId());
        assertEquals("Ray", person.getFirstName());
        assertEquals("Neyra", person.getLastName());

    }

    @Test
    void shouldSaveNewPersonInDbWithoutError() {

        //given
        final var person = new Person();
        person.setFirstName("firstName");
        person.setLastName("lastName");
        final var countInDbBeforeSave = personRepository.findAll().count().block();

        //when
        final var savedPerson = personRepository.save(person).block();

        //Id is properly returned here
        log.info("Saved person" + savedPerson);

        //then
        final var countInDbAfterSave = personRepository.findAll().count().block();
        assertEquals(1L, countInDbBeforeSave);
        assertEquals(2L, countInDbAfterSave);
    }


}
