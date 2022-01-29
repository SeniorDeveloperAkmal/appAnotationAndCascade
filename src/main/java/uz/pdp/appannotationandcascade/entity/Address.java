package uz.pdp.appannotationandcascade.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String city;

    @JsonIgnore // shu annotatsiya quyilgan fieldni olmay ketadi, Personga kirib ketmaydi chunki personda adres bor va rekursiyaga tushib qolishi mn
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Person person;

    public Address(String street, String city, Person person) {
        this.street = street;
        this.city = city;
        this.person = person;
    }
}
