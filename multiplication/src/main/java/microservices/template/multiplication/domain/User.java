package microservices.template.multiplication.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

/**
 * Stores information to identify the user.
 */
@ToString
@EqualsAndHashCode
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public final class User {
    @Id
    @GeneratedValue
    @Column(name = "USER_ID")
    private Long id;

    private String alias;

    private String middleName;
    private String firstName;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dateOfBirth;
    private Integer siblings;
}