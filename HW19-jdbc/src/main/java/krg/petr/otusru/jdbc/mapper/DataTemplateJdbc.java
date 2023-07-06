package krg.petr.otusru.jdbc.mapper;

import krg.petr.otusru.core.repository.DataTemplate;
import krg.petr.otusru.core.repository.DataTemplateException;
import krg.petr.otusru.core.repository.executor.DbExecutor;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Сохратяет объект в базу, читает объект из базы
 */
public class DataTemplateJdbc<T> implements DataTemplate<T> {

    private final DbExecutor dbExecutor;
    private final EntitySQLMetaData entitySQLMetaData;
    private final EntityClassMetaData<T> entityClassMetaData;

    public DataTemplateJdbc(DbExecutor dbExecutor, EntitySQLMetaData entitySQLMetaData, EntityClassMetaData<T> entityClassMetaData) {
        this.dbExecutor = dbExecutor;
        this.entitySQLMetaData = entitySQLMetaData;
        this.entityClassMetaData = entityClassMetaData;
    }

    private T createObjectFromResultSet(ResultSet resultSet) throws ReflectiveOperationException, SQLException {
        T object = entityClassMetaData.getConstructor().newInstance();
        for (Field field : entityClassMetaData.getAllFields()) {
            field.setAccessible(true);
            field.set(object, resultSet.getObject(field.getName(), field.getType()));
        }
        return object;
    }

    @Override
    public Optional<T> findById(Connection connection, long id) {
        return dbExecutor.executeSelect(connection, entitySQLMetaData.getSelectByIdSql(), List.of(id), resultSet -> {
            try {
                if (resultSet.next()) {
                    return createObjectFromResultSet(resultSet);
                }
                return null;
            } catch (ReflectiveOperationException | SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }


    @Override
    public List<T> findAll(Connection connection) {
        return dbExecutor.executeSelectQuery(connection, entitySQLMetaData.getSelectAllSql(), List.of(), resultSet -> {
            try {
                return createObjectFromResultSet(resultSet);
            } catch (ReflectiveOperationException | SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public long insert(Connection connection, T client) {
        List<Object> params = new ArrayList<>();
        for (Field field : entityClassMetaData.getFieldsWithoutId()) {
            field.setAccessible(true);
            try {
                params.add(field.get(client));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return -1;
            }
        }

        return dbExecutor.executeStatement(connection, entitySQLMetaData.getInsertSql(), params);
    }

    @Override
    public void update(Connection connection, T client) {
        List<Object> values = new ArrayList<>();
        for (Field field : entityClassMetaData.getFieldsWithoutId()) {
            try {
                values.add(field.get(client));
            } catch (IllegalAccessException e) {
                throw new DataTemplateException(e);
            }
        }
        try {
            values.add(entityClassMetaData.getIdField().get(client));
        } catch (IllegalAccessException e) {
            throw new DataTemplateException(e);
        }

        dbExecutor.executeStatement(connection, entitySQLMetaData.getUpdateSql(), values);
    }
}
