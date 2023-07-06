package krg.petr.otusru.jdbc.mapper;

import krg.petr.otusru.jdbc.mapper.annotations.ID;
import krg.petr.otusru.jdbc.mapper.exceptions.IdAnnotatedFieldNotFoundException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class EntityClassMetaDataImpl<T> implements EntityClassMetaData<T> {

    private final Class<T> clazz;
    private final String name;
    private final List<Field> allFields;
    private final List<Field> fieldsWithoutId;
    private final Field idField;
    private final Constructor<T> constructor;

    public EntityClassMetaDataImpl(Class<T> clazz) throws NoSuchMethodException {
        this.clazz = clazz;
        this.name = clazz.getSimpleName();
        this.allFields = List.of(clazz.getDeclaredFields());

        Field id = null;
        List<Field> fieldsWithoutID = new ArrayList<>();

        for (Field field : allFields) {
            if (field.isAnnotationPresent(ID.class)) {
                id = field;
            } else {
                fieldsWithoutID.add(field);
            }
        }

        if (id == null) {
            throw new IdAnnotatedFieldNotFoundException(this.clazz);
        }

        this.idField = id;
        this.fieldsWithoutId = fieldsWithoutID;
        this.constructor = clazz.getConstructor();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Constructor<T> getConstructor() {
        return constructor;
    }

    @Override
    public Field getIdField() {
        return idField;
    }

    @Override
    public List<Field> getAllFields() {
        return allFields;
    }

    @Override
    public List<Field> getFieldsWithoutId() {
        return fieldsWithoutId;
    }
}
