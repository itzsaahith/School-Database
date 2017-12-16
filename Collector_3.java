import java.util.*;
import java.io.*;


public class Collector_3 {
      
   //Defining main method for program execution
   public static void main(String args[]){
      
      
      Collector_3 test = new Collector_3();
       
      ArrayList<Student> StdList = new ArrayList<Student>();
     
      test.readFromFile(StdList);
      
      
      test.userRequest(StdList);
      //test.getList(StdList);
      
      
    
   }
   
   
/*   
   public void getList(ArrayList<Student> listname){
      
      
      Boolean loopContinues;
      loopContinues = true;
      Scanner scanner = new Scanner(System.in);
      String answerToLoopContinues;
      Boolean noOutput = true;
      Boolean loopContinuesone = true;
      
      
          
      while(loopContinues){
         System.out.println("Do you have student information to enter? Please enter yes or no");
         answerToLoopContinues = scanner.nextLine();
         
         if (answerToLoopContinues.equals("yes")){
            
            
            Student temp;
            temp = getStudentInfo();
            listname.add(temp);
            noOutput = false;
            

         
         }
         else if (answerToLoopContinues.equals("no")){
            
            if (noOutput){
               System.out.println("You have not entered any information.");
               
            }
            else {
         //from here on, the code is to print the information
         while(loopContinuesone){
         System.out.println("Do you want to print the information of every student?");
         String response = scanner.nextLine();
         
         
            if ((response.equals("yes")) || (response.equals("Yes"))){

                 
               //System.out.println(listname.size());
               loopContinuesone = false;
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
               loopContinuesone = false;
               
               Boolean loopContinuestwo = true;
               ArrayList<String> NamesList = new ArrayList<String>();
               while (loopContinuestwo){
                  System.out.println("Enter the id number of the student you want to print. You can add more later");
                  NamesList.add(scanner.nextLine());
                  System.out.println("Do you want to specify more students?");
                     String response3 = scanner.nextLine();
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
            loopContinues = false;
            
         }
      
      }
   
   }
   }
   
   
*/   
   
   
   
   
   
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
   
   try {
      FileReader fileReader = new FileReader("data.txt");
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      
      Boolean h = true;
      while((line = bufferedReader.readLine()) != null) {
                String[] array = line.split(" ");
                
                Student tempo = new Student();
                tempo.rollNumber = array[0];
                tempo.firstName = array[1];
                tempo.lastName = array[2];
                listname.add(tempo);
                
                /*for(String w:line.split("\\s",1)){  
                  System.out.print(w);  
                  } *\  
                /*System.out.println(line);
                String array[] = line.split("\\s");
                Student tempo = new Student();
                tempo.rollNumber = array[0];
                tempo.firstName = array[1];
                tempo.lastName = array[2];
                listname.add(tempo);*/
                }
            }
   
   
   
   catch(IOException ex){
      System.out.println("Unable to open file");
     }
   catch(java.lang.ArrayIndexOutOfBoundsException a){
      
   }
   
}


public void viewInfo(ArrayList<Student> listname){
   System.out.println("Do you want to print the information of every student?");
         Scanner scanner1 = new Scanner(System.in);
         String response = scanner1.nextLine();
         
         
            if ((response.equals("yes")) || (response.equals("Yes"))){

                 
               //System.out.println(listname.size());
               
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


public void deleteInfo(ArrayList<Student> listname){
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
      deleteInfo(listname);
     }
     
     
     
     if(userPurpose.equals("3")){
     
     try{
     Boolean loopContinueseight = true;
     while (loopContinueseight){
      System.out.println("Do you have student information that you want to delete? Please enter yes or no");
      String response12 = scanner2.nextLine();
      
      if (response12.equals("yes")){
         System.out.println("Enter the id of the student you want to delete");
         
         String idnumber = scanner2.nextLine();
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
     
     if(userPurpose.equals("S")){
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
     } 
     
     
   if(userPurpose.equals("E")){
      userRequestContinues = false;

   }   
   }
}}