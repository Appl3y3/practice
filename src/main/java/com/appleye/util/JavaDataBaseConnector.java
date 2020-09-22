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

    private static Connection conn;
    private static PreparedStatement ps;
    private static ResultSet rs;

    /**
     * 连接数据库
     * @return
     */
    public static Connection getConn() {

        try {
            conn = DriverManager.getConnection(
                    Contants.URL.getContext()
                    ,Contants.USERNAME.getContext()
                    ,Contants.PASSWORD.getContext());
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

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            LOGGER.info("获取结果集失败");
            e.printStackTrace();
            return null;
        }
        return rs;
    }


    public static void closeConn(){
        boolean flag = true;

        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            flag = false;
        }

        try {
            if (rs != null) {
                rs.close();
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
