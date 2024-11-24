package 王逸群.hrManagerSystem.util;

import 王逸群.hrManagerSystem.db.DepartmentDao;
import 王逸群.hrManagerSystem.db.UserDao;
import 王逸群.hrManagerSystem.entity.Employee;

import java.sql.SQLException;
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

    public static void main(String[] args) throws ClassNotFoundException {
        UserDao userDao = new UserDao();
        DepartmentDao deptDao= new DepartmentDao();
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
                    System.out.println("请输入用户名：");
                    String userName = input.next();
                    System.out.println("请输入密码：");
                    String password = input.next();
                    Employee emp=null;
                    try {
                        emp=userDao.loginByDb(userName, password);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }catch (SQLException e) {
                        e.printStackTrace();
                    }catch (InstantiationException e) {
                        e.printStackTrace();
                    }catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    if (emp==null) {
                        System.out.println("用户名或者密码不正确，请重新输入选项数字:");
                        continue;
                    }
                    if (emp.getRoleId() == 1) {
                        menu.showStaffMenu();
                    }else if (emp.getRoleId() == 2) {
                        menu.showManagerMenu();
                    }else if (emp.getRoleId() == 3) {
                        menu.showAdminMenu();
                    }
                    else  menu.showLongMenu();
                    break;

                case 2:
                    System.out.println("请输入员工编号：");
                    String empNo =input.next();
                    System.out.println("请输入员工姓名:");
                    userName=input.next();
                    System.out.println("请输入密码：");
                    password = input.next();
                    while (true) {
                        System.out.println("请输入确认密码：");
                        String repassword =input.next();
                        if (repassword.equals(password)) {
                            break;
                        }else {
                            System.out.println("两次密码输入不一致！");
                        }
                    }
                    String department= null;
                    while (true) {
                        System.out.println("请输入部门，部门只能是开发部、市场部、信息部：");
                        department=input.next();
                        if (department.equals("开发部")||
                                department.equals("市场部")||
                                department.equals("信息部")) {
                            break;
                        }
                    }
                    int departmentId=0;
                    int roleId=0;
                    try {
                        departmentId=userDao.getRoleIdByRoleName(department);
                        roleId=userDao.getRoleIdByRoleName("Staff");
                    }catch (SQLException e) {
                        e.printStackTrace();
                    }
                    System.out.println("请输入员工薪资：");
                    double salary = input.nextDouble();
                    if (userDao.registerUser(userName, password, roleId, empNo, departmentId, salary)) {
                        System.out.println("用户注册成功");
                    }else {
                        System.out.println("用户注册失败");
                    }
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
