package com.stockapi.training.configuration;

import com.vladmihalcea.hibernate.type.array.StringArrayType;

import org.hibernate.dialect.PostgreSQL94Dialect;

public class PostgreSqlCustom extends PostgreSQL94Dialect {
    public PostgreSqlCustom() {
        this.registerHibernateType(2003, StringArrayType.class.getName());
    }
}
