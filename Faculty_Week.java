package timetable;
import java.util.*;

class FacultyExtraction {
    public HashMap<Integer,String[]>  displayFacultyTimeTable(HashMap<Integer,String[]> tt,String subName) {
    	HashMap<Integer,String[]> facultyTT = new HashMap<Integer,String[]>();
    	for(int i=1;i<=5;i++) {
    		String t[] = tt.get(i);
    		String f[] = new String[7];
    		for(int j=0;j<7;j++) {
    			if(t[j].equalsIgnoreCase(subName)) {
    				System.out.print(t[j]+"\t");
    				f[j]=t[j];
    			}
    			else {
    				System.out.print("free\t");
    				f[j]="free";
    			}
    		}
    		facultyTT.put(i, f);
    		System.out.println();
    	}
		return facultyTT;
    }
}


class Course {
    String name;
    String faculty;

    public Course(String name, String faculty) {
        this.name = name;
        this.faculty = faculty;
    }
    String getFaculty() {
    	return faculty;
    }
    String getName() {
    	return name;
    }
}

public class Faculty_Week {
	public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Course> courses = new ArrayList<>();
        HashMap<Integer, Integer[]> periods = new HashMap<>();
        ArrayList<String> lab = new ArrayList<>();
        ArrayList<Integer> lab_days = new ArrayList<>();
        int max_lecs_per_day = 7;
        int max_days_per_week = 5;
        int ran_day = 0, ran_lec1 = 0, ran_lec2 = 0;
        HashMap<Integer, String[]> tt = new HashMap<>();
        HashMap<String, Integer> labs = new HashMap<>();
        String[] day1 = new String[7];
        String[] day2 = new String[7];
        String[] day3 = new String[7];
        String[] day4 = new String[7];
        String[] day5 = new String[7];

        System.out.print("Enter the number of subjects: ");
        int numOfSubjects = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < numOfSubjects; i++) {
            System.out.print("Enter subject " + (i + 1) + ": ");
            String subject = sc.nextLine();
            System.out.print("Enter Faculty Name for "+subject+" : ");
            String faculty = sc.nextLine();
            courses.add(new Course(subject,faculty));
        }

        HashMap<String, Integer> subjectRepeats = new HashMap<>();

        for (Course subject : courses) {
            System.out.print("Enter the number of times " + subject.getName() + " should repeat: ");
            int repeats = sc.nextInt();
            sc.nextLine();
            subjectRepeats.put(subject.getName(), repeats);

            System.out.print("Do " + subject.getName() + " is a lab integrated course (yes/no): ");
            String response = sc.nextLine();
            if (response.equalsIgnoreCase("yes")) {
                labs.put(subject.getName(), null);
                lab.add(subject.getName());
            }
        }

        int labsize = lab.size();
        tt.put(1, day1);
        tt.put(2, day2);
        tt.put(3, day3);
        tt.put(4, day4);
        tt.put(5, day5);
        periods.put(1, new Integer[7]);
        periods.put(2, new Integer[7]);
        periods.put(3, new Integer[7]);
        periods.put(4, new Integer[7]);
        periods.put(5, new Integer[7]);

        while (lab_days.size() != labsize) {
            ran_day = randomMaker(1, max_days_per_week);
            String temp = lab.get(randomMaker(0, lab.size() - 1));
            if (lab_days.contains(ran_day)) {
                continue;
            }
            lab_days.add(ran_day);
            labs.replace(temp, ran_day);
            ran_lec1 = randomMaker(1, max_lecs_per_day - 1);
            ran_lec2 = ran_lec1 + 1;
            lab.remove(temp);
            switch (ran_day) {
                case 1: {
                    day1[ran_lec1 - 1] = temp;
                    day1[ran_lec2 - 1] = temp;
                    tt.replace(1, day1);
                    Integer[] arr = periods.get(1);
                    arr[ran_lec1 - 1] = ran_lec1;
                    arr[ran_lec2 - 1] = ran_lec2;
                    periods.replace(1, arr);
                    break;
                }
                case 2: {
                    day2[ran_lec1 - 1] = temp;
                    day2[ran_lec2 - 1] = temp;
                    tt.replace(2, day2);
                    Integer[] arr = periods.get(2);
                    arr[ran_lec1 - 1] = ran_lec1;
                    arr[ran_lec2 - 1] = ran_lec2;
                    periods.replace(2, arr);
                    break;
                }
                case 3: {
                    day3[ran_lec1 - 1] = temp;
                    day3[ran_lec2 - 1] = temp;
                    tt.replace(3, day3);
                    Integer[] arr = periods.get(3);
                    arr[ran_lec1 - 1] = ran_lec1;
                    arr[ran_lec2 - 1] = ran_lec2;
                    periods.replace(3, arr);
                    break;
                }
                case 4: {
                    day4[ran_lec1 - 1] = temp;
                    day4[ran_lec2 - 1] = temp;
                    tt.replace(4, day4);
                    Integer[] arr = periods.get(4);
                    arr[ran_lec1 - 1] = ran_lec1;
                    arr[ran_lec2 - 1] = ran_lec2;
                    periods.replace(4, arr);
                    break;
                }
                case 5: {
                    day5[ran_lec1 - 1] = temp;
                    day5[ran_lec2 - 1] = temp;
                    tt.replace(5, day5);
                    Integer[] arr = periods.get(5);
                    arr[ran_lec1 - 1] = ran_lec1;
                    arr[ran_lec2 - 1] = ran_lec2;
                    periods.replace(5, arr);
                    break;
                }
                default:
                    System.out.println("Error! Program terminated");
                    System.exit(0);
            }
        }
        for (int k = 1; k <= 5; k++) {
            String[] t = tt.get(k);
            for (int j = 0; j < 7; j++) {
                System.out.print(t[j] + "\t");
            }
            System.out.println();
        }
        int z=1;
        int num = 0;
        while( z <= courses.size()) {
        String ranSub = (courses.get(num)).getName();
        int i =1;
        while ( subjectRepeats.get(ranSub)!=0) {
            String[] perDay = tt.get(i);
            int count = 0,counter=0;
            for (int j = 0; j < perDay.length; j++) {
                if (perDay[j] == ranSub) {
                    count++;
                }
                if(perDay[j]==null) {
                	counter++;
                }
            }
            if (count < 2 && counter!=0) {
                int ranLec;
                while (true) {
                    ranLec = randomMaker(0, max_lecs_per_day - 1);
                    if (perDay[ranLec] == null) {
                        perDay[ranLec] = ranSub;
                        int a = subjectRepeats.get(ranSub);
                        a--;
                        subjectRepeats.put(ranSub, a);
                        break;
                    }
                }
            }
            if(i<5) {
            	i++;
            }
            else if(i>=5) {
            	i=1;
            }
        }
        z++;
        num++;
        
        }
        for (int k = 1; k <= 5; k++) {
            String[] t = tt.get(k);
            for (int j = 0; j < 7; j++) {
                System.out.print(t[j] + "\t");
            }
            System.out.println();
        }    
        System.out.print("Enter the faculty name: ");
        String facName = sc.nextLine();
        FacultyExtraction fe = new FacultyExtraction();
        System.out.println(fe.displayFacultyTimeTable(tt, facName));
    }
    public static int randomMaker(int min, int max) {
    	Random random = new Random();
	    int range = max - min + 1;
	    int randomNumber = random.nextInt(range) + min;
	    return randomNumber;
    }
}

