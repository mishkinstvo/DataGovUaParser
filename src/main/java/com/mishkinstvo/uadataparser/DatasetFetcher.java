package com.mishkinstvo.uadataparser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class DatasetFetcher implements Erroneous<Dataset> {
	
	private String id;
	private OptionsStorage optionsStorage;
	
	public DatasetFetcher(String id, OptionsStorage optionsStorage) {
		this.id = id;
		this.optionsStorage = optionsStorage;
	}
	
	public Dataset execute() throws Exception {
		LOG.debug("Parsing dataset with id {} was started", id);
		LOG.debug("Dataset url: {}", optionsStorage.getDatasetUrl(id));
		
		Document doc = Jsoup.connect(optionsStorage.getDatasetUrl(id)).get();
		
		Dataset dataset = new Dataset();
		dataset.setId(DatasetPath.ID.element(doc));
		dataset.setName(DatasetPath.TITLE.element(doc));
		dataset.setRevisionId(DatasetPath.REVISION_ID.element(doc));
		dataset.setLastRevisionId(DatasetPath.LAST_REVISION_ID.element(doc));
		dataset.setCreatedAt(DatasetPath.CREATED.element(doc));
		dataset.setUpdatedAt(DatasetPath.CHANGED.element(doc));
		dataset.setLanguage(DatasetPath.LANGUAGE.element(doc));
		dataset.setCategory(DatasetPath.CATEGORY.element(doc));
		dataset.setResponsiblePerson(DatasetPath.RESPONSIBLE_PERSON_NAME.element(doc));
		dataset.setResponsiblePersonEmail(DatasetPath.RESPONSIBLE_PERSON_EMAIL.element(doc));
		dataset.setDescription(DatasetPath.DESCRIPTION.element(doc));
		
		for (Element e : DatasetPath.KEYWORDS.elements(doc)) {
			dataset.addKeyword(e.text());
		}
		
		for (Element e : DatasetPath.FILES.elements(doc)) {
			dataset.addDocument(new DatasetFile(
				e.getElementsByTag("title").text(),
				e.getElementsByTag("format").text(),
				e.getElementsByTag("url").text()
			));
		}
		
		LOG.debug("Parsing dataset with id {} was successfully finished", id);
		
		return dataset;
	}
	
	final static Logger LOG = LogManager.getLogger(DatasetFetcher.class);
	
}
