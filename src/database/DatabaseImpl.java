package database;

import lombok.AllArgsConstructor;
import lombok.Data;
import resource.DBNode;
import resource.data.Row;

import java.util.List;

@Data
@AllArgsConstructor
public class DatabaseImpl implements Database{
    private Repository repository;


    @Override
    public DBNode loadResource() {
        return repository.getSchema();
    }

    @Override
    public List<Row> readDataFromTable(String tableName) {
        return repository.get(tableName);
    }

    @Override
    public List<Row> orderColomnsBy(String from, String order) {
        return repository.orderBy(from,order);
    }
    @Override
    public List<Row> select(String from, String select) {
        return repository.select(from,select);
    }
}
