package com.xcode126.kgplayer.net.vo;


import java.util.List;

/**
 * Author：sky on 2019/4/26 14:55.
 * Email：xcode126@126.com
 * Desc：
 */

public class NetListVo {

    public List<TrailersBean> trailers;

    public static class TrailersBean {
        public int id;
        public String movieName;
        public String coverImg;
        public int movieId;
        public String url;
        public String hightUrl;
        public String videoTitle;
        public int videoLength;
        public int rating;
        public String summary;
        public List<String> type;
    }
}
