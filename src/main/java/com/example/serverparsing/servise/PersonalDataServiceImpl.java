package com.example.serverparsing.servise;

import com.example.serverparsing.entity.PersonalData;
import com.example.serverparsing.repository.PersonalDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonalDataServiceImpl implements PersonalDataService {
    private final PersonalDataRepository personalDataRepository;

    @Override
    public List<PersonalData> getListPersonalDataAll(int pageNumber) {
        int resumeCountPage = 30; // количество резюме

        int start = (pageNumber - 1) * resumeCountPage; // Начальный индекс, начиная с нуля для первой страницы
        int end = start + resumeCountPage; // Конечный индекс

        return personalDataRepository.findAll(start, end);
    }

    @Override
    public Optional<PersonalData> getPersonDataId(int personId) {
        return this.personalDataRepository.findById(personId);
    }
}
