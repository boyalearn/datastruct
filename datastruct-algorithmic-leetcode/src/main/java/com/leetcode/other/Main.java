package com.leetcode.other;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);

        String context="";
        while (sc.hasNextLine()){
            context=sc.nextLine();
            break;
        }

        String[] arr=context.split("[ ]+");
        String str=arr[arr.length-1];
        System.out.println(str.length());
    }

    public static void main9(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()) {
            String[] arr = sc.nextLine().split("[ ]+");
            if(arr.length==1){
                System.out.println(Long.valueOf(arr[0]));
            }else {
                System.out.println(Long.valueOf(arr[0]) + Long.valueOf(arr[1]));
            }
        }
    }

    private static boolean isMax(String s1, String s2) {
        int len = Math.min(s1.length(), s2.length());
        for (int i = 0; i < len; i++) {
            if (s1.charAt(i) - s2.charAt(i) < 0) {
                return false;
            } else if (s1.charAt(i) - s2.charAt(i) > 0) {
                return true;
            }
        }
        return false;
    }


    public static void main8(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()) {
            String[] arr = sc.nextLine().split("[,]+");


            if (arr.length == 1) {
                System.out.println(arr[0]);
                continue;
            }

            for (int i = 0; i < arr.length; i++) {
                for (int j = arr.length - 2; j >= i; j--) {
                    if (isMax(arr[j], arr[j + 1])) {
                        String temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;
                    }
                }
            }

            for (int i=0;i<arr.length;i++) {
                if(i==arr.length-1){
                    System.out.println(arr[i]);
                }else {
                    System.out.print(arr[i] + ",");
                }
            }
        }

    }

    public static void main7(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = 0;

        while (sc.hasNextInt()) {
            n = sc.nextInt();
        }

        String[] arr = new String[]{};
        while (sc.hasNextLine()) {
            arr = sc.nextLine().split("[ ]+");
        }

        if(arr.length==1){
            System.out.println(arr[0]);
            return ;
        }

        for (int i = 0; i < n; i++) {
            for (int j = n - 2; j >= i; j--) {
                if (isMax(arr[j], arr[j + 1])) {
                    String temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

        for (String item : arr) {
            System.out.print(item + " ");
        }

    }




    public static void main6(String[] args) {
        Scanner sc = new Scanner(System.in);

        String context = "";
        while (sc.hasNextLine()) {
            context = sc.nextLine();
            String[] arr = context.split("[ ]+");

            int sum = 0;
            for (int i = 0; i <= arr.length; i++) {
                sum += Integer.valueOf(arr[i]);
            }
            System.out.println(sum);
        }
    }

    public static void main5(String[] args) {
        Scanner sc = new Scanner(System.in);

        String context = "";

        int n = 1;

        while (n > 0 && sc.hasNextLine()) {

            context = sc.nextLine();
            String[] arr = context.split("[ ]+");

            if (arr.length == 1) {
                n = Integer.valueOf(arr[0]);
                continue;
            }

            int sum = 0;
            for (int i = 1; i <= Integer.valueOf(arr[0]); i++) {
                sum += Integer.valueOf(arr[i]);
            }
            System.out.println(sum);
            n--;
        }
    }

    public static void main4(String[] args) {
        Scanner sc = new Scanner(System.in);

        String context = "";
        while (sc.hasNextLine() && !(context = sc.nextLine()).equals("0")) {

            String[] arr = context.split("[ ]+");

            int sum = 0;
            for (int i = 1; i <= Integer.valueOf(arr[0]); i++) {
                sum += Integer.valueOf(arr[i]);
            }
            System.out.println(sum);
        }
    }

    public static void main3(String[] args) {
        Scanner sc = new Scanner(System.in);

        String context = "";
        while (sc.hasNextLine() && !(context = sc.nextLine()).equals("0 0")) {

            String[] arr = context.split("[ ]+");

            System.out.println(Integer.valueOf(arr[0]) + Integer.valueOf(arr[1]));
        }
    }


    public static void main2(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String context = sc.nextLine();
            String[] arr = context.split("[ ]+");
            System.out.println(Integer.valueOf(arr[0]) + Integer.valueOf(arr[1]));
        }
    }


    public static void main1(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            int data = sc.nextInt();
            boolean result = true;
            if (data <= 1) {
                System.out.println("no");
                continue;
            } else if (data == 2) {
                System.out.println("yes");
                continue;
            }
            for (int i = 2; i < data; i++) {
                if (data % i == 0) {
                    result = false;
                    break;
                }
            }
            System.out.println(result ? "yes" : "no");
        }

    }

}
