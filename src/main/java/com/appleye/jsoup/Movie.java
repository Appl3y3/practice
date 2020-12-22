package com.appleye.jsoup;

import lombok.Data;

/**
 * @author Appleye
 * @created 2020-12-22 22:51
 */
@Data
public class Movie {

    public static final String mainURL = "https://www.ac55.xyz/";

    private String movieID;
    private String title;
    private String publishDate;
    private String magnet;
    private String[] pictures;


}
