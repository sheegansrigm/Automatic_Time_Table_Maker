import java.util.*;
public class Timetable {
    public static void main(String args[]){
        ArrayList<String> faculty=new ArrayList<>();
        ArrayList<String> courses=new ArrayList<>();
        HashMap<Integer,Integer[]> periods=new HashMap<>();
        ArrayList<String> lab=new ArrayList<>();
        ArrayList<Integer> lab_days=new ArrayList<>();
        int max_lecs_per_day=7;
        int max_days_per_week=5;
        int curr_lecs_per_day=7;
        int curr_days_per_week=5;
        int ran_day=0,ran_lec1=0,ran_lec2=0;
        HashMap<Integer,String[]> tt=new HashMap<>();
        HashMap<String,Integer> course_per_day=new HashMap<>();
        HashMap<String,Integer> course_total=new HashMap<>();
        HashMap<String,Integer> labs=new HashMap<>();
        String[] day1=new String[7];
        String[] day2=new String[7];
        String[] day3=new String[7];
        String[] day4=new String[7];
        String[] day5=new String[7];
        courses.add("DSA");
        courses.add("STATS");
        courses.add("LINEAR");
        courses.add("PEE");
        courses.add("ENG");
        courses.add("UHV");
        courses.add("LIB");
        courses.add("TECH");
        lab.add("DSA");
        lab.add("STATS");
        lab.add("PEE");
        lab.add("ENG");
        labs.put("DSA",null);
        labs.put("STATS",null);
        labs.put("PEE",null);
        labs.put("ENG",null);
        tt.put(1,day1);
        tt.put(2,day2);
        tt.put(3,day3);
        tt.put(4,day4);
        tt.put(5,day5);
        periods.put(1,new Integer[7]);
        periods.put(2,new Integer[7]);
        periods.put(3,new Integer[7]);
        periods.put(4,new Integer[7]);
        periods.put(5,new Integer[7]);
        course_per_day.put("DSA",null);
        course_per_day.put("STATS",null);
        course_per_day.put("LINEAR",null);
        course_per_day.put("PEE",null);
        course_per_day.put("ENG",null);
        course_per_day.put("UHV",null);
        course_per_day.put("LIB",null);
        course_per_day.put("TECH",null);
        course_total.put("DSA",null);
        course_total.put("STATS",null);
        course_total.put("LINEAR",null);
        course_total.put("PEE",null);
        course_total.put("ENG",null);
        course_total.put("UHV",null);
        course_total.put("LIB",null);
        course_total.put("TECH",null);
        System.out.println("!");
        //randomly assign two periods for product development lab
        ran_day=randomMaker(1, max_days_per_week);
        ran_lec1=randomMaker(1, max_lecs_per_day-1);
        ran_lec2=ran_lec1+1;
        lab_days.add(ran_day);
        System.out.println(ran_day+" "+ran_lec1+" "+ran_lec2);
        switch(ran_day){
            case 1:{
                day1[ran_lec1-1]="PDL";
                day1[ran_lec2-1]="PDL";
                tt.replace(1,day1);
                Integer[] arr=periods.get(1);
                arr[ran_lec1-1]=ran_lec1;
                arr[ran_lec2-1]=ran_lec2;
                periods.replace(1,arr);
                break;
            }   
            case 2:{
                day2[ran_lec1-1]="PDL";
                day2[ran_lec2-1]="PDL";
                tt.replace(2,day2);
                Integer[] arr=periods.get(2);
                arr[ran_lec1-1]=ran_lec1;
                arr[ran_lec2-1]=ran_lec2;
                periods.replace(2,arr);
                break;
            }
            case 3:{
                day3[ran_lec1-1]="PDL";
                day3[ran_lec2-1]="PDL";
                tt.replace(3,day3);
                Integer[] arr=periods.get(3);
                arr[ran_lec1-1]=ran_lec1;
                arr[ran_lec2-1]=ran_lec2;
                periods.replace(3,arr);
                break;
            }
            case 4:{
                day4[ran_lec1-1]="PDL";
                day4[ran_lec2-1]="PDL";
                tt.replace(4,day4);
                Integer[] arr=periods.get(4);
                arr[ran_lec1-1]=ran_lec1;
                arr[ran_lec2-1]=ran_lec2;
                periods.replace(4,arr);
                break;
            }
            case 5:{
                day5[ran_lec1-1]="PDL";
                day5[ran_lec2-1]="PDL";
                tt.replace(5,day5);
                Integer[] arr=periods.get(5);
                arr[ran_lec1-1]=ran_lec1;
                arr[ran_lec2-1]=ran_lec2;
                periods.replace(5,arr);
                break;
            }
            default:
            System.out.println("Error! Program terminated");
            System.exit(0);
        }

        //assign lab periods

        while(lab_days.size()!=5){
            ran_day=randomMaker(1, max_days_per_week);
            String temp=lab.get(randomMaker(0,lab.size()-1));
            if(lab_days.contains(ran_day)){
                continue;
            }
            lab_days.add(ran_day);
            labs.replace(temp,ran_day);
            ran_lec1=randomMaker(1, max_lecs_per_day-1);
            ran_lec2=ran_lec1+1;
            lab.remove(temp);
            switch(ran_day){
                case 1:{
                    day1[ran_lec1-1]=temp;
                    day1[ran_lec2-1]=temp;
                    tt.replace(1,day1);
                    Integer[] arr=periods.get(1);
                    arr[ran_lec1-1]=ran_lec1;
                    arr[ran_lec2-1]=ran_lec2;
                    periods.replace(1,arr);
                    break;
                }   
                case 2:{
                    day2[ran_lec1-1]=temp;
                    day2[ran_lec2-1]=temp;
                    tt.replace(2,day2);
                    Integer[] arr=periods.get(2);
                    arr[ran_lec1-1]=ran_lec1;
                    arr[ran_lec2-1]=ran_lec2;
                    periods.replace(2,arr);
                    break;
                }
                case 3:{
                    day3[ran_lec1-1]=temp;
                    day3[ran_lec2-1]=temp;
                    tt.replace(3,day3);
                    Integer[] arr=periods.get(3);
                    arr[ran_lec1-1]=ran_lec1;
                    arr[ran_lec2-1]=ran_lec2;
                    periods.replace(3,arr);
                    break;
                }
                case 4:{
                    day4[ran_lec1-1]=temp;
                    day4[ran_lec2-1]=temp;
                    tt.replace(4,day4);
                    Integer[] arr=periods.get(4);
                    arr[ran_lec1-1]=ran_lec1;
                    arr[ran_lec2-1]=ran_lec2;
                    periods.replace(4,arr);
                    break;
                }
                case 5:{
                    day5[ran_lec1-1]=temp;
                    day5[ran_lec2-1]=temp;
                    tt.replace(5,day5);
                    Integer[] arr=periods.get(5);
                    arr[ran_lec1-1]=ran_lec1;
                    arr[ran_lec2-1]=ran_lec2;
                    periods.replace(5,arr);
                    break;
                }
                default:
                System.out.println("Error! Program terminated");
                System.exit(0);
            }
            
        }

        for(int i=1;i<=5;i++){
            String[] t=tt.get(i);
            for(int j=0;j<7;j++){
                System.out.print(t[j]+"\t");
            }
            System.out.println();
        }

    }
    public static int randomMaker(int min,int max){
        Random random = new Random();
        int range = max - min + 1;
        int randomNumber = random.nextInt(range) + min;
        return randomNumber;
    }
}
