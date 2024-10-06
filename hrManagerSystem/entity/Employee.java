    package 王逸群.hrManagerSystem.entity;

    public class Employee {
        private int _userID;
        private String _userName;
        private String _password;
        private int _roldID;
        private String _empNO;
        private int _departID;
        private double _salary;

        public int get_userID() {
            return _userID;
        }

        public String get_userName() {
            return _userName;
        }

        public String get_password() {
            return _password;
        }

        public int get_roldID() {
            return _roldID;
        }

        public String get_empNO() {
            return _empNO;
        }

        public int get_departID() {
            return _departID;
        }

        public double get_salary() {
            return _salary;
        }

        public void set_userID(int _userID) {
            this._userID = _userID;
        }

        public void set_userName(String _userName) {
            this._userName = _userName;
        }

        public void set_password(String _password) {
            this._password = _password;
        }

        public void set_roldID(int _roldID) {
            this._roldID = _roldID;
        }

        public void set_empNO(String _empNO) {
            this._empNO = _empNO;
        }

        public void set_departID(int _departID) {
            this._departID = _departID;
        }

        public void set_salary(double _salary) {
            this._salary = _salary;
        }
        //constructor

        public Employee(int _userID, String _userName, String _password, int _roldID, String _empNO, int _departID, double _salary) {
            this._userID = _userID;
            this._userName = _userName;
            this._password = _password;
            this._roldID = _roldID;
            this._empNO = _empNO;
            this._departID = _departID;
            this._salary = _salary;
        }

        public Employee() {
            _userID = 0;
            _userName = "";
            _password = "";
            _roldID = 0;
            _empNO = "";
            _departID = 0;
            _salary = 0;

        }
        //method
         public void  displayInfo(){
            String position =null;
            switch(_roldID){
                case 1:
                    position = "Staff";
                    break;
                case 2:
                    position = "Manager";
                    break;
                case 3:
                    position = "Admin";
                    break;
                default:
                    position = null;
                    break;
            }
            String department = null;
            switch(_departID){
                case 1:
                    department = "市场部";
                    break;
                case 2:
                    department = "开发部";
                    break;
                case 3:
                    department = "信息部";
                    break;
                default:
                    department = null;
                    break;
            }
             System.out.println("\t\t当前用户信息\n\n");
             System.out.println("****************************************************");
             System.out.println("\t\t雇员编号：" + _userID);
             System.out.println("\t\t雇员姓名：" + _userName);
             System.out.println("\t\t职位：" + position);
             System.out.println("\t\t部门：" + department);
             System.out.println("\t\t薪水：" + String.valueOf(_salary));
             System.out.println("****************************************************");
         }

         /**
         // modify password
        @param newPassword
        */
        public void modifyPassword(String newPassword){
            _password = newPassword;
            System.out.println("密码修改成功！");
        }
    }
