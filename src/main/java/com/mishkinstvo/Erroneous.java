package com.mishkinstvo;

public interface Erroneous<T> {
	T execute() throws Exception;
}
