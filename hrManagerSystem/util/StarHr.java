package 王逸群.hrManagerSystem.util;

import 王逸群.hrManagerSystem.entity.Employee;

import java.util.Arrays;
import java.util.Scanner;

public class StarHr {
//    public static void main(String[] args) {
//        Employee staff=new Employee(1,"Staff","Staff",1,"DH001",2,3000.5);
//        Employee manager=new Employee(2,"Manager","Manager",2,"DH002",2,6000.5);
//        Employee admin=new Employee(3,"Admin","Admin",3,"DH003",2,4000.5);
//        Menu menu = new Menu();
//        menu.showLongMenu();
//        boolean flag = true;
//        Scanner input = new Scanner(System.in);
//          while (flag){
//              int choice;
//              switch (choice=input.nextInt()) {
//                  case 1:
//                      System.out.println("请输入用户名:");
//                      String userName = input.next();
//                      System.out.println("请输入密码:");
//                      String password = input.next();
//                      if (userName.equals(staff.get_userName()) && password.equals(staff.get_password())) {
//                          menu.showStaffMenu();
//                      } else if (userName.equals(manager.get_userName()) && password.equals(manager.get_password())) {
//                          menu.showManagerMenu();
//                      } else if (userName.equals(admin.get_userName()) && password.equals(admin.get_password())) {
//                          menu.showAdminMenu();
//                      } else {
//                          System.out.println("用户名或密码错误，请重新输入！");
//                      }
//                      break;
//                  case 2:
//                      System.out.println("本功能将在后面实现");
//                      menu.showLongMenu();
//                      break;
//                  case 3:
//                      flag = false;
//                      System.out.println("感谢您的使用，再见！");
//                      break;
//                  default:
//                      System.out.println("输入选项有误，请重新输入！");
//              }
//              if (!flag){
//                  break;
//              }
//          }
//
//    }

    public static void main(String[] args) {
        HrHelper helper=new HrHelper();
        Data.init();
        Menu menu = new Menu();
        menu.showLongMenu();
        boolean flag = true;
        Scanner input = new Scanner(System.in);
        while (flag){
            int choice;
            try {
                choice = input.nextInt();
            } catch (Exception e) {
                System.out.println(Arrays.toString(e.getStackTrace()));
                System.out.println("输入有误，请重新输入！\n“只能输入整型数字\n请输入菜单项数字");
                input.nextLine();//清空缓冲区

                continue;


            }
            switch (choice) {
                case 1:
                    System.out.println("请输入用户名:");
                    String userName = input.next();
                    System.out.println("请输入密码:");
                    String password = input.next();
                    Employee employee=helper.login(userName,password);
                    if (employee!=null) {
                        if (employee.get_roldID()==1){
                            menu.showStaffMenu();
                        }else if (employee.get_roldID()==2){
                            menu.showManagerMenu();
                        }else if (employee.get_roldID()==3){
                            menu.showAdminMenu();
                        }
                    } else {
                        System.out.println("用户名或密码错误，请重新输入！");
                    }
                    break;
                case 2:
                    System.out.println("本功能将在后面实现");
                    menu.showLongMenu();
                    break;
                case 3:
                    flag = false;
                    System.out.println("感谢您的使用，再见！");
                    break;
                default:
                    System.out.println("输入选项有误，请重新输入！");
            }
            if (!flag){
                break;
            }
        }
    }

}
