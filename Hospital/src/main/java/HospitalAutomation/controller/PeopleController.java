package HospitalAutomation.controller;

import HospitalAutomation.entity.People;
import HospitalAutomation.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/people")
public class PeopleController {

    @Autowired
    PeopleService peopleService;

    @GetMapping("/list")
    public ResponseEntity getAllPatients(){
        try {
            return ResponseEntity.ok(peopleService.listPeople());
        }catch (Exception exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @GetMapping("/listIds")
    public ResponseEntity getPatient(@RequestParam Long id){
        try{
            return ResponseEntity.ok(peopleService.findPatient(id));
        }catch (Exception exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @GetMapping("/findByWard")
    public ResponseEntity getPatientWard(@RequestParam String wardName){
        try{
            return ResponseEntity.ok(peopleService.findByWard(wardName));
        }catch (Exception exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @GetMapping("/findByDiagnosis")
    public ResponseEntity getPatientDiagnosis(@RequestParam String diagName){
        try{
            return ResponseEntity.ok(peopleService.findByDiagnosis(diagName));
        }catch (Exception exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @GetMapping("/findByFirstName")
    public ResponseEntity getPatientsWithSameFirstName(@RequestParam String firstName){
        try{
            return ResponseEntity.ok(peopleService.findByFirstName(firstName));
        }catch (Exception exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @GetMapping("/findByLastName")
    public ResponseEntity getPatientsWithSameLastName(@RequestParam String lastName){
        try{
            return ResponseEntity.ok(peopleService.findByLastName(lastName));
        }catch (Exception exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @GetMapping("/findByPatherName")
    public ResponseEntity getPatientsWithSamePatherName(@RequestParam String patherName){
        try{
            return ResponseEntity.ok(peopleService.findByPatherName(patherName));
        }catch (Exception exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @PostMapping("/addPeople")
    public ResponseEntity writePatient(@RequestBody People people,
                                       @RequestParam Long diagId,
                                       @RequestParam Long wardId){
        try{
            return ResponseEntity.ok(peopleService.writePerson(people, diagId, wardId));

        }catch (Exception exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePatient(@PathVariable Long id){
        try{
            return ResponseEntity.ok(peopleService.deletePerson(id));
        }catch (Exception exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity editPatient(@PathVariable Long id, @RequestBody People people){
        try {
            return ResponseEntity.ok(peopleService.editPatient(id, people));
        }catch (Exception exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

}
