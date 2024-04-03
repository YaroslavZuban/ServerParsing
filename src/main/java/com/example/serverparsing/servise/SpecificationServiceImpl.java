package com.example.serverparsing.servise;

import com.example.serverparsing.entity.*;
import com.example.serverparsing.job.FieldPredicate;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Service;

@Service
public class SpecificationServiceImpl implements SpecificationService {
    @Override
    public Predicate getEducationLevelPredicate(Root<?> root, CriteriaBuilder builder, String education) {
        Join<PersonalData, Specification> specificationJoin = root.join("specifications", JoinType.INNER);
        Join<Specification, EducationType> genderJoin = specificationJoin.join("educationType", JoinType.INNER);

        return builder.equal(genderJoin.get("educationLevel"), education);
    }

    @Override
    public Predicate getEndingPredicate(Root<?> root, CriteriaBuilder builder, Integer graduationYear) {
        return FieldPredicate.getFieldPredicate(root, builder,
                "specifications", "ending",
                graduationYear, PersonalData.class, Specification.class);
    }

    @Override
    public Predicate getEducationalInstitutionPredicate(Root<?> root, CriteriaBuilder builder, String educationalInstitution) {
        return FieldPredicate.getFieldPredicate(root, builder,
                "specifications", "educationalInstitution",
                educationalInstitution, PersonalData.class, Specification.class);
    }

    @Override
    public Predicate getSpecializationPredicate(Root<?> root, CriteriaBuilder builder, String specialization) {
        return FieldPredicate.getFieldPredicate(root, builder,
                "specifications", "direction",
                specialization, PersonalData.class, Specification.class);
    }
}