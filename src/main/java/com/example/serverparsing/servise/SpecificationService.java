package com.example.serverparsing.servise;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public interface SpecificationService {
    Predicate getEducationLevelPredicate(Root<?> root, CriteriaBuilder builder,
                                         String Education);

    Predicate getEndingPredicate(Root<?> root, CriteriaBuilder builder,
                                 Integer graduationYear);

    Predicate getEducationalInstitutionPredicate(Root<?> root, CriteriaBuilder builder,
                                                 String educationalInstitution);

    Predicate getSpecializationPredicate(Root<?> root, CriteriaBuilder builder,
                                         String specialization);
}
