package com.example.serverparsing.servise;

import com.aspose.cells.Workbook;
import com.example.serverparsing.entity.*;
import com.example.serverparsing.graph_excel.GraphExcelFile;
import com.example.serverparsing.graph_excel.GraphExcelFileImpl;
import com.example.serverparsing.job.FindSkillsPersonalData;
import com.example.serverparsing.job.SkillCombinations;
import com.example.serverparsing.p_enum.AnalyticTitleEnum;
import com.example.serverparsing.repository.PersonalDataRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class PersonalDataServiceImpl implements PersonalDataService {
    private final PersonalDataRepository personalDataRepository;
    private final EntityManagerFactory entityManagerFactory;
    private final SpecificationService specificationServer;
    private final InformationService informationService;

    private final static int RESUME_COUNT_PAGE = 15;


    @Override
    public Optional<PersonalData> getPersonDataId(int personId) {
        return this.personalDataRepository.findById(personId);
    }

    @Override
    public List<PersonDataDto> findAllPersonData(String cityResidence,
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
                                                 String educationLevel,
                                                 int pageNumber) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<PersonalData> query = builder.createQuery(PersonalData.class);
        Root<PersonalData> root = query.from(PersonalData.class);

        List<Predicate> predicates = new ArrayList<>();

        // Обработка персональных данных (ЗП)
        if (wages != null) {
            Predicate wagesPredicate = builder.and(
                    builder.isNotNull(root.get("wages")),
                    builder.greaterThanOrEqualTo(root.get("wages"), wages)
            );

            predicates.add(wagesPredicate);
        }

        // Обработка города

        if (cityResidence != null && !cityResidence.isEmpty()) {
            Join<PersonalData, Habitation> habitationJoin = root.join("habitation", JoinType.INNER);
            predicates.add(builder.equal(habitationJoin.get("city"), cityResidence));
        }

        // Обработка пола

        if (gender != null && !gender.isEmpty()) {
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

        query.where(predicates.toArray(new Predicate[0]));

        List<PersonalData> personalDataList = entityManager.createQuery(query).getResultList();

        if (skills != null && !skills.isEmpty()) {
            List<PersonalData> skillsSortPerson = findSkillPersonalData(personalDataList, skills);
            return conversionPersonData(skillsSortPerson, pageNumber);
        }

        return conversionPersonData(personalDataList, pageNumber);
    }

    @Override
    public Map<String, Integer> analyticSkillsUniversity(String university, List<String> skills) {
        if (university == null || university.isEmpty()) {
            return null;
        }

        if (skills == null || skills.isEmpty()) {
            return null;
        }

        List<String> uniqueSkills = getUniqueKnowledgeList(skills);
        Map<String, Integer> statistics = new HashMap<>();

        for (String skill : uniqueSkills) {
            Integer resultSkill = this.personalDataRepository.getSkillsUniversity(university, skill);

            statistics.put(skill, Objects.requireNonNullElse(resultSkill, 0));
        }

        return statistics;
    }

    @Override
    public Map<String, Integer> analyticSkillsSpecialties(String university, String specialties, List<String> skills) {
        if (university == null || university.isEmpty()) {
            return null;
        }

        if (specialties == null || specialties.isEmpty()) {
            return null;
        }

        if (skills == null || skills.isEmpty()) {
            return null;
        }

        List<String> uniqueSkills = getUniqueKnowledgeList(skills);
        Map<String, Integer> statistics = new HashMap<>();

        for (String skill : uniqueSkills) {
            Integer resultSkill = this.personalDataRepository.getSkillsSpecialties(university, specialties, skill);

            statistics.put(skill, Objects.requireNonNullElse(resultSkill, 0));
        }

        return statistics;
    }

    @Override
    public Map<String, Integer> analyticSkillsSpecialtiesYear(String university, String specialties, Integer year, List<String> skills) {
        if (university == null || university.isEmpty()) {
            return null;
        }

        if (specialties == null || specialties.isEmpty()) {
            return null;
        }

        if (year == null) {
            return null;
        }

        if (skills == null || skills.isEmpty()) {
            return null;
        }

        List<String> uniqueSkills = getUniqueKnowledgeList(skills);
        Map<String, Integer> statistics = new HashMap<>();

        for (String skill : uniqueSkills) {
            Integer resultSkill = this.personalDataRepository.getSkillsSpecialtiesYear(university, specialties, year, skill);

            statistics.put(skill, Objects.requireNonNullElse(resultSkill, 0));
        }

        return statistics;
    }

    @Override
    public Resource graphExcelSkillsUniversity(String university, List<String> skills) {
        List<String> uniqueSkills = getUniqueKnowledgeList(skills);

        Map<String, Integer> result = analyticSkillsUniversity(university, uniqueSkills);

        return new GraphExcelFileImpl().getHistogramFile(result, AnalyticTitleEnum.ANALYTIC_TITLE_SKILLS_UNIVERSITY);
    }

    @Override
    public Resource graphExcelSkillsSpecialties(String university, String specialties, List<String> skills) {
        List<String> uniqueSkills = getUniqueKnowledgeList(skills);

        Map<String, Integer> result = analyticSkillsSpecialties(university, specialties, uniqueSkills);

        return new GraphExcelFileImpl().getHistogramFile(result, AnalyticTitleEnum.ANALYTIC_TITLE_SKILLS_SPECIALTIES);
    }

    @Override
    public Resource graphExcelSkillsSpecialtiesYear(String university, String specialties, Integer year, List<String> skills) {
        List<String> uniqueSkills = getUniqueKnowledgeList(skills);

        Map<String, Integer> result = analyticSkillsSpecialties(university, specialties, uniqueSkills);

        return new GraphExcelFileImpl().getHistogramFile(result, AnalyticTitleEnum.ANALYTIC_TITLE_SKILLS_SPECIALTIES_YEAR);
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

    private List<PersonalData> findSkillPersonalData(List<PersonalData> personalDataList, List<String> skill) {
        if (skill == null || skill.isEmpty()) {
            return personalDataList;
        }

        SkillCombinations skillCombinations = new SkillCombinations();
        List<List<String>> skillsCombinations = skillCombinations.getAllCombinations(skill);

        FindSkillsPersonalData findSkillsPersonalData = new FindSkillsPersonalData();
        return findSkillsPersonalData.findPersonalData(personalDataList, skillsCombinations);
    }

    private List<PersonDataDto> conversionPersonData(List<PersonalData> personalDataList, int pageNumber) {
        if (personalDataList == null || personalDataList.isEmpty()) {
            return null;
        }

        int start = (pageNumber - 1) * RESUME_COUNT_PAGE;

        if (start >= personalDataList.size()) {
            return null;
        }

        Collections.reverse(personalDataList);

        return getPersonDataDtos(personalDataList, start);
    }

    private List<PersonDataDto> getPersonDataDtos(List<PersonalData> personalDataList, int start) {
        int end = Math.min(start + RESUME_COUNT_PAGE, personalDataList.size());

        List<PersonDataDto> personDataDtoList = new ArrayList<>(end - start);

        for (int i = start; i < end; i++) {
            PersonalData person = personalDataList.get(i);

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

    private List<String> getUniqueKnowledgeList(List<String> skills) {
        Set<String> uniqueKnowledge = new HashSet<>(skills);
        return uniqueKnowledge.stream().toList();
    }
}
