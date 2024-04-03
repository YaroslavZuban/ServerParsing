package com.example.serverparsing.job;

import com.example.serverparsing.entity.PersonalData;
import com.example.serverparsing.entity.Specification;
import jakarta.persistence.criteria.*;

import java.util.Collection;

public class FieldPredicate {
    public static <T, U, E> Predicate getFieldPredicate(Root<?> root, CriteriaBuilder builder,
                                                        String objectName, String field,
                                                        T findField,
                                                        Class<E> typeClass2, Class<U> typeClass1) {
        if (findField == null) {
            return null;
        }

        Join<E, U> tableJoin = root.join(objectName, JoinType.INNER);

        return builder.equal(tableJoin.get(field), findField);
    }
}
