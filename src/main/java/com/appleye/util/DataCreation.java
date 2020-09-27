package com.appleye.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

/**
 * @author Appleye
 * @version 1.0
 * @date 2020/7/9
 * @time 14:16
 */
public class DataCreation {
    private static final Logger LOGGER = Logger.getLogger("DataCreation");
    static String firstNameFilePath = "D:\\Files\\姓氏.txt";
    static String endNameFilePath = "D:\\Files\\名.txt";
    static final List<String> firstName = TxtOperation.splitText(TxtOperation.readTxt(firstNameFilePath));

    static final List<String> when = Arrays.asList("");
    static final List<String> where = Arrays.asList("");
    static final List<String> doSth = Arrays.asList("");

    static final List<String> endName = TxtOperation.splitText(TxtOperation.readTxt(endNameFilePath));

    static final Integer MALE_LIMIT = 74;

    static final Random RANDOM = new Random();

    /**
     * 生成姓名+性别
     * @param singleRate 单名率
     * @return {"xxx","x"}
     */
    public static String[] getCoooolNameAndGender(double singleRate){
        StringBuilder sb = new StringBuilder();

        int no1 = RANDOM.nextInt((int)Math.round((endName.size() - 1) / (1 - singleRate)));
        int no2 = RANDOM.nextInt(endName.size() - 1);

        sb.append(firstName.get(RANDOM.nextInt(firstName.size())))
                .append(no1 >= endName.size() - 1 ? "" : endName.get(no1))
                .append(endName.get(no2));
        return new String[]{sb.toString(),no2 <= MALE_LIMIT ? "男" : "女"};
    }

    /**
     * 生成范围内随机年龄
     * @param min
     * @param max
     * @return
     */
    public static  Integer getAge(Integer min, Integer max) {
        return RANDOM.nextInt(max - min + 1) + min;
    }

    /**
     * 造数工具
     * @param filePath sql生成文件完全限定名
     * @param num 生成文件数据条数
     * @param sthFlag 是否生成event
     */
    public static void createData(String filePath, Integer num, Boolean sthFlag) {

        File file = new File(filePath);

        try {

            //覆盖不追加
            Writer writer = new FileWriter(file, false);

            for (int i = 0; i < num; i++) {
                StringBuilder sb = new StringBuilder();
                String[] coooolNameAndGender = getCoooolNameAndGender(0.33);

                sb.append("insert into test.people(age,name,gender");

                if (sthFlag) {
                    sb.append(",event");
                }

                sb.append(") values (");

                sb.append(getAge(18,35));

                sb.append(",");
                sb.append("'");
                sb.append(coooolNameAndGender[0]);
                sb.append("'");

                sb.append(",");
                sb.append("'");
                sb.append(coooolNameAndGender[1]);
                sb.append("'");

                if (sthFlag) {
                    sb.append(",");
                    sb.append("'");
                    sb.append(coooolNameAndGender[0])
                            .append(when.get(RANDOM.nextInt(38)))
                            .append("的时候")
                            .append(where.get(RANDOM.nextInt(5)))
                            .append(doSth.get(RANDOM.nextInt(10)));
                    sb.append("'");
                }

                sb.append(");\n");
                writer.write(sb.toString());

            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static Boolean insert(String filePath) {
        boolean flag = true;
        String s= TxtOperation.readTxt(filePath);
        String[] split = s.split(";");
        final Connection conn = JavaDataBaseConnector.getConnection(Constants.URL.getContext(), Constants.USERNAME.getContext(), Constants.PASSWORD.getContext());

        for (int i = 0; i < split.length; i++) {
            PreparedStatement ps = JavaDataBaseConnector.getPs(split[i], conn);

            try {
                ps.execute();
                LOGGER.info("第" + (i + 1) + "条数据插入成功");
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                flag = false;
            }
        }

        return flag;

    }


    public static String getPlace() {
        //TODO
        return null;
    }

    public static String getEvent() {
        //TODO
        return null;
    }
}
