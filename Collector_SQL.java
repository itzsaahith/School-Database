import java.util.*;
import java.io.*;
import java.beans.XMLEncoder;
import java.beans.XMLDecoder;
import java.sql.*;


public class Collector_SQL {
public static final String SERIALIZED_FILE_NAME="students.xml";

   public static void main(String args[]){
      
      
      Collector_SQL test = new Collector_SQL();
       
      ArrayList<Student> StdList = new ArrayList<Student>();
     
      test.readFromFile(StdList);
      
      
      test.userRequest(StdList); 
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
         System.out.println("The records selected are:");
         
         
         
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
   
   
   /*/*XMLDecoder decoder=null;
		try {
			decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(SERIALIZED_FILE_NAME)));
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: File dvd.xml not found");
		}

		ArrayList<Student> listfromfile=(ArrayList<Student>)decoder.readObject();
		System.out.println(listfromfile);
      listname = listfromfile;*/
   
   
}


public void viewInfo(ArrayList<Student> listname){
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


public void addInfo(ArrayList<Student> listname){
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
            listname.add(temp);
            noOutput = false;
            }
         
         else if (answerToLoopContinues.equals("no")){
            loopContinuesseven = false;
         }   

         
         }

}


public void deleteInfo(ArrayList<Student> listname){
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
               listname.remove(listname.indexOf(next));
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


public void saveInfo(ArrayList<Student> listname){
   Iterator<Student> iteratorthree = listname.iterator();
               
               String printoutToFile = "";
               
               while(iteratorthree.hasNext()){
                                    
                  Student next = iteratorthree.next();
                  String printout; 
                  printout = next.getRollNumber() + " "+  next.getFullName();
                  
                  printout = next.getRollNumber() + " "+  next.getFullName() + "\n";
                  
                  printoutToFile += printout;
                  
                  
               }
               
               printToFile(printoutToFile);
               System.out.println("Changes were saved");
               
               
               
               /*XMLEncoder encoder=null;
		try{
		encoder=new XMLEncoder(new BufferedOutputStream(new FileOutputStream(SERIALIZED_FILE_NAME)));
		}catch(FileNotFoundException fileNotFound){
			System.out.println("ERROR: While Creating or Opening the File dvd.xml");
		}
		encoder.writeObject(listname);
		encoder.close();*/
}


public void userRequest(ArrayList<Student> listname){
   Boolean userRequestContinues = true;
   
   while(userRequestContinues){
   System.out.println("******************************************** \n What do you want to do? \n 1 view info \n 2 add info \n 3 delete info \n S save changes \n E exit");
   Scanner scanner2 = new Scanner(System.in);
   String userPurpose = scanner2.nextLine();
   
   if (userPurpose.equals("1")){
      viewInfo(listname);  
      }
            
            
            
     if (userPurpose.equals("2")){
      addInfo(listname);
     }
     
     
     
     if(userPurpose.equals("3")){
      deleteInfo(listname);}
          
     
     
     if(userPurpose.equals("S")){
      saveInfo(listname);
     } 
     
     
   
   if(userPurpose.equals("E")){
      userRequestContinues = false;

   }   
   }
}}