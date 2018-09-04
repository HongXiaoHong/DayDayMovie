package com.hong.bean;

import java.sql.Timestamp;

/*

  `id` varchar(32)
  `mvName` varchar(32)
  `mvDesc` varchar(32)
  `mvDuration` varchar(32)
  `uploader` varchar(32)
  `uploadTime` timestamp
  `playTimes` int(32)
  `isEnabled` varchar(2)
  `label` varchar(32)
  `goodCount` int(32)
  `category` varchar(32)
  `costPoint` int(16)
  `extName` varchar(16)
  `imgExtName` varchar(32)
  `createDate` timestamp
  `lastEditDate` timestamp 

 */
//bean层用于保存电影
public class Movie {

	private String id;
	private String mvName;
	private String mvDesc;
	private String mvDuration;
	private String uploader;//ID
	private Timestamp uploadTime;
	private int playTimes;
	private String isEnabled;
	private String label;
	private int goodCount;
	private String category;
	private int costPoint;
	private String extName;
	private String imgExtName;
	private Timestamp createDate;
	private Timestamp lastEditDate;
	
	//要播放的时候才需要用到
	private String uploaderName;       //上传者的名称(Res)
	private String uploaderNickName;  //上传者的昵称
	
	

	public Movie() {
		super();
	}
	
	public Movie(String id, String mvName, String mvDesc, String mvDuration,
			String uploader, Timestamp uploadTime, int playTimes,
			String isEnabled, String label, int goodCount, String category,
			int costPoint, String extName, String imgExtName,
			Timestamp createDate, Timestamp lastEditDate) {
		super();
		this.id = id;
		this.mvName = mvName;
		this.mvDesc = mvDesc;
		this.mvDuration = mvDuration;
		this.uploader = uploader;
		this.uploadTime = uploadTime;
		this.playTimes = playTimes;
		this.isEnabled = isEnabled;
		this.label = label;
		this.goodCount = goodCount;
		this.category = category;
		this.costPoint = costPoint;
		this.extName = extName;
		this.imgExtName = imgExtName;
		this.createDate = createDate;
		this.lastEditDate = lastEditDate;
	}

	public Movie(String mvName, String mvDesc, String mvDuration,
			String uploader, String isEnabled, String label, String category,
			int costPoint, String extName, String imgExtName) {
		super();
		this.mvName = mvName;
		this.mvDesc = mvDesc;
		this.mvDuration = mvDuration;
		this.uploader = uploader;
		this.isEnabled = isEnabled;
		this.label = label;
		this.category = category;
		this.costPoint = costPoint;
		this.extName = extName;
		this.imgExtName = imgExtName;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", mvName=" + mvName + ", mvDesc=" + mvDesc
				+ ", mvDuration=" + mvDuration + ", uploader=" + uploader
				+ ", uploadTime=" + uploadTime + ", playTimes=" + playTimes
				+ ", isEnabled=" + isEnabled + ", label=" + label
				+ ", goodCount=" + goodCount + ", category=" + category
				+ ", costPoint=" + costPoint + ", extName=" + extName
				+ ", imgExtName=" + imgExtName + ", createDate=" + createDate
				+ ", lastEditDate=" + lastEditDate + "]";
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMvName() {
		return mvName;
	}
	public void setMvName(String mvName) {
		this.mvName = mvName;
	}
	public String getMvDesc() {
		return mvDesc;
	}
	public void setMvDesc(String mvDesc) {
		this.mvDesc = mvDesc;
	}
	public String getMvDuration() {
		return mvDuration;
	}
	public void setMvDuration(String mvDuration) {
		this.mvDuration = mvDuration;
	}
	public String getUploader() {
		return uploader;
	}
	public void setUploader(String uploader) {
		this.uploader = uploader;
	}
	
	public Timestamp getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Timestamp uploadTime) {
		this.uploadTime = uploadTime;
	}

	public int getPlayTimes() {
		return playTimes;
	}
	public void setPlayTimes(int playTimes) {
		this.playTimes = playTimes;
	}
	public String getIsEnabled() {
		return isEnabled;
	}
	public void setIsEnabled(String isEnabled) {
		this.isEnabled = isEnabled;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public int getGoodCount() {
		return goodCount;
	}
	public void setGoodCount(int goodCount) {
		this.goodCount = goodCount;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getCostPoint() {
		return costPoint;
	}
	public void setCostPoint(int costPoint) {
		this.costPoint = costPoint;
	}
	public String getExtName() {
		return extName;
	}
	public void setExtName(String extName) {
		this.extName = extName;
	}
	public String getImgExtName() {
		return imgExtName;
	}
	public void setImgExtName(String imgExtName) {
		this.imgExtName = imgExtName;
	}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	public Timestamp getLastEditDate() {
		return lastEditDate;
	}
	public void setLastEditDate(Timestamp lastEditDate) {
		this.lastEditDate = lastEditDate;
	}
	public String getUploaderName() {
		return uploaderName;
	}

	public void setUploaderName(String uploaderName) {
		this.uploaderName = uploaderName;
	}

	public String getUploaderNickName() {
		return uploaderNickName;
	}

	public void setUploaderNickName(String uploaderNickName) {
		this.uploaderNickName = uploaderNickName;
	}
	public String getImgURL(){
		System.out.println("videoURL:"+String.format("/ResGetter/get?user=%s&movieId=%s&type=%s&extName=%s",
				uploaderName, id, "img", imgExtName));;
		return String.format("/ResGetter/get?user=%s&movieId=%s&type=%s&extName=%s",
				uploaderName, id, "img", imgExtName);
	}
	public String getVideoURL(){
		System.out.println("videoURL:"+String.format("/ResGetter/get?user=%s&movieId=%s&type=%s&extName=%s",
				uploaderName, id, "video", extName));;
		return String.format("/ResGetter/get?user=%s&movieId=%s&type=%s&extName=%s",
				uploaderName, id, "video", extName);
	}
}
