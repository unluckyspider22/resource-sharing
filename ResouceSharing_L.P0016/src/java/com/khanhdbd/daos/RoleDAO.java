/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khanhdbd.daos;

import com.khanhbdb.utils.DBUtil;
import com.khanhdbd.dtos.RoleDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

public class RoleDAO implements Serializable {

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public List<RoleDTO> getRole() throws SQLException, NamingException {
        List<RoleDTO> result = null;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "SELECT RoleId,Name FROM Roles";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                result = new ArrayList();
                while (rs.next()) {
                    int roleId = rs.getInt("RoleId");
                    String name = rs.getString("Name");
                    RoleDTO roleDto = new RoleDTO(roleId, name);
                    result.add(roleDto);
                }
            }
        } finally {
            DBUtil.closeConnection(conn, ps, rs);
        }
        return result;
    }
}
