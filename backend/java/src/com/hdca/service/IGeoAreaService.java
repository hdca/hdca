package com.hdca.service;

import java.util.List;

import com.hdca.domain.GeoArea;

public interface IGeoAreaService {
	List<GeoArea> getGeoAreaByDirectParent(int parentid);
	public List<GeoArea> getRegisteredCity();

	public String jsongetProvinceMap();
	public String jsongetProvinceCityMap();
}
