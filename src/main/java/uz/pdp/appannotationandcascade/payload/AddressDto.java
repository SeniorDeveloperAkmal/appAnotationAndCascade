package uz.pdp.appannotationandcascade.payload;

import lombok.Data;
import uz.pdp.appannotationandcascade.entity.Person;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class AddressDto {

    @NotNull
    @Size(min = 3, max = 50)
    private String street;

    @NotNull
    @Size(min = 3, max = 50)
    private String city;

    private Integer personId;

}
