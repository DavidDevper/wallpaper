package com.wdw.wallpaper.model.dto;

public class WallPaperReqDto {

    private Integer page;

    private Integer typeId;

    private Integer isHome;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getIsHome() {
        return isHome;
    }

    public void setIsHome(Integer isHome) {
        this.isHome = isHome;
    }
}
