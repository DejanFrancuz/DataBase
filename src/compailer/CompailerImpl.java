package compailer;

import gui.MainFrame;

import java.util.HashMap;
import java.util.Map;

public class CompailerImpl implements Compailer{

    MainFrame mf=MainFrame.getInstance();
    String string = "";
    //var query = new Query("Departments").OrderBy("manager_id")
    @Override
    public void compaile(String s){
        Map<String,String[]> map = new HashMap<>();
        map.put("Query",null);
        map.put("Select",null);
        map.put("OrderBy",null);
        map.put("Join",null);
        map.put("On",null);
        map.put("Where",null);
        map.put("OrWhere",null);
        map.put("AndWhere",null);
        map.put("WhereBetween",null);
        map.put("WhereIn",null);
        map.put("ParametarList",null);
        map.put("WhereEndsWith",null);
        map.put("WhereStartsWith",null);
        map.put("WhereCounts",null);
        map.put("Avg",null);
        map.put("Count",null);
        map.put("Min",null);
        map.put("Max",null);
        map.put("GroupBy",null);
        map.put("Having",null);
        map.put("AndHaving",null);
        map.put("OrHaving",null);
        map.put("WhereInQ",null);
        map.put("WhereEqQ",null);
        String[] array=s.split("\\.");
        String star="*";
        for(int i=0;i<array.length;i++){
            if(array[i].contains("Select"))star=array[i];
        }
        select(star);
        for(int i=0;i<array.length;i++){
            if(array[i].contains("Query"))query(array[i]);
        }
        for(int i=0;i<array.length;i++){
            if(array[i].contains("Where")){
                where(array[i]);
                break;
            }
        }
        for(int i=0;i<array.length;i++){
            if(array[i].contains("OrWhere")){
                orWhere(array[i]);
                break;
            }
        }
        for(int i=0;i<array.length;i++){
            if(array[i].contains("AndWhere")){
                andWhere(array[i]);
                break;
            }
        }
        for(int i=0;i<array.length;i++){
            if(array[i].contains("OrderBy")||array[i].contains("OrderByDesc"))orderBy(array[i]);
        }
          mf.getAppCore().readDataFromTable(string);
        System.out.println(string);

    }
    public void andWhere(String s){
        String[] array = s.split(",");
        String[] array1=array[0].split("\"");
        string+=" AND ";
        //Where("department_id", ">", 50)
        string+=array1[1]; // dep id
        string+=" ";
        String[] array2 = array[1].split("\"");
        string+=array2[1]; // >
        string+=" ";
        if(array[2].contains("\"")){
            String[] array3 =array[2].split("\"");
            if(array3[1].contains("%")){
                String sima="'";
                sima+=array3[1];
                sima+="'";
                string+=sima;
            }else
                string+=array3[1];}else{
            String[] array4 =array[2].split("\\)");
            string+=array4[0]; // 50
        }
        //string.replaceAll("\\)"," ");
    }
    public void orWhere(String s){
        String[] array = s.split(",");
        String[] array1=array[0].split("\"");
        string+=" OR ";
        //Where("department_id", ">", 50)
        string+=array1[1]; // dep id
        string+=" ";
        String[] array2 = array[1].split("\"");
        string+=array2[1]; // >
        string+=" ";
        if(array[2].contains("\"")){
            String[] array3 =array[2].split("\"");
            if(array3[1].contains("%")){
                String sima="'";
                sima+=array3[1];
                sima+="'";
                string+=sima;
            }else
                string+=array3[1];}else{
            String[] array4 =array[2].split("\\)");
            string+=array4[0]; // 50
        }
        //string.replaceAll("\\)"," ");
    }
    public void where(String s){
        String[] array = s.split(",");
        String[] array1=array[0].split("\"");
        string+=" WHERE ";
         //Where("department_id", ">", 50)
        string+=array1[1]; // dep id
        string+=" ";
        String[] array2 = array[1].split("\"");
        string+=array2[1]; // >
        string+=" ";
        if(array[2].contains("\"")){
        String[] array3 =array[2].split("\"");
        if(array3[1].contains("%")){
            String sima="'";
            sima+=array3[1];
            sima+="'";
            string+=sima;
        }else
        string+=array3[1];}else{
            String[] array4 =array[2].split("\\)");
            string+=array4[0]; // 50
        }
        //string.replaceAll("\\)"," ");
    }

    public void query(String s){
        String[] array = s.split("\"");
        string +=" FROM ";
        string+=array[1];
    }
    public void orderBy(String s){
        String[] array = s.split("\"");
        string+=" ORDER BY ";
        string+=array[1];
    }
    public void select(String s){
        string="SELECT ";
        String[] array = s.split("\"");
        if(!s.equals("*")){
        string+=array[1];
        for(int i=3;i<array.length;i+=2)
            string= string+","+array[i];
        }else
            string+="*";}
}
