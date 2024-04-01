package com.example.serverparsing.servise;

import com.example.serverparsing.entity.PersonalData;

import java.util.List;
import java.util.Optional;

public interface PersonalDataService {

     List<PersonalData> getListPersonalDataAll(int pageNumber);
     Optional<PersonalData> getPersonDataId(int personId);
}
