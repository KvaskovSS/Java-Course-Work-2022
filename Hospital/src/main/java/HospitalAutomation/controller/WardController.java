package HospitalAutomation.controller;

import HospitalAutomation.entity.Ward;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import HospitalAutomation.service.WardService;

@RestController
@RequestMapping("/wards")
public class WardController {

    @Autowired
    private WardService wardService;

    @GetMapping("/list")
    public ResponseEntity getAllWards(){
        try {
            return ResponseEntity.ok(wardService.getAllWards());
        }catch (Exception exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @GetMapping("/listIds")
    public ResponseEntity getDiagnosis(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(wardService.findWard(id));
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @PostMapping("/addWard")
    public ResponseEntity saveWard(@RequestBody Ward ward) {
        try {
            wardService.saveWard(ward);
            return ResponseEntity.ok("ward is saved");
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }



    @DeleteMapping("/{id}")
    public ResponseEntity deleteDiagnosis(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(wardService.deleteWard(id));
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity editWard(@PathVariable Long id, @RequestBody Ward ward){
        try {
            return ResponseEntity.ok(wardService.editWard(id,ward));
        }catch (Exception exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }
}
