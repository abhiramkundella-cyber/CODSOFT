package codsoft;
import java.util.Scanner;

public class StudentGradeCalculator {

    public static String findGrade(int marks) {

        if (marks >= 90)
        {
            return "O";
        }

        else if (marks >= 80)
        {
            return "A+";
        }

        else if (marks >= 70)
        {
            return "A";
        }

        else if (marks >= 60)
        {
            return "B+";
        }

        else if (marks >= 50)
        {
            return "B";
        }

        else if (marks >= 40)
        {
            return "C";
        }

        else if (marks >= 35)
        {
            return "PASS";
        }

        else
        {
            return "FAIL";
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        char ch;

        System.out.println("===== STUDENT GRADE CALCULATOR =====");

        do
        {

            int m1 = 0;

            int m2 = 0;

            int m3 = 0;

            int m4 = 0;

            int m5 = 0;

            boolean flag = false;

            while (!flag)
            {

                try
                {

                    System.out.print("\nEnter JAVA Marks : ");

                    m1 = Integer.parseInt(sc.nextLine());

                    System.out.print("Enter DSA Marks : ");

                    m2 = Integer.parseInt(sc.nextLine());

                    System.out.print("Enter DBMS Marks : ");

                    m3 = Integer.parseInt(sc.nextLine());

                    System.out.print("Enter OS Marks : ");

                    m4 = Integer.parseInt(sc.nextLine());

                    System.out.print("Enter CN Marks : ");

                    m5 = Integer.parseInt(sc.nextLine());

                    if (m1 < 0 || m1 > 100 ||
                        m2 < 0 || m2 > 100 ||
                        m3 < 0 || m3 > 100 ||
                        m4 < 0 || m4 > 100 ||
                        m5 < 0 || m5 > 100)
                    {

                        System.out.println("\nMarks should be between 0 and 100");

                        continue;
                    }

                    flag = true;
                }

                catch(NumberFormatException ex)
                {

                    System.out.println("\nPlease enter numbers only");
                }

                catch(NullPointerException ex)
                {

                    System.out.println("\nNull value found");
                }

                catch(Exception ex)
                {

                    System.out.println("\nInvalid Input");
                }

                if (!flag)
                {

                    System.out.print("\nDo you want to enter marks again? (y/n) : ");

                    ch = sc.next().charAt(0);

                    sc.nextLine();

                    if (ch != 'y' && ch != 'Y')
                    {

                        System.out.println("\nThank You");

                        sc.close();

                        return;
                    }
                }
            }

            String g1 = findGrade(m1);

            String g2 = findGrade(m2);

            String g3 = findGrade(m3);

            String g4 = findGrade(m4);

            String g5 = findGrade(m5);

            int total = m1 + m2 + m3 + m4 + m5;

            double avg = total / 5.0;

            String overall;

            if (m1 < 35 || m2 < 35 || m3 < 35 ||
                m4 < 35 || m5 < 35)
            {

                overall = "FAIL";
            }

            else
            {
                overall = findGrade((int)avg);
            }

            System.out.println("\n===== RESULT =====");

            System.out.println("JAVA Grade : " + g1);

            System.out.println("DSA Grade : " + g2);

            System.out.println("DBMS Grade : " + g3);

            System.out.println("OS Grade : " + g4);

            System.out.println("CN Grade : " + g5);

            System.out.println("\nTotal Marks : " + total);

            System.out.println("Average Percentage : " + avg + "%");

            System.out.println("Overall Grade : " + overall);

            if (overall.equals("FAIL"))
            {
                System.out.println("Result : FAIL");
            }

            else
            {
                System.out.println("Result : PASS");
            }

            System.out.print("\nDo you want to calculate again? (y/n) : ");

            ch = sc.next().charAt(0);

            sc.nextLine();

        } while (ch == 'y' || ch == 'Y');

        System.out.println("\nThank You");

        sc.close();
    }
}