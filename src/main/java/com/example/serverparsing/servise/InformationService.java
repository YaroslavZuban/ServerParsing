package com.example.serverparsing.servise;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.List;

public interface InformationService {
    Predicate getLicenceCategoryPredicate(Root<?> root, CriteriaBuilder builder,
                                          List<String> rightsCategory);

    Predicate getBusinessTripsPredicate(Root<?> root, CriteriaBuilder builder,
                                        List<String> businessTrips);

    Predicate getLanguageLevelPredicate(Root<?> root, CriteriaBuilder builder,
                                        String foreignLanguageLevel);

    Predicate getLanguagePredicate(Root<?> root, CriteriaBuilder builder,
                                   String foreignLanguage);

    Predicate getInformationPredicate(Root<?> root, CriteriaBuilder builder,
                                      List<String> skills);
}
