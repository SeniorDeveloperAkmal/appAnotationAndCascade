package uz.pdp.appannotationandcascade.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String fullName;

    @OrderBy(value = "city asc, street desc") // PERSONNI SO'RAGANIMIZDA ADRESS FIELDINI SARALAB QAYTARADI
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)//mappedBy-3-table ochilishini oldini oladi va addressdagi person fieldini kuzatib boradi
    private List<Address> addresses;

    private LocalDate birthDate;

    @Transient
    private Integer age;

    public Integer getAge() {
        if (birthDate==null)
        return 0;
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    @Transient// DATABASEGA SHU FIELDNI YOZIB QUYMAYDI LEKIN ISHLATIB YURISHIMIZ MUMKIN
    private Integer countFullNameLetters;

    public Integer getCountFullNameLetters() {
        return fullName!=null?fullName.length():0;
    }
}
