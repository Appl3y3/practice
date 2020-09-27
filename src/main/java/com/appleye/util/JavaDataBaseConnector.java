package com.appleye.util;

import java.sql.*;
import java.util.logging.Logger;

/**
 * @author Appleye
 * @version 1.0
 * @date 2020/7/9
 * @time 13:45
 */
public class JavaDataBaseConnector {
    public static final Logger LOGGER = Logger.getLogger("JavaDataBaseConnector");

    /**
     * 连接数据库
     * @return
     */
    public static Connection getConnection(String url, String username, String password) {
        Connection conn;
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            LOGGER.info("连接数据库失败");
            e.printStackTrace();
            return null;
        }
        return conn;
    }

    /**
     * 生成ps
     * @param sql
     * @param conn
     * @return
     */
    public static PreparedStatement getPs(String sql, Connection conn) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
        } catch (SQLException e) {
            LOGGER.info("生成ps失败");
            e.printStackTrace();
            return null;
        }
        return ps;
    }

    /**
     * 获取结果集
     * @param ps
     * @return
     */
    public static ResultSet getRs(PreparedStatement ps) {
        ResultSet rs = null;
        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            LOGGER.info("获取结果集失败");
            e.printStackTrace();
            return null;
        }
        return rs;
    }

    /**
     * Not recommend
     */
    @Deprecated()
    public static void closeConn(Connection conn, PreparedStatement ps, ResultSet rs){

        boolean flag = true;
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            flag = false;
        }

        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            flag = false;
        }

        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            flag = false;
        }

        if (flag) {
            LOGGER.info("成功关闭数据库连接!");
        }
    }


}
