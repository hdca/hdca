package com.hdca.domain;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Joiner;
import com.hdca.service.mybatis.typehandler.IntArrayTypeHandler;

public class Team {
	// 包括施工队名字、联系人、手机、所在城市、主要工作区域、擅长方向
	long id;
	String name;
	String contactname;
	String mobile;
	String email;

	int cityareaid;
	int adid;
	List<Integer> districtareas = new ArrayList<Integer>(); // 地区

	/**
	 * 擅长方向如: 别墅 老房 新房 餐饮 休闲 校园 办公 其他 (名称及值以default.properties为准)
	 */
	List<Integer> types = new ArrayList<Integer>();
	/**
	 * 服务支持如: 免费预约 免费量房 免费设计 定金量房 定金设计 (名称及值以default.properties为准)
	 */
	List<Integer> basepackages = new ArrayList<Integer>();
	/**
	 * 承接价位如: 3万以下 3-5万 5-8万 8-12万 12-18万 18-30万 (名称及值以default.properties为准)
	 */
	List<Integer> priceranges = new ArrayList<Integer>();

	// 30-100万

	Integer offertype;// 深化预算: 1.全面总报价; 2.全面细节报价
	String contractdesc; // 合同类型
	/**
	 * 简介
	 */
	String comment;
	String address;
	/**
	 * 特色服务
	 */
	String extrapackage;

	// composite properties
	// String c_typeids;
	// String c_pricerangeids;
	// String c_districtareaids;
	// String c_districtnames;
	// String c_basepackageids;

	// for display
	String cityname;
	List<String> districtnames;
	List<String> typenames;
	List<String> basepackagenames;
	List<String> pricerangenames;

	public Team() {
		districtnames = new ArrayList<String>();
		typenames = new ArrayList<String>();
		basepackagenames = new ArrayList<String>();
		pricerangenames = new ArrayList<String>();
	}

	// ----------------------------- composite properties------
	// public String getC_typeids() {
	// String c_typeids;
	// if (types == null || types.size() == 0) {
	// c_typeids = "";
	// } else {
	// c_typeids = Joiner.on(IntArrayTypeHandler.SEPARATOR).join(types);
	// }
	// return c_typeids;
	// }

	public void setC_typeids(String c_typeids) {
		System.out.println("c_typeids=" + c_typeids);
		// this.c_typeids = c_typeids;
		if (c_typeids != null) {
			String[] tks = c_typeids.split(",");
			List<Integer> values = new ArrayList<Integer>();
			for (int i = 0; i < tks.length; i++) {
				values.add(Integer.parseInt(tks[i]));
			}
			types = values;
		} else {
			System.out.println("c_typeids null!!!");
		}

	}

	// public String getC_pricerangeids() {
	// String c_pricerangeids;
	// if (priceranges == null || priceranges.size() == 0) {
	// c_pricerangeids = "";
	// } else {
	// c_pricerangeids =
	// Joiner.on(IntArrayTypeHandler.SEPARATOR).join(priceranges);
	// }
	// return c_pricerangeids;
	// }

	public void setC_pricerangeids(String c_pricerangeids) {

		System.out.println("c_pricerangeids=" + c_pricerangeids);
		// this.c_pricerangeids = c_pricerangeids;
		if (c_pricerangeids != null) {
			String[] tks = c_pricerangeids.split(",");
			List<Integer> values = new ArrayList<Integer>();
			for (int i = 0; i < tks.length; i++) {
				values.add(Integer.parseInt(tks[i]));
			}
			priceranges = values;
		} else {
			System.out.println("c_pricerangeids null!!!");
		}

	}

	// public String getC_districtareaids() {
	// String c_districtareaids;
	// if (districtareas == null || districtareas.size() == 0) {
	// c_districtareaids = "";
	// } else {
	// c_districtareaids =
	// Joiner.on(IntArrayTypeHandler.SEPARATOR).join(priceranges);
	// }
	// return c_districtareaids;
	// }

	public void setC_districtareaids(String c_districtareaids) {

		System.out.println("c_districtareaids=" + c_districtareaids);
		// this.c_districtareaids = c_districtareaids;
		if (c_districtareaids != null) {
			String[] tks = c_districtareaids.split(",");
			List<Integer> values = new ArrayList<Integer>();
			for (int i = 0; i < tks.length; i++) {
				values.add(Integer.parseInt(tks[i]));
			}
			districtareas = values;
		} else {
			System.out.println("c_districtareaids null!!!");
		}

	}

	public void setC_districtnames(String c_districtnames) {
		System.out.println("c_districtnames=" + c_districtnames);
		// this.c_districtnames = c_districtnames;
		if (c_districtnames != null) {
			String[] tks = c_districtnames.split(",");
			List<String> values = new ArrayList<String>();
			for (int i = 0; i < tks.length; i++) {
				values.add(tks[i]);
			}
			districtnames = values;
		} else {
			System.out.println("c_districtareaids null!!!");
		}

	}

	public void setC_basepackageids(String c_basepackageids) {
		System.out.println("c_basepackageids=" + c_basepackageids);
		// this.c_basepackageids = c_basepackageids;
		if (c_basepackageids != null) {
			String[] tks = c_basepackageids.split(",");
			List<Integer> values = new ArrayList<Integer>();
			for (int i = 0; i < tks.length; i++) {
				values.add(Integer.parseInt(tks[i]));
			}
			basepackages = values;
		} else {
			System.out.println("c_basepackageids null!!!");
		}

	}

	// ----------------------------- getters and setters ------

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactname() {
		return contactname;
	}

	public void setContactname(String contactname) {
		this.contactname = contactname;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getCityareaid() {
		return cityareaid;
	}

	public void setCityareaid(int cityareaid) {
		this.cityareaid = cityareaid;
	}

	public int getAdid() {
		return adid;
	}

	public void setAdid(int adid) {
		this.adid = adid;
	}

	public List<Integer> getDistrictareas() {
		return districtareas;
	}

	public void setDistrictareas(List<Integer> districtareas) {
		this.districtareas = districtareas;
	}

	public List<Integer> getTypes() {
		return types;
	}

	public void setTypes(List<Integer> types) {
		this.types = types;
	}

	public List<Integer> getBasepackages() {
		return basepackages;
	}

	public void setBasepackages(List<Integer> basepackages) {
		this.basepackages = basepackages;
	}

	public List<Integer> getPriceranges() {
		return priceranges;
	}

	public void setPriceranges(List<Integer> priceranges) {
		this.priceranges = priceranges;
	}

	public Integer getOffertype() {
		return offertype;
	}

	public void setOffertype(Integer offertype) {
		this.offertype = offertype;
	}

	public String getContractdesc() {
		return contractdesc;
	}

	public void setContractdesc(String contractdesc) {
		this.contractdesc = contractdesc;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getExtrapackage() {
		return extrapackage;
	}

	public void setExtrapackage(String extrapackage) {
		this.extrapackage = extrapackage;
	}

	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

	public List<String> getDistrictnames() {
		return districtnames;
	}

	public void setDistrictnames(List<String> districtnames) {
		this.districtnames = districtnames;
	}

	public List<String> getTypenames() {
		return typenames;
	}

	public void setTypenames(List<String> typenames) {
		this.typenames = typenames;
	}

	public List<String> getBasepackagenames() {
		return basepackagenames;
	}

	public void setBasepackagenames(List<String> basepackagenames) {
		this.basepackagenames = basepackagenames;
	}

	public List<String> getPricerangenames() {
		return pricerangenames;
	}

	public void setPricerangenames(List<String> pricerangenames) {
		this.pricerangenames = pricerangenames;
	}

}
