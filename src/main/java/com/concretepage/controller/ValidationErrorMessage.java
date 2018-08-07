package com.concretepage.controller;

import java.util.LinkedList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

public final class ValidationErrorMessage {

	private Long _objectId;
	private String _columnKey;
	private String _errorMessageKey;
	private List<String> _errorMessageWildcards;
	private Object _invalidValue;
	private String _errorField;

	private ValidationErrorMessage(String columnKey, String errorMessage, List<String> errorMessageKeys,
			Object invalidValue) {
		this(null, columnKey, errorMessage, errorMessageKeys, invalidValue, null);
	}

	private ValidationErrorMessage(Long objectId, String columnKey, String errorMessage, List<String> errorMessageKeys,
			Object invalidValue, String errorField) {
		this._objectId = objectId;
		this._columnKey = columnKey;
		this._errorMessageKey = errorMessage;
		this._errorMessageWildcards = errorMessageKeys;
		this._invalidValue = invalidValue;
		this._errorField = errorField;
	}

	public static ValidationErrorMessage constructErrorMessageWithWildcards(String columnKey, String errorMessageKey,
			List<String> errorMessageKeys, Object invalidValue) {
		return new ValidationErrorMessage(columnKey, errorMessageKey, errorMessageKeys, invalidValue);
	}

	public static ValidationErrorMessage constructErrorMessageWithWildcards(String columnKey, String errorMessageKey,
			List<String> errorMessageKeys, Object invalidValue, String errorField) {
		return new ValidationErrorMessage(null, columnKey, errorMessageKey, errorMessageKeys, invalidValue, errorField);
	}

	public static ValidationErrorMessage constructErrorMessageWithWildcards(Long objectId, String columnKey,
			String errorMessageKey, List<String> errorMessageKeys, Object invalidValue, String errorField) {
		return new ValidationErrorMessage(objectId, columnKey, errorMessageKey, errorMessageKeys, invalidValue,
				errorField);
	}

	public static ValidationErrorMessage constructErrorMessage(String columnKey, String errorMessageKey,
			Object invalidValue) {
		return constructErrorMessageWithWildcards(columnKey, errorMessageKey, constructWildcardList(columnKey),
				invalidValue);
	}

	public static ValidationErrorMessage constructErrorMessage(String columnKey, String errorMessageKey,
			Object invalidValue, String errorField) {
		return constructErrorMessageWithWildcards(columnKey, errorMessageKey, constructWildcardList(columnKey),
				invalidValue, errorField);
	}

	public static ValidationErrorMessage constructErrorMessage(Long objectId, String columnKey, String errorMessageKey,
			Object invalidValue, String errorField) {
		return constructErrorMessageWithWildcards(objectId, columnKey, errorMessageKey,
				constructWildcardList(columnKey), invalidValue, errorField);
	}

	public static List<String> constructWildcardList(String... args) {
		List<String> wildcardList = new LinkedList<String>();
		if (args != null) {
			for (String w : args) {
				wildcardList.add(w);
			}
		}
		return wildcardList;
	}

	public Long getObjectId() {
		return _objectId;
	}

	public void setObjectId(Long objectId) {
		this._objectId = objectId;
	}

	public String getColumnKey() {
		return _columnKey;
	}

	public String getErrorMessageKey() {
		return _errorMessageKey;
	}

	public List<String> getErrorMessageWildcards() {
		return _errorMessageWildcards;
	}

	public Object getInvalidValue() {
		return _invalidValue;
	}

	public String getErrorField() {
		return _errorField;
	}

	@Override
	public String toString() {
		// return "column key: [" + this.getColumnKey() + "] error key: [" +
		// this.getErrorMessageKey() + "]";
		ObjectMapper mapper = new ObjectMapper();
		String output = "";
		try {
			output = mapper.writeValueAsString(this);
		} catch (Exception e) {
			System.out.println(e);
		}
		return output;
	}

}
