package database;

import resource.DBNode;
import resource.data.Row;

import java.util.List;

public interface Repository {

    DBNode getSchema();

    List<Row> get(String from);
    List<Row> orderBy(String from,String order);
    List<Row> select(String from,String colomn);
}
