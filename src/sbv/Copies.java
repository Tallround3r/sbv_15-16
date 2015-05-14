package sbv;

import java.util.ArrayList;
import java.util.Date;

public class Copies {
    // how many copies peer label 
    public static ArrayList<String> CopyCount(String BookID){ //Takes super long
        try{
            return  Query.anyQuery("SELECT sbm_books.ID, sbm_books.label, COUNT(sbm_copieshistory.ID) FROM sbm_copieshistory, sbm_books, sbm_copies WHERE sbm_copies.book_id LIKE sbm_books.ID AND sbm_copieshistory.copy_id LIKE sbm_copies.ID GROUP BY sbm_books.label");
        }catch(Exception e){System.out.println(e+ "CopyCount");}
        return null;
    }
    
    //Buch informationen abhängig vom der copyID und einem index zum durchschalten der einzelnen infos 
    public static String Singlecopy ( String copyId,int index){ 
        try{
            ArrayList<String> result = Query.anyQuery("SELECT label, sbm_copieshistory.ID, distributed, collected, bought, notice, paid FROM sbm_copieshistory, sbm_copies, sbm_books, sbm_students WHERE sbm_books.ID = sbm_copies.book_id AND sbm_copieshistory.copy_id = sbm_copies.ID  AND sbm_copieshistory.student_id = sbm_students.ID AND sbm_copieshistory.ID LIKE "+copyId);
            return result.get(index);
            }catch(Exception e){System.out.println(e + "Singlecopy");}
        return null;
    }
    
    // eine buch ausleihen (mit copy id)
    public static void distributeCopy(String copy_id){
        Date now = new Date();  	
        Long longTime = now.getTime()/1000;
        longTime.intValue();
            try {
                Query.anyUpdate("UPDATE sbm_copieshistory SET distributed = "+longTime+" ,collected = NULL  WHERE copy_id LIKE " + copy_id);                
            }catch(Exception e){System.out.println(e + "distributeCopy");}
    }
    
    //buch einsammeln (mit copy id)
    public static void collectCopy(String copy_id){        
        Date now = new Date();  	
        Long longTime = now.getTime()/1000;
        longTime.intValue();
            try {
                Query.anyUpdate("UPDATE sbm_copieshistory SET collected = "+longTime+" WHERE copy_id LIKE " + copy_id);
            }catch(Exception e){System.out.println(e + "collectCopy");}
    }
    
    public static void copyBought(String copy_id){        
        Date now = new Date();  	
        Long longTime = now.getTime()/1000;
        longTime.intValue();
            try {
                Query.anyUpdate("UPDATE sbm_copieshistory SET collected = "+longTime+" WHERE copy_id LIKE " + copy_id);
            }catch(Exception e){System.out.println(e + "collectCopy");}
    }
    
    //copy erzeugen
    public static void addCopy(String book_id){
        try{
            Query.anyQuery("INSERT INTO sbm_copies SET book_id = "+ book_id);
        }catch(Exception e){System.out.println(e + "addCopy");} 
    }
    
    //copy löschen
    public static void deleteCopy(String ID){
        try{
            Query.anyQuery("DELETE FROM sbm_copies WHERE ID = "+ ID);
        }catch(Exception e){System.out.println(e + "addCopy");} 
    }
}