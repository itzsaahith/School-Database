public class Student{

   //defining variables for constructor   
   public String rollNumber;
   public String firstName;
   public String lastName;
   
   //defining constructor
   
   /*public Student(String roll, String first, String last) {

      rollNumber = roll;
      firstName = first;
      lastName = last;
   }*/
   
   public Student() {

      rollNumber = "empty";
      firstName = "empty";
      lastName = "empty";
   }
   
   
   //defining method to get first name
   public String getFirstName(){
      return firstName;
   }
   
   //defining method to get last name
   public String getLastName(){
      return lastName;
   }
   
   //defining method to get full name
   public String getFullName(){
      return firstName + " " + lastName;
   }
   
   //defining method to get roll number
   public String getRollNumber(){
      return rollNumber;
   }
   
}