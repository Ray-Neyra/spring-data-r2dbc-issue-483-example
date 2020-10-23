package pl.ray.camelcase.persons;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("persons")
class Person {

    @Id
    private Integer id;

    @Column("firstName")
    private String firstName;

    @Column("lastName")
    private String lastName;

}
