/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sbv;

import java.util.ArrayList;

/**
 *
 * @author Mack
 */
public class Students {
    
    //TODO edit SQL statements
    public static String StudentsList( String StudentId, int index) { 
        ArrayList<String> result = Query.anyQuery("SELECT ID, forename, surename, birth, name FROM sbm_students, sbm_classes, sbm_students-classes WHERE " +StudentId);
        return result.get(index);    
    }
    
    
    public static String SingelStudent( String StudentId, int index) { 
        ArrayList<String> result = Query.anyQuery("SELECT ID, forename, surename, birth, FROM sbm_students WHERE ID LIKE " + StudentId); 
        return result.get(index);     
    }
    
}