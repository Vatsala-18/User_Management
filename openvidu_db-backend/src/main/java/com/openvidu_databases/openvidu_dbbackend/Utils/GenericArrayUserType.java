package com.openvidu_databases.openvidu_dbbackend.Utils;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.sql.*;
import java.util.Objects;

@Component
public class GenericArrayUserType implements UserType {
    private static final int[] SQL_TYPES = {Types.ARRAY };

    private final Class<?> clazz;


    public GenericArrayUserType(Class<?> clazz) {
        this.clazz = clazz;
    }

    @Override
    public int[] sqlTypes() {
        return SQL_TYPES;
    }

    @Override
    public Class<?> returnedClass() {
        return clazz;
    }

    @Override
    public boolean equals(Object o, Object o1) {
        return Objects.equals(o, o1);
    }

    @Override
    public int hashCode(Object o) {
        return Objects.hashCode(o);
    }

    @Override
    public Object nullSafeGet(ResultSet resultSet, String[] strings, SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws SQLException {
        if (resultSet.wasNull()) {
            return null;
        }

        Array array = resultSet.getArray(strings[0]);
        if (array == null) {
            return null;
        }

        Object[] javaArray = (Object[]) array.getArray();
        Object[] newArray = (Object[]) java.lang.reflect.Array.newInstance(clazz.getComponentType(), javaArray.length);

        System.arraycopy(javaArray, 0, newArray, 0, javaArray.length);
        return newArray;
    }

    @Override
    public void nullSafeSet(PreparedStatement preparedStatement, Object o, int i, SharedSessionContractImplementor sharedSessionContractImplementor) throws SQLException {
        if (o == null) {
            preparedStatement.setNull(i, SQL_TYPES[0]);
        } else {
            Object[] castObject = (Object[]) o;
            Array array = sharedSessionContractImplementor.connection().createArrayOf("integer", castObject);
            preparedStatement.setArray(i, array);
        }
    }

    @Override
    public Object deepCopy(Object o) {
        if (o == null) {
            return null;
        }

        Object[] castObject = (Object[]) o;
        Object[] newArray = (Object[]) java.lang.reflect.Array.newInstance(clazz.getComponentType(), castObject.length);
        System.arraycopy(castObject, 0, newArray, 0, castObject.length);
        return newArray;
    }

    @Override
    public boolean isMutable() {
        return true;
    }

    @Override
    public Serializable disassemble(Object o) {
        return (Serializable) o;
    }

    @Override
    public Object assemble(Serializable serializable, Object o) {
        return serializable;
    }

    @Override
    public Object replace(Object o, Object o1, Object o2) {
        return o;
    }

}
