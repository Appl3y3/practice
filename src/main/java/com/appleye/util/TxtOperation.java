package com.appleye.util;

import java.io.*;
import java.util.Arrays;
import java.util.List;

/**
 * @author Appleye
 * @version 1.0
 * @date 2020/7/9
 * @time 10:08
 */
public class TxtOperation {

    /**
     * 读取文件内容
     * @param txtPath
     * @return
     */
    public static String readTxt(String txtPath) {
        File file = new File(txtPath);
        if (file.isFile()) {
            try {
                FileInputStream fileInputStream = null;

                fileInputStream = new FileInputStream(file);

                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuilder stringBuilder = new StringBuilder();

                String text = null;
                while ((text = bufferedReader.readLine()) != null) {
                    stringBuilder.append(text);
                }
                return stringBuilder.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    public static void modifyFileContent(String filePath) {


        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(filePath,"rw");
            String s = randomAccessFile.readLine();
            String replace = s.replace("丘", "是否");
            randomAccessFile.writeBytes(replace);

            System.out.println(replace);


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static List<String> splitText(String text) {
        return Arrays.asList(text.split(","));
    }

    public static List<String> splitText(String text, String regex) {
        return Arrays.asList(text.split(regex));
    }
}
