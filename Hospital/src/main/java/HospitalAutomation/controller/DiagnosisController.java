package HospitalAutomation.controller;

import HospitalAutomation.entity.Diagnosis;
import HospitalAutomation.service.DiagnosisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/diagnosis")
public class DiagnosisController {

    @Autowired
    private DiagnosisService diagnosisService;

    @GetMapping(value = "/list")
    public ResponseEntity getAllDiagnosis(){
        try {
            return ResponseEntity.ok(diagnosisService.getAllDiagnosis());
        }catch (Exception exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @GetMapping(value = "/listIds")
    public ResponseEntity getDiagnosis(@RequestParam Long id){
        try{
            return ResponseEntity.ok(diagnosisService.findDiagnosis(id));
        }catch (Exception exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @PostMapping("/addDiagnosis")
    public ResponseEntity saveDiagnosis(@RequestBody Diagnosis diagnosis){
        try{
            diagnosisService.saveDiagnosis(diagnosis);
            return ResponseEntity.ok("Diagnosis is saved");
        }catch (Exception exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteDiagnosis(@PathVariable Long id){
        try{
            return ResponseEntity.ok(diagnosisService.deleteDiagnosis(id));
        }catch (Exception exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity editDiagnosis(@PathVariable Long id, @RequestBody Diagnosis diagnosis){
        try {
            return ResponseEntity.ok(diagnosisService.editDiagnosis(id, diagnosis));
        }catch (Exception exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

}
