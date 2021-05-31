package compailer;

import gui.MainFrame;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CompailerImpl implements Compailer{
    int order=0;
    MainFrame mf=MainFrame.getInstance();
    String string = "";
    //var query = new Query("Departments").OrderBy("manager_id")
    @Override
    public void compaile(String s){
        String[] array=s.split("\\)\\.");
        String star="*";
        for(int i=0;i<array.length;i++){
            if(array[i].contains("Select"))star=array[i];
        }
        select(star);
        for(int i=0;i<array.length;i++){
            if(array[i].contains("Avg")){
                avg(array[i]);
                break;
            }
        }
        for(int i=0;i<array.length;i++){
            if(array[i].contains("Count")){
                count(array[i]);
                break;
            }
        }
        for(int i=0;i<array.length;i++){
            if(array[i].contains("Max")){
                max(array[i]);
                break;
            }
        }
        for(int i=0;i<array.length;i++){
            if(array[i].contains("Min")){
                min(array[i]);
                break;
            }
        }
        for(int i=0;i<array.length;i++){
            if(array[i].contains("Query"))query(array[i]);
        }
        for(int i=0;i<array.length;i++){
            if(array[i].contains("Join"))join(array[i]);
        }
        for(int i=0;i<array.length;i++){
            if(array[i].contains("On"))on(array[i]);
        }

        for(int i=0;i<array.length;i++){
            if(array[i].contains("Where(")&&array[i].charAt(0)=='W'){
                where(array[i]);
                break;
            }
        }
        for(int i=0;i<array.length;i++){
            if(array[i].contains("WhereBetween")){
                whereBetween(array[i]);
                break;
            }
        }
        for(int i=0;i<array.length;i++){
            if(array[i].contains("WhereEndsWith")){
                whereEndsWith(array[i]);
                break;
            }
        }
        for(int i=0;i<array.length;i++){
            if(array[i].contains("WhereStartsWith")){
                whereStartsWith(array[i]);
                break;
            }
        }
        for(int i=0;i<array.length;i++){
            if(array[i].contains("WhereContains")){
                whereContains(array[i]);
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
            if(array[i].contains("OrWhere")){
                orWhere(array[i]);
                break;
            }
        }
        for(int i=0;i<array.length;i++){
            if(array[i].contains("GroupBy")){
                groupBy(array[i]);
                break;
            }
        }
        for(int i=0;i<array.length;i++){
            if(array[i].contains("Having")&&array[i].charAt(0)=='H'){
                having(array[i]);
                break;
            }
        }
        for(int i=0;i<array.length;i++){
            if(array[i].contains("AndHaving")&&array[i].charAt(0)=='H'){
                andHaving(array[i]);
                break;
            }
        }
        for(int i=0;i<array.length;i++){
            if(array[i].contains("OrHaving")){
                orHaving(array[i]);
                break;
            }
        }
        for(int i=0;i<array.length;i++){
            if(array[i].contains("OrderByDesc")){
                orderByDesc(array[i]);
                order=1;
                break;
            }
        }
        for(int i=0;i<array.length;i++){
            if(array[i].contains("OrderBy")&&(order==0)){
                orderBy(array[i]);
                break;
            }
        }
        order=0;
         mf.getAppCore().readDataFromTable(string);
        System.out.println(string);

    }
    public void andHaving(String s){
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
    public void orHaving(String s){
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
    public void having(String s){ // having("salary",">","500")
        String[] array = s.split(",");
        String[] array1=array[0].split("\"");
        string+=" HAVING ";
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
    public void max(String s){
        String[] array = s.split(",");
        String[] array2 = array[0].split("\"");
        String n ="";
        n+=" MAX(";
        n+=array2[1];
        n+=")";
        if(array.length==2){
            String[] array1 = array[1].split("\\)");
            String[] array3 = array1[0].split("\"");
            n+=" AS ";
            n += "\""+array3[1]+"\"";
            string = string.replaceAll(array3[1],n);}else{
            String m =",";
            m+=n;
            string+=m;
        }
        //  string+=array1[0];

    }
    public void min(String s){
        String[] array = s.split(",");
        String[] array2 = array[0].split("\"");
        String n ="";
        n+=" MIN(";
        n+=array2[1]; // salary
        n+=")";
        if(array.length==2){
            String[] array1 = array[1].split("\\)");
            String[] array3 = array1[0].split("\"");
            n+=" AS ";
            n += "\""+array3[1]+"\"";
            string = string.replaceAll(array3[1],n);}else{
            String m =",";
            m+=n;
            string+=m;
        }

    }
    public void count(String s){ // Avg("salary","prosecnaPlata") = avg(salary) as “prosecnaPlata”
        String[] array = s.split(",");
        String[] array2 = array[0].split("\""); // salary 1
        String n ="";
        n+=" COUNT(";
        n+=array2[1]; // salary
        n+=")";
        if(array.length==2){
            String[] array1 = array[1].split("\\)"); // prosecnaPlata 0
            String[] array3 = array1[0].split("\"");
            n+=" AS ";
            n += "\""+array3[1]+"\"";
            string = string.replaceAll(array3[1],n);}else{
            String m =",";
            m+=n;
            string+=m;
        }
        //  string+=array1[0];

    }
    public void avg(String s){ // Avg("salary","prosecnaPlata") = avg(salary) as “prosecnaPlata”
        String[] array = s.split(",");
        String[] array2 = array[0].split("\""); // salary 1
        String n ="";
        n+=" AVG(";
        n+=array2[1]; // salary
        n+=")";
        if(array.length==2){
            String[] array1 = array[1].split("\\)"); // prosecnaPlata 0
            String[] array3 = array1[0].split("\"");
        n+=" AS ";
        n += "\""+array3[1]+"\"";
        string = string.replaceAll(array3[1],n);}else{
            String m =",";
            m+=n;
            string+=m;
        }
      //  string+=array1[0];

    }
    public void groupBy(String s){
        String[] array=s.split("\"");
        string+=" GROUP BY ";
        string+=array[1];
    }
    public void join(String s){
        String[] array=s.split("\"");
        string+=" JOIN ";
        string+=array[1];
    }
    public void on(String s){
        String[] array=s.split("\""); // on("id","=","id")
        string+=" ON ";
        string+=array[1];
        string+=array[3];
        string+=array[5];
    }
    public void whereEndsWith(String s){
        String[] array=s.split(",");
        String[] array1=array[0].split("\"");
        string+=" WHERE ";
        string+=array1[1];
        string+=" LIKE '%";
        array[1]=array[1].replace(")","");
        array[1]=array[1].replace(" ","");
        string+=array[1];
        string+="'";
    }
    public void whereStartsWith(String s){
        String[] array=s.split(",");
        String[] array1=array[0].split("\"");
        string+=" WHERE ";
        string+=array1[1];
        string+=" LIKE '";
        array[1]=array[1].replace(")","");
        array[1]=array[1].replace(" ","");
        string+=array[1];
        string+="%'";
    }
    public void whereContains(String s){
        String[] array=s.split(",");
        String[] array1=array[0].split("\"");
        string+=" WHERE ";
        string+=array1[1];
        string+=" LIKE '%";
        array[1]=array[1].replace(")","");
        array[1]=array[1].replace(" ","");
        string+=array[1];
        string+="%'";
    }
    public void whereBetween(String s){
        String[] array=s.split(",");
        String[] array1 = array[0].split("\"");
        string+=" WHERE ";
        string+=array1[1];
        string+=" BETWEEN ";
        string+=array[1];
        string+=" AND ";
        string+=array[2].replace(")","");
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
    public void orderByDesc(String s){
        String[] array = s.split("\"");
        string+=" ORDER BY ";
        string+=array[1];
        string+=" DESC ";
    }
}
