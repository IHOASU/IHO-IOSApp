package com.iho.asu.Database;

/**
 * Created by Barathi on 7/4/2014.
 */
public enum Columns {
    KEY_GALLERY_ID("ImageID"),KEY_GALLERY_NAME("ImageName"),KEY_EVENT_ID("EventId"),KEY_EVENT_WHERE("EventWhere"),KEY_EVENT_WHEN("EventWhen"),KEY_EVENT_TITLE("EventTitle"),KEY_EVENT_MAP("mapLink"),KEY_EVENT_REG("EventReg"),KEY_EVENT_DESC("EventDesc"),KEY_LECTURER_ID("LectID"),KEY_LECTURER_IMAGE("Image"),KEY_LECTURER_NAME("Name"),KEY_LECTURER_BIO("Bio"),KEY_LECTURER_LINK("Link"),KEY_LECTURER_EMAIL("Email"),KEY_LECTURE_TITLE("Title"),KEY_LECTURE_IMAGE("Image"),KEY_NEWS_ID("NewsID"),KEY_NEWS_TITLE("NewsTitle"),KEY_NEWS_IMAGE("NewsImage"),KEY_NEWS_TEXT("NewsText"),KEY_NEWS_LINK("NewsLink"),KEY_SCIENCE_ID("ScienceID"),KEY_SCIENCE_LINK("ScienceLink"),KEY_SCIENCE_TITLE("ScienceTitle");
    private String name;
    private Columns(String name){
        this.name = name;
    }
    private String getColumnName(){
        return name;
    }
}
