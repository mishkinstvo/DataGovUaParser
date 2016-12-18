package com.mishkinstvo.uadataparser;

public interface Erroneous<T> {
	T execute() throws Exception;
}
