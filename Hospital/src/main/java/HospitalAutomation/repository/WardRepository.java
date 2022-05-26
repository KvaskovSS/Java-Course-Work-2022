package HospitalAutomation.repository;

import HospitalAutomation.entity.Ward;
import org.springframework.data.repository.CrudRepository;

public interface WardRepository extends CrudRepository<Ward, Long> {
    Ward findByName(String name);
}
