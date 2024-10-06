package 王逸群.hrManagerSystem.util;

import java.util.Scanner;

public class Menu {
    private Scanner input = new Scanner(System.in);
    private int _choice;

    // 角色值1：普通员工，2：部门经理，3：系统管理员
    //部门值1.市场部，2。开发部，3.信息部
    public void showLongMenu() {
        System.out.println("*******************************");
        System.out.println("\t\t\t1.登入系统");
        System.out.println("\t\t\t2.注册用户");
        System.out.println("\t\t\t3.退出系统");
        System.out.println("*******************************");
        System.out.print("请输入您的选择：");

    }

    private void returnMenu(Scanner input) {
        boolean flag = true;
        /*do {*/
            String num = input.next();
          /*  if(num.toUpperCase().equals("R")){
                flag = false;
                showLongMenu();
            }else{
                System.out.println("具体功能正在开发中，敬请期待！\n\t\t\t\t\t\t输入R/r返回上一级菜单");
            }*/
            if (num.equalsIgnoreCase("R")) {
                flag = false;
                showLongMenu();
            } else if (num.equals("1")) {
                showLongMenu();
                flag = false;
            } else if (num.equals("2")) {
                System.out.println("具体功能正在开发中，敬请期待！\n\t\t\t\t\t\t输入R/r返回上一级菜单");
                flag = false;
            } else if (num.equals("3")) {
                System.out.println("感谢您的使用，再见！");
                System.exit(0);
            } else {
                System.out.println("输入选项有误，请重新输入！");
            }
          /*  switch (num.toUpperCase()) {
                case "R":
                    flag = false;
                    showLongMenu();
                    break;
                case "1":
                    showLongMenu();
                    flag = false;
                    break;
                case "2":
                    System.out.println("具体功能正在开发中，敬请期待！\n\t\t\t\t\t\t输入R/r返回上一级菜单");
                    flag = false;
                    break;
                case "3":
                    System.out.println("感谢您的使用，再见！");
                    System.exit(0);
                    break;
                default:
                    System.out.println("输入选项有误，请重新输入！");
                    break;
            }
        } while (flag);*/

    }

    public void showStaffMenu() {
        System.out.println("人力资源管理系统=>普通员工");
        System.out.println("*******************************");
        System.out.println("\t\t\t1.汇报工作\n");
        System.out.println("\t\t\t2.查看个人信息\n");
        System.out.println("\t\t\t3.修改密码\n");
        System.out.println("\t\t\t4.查看评测\n");
        System.out.println("\t\t\t5.查看工资范畴\n");
        System.out.println("\t\t\t6.今日工作\n");
        System.out.println("\t\t\t7.在线工作交流\n");
        System.out.println("*******************************");
/*        System.out.print("请输入菜单项数字或者输入R/r返回上一级菜单：");
        returnMenu(input);*/
        System.out.print("请输入菜单项数字或者输入R/r返回上一级菜单：");
        boolean flag = true;
        do {
            String num = input.next();
            switch (num) {
                case "1":
                    //汇报工作代码
                    Data.staff.addReport();
                    //重新显示普通员工菜单
                    showStaffMenu();
                    flag = false;
                    break;

                case "2":
                    //查看个人信息代码
                    Data.staff.displayInfo();
                    //重新显示普通员工菜单
                    showStaffMenu();
                    flag = false;
                    break;
                case "3":
                    //修改密码代码
                    String newPassword = input.next();
                    Data.staff.modifyPassword(newPassword);
                    //重新显示普通员工菜单
                    showStaffMenu();
                    flag = false;
                    break;
                case "4":
                    //TODO 查看评测系统
                    break;
                case "5":
                    //TODO 查看工资范畴
                    break;
                case "6":
                    //TODO 查看今日工作
                    break;
                case "7":
                    //TODO 在线工作交流
                    break;
                case "r":
                case "R":
                    flag = false;
                    showLongMenu();
                    break;
                default:
                    System.out.println("输入选项有误，请重新输入！");
                    // break;
            }

        } while (flag);
       // returnMenu(input);
    }

