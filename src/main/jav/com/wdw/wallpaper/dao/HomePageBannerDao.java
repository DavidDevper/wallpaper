package com.wdw.wallpaper.dao;

import com.wdw.wallpaper.model.HomePageBannerImage;
import com.wdw.wallpaper.util.MysqlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HomePageBannerDao {

    private static final Logger logger = LoggerFactory.getLogger(HomePageBannerDao.class);
    public static List<HomePageBannerImage> getHomePageImages() {
        List<HomePageBannerImage> bannerImageList = new ArrayList<>();
        HomePageBannerImage bannerImage;
        Connection conn = MysqlUtil.getConnection();
        String matchingSql = "select id,category_id,image_w,image_h,image_path from wp_homepage_banner_image wherestatus = 1 order by id asc  limit 5";
        Statement stmt = null;
        try {
            logger.info("Cache Offer Sql:"+matchingSql);
            stmt = conn.createStatement();
            ResultSet rst = stmt.executeQuery(matchingSql);
            while (rst != null && rst.next()) {
                bannerImage = new HomePageBannerImage();
                bannerImage.setId(rst.getInt("id"));
                bannerImage.setCategoryId(rst.getInt("category_id"));
                bannerImage.setImageW(rst.getInt("image_w"));
                bannerImage.setImageH(rst.getInt("image_h"));
                bannerImage.setImagePath(rst.getString("image_path"));
                bannerImageList.add(bannerImage);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("SQL exception, {}", e.getMessage());
        } finally {
            if (stmt != null) {
                try {
                    if (!stmt.isClosed())
                        stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                stmt = null;
            }
            if (conn != null) {
                try {
                    if (!conn.isClosed())
                        conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                conn = null;
            }
        }
        return bannerImageList;
    }
}
