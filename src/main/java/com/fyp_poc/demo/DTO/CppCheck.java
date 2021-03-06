package com.fyp_poc.demo.DTO;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;


@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="cpp_check")
public class CppCheck {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name="id")
    private UUID id;
    @JsonProperty("error")
    long error;
    @JsonProperty("performance")
    long performance;
    @JsonProperty("portability")
    long portability;
    @JsonProperty("style")
    long style;
    @JsonProperty("warning")
    long warning;
}
