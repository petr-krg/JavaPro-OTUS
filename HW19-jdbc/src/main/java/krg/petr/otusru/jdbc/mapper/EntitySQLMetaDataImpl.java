package krg.petr.otusru.jdbc.mapper;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class EntitySQLMetaDataImpl implements EntitySQLMetaData{
    private final String selectAllSql;
    private final String selectByIdSql;
    private final String insertSql;
    private final String updateSql;
    private final EntityClassMetaData<?> entityClassMetaData;

    public EntitySQLMetaDataImpl(EntityClassMetaData<?> entityClassMetaData) {
        this.entityClassMetaData = entityClassMetaData;

        this.selectAllSql = String.format("SELECT * FROM %s",
                entityClassMetaData.getName().toLowerCase(Locale.ROOT));

        this.selectByIdSql = String.format("SELECT * FROM %s WHERE %s = ?",
                entityClassMetaData.getName().toLowerCase(Locale.ROOT),
                entityClassMetaData.getIdField().getName());

        StringBuilder insertFields = new StringBuilder();
        List<Field> fieldsWithoutId = entityClassMetaData.getFieldsWithoutId();
        for (int i = 0; i < fieldsWithoutId.size(); i++) {
            insertFields.append(fieldsWithoutId.get(i).getName());
            if (i < fieldsWithoutId.size() - 1) {
                insertFields.append(", ");
            }
        }
        this.insertSql = String.format("INSERT INTO %s (%s) VALUES(%s)",
                entityClassMetaData.getName().toLowerCase(Locale.ROOT),
                insertFields.toString(),
                String.join(", ", Collections.nCopies(fieldsWithoutId.size(), "?")));


        StringBuilder updateFields = new StringBuilder();
        for (int i = 0; i < fieldsWithoutId.size(); i++) {
            updateFields.append(fieldsWithoutId.get(i).getName()).append(" = ?");
            if (i < fieldsWithoutId.size() - 1) {
                updateFields.append(", ");
            }
        }
        this.updateSql = String.format("UPDATE %s SET %s WHERE %s",
                entityClassMetaData.getName().toLowerCase(Locale.ROOT),
                updateFields.toString(),
                entityClassMetaData.getIdField().getName() + " = ?");
    }

    @Override
    public String getSelectAllSql() {
        return selectAllSql;
    }

    @Override
    public String getSelectByIdSql() {
        return selectByIdSql;
    }

    @Override
    public String getInsertSql() {
        return insertSql;
    }

    @Override
    public String getUpdateSql() {
        return updateSql;
    }
}
