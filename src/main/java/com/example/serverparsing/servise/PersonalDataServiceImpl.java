package com.example.serverparsing.servise;

import com.example.serverparsing.entity.*;
import com.example.serverparsing.repository.PersonalDataRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonalDataServiceImpl implements PersonalDataService {
    private final PersonalDataRepository personalDataRepository;
    private final EntityManagerFactory entityManagerFactory;
    private final SpecificationService specificationServer;
    private final InformationService informationService;

    @Override
    public List<PersonDataDto> getListPersonalDataAll(int pageNumber) {
        int resumeCountPage = 30; // количество резюме

        int start = (pageNumber - 1) * resumeCountPage; // Начальный индекс, начиная с нуля для первой страницы
        int end = start + resumeCountPage; // Конечный индекс

        return personalDataRepository.findDtoAll(start, end);
    }

    @Override
    public Optional<PersonalData> getPersonDataId(int personId) {
        return this.personalDataRepository.findById(personId);
    }

    @Override
    public List<PersonalData> findAllPersonData(String cityResidence,
                                                Integer wages,
                                                String foreignLanguage,
                                                String foreignLanguageLevel,
                                                List<String> educations,
                                                String gender,
                                                List<String> workSchedules,
                                                List<String> rightsCategory,
                                                List<String> businessTrips,
                                                String educationalInstitution,
                                                String specialization,
                                                Integer graduationYear,
                                                List<String> skills,
                                                List<String> citizenship,
                                                String educationLevel) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<PersonalData> query = builder.createQuery(PersonalData.class);
        Root<PersonalData> root = query.from(PersonalData.class);

        List<Predicate> predicates = new ArrayList<>();

        if (wages != null) {
            Predicate wagesPredicate = builder.and(
                    builder.isNotNull(root.get("wages")),
                    builder.greaterThanOrEqualTo(root.get("wages"), wages)
            );

            predicates.add(wagesPredicate);
        }

        if (cityResidence != null) {
            Join<PersonalData, Habitation> habitationJoin = root.join("habitation", JoinType.INNER);
            predicates.add(builder.equal(habitationJoin.get("city"), cityResidence));
        }

        if (gender != null) {
            Join<PersonalData, Gender> genderJoin = root.join("gender", JoinType.INNER);

            Predicate gendersPredicate = builder.equal(genderJoin.get("type"), gender);

            predicates.add(gendersPredicate);
        }

        if (workSchedules != null && !workSchedules.isEmpty()) {
            predicates.add(getPredicateWorkSchedule(root, builder, workSchedules));
        }

        if (citizenship != null && !citizenship.isEmpty()) {
            predicates.add(getPredicateCitizenshipType(root, builder, citizenship));
        }

        // Работа с данными из специальности
        if (educationalInstitution != null && !educationalInstitution.isEmpty()) {
            predicates.add(specificationServer
                    .getEducationalInstitutionPredicate(root, builder, educationalInstitution));
        }

        if (specialization != null && !specialization.isEmpty()) {
            predicates.add(specificationServer
                    .getSpecializationPredicate(root, builder, specialization));
        }

        if (graduationYear != null) {
            predicates.add(specificationServer
                    .getEndingPredicate(root, builder, graduationYear));
        }

        if (educationLevel != null && !educationLevel.isEmpty()) {
            predicates.add(specificationServer
                    .getEducationLevelPredicate(root, builder, educationLevel));
        }

       /* addIfNotNull(predicates, specificationServer
                .getEducationalInstitutionPredicate(root, builder, educationalInstitution));


        addIfNotNull(predicates, specificationServer
                .getSpecializationPredicate(root, builder, specialization));


        addIfNotNull(predicates, specificationServer
                .getEndingPredicate(root, builder, graduationYear));


        addIfNotNull(predicates, specificationServer
                .getEducationLevelPredicate(root, builder, educationLevel));*/


        // Работа с данными из дополнительной информации

        if (businessTrips != null && !businessTrips.isEmpty()) {
            predicates.add(informationService.getBusinessTripsPredicate(root,
                    builder, businessTrips));
        }

        if (foreignLanguage != null && !foreignLanguage.isEmpty()) {
            predicates.add(informationService.getLanguagePredicate(root,
                    builder, foreignLanguage));
        }

        if (foreignLanguageLevel != null && !foreignLanguageLevel.isEmpty()) {
            predicates.add(informationService.getLanguageLevelPredicate(root,
                    builder, foreignLanguageLevel));
        }

        if (rightsCategory != null && !rightsCategory.isEmpty()) {
            predicates.add(informationService.getLicenceCategoryPredicate(root,
                    builder, rightsCategory));
        }

       /* addIfNotNull(predicates, informationService.getBusinessTripsPredicate(root,
                builder, businessTrips));


        addIfNotNull(predicates, informationService.getLanguagePredicate(root,
                builder, foreignLanguage));

        addIfNotNull(predicates, informationService.getLanguageLevelPredicate(root,
                builder, foreignLanguageLevel));

        addIfNotNull(predicates, informationService.getLicenceCategoryPredicate(root,
                builder, rightsCategory));*/


        query.where(predicates.toArray(new Predicate[0]));

        //List<PersonalData> personalDataList = entityManager.createQuery(query).getResultList();

        return entityManager.createQuery(query).getResultList();
        //return conversionPersonData(personalDataList);
    }

    private Predicate getPredicateWorkSchedule(Root<PersonalData> root, CriteriaBuilder builder,
                                               List<String> workSchedules) {
        if (workSchedules == null || workSchedules.isEmpty()) {
            return null;
        }

        Join<PersonalData, WorkSchedule> workScheduleJoin = root.join("workSchedule", JoinType.INNER);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(workScheduleJoin.get("workType").in(workSchedules));

        return builder.and(predicates.toArray(new Predicate[0]));
    }

    private Predicate getPredicateCitizenshipType(Root<PersonalData> root, CriteriaBuilder builder,
                                                  List<String> citizenship) {

        if (citizenship == null || citizenship.isEmpty()) {
            return null;
        }

        Join<PersonalData, CitizenshipType> workScheduleJoin = root.join("citizenshipType", JoinType.INNER);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(workScheduleJoin.get("name").in(citizenship));

        return builder.and(predicates.toArray(new Predicate[0]));
    }

    private void addIfNotNull(List<Predicate> predicates, Predicate predicate) {
        if (predicate != null) {
            predicates.add(predicate);
        }
    }

    private List<PersonDataDto> conversionPersonData(List<PersonalData> personalDataList) {
        if (personalDataList == null || personalDataList.isEmpty()) {
            return null;
        }

        List<PersonDataDto> personDataDtoList = new ArrayList<>(personalDataList.size());

        for (PersonalData person : personalDataList) {
            Information information = person.getInformation();
            String skill = null;

            if (information != null) {
                skill = information.getSkills();
            }

            PersonDataDto newPersonDataDto = new PersonDataDto(person.getId(), person.getName(),
                    person.getTitle(), person.getWages(), skill);

            personDataDtoList.add(newPersonDataDto);
        }

        return personDataDtoList;
    }
}