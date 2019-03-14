package com.yaleyoo.blog.domain;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import javax.validation.constraints.Size;
import java.time.LocalDate;
/**
 * Created by steve on 13/3/19.
 */
@Document(collection = "blog")
public class Blog {
    @Id
    private String id;
    @Size(max = 100)
    private String blogName;
	private LocalDate createDate;
	@Transient
	private int createYear;
    @Transient
	private int createMonth;
    @Transient
	private int createDay;
	private String blogContent;
	private boolean blogHP;
	@Size(max = 100)
	private String blogKeyword;
	private LocalDate lastUpdateDate;
    @Size(max = 300)
	private String blogDescription;
    @Size(max = 20)
	private String type;
	private boolean isPrivate;

	public Blog(){

    }

    /**
     * Full parameters constructor, used for generating JSON.
     * @param id
     * @param blogName
     * @param createDate
     * @param createYear
     * @param createMonth
     * @param createDay
     * @param blogContent
     * @param blogHP
     * @param blogKeyword
     * @param lastUpdateDate
     * @param blogDescription
     * @param type
     */
    public Blog(String id, String blogName, LocalDate createDate, int createYear, int createMonth, int createDay, String blogContent, boolean blogHP, String blogKeyword, LocalDate lastUpdateDate, String blogDescription, String type, boolean isPrivate) {
        this.id = id;
        this.blogName = blogName;
        this.createDate = createDate;
        this.createYear = createYear;
        this.createMonth = createMonth;
        this.createDay = createDay;
        this.blogContent = blogContent;
        this.blogHP = blogHP;
        this.blogKeyword = blogKeyword;
        this.lastUpdateDate = lastUpdateDate;
        this.blogDescription = blogDescription;
        this.type = type;
        this.isPrivate = isPrivate;
    }

    /**
     * Constructor with id, used for update blog or response
     * @param id
     * @param blogName
     * @param createDate
     * @param blogContent
     * @param blogHP
     * @param blogKeyword
     * @param lastUpdateDate
     * @param blogDescription
     * @param type
     */
    public Blog(String id, String blogName, LocalDate createDate, String blogContent, boolean blogHP, String blogKeyword, LocalDate lastUpdateDate, String blogDescription, String type, boolean isPrivate) {
        this.id = id;
        this.blogName = blogName;
        this.createDate = createDate;
        this.createYear = createDate.getYear();
        this.createMonth = createDate.getMonthValue();
        this.createDay = createDate.getDayOfMonth();
        this.blogContent = blogContent;
        this.blogHP = blogHP;
        this.blogKeyword = blogKeyword;
        this.lastUpdateDate = lastUpdateDate;
        this.blogDescription = blogDescription;
        this.type = type;
        this.isPrivate = isPrivate;
    }

    /**
     * Constructor without id, used for insert blog.
     * @param blogName
     * @param createDate
     * @param blogContent
     * @param blogHP
     * @param blogKeyword
     * @param lastUpdateDate
     * @param blogDescription
     * @param type
     */
    public Blog(String blogName, LocalDate createDate, String blogContent, boolean blogHP, String blogKeyword, LocalDate lastUpdateDate, String blogDescription, String type,boolean isPrivate) {
        this.blogName = blogName;
        this.createDate = createDate;
        this.createYear = createDate.getYear();
        this.createMonth = createDate.getMonthValue();
        this.createDay = createDate.getDayOfMonth();
        this.blogContent = blogContent;
        this.blogHP = blogHP;
        this.blogKeyword = blogKeyword;
        this.lastUpdateDate = lastUpdateDate;
        this.blogDescription = blogDescription;
        this.type = type;
        this.isPrivate = isPrivate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBlogContent() {
        return blogContent;
    }

    public void setBlogContent(String blogContent) {
        this.blogContent = blogContent;
    }

    public String getBlogName() {
        return blogName;
    }

    public void setBlogName(String blogName) {
        this.blogName = blogName;
    }

    public String getBlogKeyword() {
        return blogKeyword;
    }

    public void setBlogKeyword(String blogKeyword) {
        this.blogKeyword = blogKeyword;
    }

    public String getBlogDescription() {
        return blogDescription;
    }

    public void setBlogDescription(String blogDescription) {
        this.blogDescription = blogDescription;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isBlogHP() {
        return blogHP;
    }

    public void setBlogHP(boolean blogHP) {
        this.blogHP = blogHP;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public LocalDate getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(LocalDate lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public int getCreateYear() {
        return this.createDate.getYear();
    }

    public int getCreateMonth() {
        return this.createDate.getMonthValue();
    }

    public int getCreateDay() {
        return this.createDate.getDayOfMonth();
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }
}
