package uz.pdp.appannotationandcascade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appannotationandcascade.entity.Address;
import uz.pdp.appannotationandcascade.entity.Person;
import uz.pdp.appannotationandcascade.payload.AddressDto;
import uz.pdp.appannotationandcascade.payload.PersonDto;
import uz.pdp.appannotationandcascade.repository.AddressRepository;
import uz.pdp.appannotationandcascade.repository.PersonRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    AddressRepository addressRepository;

//    @Transactional(rollbackFor = NullPointerException.class)//norollback-orqada qaytarma
    @PostMapping
    public HttpEntity<?> addPerson(@RequestBody PersonDto dto){

        //Personni saqlab oldik
        Person person = new Person();
        person.setFullName(dto.getFullName());

        //Adress yasab olamiz
        List<Address> addresses = new ArrayList<>();
        for (AddressDto addressDto : dto.getAddressDtoList()) {
            Address address = new Address(
                    addressDto.getStreet(),
                    addressDto.getCity(),
                    person
            );
            addresses.add(address);
        }
        person.setAddresses(addresses);
        personRepository.save(person);
        return ResponseEntity.ok("Saqlandi");

    }

    @PutMapping("/{id}")
    public HttpEntity<?> editPerson(@PathVariable Integer id){

        //Personni saqlab oldik
        Person person = personRepository.getOne(id);
        person.setFullName("ism o'zgardi");

        for (Address address : person.getAddresses()) {
            address.setStreet("kocha nomi ozgardi");
        }
        personRepository.save(person);
        return ResponseEntity.ok("Saqlandi");

    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deletePerson(@PathVariable Integer id){
        try {
            personRepository.deleteById(id);
            return ResponseEntity.ok("O'chirildi");
        }catch (Exception e){
            return ResponseEntity.ok("O'chirilmadi");
        }
    }

    @DeleteMapping("/forTransaction/{id}")
    public HttpEntity<?> deleteForTransactional(@PathVariable Integer id){
        personRepository.deleteById(id);
        throw new NullPointerException();
    }

    @GetMapping
    public HttpEntity<?> getPersons(){
        return ResponseEntity.ok(personRepository.findAll());
    }


}
