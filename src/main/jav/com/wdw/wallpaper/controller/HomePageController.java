package com.wdw.wallpaper.controller;

import com.wdw.wallpaper.model.HomePageBannerImage;
import com.wdw.wallpaper.model.ResponseModel;
import com.wdw.wallpaper.model.WallPaperCategory;
import com.wdw.wallpaper.model.WallPaperCategoryDetail;
import com.wdw.wallpaper.service.HomePageService;
import com.wdw.wallpaper.service.WallPaperCategoryService;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value = "/wp")
public class HomePageController {

    private static final Logger logger = LoggerFactory.getLogger(HomePageController.class);

    @Autowired
    private HomePageService homePageService;
    @Autowired
    private WallPaperCategoryService wallPaperCategoryService;

    @RequestMapping(value = "/homepage/mixdata")
    @ResponseBody
    public ResponseModel bannerList(HttpServletRequest request, HttpServletResponse response) throws Exception {

        ResponseModel rtnObj =  new ResponseModel(HttpStatus.SC_MOVED_TEMPORARILY, "success ");
        try {
            Map<String,Object> dataMap = new HashMap<>();
            //轮播图
           List<HomePageBannerImage> imageList =  homePageService.getHomePageBannerImages();
           if(imageList != null && imageList.size() > 0){
               dataMap.put("banner",imageList);
           }
            //壁纸类型
            List<WallPaperCategory> categoryList = wallPaperCategoryService.getWallPaperCategory();
            if(categoryList != null && categoryList.size() > 0){
                dataMap.put("category",categoryList);
            }
            rtnObj.setData(dataMap);
        } catch (Exception e) {
            logger.error("trackingerror:", e);
            return new ResponseModel(HttpStatus.SC_INTERNAL_SERVER_ERROR, "server error ");
        }
        return rtnObj;
    }

}
