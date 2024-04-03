package com.example.serverparsing.servise;

import com.example.serverparsing.entity.*;
import com.example.serverparsing.job.FieldPredicate;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class InformationServiceImpl implements InformationService {
    @Override
    public Predicate getLicenceCategoryPredicate(Root<?> root, CriteriaBuilder builder, List<String> rightsCategory) {
        Join<PersonalData, Information> informationJoin = root.join("information", JoinType.INNER);
        Join<Information, LicenceCategory> licenceCategoryJoin = informationJoin.join("categoryList", JoinType.INNER);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(licenceCategoryJoin.get("category").in(rightsCategory));

        return builder.and(predicates.toArray(new Predicate[0]));
    }

    @Override
    public Predicate getBusinessTripsPredicate(Root<?> root, CriteriaBuilder builder, List<String> businessTrips) {
        Join<PersonalData, Information> informationJoin = root.join("information", JoinType.INNER);
        Join<Information, BusinessTrips> businessTripsJoin = informationJoin.join("businessTrips", JoinType.INNER);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(businessTripsJoin.get("readiness").in(businessTrips));

        return builder.and(predicates.toArray(new Predicate[0]));
    }

    @Override
    public Predicate getLanguageLevelPredicate(Root<?> root, CriteriaBuilder builder, String foreignLanguageLevel) {
        Join<PersonalData, Information> informationJoin = root.join("information", JoinType.INNER);
        Join<Information, Level> levelJoin = informationJoin.join("levels", JoinType.INNER);

        return builder.equal(levelJoin.get("knowledgeLevel"), foreignLanguageLevel);
    }

    @Override
    public Predicate getLanguagePredicate(Root<?> root, CriteriaBuilder builder, String foreignLanguage) {
        Join<PersonalData, Information> informationJoin = root.join("information", JoinType.INNER);
        Join<Information, Language> languageJoin = informationJoin.join("languages", JoinType.INNER);

        List<Predicate> predicates = new ArrayList<>();

        if (foreignLanguage != null && !foreignLanguage.isEmpty()) {
            predicates.add(languageJoin.get("name").in(foreignLanguage));
        }

        return builder.and(predicates.toArray(new Predicate[0]));
    }

    @Override
    public Predicate getInformationPredicate(Root<?> root, CriteriaBuilder builder, List<String> skills) {
        return null;
    }
}