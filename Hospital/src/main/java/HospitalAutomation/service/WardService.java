package HospitalAutomation.service;

import HospitalAutomation.entity.Ward;
import HospitalAutomation.exceptions.WardIsAlreadyExistException;
import HospitalAutomation.exceptions.WardIsNotFoundException;
import HospitalAutomation.models.WardModel;

import java.util.List;

public interface WardService {
    List<Ward> listWard();
    WardModel findWard(Long id) throws WardIsNotFoundException;
    Ward saveWard(Ward ward) throws WardIsAlreadyExistException;
    Long deleteWard(Long id) throws WardIsNotFoundException;
    List<WardModel> getAllWards() throws Exception;
    Ward editWard(Long id, Ward ward);
}
