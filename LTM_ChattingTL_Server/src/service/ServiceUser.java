package service;

import dbconnection.Database_connection;
import model.Model_Message;
import model.Model_Register;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Model_Client;
import model.Model_Login;
import model.Model_User_Account;

public class ServiceUser {

    private final Connection con;

    public ServiceUser() {
        this.con = Database_connection.getInstance().getConnection();
    }

//    public Model_Message register(Model_Register data) {
//          Check user exit
//        Model_Message message = new Model_Message();
//        try {
//            PreparedStatement p = con.prepareStatement(CHECK_USER);
//            p.setString(1, data.getUserName());
//            ResultSet r = p.executeQuery();
//            if (r.first()) {
//                message.setAction(false);
//                message.setMessage("Tài khoản này đã tồn tại!");
//            } else {
//                message.setAction(true);
//            }
//            r.close();
//            p.close();
//            if (message.isAction()) {
//                //  Insert User Register
//                p = con.prepareStatement(INSERT_USER);
//                p.setString(1, data.getUserName());
//                p.setString(2, data.getPassword());
//                p.execute();
//                p.close();
//                message.setAction(true);
//                message.setMessage("Ok");
//            }
//        } catch (SQLException e) {
//            message.setAction(false);
//            message.setMessage("Lỗi hệ thống");
//        }
//        return message;
//    }
    public Model_Message register(Model_Register data) {
        //  Kiểm tra tài khoản tồn tại
        Model_Message message = new Model_Message();
        try {
            PreparedStatement p = con.prepareStatement(CHECK_USER);
            p.setString(1, data.getUserName());
            ResultSet r = p.executeQuery();
            if (r.first()) {
                message.setAction(false);
                message.setMessage("Tài khoản này đã tồn tại!");
            } else {
                message.setAction(true);
            }
            r.close();
            p.close();
            if (message.isAction()) {
                //  Thêm dl người dùng
                con.setAutoCommit(false);
                p = con.prepareStatement(INSERT_USER, PreparedStatement.RETURN_GENERATED_KEYS);
                p.setString(1, data.getUserName());
                p.setString(2, data.getPassword());
                p.execute();
                r = p.getGeneratedKeys();
                r.first();
                int userID = r.getInt(1);
                r.close();
                p.close();
                //  Tạo tk 
                p = con.prepareStatement(INSERT_USER_ACCOUNT);
                p.setInt(1, userID);
                p.setString(2, data.getUserName());
                p.execute();
                p.close();
                con.commit();
                con.setAutoCommit(true);
                message.setAction(true);
                message.setMessage("Ok");
                message.setData(new Model_User_Account(userID, data.getUserName(), "", "", true));
            }
        } catch (SQLException e) {
            message.setAction(false);
            message.setMessage("Lỗi hệ thống");
            try {
                if (con.getAutoCommit() == false) {
                    con.rollback();
                    con.setAutoCommit(true);
                }
            } catch (SQLException e1) {
            }
        }
        return message;
    }

    //liệt kê list user
    public List<Model_User_Account> getUser(int exitUser) throws SQLException {
        List<Model_User_Account> list = new ArrayList<>();
        PreparedStatement p = con.prepareStatement(SELECT_USER_ACCOUNT);
        p.setInt(1, exitUser);
        ResultSet r = p.executeQuery();
        while (r.next()) {
            int userID = r.getInt(1);
            String userName = r.getString(2);
            String gender = r.getString(3);
            String image = r.getString(4);
            list.add(new Model_User_Account(userID, userName, gender, image, checkUserStatus(userID)));
        }
        r.close();
        p.close();
        return list;
    }

    //login tài khoản
    public Model_User_Account login(Model_Login login) throws SQLException {
        Model_User_Account data = null;
        PreparedStatement p = con.prepareStatement(LOGIN);
        p.setString(1, login.getUserName());
        p.setString(2, login.getPassword());
        ResultSet r = p.executeQuery();
        if (r.first()) {
            int userID = r.getInt(1);
            String userName = r.getString(2);
            String gender = r.getString(3);
            String image = r.getString(4);
            data = new Model_User_Account(userID, userName, gender, image, true);
        }
        r.close();
        p.close();
        return data;
    }

    //kiểm tra trạng thái hoạt động của user
    private boolean checkUserStatus(int userID) {
        List<Model_Client> clients = Service.getInstance(null).getListClient();
        for (Model_Client c : clients) {
            if (c.getUser().getUserID() == userID) {
                return true;
            }
        }
        return false;
    }

    // Tìm kiếm user
//    public List<Model_User_Account> searchUsers(String keyword) throws SQLException {
//        List<Model_User_Account> list = new ArrayList<>();
//        PreparedStatement p;
//        p = con.prepareStatement("SELECT UserID, UserName, Gender, ImageString FROM user_account WHERE UserName LIKE ?");
//        p.setString(1, "%" + keyword + "%"); // Use wildcard for partial matching
//        ResultSet r = p.executeQuery();
//        while (r.next()) {
//            int userID = r.getInt("UserID");
//            String userName = r.getString("UserName");
//            String gender = r.getString("Gender");
//            String image = r.getString("ImageString");
//            list.add(new Model_User_Account(userID, userName, gender, image, true));
//        }
//        r.close();
//        p.close();
//        return list;
//    }

    // SQL
    private final String INSERT_USER = "insert into user (UserName, `Password`) values (?,?)";
    private final String CHECK_USER = "select UserID from user where UserName =? limit 1";
    private final String INSERT_USER_ACCOUNT = "insert into user_account (UserID, UserName) values (?,?)";
    private final String SELECT_USER_ACCOUNT = "select UserID, UserName, Gender, ImageString from user_account where user_account.`Status`='1' and UserID<>?";
    private final String LOGIN = "select UserID, user_account.UserName, Gender, ImageString from `user` join user_account using (UserID) where `user`.UserName=BINARY(?) and `user`.`Password`=BINARY(?) and user_account.`Status`='1'";

}
