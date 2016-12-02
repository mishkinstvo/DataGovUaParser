package com.mishkinstvo;

public class DatasetFile {
	private String id = "";
	private String name = "";
	private String format = "";
	private String url = "";
	
	public DatasetFile() {

	}
	
	public DatasetFile(String name, String format, String url) {
		this.name = name;
		this.format = format;
		this.url = url;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getFormat() {
		return format;
	}
	
	public void setFormat(String format) {
		this.format = format;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
}
