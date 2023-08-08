package com.wdw.wallpaper.controller;

import com.wdw.wallpaper.model.ResponseModel;
import com.wdw.wallpaper.model.WallPaperCategoryDetail;
import com.wdw.wallpaper.model.dto.WallPaperReqDto;
import com.wdw.wallpaper.service.WallPaperCategoryListService;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping(value = "/wp")
public class WallPaperListController {

    private static final Logger logger = LoggerFactory.getLogger(WallPaperListController.class);

    @Autowired
    private WallPaperCategoryListService wallPaperListService;

    @RequestMapping(value = "/wallpaper/images")
    @ResponseBody
    public ResponseModel bannerList(@RequestBody WallPaperReqDto dto) throws Exception {

        ResponseModel rtnObj =  new ResponseModel(HttpStatus.SC_MOVED_TEMPORARILY, "success ");
        try {
           List<WallPaperCategoryDetail> imageList =  wallPaperListService.getWallPaperTypeDetailImages(dto);
           if(imageList != null && imageList.size() > 0){
                rtnObj.setData(imageList);
           }
        } catch (Exception e) {
            logger.error("trackingerror:", e);
            return new ResponseModel(HttpStatus.SC_INTERNAL_SERVER_ERROR, "server error ");
        }
        return rtnObj;
    }

}
