package database;

import resource.DBNode;
import resource.data.Row;

import java.util.List;

public interface Database{

    DBNode loadResource();

    List<Row> readDataFromTable(String tableName);

    List<Row> orderColomnsBy(String from,String order);
    List<Row> select(String from,String select);


}
