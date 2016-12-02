package com.mishkinstvo;

import org.jsoup.select.Elements;

public enum DatasetPath {
	BASE_TAG("result"),
	ID(selectFromBaseTag("dataset_id")),
	TITLE(selectFromBaseTag("title")),
	CATEGORY(selectFromBaseTag("category")),
	REVISION_ID(selectFromBaseTag("revision_id")),
	LAST_REVISION_ID(selectFromBaseTag("last_revision_id")),
	CREATED(selectFromBaseTag("created")),
	CHANGED(selectFromBaseTag("changed")),
	LANGUAGE(selectFromBaseTag("language > machine_name")),
	DESCRIPTION(selectFromBaseTag("description")),
	KEYWORDS(selectFromBaseTag("keywords > item")),
	RESPONSIBLE_PERSON_NAME(selectFromBaseTag("responsible_person > name")),
	RESPONSIBLE_PERSON_EMAIL(selectFromBaseTag("responsible_person > email")),
	FILES(selectFromBaseTag("files > item"));
	
	DatasetPath(String path) {
		this.path = path;
	}
	
	public String element(org.jsoup.nodes.Document document) {
		return document.select(path).text();
	}
	
	public Elements elements(org.jsoup.nodes.Document document) {
		return document.select(path);
	}
	
	public static final String selectFromBaseTag(String selector) {
		return BASE_TAG.path + " > " + selector;
	}
	
	private String path;
}
