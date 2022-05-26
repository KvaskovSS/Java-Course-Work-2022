package HospitalAutomation.repository;

import HospitalAutomation.entity.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PeopleRepository extends JpaRepository<People, Long> {
    People findByFirstNameAndLastNameAndPatherName(String firstName, String lastName, String patherName);
    List<People> findByFirstName(String firstName);
    List<People> findByLastName(String lastName);
    List<People> findByPatherName(String patherName);

    @Query("SELECT p FROM People p WHERE p.wardId.name = :wardName")
    List<People> findByWardName(@Param("wardName")String wardName);

    @Query("SELECT p FROM People p WHERE p.diagnosisId.name = :diagnosisName")
    List<People> findByDiagnosis(@Param("diagnosisName")String diagnosisName);
}
