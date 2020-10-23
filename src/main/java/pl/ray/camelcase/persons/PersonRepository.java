package pl.ray.camelcase.persons;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

interface PersonRepository extends ReactiveCrudRepository<Person, Integer> {
}
