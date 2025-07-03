import java.util.Scanner;

public class StudentGradeCalculator {
    public StudentGradeCalculator() {
        System.out.println("Marks for 7th Semester BSc CSIT");

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        String[] subjects = {
                "Advanced Java",
                "Data Mining",
                "Principle of Management",
                "Software Engineering"
        };

        float[] marks = new float[4];


        float total = 0;
        for (int i = 0; i < 4; i++) {
            System.out.print(subjects[i] + ": ");
            marks[i] = sc.nextFloat();
            if(marks[i]<=100 && marks[i]>=0) {
                total += marks[i];
            }
            else{
                marks[i] = 0;
                System.out.println("Mark is between 0 and 100, so "+subjects[i]+ "'s mark excluded");
            }
        }

        System.out.println("\n\n"+name + " Your reports are as follow");
        System.out.println("\nSubject Marks:");
        for (int i = 0; i < 4; i++) {
            System.out.printf("%-30s %6.2f%n", subjects[i], marks[i]);
        }

        System.out.println("===================================");
        System.out.println("Total  \t\t\t\t\t\t   "+ total);


        float percentage = total/subjects.length;
        System.out.println("\nPercentage: "+ percentage);

        if(percentage>=90){
            System.out.println("Grade: A+");
        }

        else if(percentage>=80){
            System.out.println("Grade: A");
        }

        else if(percentage>=70){
            System.out.println("Grade: B");
        }

        else if(percentage>=60){
            System.out.println("Grade: C");
        }

        else if(percentage>=50){
            System.out.println("Grade: D");
        }

        else {
            System.out.println("Grade: F");
        }

    }
}
