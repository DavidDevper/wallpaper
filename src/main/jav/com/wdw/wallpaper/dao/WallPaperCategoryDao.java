package com.wdw.wallpaper.dao;

import com.wdw.wallpaper.model.HomePageBannerImage;
import com.wdw.wallpaper.model.WallPaperCategory;
import com.wdw.wallpaper.util.MysqlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class WallPaperCategoryDao {


    private static final Logger logger = LoggerFactory.getLogger(WallPaperCategoryDao.class);
    public static List<WallPaperCategory> getWallPaperCategory() {
        List<WallPaperCategory> categoryList = new ArrayList<>();
        WallPaperCategory wallPaperCategory;
        Connection conn = MysqlUtil.getConnection();
        String matchingSql = "select id,category_name  from wp_category where category_status = 1 order by id asc";
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            ResultSet rst = stmt.executeQuery(matchingSql);
            while (rst != null && rst.next()) {
                wallPaperCategory = new WallPaperCategory();
                wallPaperCategory.setCategoryId(rst.getInt("id"));
                wallPaperCategory.setCategoryName(rst.getString("category_name"));
                categoryList.add(wallPaperCategory);
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
        return categoryList;
    }
}
