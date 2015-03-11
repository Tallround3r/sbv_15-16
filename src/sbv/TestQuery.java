package sbv;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestQuery {
    
    /*
    executes any SQL query 
    */
    public static ArrayList<String> anyQuery(String input) throws Exception{
        try{
            Connection con = DbConnector.getConnection(); //connect
            PreparedStatement statement = con.prepareStatement(input);//SQL Query
            ResultSet result = statement.executeQuery();        // gets results
            ArrayList<String> array = new ArrayList();          //Arraylist for Resluts
            String[] collum = TableNames(input);                //gets collum names
            int collum_nr = collum.length;                      //gets number ov collums 
            
            while (result.next()){                              //Saves results
                for (int i =0; i<collum_nr;i++){
                    array.add(result.getString(collum[i]));
                }     
            }
            return array;
        }
        catch(Exception e){System.out.println(e);}
        return null;     
    } 
    
    /*
    Input is SQL statement Return is an Array of collum Names 
    */
    public static String[] TableNames (String statement ){
        
            Pattern rawPattern = Pattern.compile("SELECT.*FROM"); // catches hole SELECT ... FROM
            Matcher rawMatcher = rawPattern.matcher(statement); 
            rawMatcher.find();

            Pattern selectPattern = Pattern.compile("SELECT ");      //prepares to remove SELCT from the sql statement
            Matcher selectMatcher = selectPattern.matcher(statement);
            selectMatcher.find();
            int selectStart = selectMatcher.start(); 
            int selectEnd = selectMatcher.end();

            Pattern fromPattern = Pattern.compile("FROM");          //prepares to remove FROM from the sql statement
            Matcher fromMatcher = fromPattern.matcher(statement);
            fromMatcher.find();
            int fromStart = fromMatcher.start();
            int fromEnd = fromMatcher.end();

            String kommaPattern = "," ;                                                  //for removing the "," of the SQL statement

            StringBuffer raw = new StringBuffer(" ");                                    // SELECT and FROM gets cut out from Statement Stringbuffer 
            String tableLong = rawMatcher.group();                                       //and splited to an array of words (the collum names)
            raw.insert(0,tableLong);
            System.out.println(raw);
            raw.delete(fromStart, fromEnd); 
            raw.delete(selectStart, selectEnd);
            tableLong = raw.toString();
            tableLong = tableLong.replaceAll(kommaPattern,"");
            String[] table = tableLong.split(" ");

        return table;
    }
    
    
    /*
    primitiv method vor SystemPrinting SQL Results
    */
  
    public static void output(ArrayList<String> result, String[] collums){
        int rows = collums.length;
        
        for (int i = 0 ; i < result.size() ; i=i+rows){
            for(int o=i; o < (rows+i); o++){
                System.out.print(result.get(o));
                System.out.print(" ");
            }
            System.out.println(" ");
        }
        
    }
}