package pl.ray.camelcase.persons;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.r2dbc.BadSqlGrammarException;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.test.context.ActiveProfiles;
import reactor.test.StepVerifier;

@Log4j2
@ActiveProfiles("test")
@SpringBootTest(properties = {"database.quote:false"})
class PersonRepositoryWithoutQuoteTest {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    DatabaseClient databaseClient;

    @AfterEach
    void clean() {
        databaseClient.execute("DROP SCHEMA public CASCADE; CREATE SCHEMA public;").then().block();
    }

    @Test
    void shouldThrowBadGrammarExceptionOnSave() {

        //given
        final var person = new Person();
        person.setFirstName("firstName");
        person.setLastName("lastName");

        //then
        StepVerifier.create(personRepository.save(person).onErrorMap(throwable -> {
            log.error(throwable);
            return throwable;
        })).verifyError(BadSqlGrammarException.class);
    }


}
