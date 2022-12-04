package com.automationfraternity;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder(setterPrefix = "with")
@EqualsAndHashCode
@Entity
public class ModeOfCommunication {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    String phone;
    String mobile;
    String homePhone;

    @OneToOne(mappedBy = "modeOfCommunication")
    Employee employee;
}