    public void showManagerMenu() {
        System.out.println("人力资源管理系统=>部门经理");
        System.out.println("*******************************");
        System.out.println("\t\t\t1.查看汇报工作\n");
        System.out.println("\t\t\t2.考评员工\n");
        System.out.println("\t\t\t3.查看个人信息\n");
        System.out.println("\t\t\t4.修改密码\n");
        System.out.println("\t\t\t5.查看工资范畴\n");
        System.out.println("\t\t\t6.今日工作\n");
        System.out.println("\t\t\t7.在线工作交流\n");
        System.out.println("*******************************");
        System.out.print("请输入菜单项数字或者输入R/r返回上一级菜单：");

        boolean flag = true;
        do {
            String num = input.next();
            switch (num) {
                case "1":
                    Data.manager.displayReports();
                    showManagerMenu();
                    flag = false;
                    break;
                case "2":
                    //TODO 考评员工
                    break;
                case "3":
                    //查看个人信息
                    Data.manager.displayInfo();
                    showManagerMenu();
                    flag = false;
                    break;
                case "4":
                    //修改密码
                    System.out.println("请输入新密码：");
                    String newPassword = input.next();
                    Data.manager.modifyPassword(newPassword);
                    showManagerMenu();
                    flag = false;
                    break;
                case "5":
                    //TODO 查看工资范畴
                    break;
                case "6":
                    //TODO 查看今日工作
                    break;
                case "7":
                    //TODO 在线工作交流
                    break;
                case "r":
                case "R":
                    flag = false;
                    showLongMenu();
                    break;
                default:
                    System.out.println("输入选项有误，请重新输入！");
                    // break;

            }
        }while (flag);
        returnMenu(input);
    }

    public void showAdminMenu() {
        System.out.println("人力资源管理系统=>系统管理员");
        System.out.println("*******************************");
        System.out.println("\t\t\t1.查看员工信息\n");
        System.out.println("\t\t\t2.修改员工信息\n");
        System.out.println("\t\t\t3.查看个人信息\n");
        System.out.println("\t\t\t4.修改密码\n");
        System.out.println("\t\t\t5.工资范畴\n");
        System.out.println("\t\t\t6.今日工作\n");
        //System.out.println("\t\t\t7.在线工作交流\n");
        System.out.println("*******************************");
        System.out.print("请输入菜单项数字或者输入R/r返回上一级菜单：");
        boolean flag = true;
        do {
            String num = input.next();
            switch (num) {
                case "1":
                    //查看员工信息
                    showAdminMenu();
                    flag = false;
                    break;
                case "2":
                    //修改员工角色
                    System.out.println("请输入员工编号：");
                    String empNO = input.next();
                    System.out.println("请输入员工角色,角色只能是Staff,Manager,Admin中的一个：");
                    String role = input.next();
                    Data.admin.modifyEmployeeRole(empNO, role);
                    showAdminMenu();
                    flag = false;
                    break;
                case "3":
                    //查看个人信息
                    Data.admin.displayInfo();
                    showAdminMenu();
                    flag = false;
                    break;
                case "4":
                    //修改密码
                    System.out.println("请输入新密码：");
                    String newPassword = input.next();
                    Data.admin.modifyPassword(newPassword);
                    showAdminMenu();
                    flag = false;
                    break;
                case "5":
                    //TODO 工资范畴
                    break;
                case "6":
                    //TODO 今日工作
                    break;
                case "7":
                    //TODO 在线工作交流
                    break;
                case "r":
                case "R":
                    flag = false;
                    showLongMenu();
                    break;
                default:
                    System.out.println("输入选项有误，请重新输入！");
                    // break;
            }
        } while (flag);
        returnMenu(input);
    }


}
