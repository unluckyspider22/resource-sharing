/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khanhdbd.daos;

import com.khanhbdb.utils.DBUtil;
import com.khanhdbd.dtos.AccountDTO;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

public class AccountDAO implements Serializable {

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

//    private void closeConnection() throws SQLException {
//        if (rs != null) {
//            rs.close();
//        }
//        if (ps != null) {
//            ps.close();
//        }
//        if (conn != null) {
//            conn.close();
//        }
//    }
    public AccountDTO checkLogin(String email, String password) throws SQLException, NamingException, NoSuchAlgorithmException {
        AccountDTO result = null;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "SELECT a.Name, r.Name as RoleName, "
                        + "s.Name as StatusName "
                        + "FROM Accounts a, Roles r, AccountStatus s "
                        + "WHERE Email = ? AND Password = ? "
                        + "AND a.RoleId = r.RoleId AND a.AccountStatusId = s.AccountStatusId ";
                ps = conn.prepareStatement(sql);
                ps.setString(1, email);
                ps.setString(2, password);
                rs = ps.executeQuery();
                if (rs.next()) {
                    String userName = rs.getString("Name");
                    String role = rs.getString("RoleName");
                    String status = rs.getString("Status    Name");
                    String roleName = rs.getString("RoleName");
                    result = new AccountDTO();
                    result.setEmail(email);
                    result.setName(userName);
                    result.setStatus(status);
                    result.setRoleName(roleName);
                }
            }
        } finally {
            DBUtil.closeConnection(conn, ps, rs);
        }
        return result;
    }

    public boolean checkExistEmail(String email) throws SQLException, NamingException {
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "SELECT Email FROM Accounts WHERE Email = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, email);
                rs = ps.executeQuery();
                if (rs.next()) {
                    return true;
                }
            }
        } finally {
            DBUtil.closeConnection(conn, ps, rs);
        }
        return false;
    }

    public String getAccountVerifyCode(String email) throws SQLException, NamingException {
        String result = "";
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "SELECT VerifyCode FROM Accounts WHERE Email = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, email);
                rs = ps.executeQuery();
                if (rs.next()) {
                    result = rs.getString("VerifyCode");
                }
            }
        } finally {
            DBUtil.closeConnection(conn, ps, rs);
        }
        return result;
    }

    public boolean registerAccount(AccountDTO newUser) throws NamingException, SQLException {
        boolean result = false;
        try {
            RoleDAO roleDAO = new RoleDAO();
            int roleId = roleDAO.getRoleIDByName(newUser.getRoleName());
            AccountStatusDAO accountStatusDAO = new AccountStatusDAO();
            int statusId = accountStatusDAO.getStatusIdByName(newUser.getStatus());
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO Accounts(Email, Password, Name, "
                        + "RoleId, AccountStatusId,VerifyCode,InsDate) VALUES(?,?,?,?,?,?,?)";
                ps = conn.prepareStatement(sql);
                ps.setString(1, newUser.getEmail());
                ps.setString(2, newUser.getPassword());
                ps.setString(3, newUser.getName());
                ps.setInt(4, roleId);
                ps.setInt(5, statusId);
                ps.setString(6, newUser.getVerifyCode());
                ps.setDate(7, newUser.getInsDate());
                result = (ps.executeUpdate() > 0);
            }
        } finally {
            DBUtil.closeConnection(conn, ps, rs);
        }
        return result;
    }

    public boolean activeAccount(String email, String activeStatusName) throws SQLException, NamingException {
        boolean result = false;
        AccountStatusDAO accountStatusDao = new AccountStatusDAO();
        int activeStatusId = accountStatusDao.getStatusIdByName(activeStatusName);
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "UPDATE Accounts SET StatusID = ? "
                        + "WHERE Email = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, activeStatusId);
                ps.setString(2, email);
                result = (ps.executeUpdate() > 0);
            }
        } finally {
            DBUtil.closeConnection(conn, ps, rs);
        }
        return result;
    }
}
