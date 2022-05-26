package HospitalAutomation.repository;

import HospitalAutomation.entity.Diagnosis;
import org.springframework.data.repository.CrudRepository;

public interface DiagnosisRepository extends CrudRepository<Diagnosis, Long> {
    Diagnosis findByName(String name);
}
