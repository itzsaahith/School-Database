import java.util.*;
import java.io.*;
import java.sql.*;


public class Collector_SQL {
public static final String SERIALIZED_FILE_NAME="students.xml";

   public static void main(String args[]){
      
      
      Collector_SQL test = new Collector_SQL();
       
      ArrayList<Student> StdList = new ArrayList<Student>();
     
      test.readFromFile(StdList);
      
      ArrayList<Student> additions = new ArrayList<Student>();
      ArrayList<String> deletions = new ArrayList<String>();
      
      test.userRequest(StdList, additions, deletions); 
   }


   
public Student getStudentInfo(){
   
       Student std = new Student();
       
      Scanner scanner1 = new Scanner(System.in);
      
      System.out.println("What is the ID of the student?");
      std.rollNumber = scanner1.nextLine();

      System.out.println("What is the first name of the student?");
      std.firstName = scanner1.nextLine();
      
      System.out.println("What is the last name of the student?");
      std.lastName = scanner1.nextLine();

    return std;

   }

   
public void printToFile(String info){
   try{
      FileWriter fileWriter = new FileWriter("data.txt");
      PrintWriter printWriter = new PrintWriter(fileWriter);
      printWriter.println(info);
      printWriter.close();
      
      }
   catch(IOException e){
      System.out.println("There was an error with writing to the file");
   }
   }
   

public void readFromFile(ArrayList<Student> listname){
    
   String line = "";
   try (
         // Step 1: Allocate a database 'Connection' object
         Connection conn = DriverManager.getConnection(
               "jdbc:mysql://localhost:3306/studentinfo?useSSL=false", "root", "root");
               // MySQL: "jdbc:mysql://hostname:port/databaseName", "username", "password"
 
         // Step 2: Allocate a 'Statement' object in the Connection
         Statement stmt = conn.createStatement();
      ) {
         // Step 3: Execute a SQL SELECT query, the query result
         //  is returned in a 'ResultSet' object.
         String strSelect = "SELECT * FROM students";
         /*System.out.println("The SQL query is: " + strSelect); // Echo For debugging
         System.out.println();*/
 
         ResultSet rset = stmt.executeQuery(strSelect);
 
         // Step 4: Process the ResultSet by scrolling the cursor forward via next().
         //  For each row, retrieve the contents of the cells with getXxx(columnName).
         
         
         
         while(rset.next()) {   // Move the cursor to the next row, return false if no more row
            
            
            
            String id = rset.getString("id");
            String first = rset.getString("firstname");
            String last = rset.getString("lastname");
            
            Student temp = new Student();
            temp.rollNumber = id;
            temp.firstName = first;
            temp.lastName = last;
            
            listname.add(temp);
            
            
            
         }
         
 
      } catch(SQLException ex) {
         ex.printStackTrace();
      } 
}


public void viewInfo(ArrayList<Student> listname){
   System.out.println("***Please note that only changes that have been saved will appear here***");
   System.out.println("Do you want to print the information of every student?");
         Scanner scanner1 = new Scanner(System.in);
         String response = scanner1.nextLine();
         
         
            if ((response.equals("yes")) || (response.equals("Yes"))){
               
               Iterator<Student> iterator = listname.iterator();
               
               String printoutToFile = "";
               
               while(iterator.hasNext()){
                                    
                  Student next = iterator.next();
                  String printout; 
                  printout = next.getRollNumber() + " "+  next.getFullName();
                  System.out.println(printout);
                  printout = next.getRollNumber() + " "+  next.getFullName() + "\n";
                  
                  printoutToFile += printout;
                  
                  
               }
               
               printToFile(printoutToFile);
               
               
            }
            
            else if ((response.equals("no")) || (response.equals("No"))){
               
               
               Boolean loopContinuestwo = true;
               ArrayList<String> NamesList = new ArrayList<String>();
               while (loopContinuestwo){
                  System.out.println("Enter the id number of the student you want to print. You can add more later");
                  NamesList.add(scanner1.nextLine());
                  System.out.println("Do you want to specify more students?");
                     String response3 = scanner1.nextLine();
                     if ((response3.equals("no")) || (response3.equals("No"))){
                        loopContinuestwo = false;
                        
                        
                        
                        for(String nexty : NamesList){
                        
                           for(Student next : listname){
                           
                              if ((nexty).equals(next.getRollNumber())){

                           System.out.println(next.getRollNumber() + " "+  next.getFullName());}
               

                        
                     }

               }

                        }      
            }
      
     }

}


public ArrayList<Student> addInfo(ArrayList<Student> additions){
 Boolean loopContinuesone;
      
      Scanner scanner2 = new Scanner(System.in);
      String answerToLoopContinues;
      Boolean noOutput = true;
      Boolean loopContinuesseven = true;
      
      
          
      while(loopContinuesseven){
         
         System.out.println("Do you have student information to enter? Please enter yes or no");
            answerToLoopContinues = scanner2.nextLine();
         
         if (answerToLoopContinues.equals("yes")){
            
            
            Student temp;
            temp = getStudentInfo();
            additions.add(temp);
            int size = additions.size();
            
            noOutput = false;
            }
         
         else if (answerToLoopContinues.equals("no")){
            loopContinuesseven = false;
         }   

         
         }
         
         return additions;

}


public void deleteInfo(ArrayList<Student> listname, ArrayList<String> deletions){
   Scanner scanner3 = new Scanner(System.in);

   try{
     Boolean loopContinueseight = true;
     while (loopContinueseight){
      System.out.println("Do you have student information that you want to delete? Please enter yes or no");
      String response12 = scanner3.nextLine();
      
      if (response12.equals("yes")){
         System.out.println("Enter the id of the student you want to delete");
         
         String idnumber = scanner3.nextLine();
         for(Student next : listname){
            if (next.rollNumber.equals(idnumber)){
               
               //deletions.add((listname.get(listname.indexOf(next))).toString());no
               
               deletions.add(idnumber);
               
            }
         }
         
   
      }
      
      else if (response12.equals("no")){
      loopContinueseight = false;
      }
      
     
     }
     }
     
     catch (java.util.ConcurrentModificationException e){
     
     }

   }


public void saveInfo(ArrayList<Student> additions, ArrayList<String> deletions){

               try (
         // Step 1: Allocate a database 'Connection' object
         Connection conn = DriverManager.getConnection(
               "jdbc:mysql://localhost:3306/studentinfo?useSSL=false", "root", "root"); // MySQL
               
         // Step 2: Allocate a 'Statement' object in the Connection
         Statement stmt = conn.createStatement();
      ) {
         
          
         Iterator<String> iteratorthree = deletions.iterator();
         
         
         while(iteratorthree.hasNext()){
                               
                  String next = iteratorthree.next();
                  String sqlDelete = "delete from students where id = " + next;
                  
                 stmt.executeUpdate(sqlDelete);
                  }
     

         
         Iterator<Student> iteratorfour = additions.iterator();
         

         
         for(Student elem : additions){
                     
                  
                  
                  String id = elem.getRollNumber();
                  String first = elem.getFirstName();
                  String last = elem.getLastName();
                  
                  String sqlInsert = "insert into students " + "values ('" + id + "', '" + first + "', '" + last + "')";
                  
              // Echo for debugging
           int countInserted = stmt.executeUpdate(sqlInsert);
         //System.out.println(countInserted + " records inserted.\n");
                  }

    } catch(SQLException ex) {
         System.out.println("We got an exception");

         ex.printStackTrace();
      }

                  System.out.println("Changes were saved"); 
                  
               }




public void userRequest(ArrayList<Student> listname, ArrayList<Student> additions, ArrayList<String> deletions){
   Boolean userRequestContinues = true;
   
   while(userRequestContinues){
   System.out.println("******************************************** \n What do you want to do? \n 1 view info \n 2 add info \n 3 delete info \n S save changes \n E exit");
   Scanner scanner2 = new Scanner(System.in);
   String userPurpose = scanner2.nextLine();
   
      if (userPurpose.equals("1")){
      viewInfo(listname);  
      }
            
            
            
     if (userPurpose.equals("2")){
      additions = addInfo(additions);
     }
     
     
     
     if(userPurpose.equals("3")){
      deleteInfo(listname, deletions);}
          
     
     
     if(userPurpose.equals("S")){
      saveInfo(additions, deletions);
      listname.clear();
      readFromFile(listname);
      
     } 
     
     
   
   if(userPurpose.equals("E")){
      userRequestContinues = false;

   }   
   }
}}