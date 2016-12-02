package com.mishkinstvo;

import org.apache.commons.lang3.StringEscapeUtils;

import java.util.ArrayList;
import java.util.List;

public class Dataset {
	// -- ADD ME
	private String id;
	private String revisionId;
	private String lastRevisionId;
	private String language;
	
	//FORMATS
	// ROZPORYADNYK
	
	private String category;
	private String responsiblePerson;
	private String responsiblePersonEmail;
	
	private List<String> keywords;
	
	private String createdAt;
	private String updatedAt;
	
	// UPDATED FREQ
	// Planned update freq
	
	// --- END ADD ME
	
	private String name;
	private String description;
	private List<DatasetFile> datasetFiles;
	
	public Dataset() {
		this.datasetFiles = new ArrayList<DatasetFile>();
		this.keywords = new ArrayList<String>();
	}
	
	public Dataset(String name, String description) {
		this.name = name;
		this.description = description;
		this.datasetFiles = new ArrayList<DatasetFile>();
	}
	
	public Dataset(String name, String description, List<DatasetFile> datasetFiles) {
		this.name = name;
		this.description = description;
		this.datasetFiles = datasetFiles;
	}

	public String getLastRevisionId() {
		return lastRevisionId;
	}

	public void setLastRevisionId(String lastRevisionId) {
		this.lastRevisionId = lastRevisionId;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		// Brutal fix of stupid id values in url
		this.id = id.trim().substring(0, 36);
	}
	
	public String getRevisionId() {
		return revisionId;
	}
	
	public void setRevisionId(String revisionId) {
		this.revisionId = revisionId;
	}
	
	public String getLanguage() {
		return language;
	}
	
	public void setLanguage(String language) {
		this.language = language;
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getResponsiblePerson() {
		return responsiblePerson;
	}
	
	public void setResponsiblePerson(String responsiblePerson) {
		this.responsiblePerson = responsiblePerson;
	}
	
	public String getResponsiblePersonEmail() {
		return responsiblePersonEmail;
	}
	
	public void setResponsiblePersonEmail(String responsiblePersonEmail) {
		this.responsiblePersonEmail = responsiblePersonEmail;
	}
	
	public String getCreatedAt() {
		return createdAt;
	}
	
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	
	public String getUpdatedAt() {
		return updatedAt;
	}
	
	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public List<DatasetFile> getDatasetFiles() {
		return datasetFiles;
	}
	
	public void addDocument(DatasetFile doc) {
		datasetFiles.add(doc);
	}
	
	public List<DatasetFile> getKeywords() {
		return datasetFiles;
	}
	
	public void addKeyword(String keyword) {
		keywords.add(keyword);
	}
	
	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}
	
	public void setDatasetFiles(List<DatasetFile> datasetFiles) {
		this.datasetFiles = datasetFiles;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		if (keywords.size() < 1) {
			keywords.add("");
		}
		
		for (DatasetFile doc : datasetFiles) {
			for (String keyword : keywords) {
				sb.append(StringEscapeUtils.escapeCsv(id));
				sb.append(',');
				sb.append(StringEscapeUtils.escapeCsv(revisionId));
				sb.append(',');
				sb.append(StringEscapeUtils.escapeCsv(lastRevisionId));
				sb.append(',');
				sb.append(StringEscapeUtils.escapeCsv(language));
				sb.append(',');
				sb.append(StringEscapeUtils.escapeCsv(category));
				sb.append(',');
				sb.append(StringEscapeUtils.escapeCsv(responsiblePerson));
				sb.append(',');
				sb.append(StringEscapeUtils.escapeCsv(responsiblePersonEmail));
				sb.append(',');
				sb.append(StringEscapeUtils.escapeCsv(keyword));
				sb.append(',');
				sb.append(StringEscapeUtils.escapeCsv(createdAt));
				sb.append(',');
				sb.append(StringEscapeUtils.escapeCsv(updatedAt));
				sb.append(',');
				sb.append(StringEscapeUtils.escapeCsv(name));
				sb.append(',');
				sb.append(StringEscapeUtils.escapeCsv(description));
				sb.append(',');
				sb.append(StringEscapeUtils.escapeCsv(doc.getName()));
				sb.append(',');
				sb.append(StringEscapeUtils.escapeCsv(doc.getFormat()));
				sb.append(',');
				sb.append(StringEscapeUtils.escapeCsv(doc.getUrl()));
				sb.append(System.lineSeparator());
			}
		}

		return sb.toString();
	}
}
