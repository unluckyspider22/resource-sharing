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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

public class AccountDAO implements Serializable{
      private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    private void closeConnection() throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (ps != null) {
            ps.close();
        }
        if (conn != null) {
            conn.close();
        }
    }
    public AccountDTO checkLogin(String email, String password) throws SQLException, NamingException, NoSuchAlgorithmException {
        AccountDTO result = null;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "SELECT a.Name, r.Description as RoleDescription, "
                        + "s.Description as StatusDescription "
                        + "FROM Accounts a, Roles r, AccountStatus s "
                        + "WHERE Email = ? AND Password = ? "
                        + "AND a.RoleId = r.RoleId AND a.AccountStatusId = s.AccountStatusId ";
                ps = conn.prepareStatement(sql);
                ps.setString(1, email);
                ps.setString(2, password);
                rs = ps.executeQuery();
                if (rs.next()) {
                    String userName = rs.getString("Name");
                    String role = rs.getString("RoleDescription");
                    String status = rs.getString("StatusDescription");
                    result = new AccountDTO();
                    result.setEmail(email);
                    result.setName(userName);
                }
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    
}
