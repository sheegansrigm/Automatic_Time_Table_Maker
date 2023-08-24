import java.util.*;
import java.sql.*;
public class SQLIntegration {
    public static ArrayList<String> courses = new ArrayList<>();
    public static HashMap<Integer, Integer[]> periods = new HashMap<>();
    public static ArrayList<String> lab = new ArrayList<>();
    public static ArrayList<Integer> lab_days = new ArrayList<>();
    public static HashMap<String, Integer> subjectRepeats = new HashMap<>();
    public static int max_lecs_per_day = 7;
    public static int max_days_per_week = 5;
    public static int ran_day = 0, ran_lec1 = 0, ran_lec2 = 0;
    public static HashMap<Integer, String[]> tt = new HashMap<>();
    public static HashMap<String, Integer> labs = new HashMap<>();
    public static String[] day1 = new String[7];
    public static String[] day2 = new String[7];
    public static String[] day3 = new String[7];
    public static String[] day4 = new String[7];
    public static String[] day5 = new String[7];
    public static void main(String args[]) {
        Scanner s=new Scanner(System.in);
        String jdbcDriver = "jdbc:mysql://localhost:3306/timetable";
        String user = "user";
        String pass = "pass123";
        try (Connection con = DriverManager.getConnection(jdbcDriver, user,pass)) {
            String tableName="COURSES";
            System.out.println("How many courses?");
            String num=s.nextLine();
            int n=Integer.parseInt(num);
            for(int i=1; i<=n; i++) {
                String sql = "INSERT INTO " + tableName + " (course,lab,frequency) VALUES (?,?,?)";
                PreparedStatement stmt = con.prepareStatement(sql);
                String course_name=s.nextLine();
                String lab_period=s.nextLine();
                int frequency=Integer.parseInt(s.nextLine());
                stmt.setInt(3,frequency);
                stmt.setString(1,course_name);
                stmt.setString(2,lab_period);
                stmt.executeUpdate();
                SQLIntegration.courses.add(course_name);
                SQLIntegration.subjectRepeats.put(course_name, frequency);
                if (lab_period.equalsIgnoreCase("yes")) {
                    SQLIntegration.labs.put(course_name,null);
                    SQLIntegration.lab.add(course_name);
                    stmt.setInt(3,frequency);
                    stmt.setString(1,course_name);
                    stmt.setString(2,lab_period);
                    stmt.executeUpdate();
                } else {
                    lab_period="no";
                    stmt.setInt(3,frequency);
                    stmt.setString(1,course_name);
                    stmt.setString(2,lab_period);
                    stmt.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        lab_allotment();
        non_lab_allotment();
        tt_maker();
        s.close();
    }
    //allotting lab lectures
    public static void lab_allotment() {
        int labsize = SQLIntegration.lab.size();
        SQLIntegration.tt.put(1, day1);
        SQLIntegration.tt.put(2, day2);
        SQLIntegration.tt.put(3, day3);
        SQLIntegration.tt.put(4, day4);
        SQLIntegration.tt.put(5, day5);
        SQLIntegration.periods.put(1, new Integer[7]);
        SQLIntegration.periods.put(2, new Integer[7]);
        SQLIntegration.periods.put(3, new Integer[7]);
        SQLIntegration.periods.put(4, new Integer[7]);
        SQLIntegration.periods.put(5, new Integer[7]);
        while (SQLIntegration.lab_days.size() != labsize) {
            ran_day = randomMaker(1, max_days_per_week);
            String temp = SQLIntegration.lab.get(randomMaker(0, lab.size() - 1));
            if (SQLIntegration.lab_days.contains(ran_day)) {
                continue;
            }
            SQLIntegration.lab_days.add(ran_day);
            SQLIntegration.labs.replace(temp, ran_day);
            ran_lec1 = randomMaker(1, max_lecs_per_day - 1);
            ran_lec2 = ran_lec1 + 1;
            SQLIntegration.lab.remove(temp);
            switch (ran_day) {
            case 1: {
                SQLIntegration.day1[ran_lec1 - 1] = temp;
                SQLIntegration.day1[ran_lec2 - 1] = temp;
                SQLIntegration.tt.replace(1, day1);
                Integer[] arr = periods.get(1);
                arr[ran_lec1 - 1] = ran_lec1;
                arr[ran_lec2 - 1] = ran_lec2;
                SQLIntegration.periods.replace(1, arr);
                break;
            }
            case 2: {
                SQLIntegration.day2[ran_lec1 - 1] = temp;
                SQLIntegration.day2[ran_lec2 - 1] = temp;
                SQLIntegration.tt.replace(2, day2);
                Integer[] arr = periods.get(2);
                arr[ran_lec1 - 1] = ran_lec1;
                arr[ran_lec2 - 1] = ran_lec2;
                SQLIntegration.periods.replace(2, arr);
                break;
            }
            case 3: {
                SQLIntegration.day3[ran_lec1 - 1] = temp;
                SQLIntegration.day3[ran_lec2 - 1] = temp;
                SQLIntegration.tt.replace(3, day3);
                Integer[] arr = periods.get(3);
                arr[ran_lec1 - 1] = ran_lec1;
                arr[ran_lec2 - 1] = ran_lec2;
                SQLIntegration.periods.replace(3, arr);
                break;
            }
            case 4: {
                SQLIntegration.day4[ran_lec1 - 1] = temp;
                SQLIntegration.day4[ran_lec2 - 1] = temp;
                SQLIntegration.tt.replace(4, day4);
                Integer[] arr = periods.get(4);
                arr[ran_lec1 - 1] = ran_lec1;
                arr[ran_lec2 - 1] = ran_lec2;
                SQLIntegration.periods.replace(4, arr);
                break;
            }
            case 5: {
                SQLIntegration.day5[ran_lec1 - 1] = temp;
                SQLIntegration.day5[ran_lec2 - 1] = temp;
                SQLIntegration.tt.replace(5, day5);
                Integer[] arr = periods.get(5);
                arr[ran_lec1 - 1] = ran_lec1;
                arr[ran_lec2 - 1] = ran_lec2;
                SQLIntegration.periods.replace(5, arr);
                break;
            }
            default:
                System.out.println("Error! Program terminated");
                System.exit(0);
            }
        }
        System.out.println("Lab allotment done");
    }
    //allotting non_lab lectures
    public static void non_lab_allotment() {
        int z=1;
        int num = 0;
        while( z <= courses.size()) {
            String ranSub = courses.get(num);
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
                } else if(i>=5) {
                    i=1;
                }
            }
            z++;
            num++;
        }
        System.out.println("Non Lab allotment done");
    }
    //creating the table and storing the timetable
    public static void tt_maker() {
        String jdbcDriver = "jdbc:mysql://localhost:3306/timetable";
        String user = "user";
        String pass = "pass123";
        try (Connection con = DriverManager.getConnection(jdbcDriver, user,pass)) {
            Statement create=con.createStatement();
            String create_cmd="CREATE TABLE CSBS_TT(day varchar(20),lec1 varchar(20),lec2 varchar(20),lec3 varchar(20),lec4 varchar(20),lec5 varchar(20),lec6 varchar(20),lec7 varchar(20))";
            create.executeUpdate(create_cmd);
            String sql = "INSERT INTO CSBS_TT (day, lec1, lec2, lec3, lec4, lec5, lec6, lec7) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            for (int k = 1; k <= 5; k++) {
                String[] t = SQLIntegration.tt.get(k);
                stmt.setString(1, "day" + k);
                for (int j = 0; j < 7; j++) {
                    stmt.setString(j + 2, t[j]);
                }
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (int k = 1; k <= 5; k++) {
            String[] t = tt.get(k);
            for (int j = 0; j < 7; j++) {
                System.out.print(t[j] + "\t");
            }
            System.out.println();
        }
    }
    public static int randomMaker(int min, int max) {
        Random random = new Random();
        int range = max - min + 1;
        int randomNumber = random.nextInt(range) + min;
        return randomNumber;
    }
}
