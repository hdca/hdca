package com.hdca.domain.jqgrid;

public class JqGridResult {
	/**
	 * total number of pages
	 */
	int total;
	/**
	 * current pages
	 */
	int page;
	/**
	 * total number of records
	 */
	int records;
	String jsonData;

	public String toJsonStr() {
		StringBuilder sb = new StringBuilder("{");
		sb.append("\"total\": ").append(total).append(",");
		sb.append("\"page\": ").append(page).append(",");
		sb.append("\"records\": ").append(records).append(",");
		sb.append("\"rows\": ").append(jsonData).append("}");

		return sb.toString();
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRecords() {
		return records;
	}

	public void setRecords(int records) {
		this.records = records;
	}

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

}
