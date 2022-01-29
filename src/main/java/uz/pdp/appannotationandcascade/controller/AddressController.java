package uz.pdp.appannotationandcascade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.appannotationandcascade.entity.Address;
import uz.pdp.appannotationandcascade.entity.Person;
import uz.pdp.appannotationandcascade.payload.AddressDto;
import uz.pdp.appannotationandcascade.payload.PersonDto;
import uz.pdp.appannotationandcascade.repository.AddressRepository;
import uz.pdp.appannotationandcascade.repository.PersonRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    PersonRepository personRepository;

    @PostMapping
    public HttpEntity<?> addPerson(@RequestBody List<AddressDto> addressDtoList){
        List<Address> addresses = new ArrayList<>();
        for (AddressDto addressDto : addressDtoList) {
            Address address = new Address(
                    addressDto.getStreet(),
                    addressDto.getCity(),
                    personRepository.getOne(addressDto.getPersonId())
            );
            addresses.add(address);
        }
        addressRepository.saveAll(addresses);
        return ResponseEntity.ok("Saqlandi");

    }
}
