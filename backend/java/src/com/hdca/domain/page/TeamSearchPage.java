package com.hdca.domain.page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.base.Joiner;
import com.hdca.service.mybatis.typehandler.IntArrayTypeHandler;

public class TeamSearchPage extends Page {
	private static final Logger logger = LogManager
			.getLogger(TeamSearchPage.class.getName());

	Integer cityareaid; //
	Integer[] districtareaids; // all districts in the city if element of value
								// -1 is
								// included in the array
	Integer[] typeids; // all types if element of value -1 is included in the
						// array
	String namelike;
	String commentlike;

	public TeamSearchPage() {
		districtareaids = null;
		typeids = null;
	}

	public String getDatasql() {
//		StringBuffer sb = new StringBuffer("SELECT").append("\r\n");
//		sb.append("T.id as team_id,").append("\r\n");
//		sb.append("T.name as team_name,").append("\r\n");
//		sb.append("T.mobile as team_mobile,").append("\r\n");
//		sb.append("T.address as team_address,").append("\r\n");
//		sb.append("T.email as team_email,").append("\r\n");
//		sb.append("T.contactname as team_contactname,").append("\r\n");
//		sb.append("GACITY.name as team_cityname,").append("\r\n");
//		sb.append("TD.districtareaid as td_districtareaid,").append("\r\n");
//		sb.append("GA.name as td_districtname,").append("\r\n");
//		sb.append("TT.typeid as tt_typeid").append("\r\n");
//
//		sb.append("FROM TEAM T").append("\r\n");
//		sb.append("LEFT OUTER JOIN TEAMDISTRICT TD ON (T.id=TD.teamid)")
//				.append("\r\n");
//		sb.append("LEFT OUTER JOIN TEAMTYPE TT ON (T.id=TT.teamid)").append(
//				"\r\n");
//		sb.append("LEFT JOIN GEOAREA GACITY ON (T.cityareaid=GACITY.areaid)")
//				.append("\r\n");
//		sb.append("LEFT JOIN GEOAREA GA ON (TD.districtareaid=GA.areaid)")
//				.append("\r\n");
		// check city null
		
		StringBuffer sb = new StringBuffer("SELECT").append("\r\n");
		sb.append("T.ID,T.NAME,T.MOBILE,T.ADDRESS,T.CONTACTNAME,T.EMAIL,T.CITYAREAID,T.EXTRAPACKAGE,T.CONTRACTDESC,T.COMMENT,").append("\r\n");
		sb.append("C_DISTRICTAREAIDS,C_TYPEIDS,C_BASEPACKAGEIDS,C_PRICERANGEIDS").append("\r\n");
		sb.append("FROM TEAM T").append("\r\n");
		sb.append("LEFT OUTER JOIN (SELECT TEAMID,districtareaid,GROUP_CONCAT(CAST(districtareaid AS CHAR)) AS C_DISTRICTAREAIDS FROM TEAMDISTRICT GROUP BY TEAMID ) TD ON(T.id=TD.teamid)").append("\r\n");
		sb.append("LEFT JOIN (SELECT TEAMID,GROUP_CONCAT(CAST(typeid AS CHAR)) AS C_TYPEIDS FROM TEAMTYPE GROUP BY TEAMID ) TT ON(T.id=TT.teamid)").append("\r\n");
		sb.append("LEFT JOIN (SELECT TEAMID,GROUP_CONCAT(CAST(basepackageid AS CHAR)) AS C_basepackageIDS FROM TEAMBASEPACKAGE GROUP BY TEAMID) TBP ON(T.id=TBP.teamid)").append("\r\n");
		sb.append("LEFT JOIN (SELECT TEAMID,GROUP_CONCAT(CAST(pricerangeid AS CHAR)) AS C_pricerangeIDS FROM TEAMPRICERANGE GROUP BY TEAMID) TPR ON(T.id=TPR.teamid)").append("\r\n");

		sb.append("WHERE T.ID IN").append(' ');

		sb.append("(SELECT ID FROM").append(' ');
		sb.append("(SELECT ID FROM TEAM TSEL").append(' ');
		sb.append("WHERE").append(' ');
		sb.append("1=1").append(' ');

		if (districtareaids != null) {
			// check if district include -1 which means all
			boolean bAllDistrict = false;
			for (Integer _d : districtareaids) {
				if (_d == 0) {
					bAllDistrict = true;
					break;
				}
			}
			if (bAllDistrict) {
				sb.append(getCondStrAllDistrict(cityareaid));
				// do nothing
			} else {
				sb.append(getCondStrSomeDistrict(districtareaids));
				// w+="and "

			}
		} else {
			if (cityareaid != null) {
				sb.append(getCondStrAllDistrict(cityareaid));
			}
		}

		if (typeids != null) {
			// check if styles include -1 which means all
			boolean bAllTypes = false;
			for (Integer _t : typeids) {
				if (_t == 0) {
					bAllTypes = true;
					break;
				}
			}
			if (bAllTypes) {
				// do nothing
			} else {
				sb.append(getCondStrSomeTypes(typeids));
				// w+="and "
			}
		}

		if (namelike != null) {
			sb.append(getCondNamelike(namelike));
		}
		if (commentlike != null) {
			sb.append(getCondCommentlike(commentlike));
		}

		// paging params
		String pagingParams = String.format("LIMIT %d,%d\r\n", start, limit);
		sb.append(pagingParams);

		// LIMIT 0,5
		sb.append(") TSELWRAPPER").append(' ');
		sb.append(")").append(' ');

		String result = sb.toString();

		System.out.println(result);

		return result;

	}

	public String getCountsql() {
		StringBuffer sb = new StringBuffer("SELECT").append(" COUNT(*)\r\n");
		sb.append("FROM TEAM T").append("\r\n");

		sb.append("WHERE T.ID IN").append(' ');

		sb.append("(SELECT ID FROM").append(' ');
		sb.append("(SELECT ID FROM TEAM TSEL").append(' ');
		sb.append("WHERE").append(' ');
		sb.append("1=1").append(' ');

		// check city null

		if (districtareaids != null) {
			// check if district include -1 which means all
			boolean bAllDistrict = false;
			for (Integer _d : districtareaids) {
				if (_d == 0) {
					bAllDistrict = true;
					break;
				}
			}
			if (bAllDistrict) {
				sb.append(getCondStrAllDistrict(cityareaid));
				// do nothing
			} else {
				sb.append(getCondStrSomeDistrict(districtareaids));
				// w+="and "

			}
		} else {
			if (cityareaid != null) {
				sb.append(getCondStrAllDistrict(cityareaid));
			}
		}

		if (typeids != null) {
			// check if styles include -1 which means all
			boolean bAllTypes = false;
			for (Integer _t : typeids) {
				if (_t == 0) {
					bAllTypes = true;
					break;
				}
			}
			if (bAllTypes) {
				// do nothing
			} else {
				sb.append(getCondStrSomeTypes(typeids));
				// w+="and "
			}
		}

		sb.append(") TSELWRAPPER").append(' ');
		sb.append(")").append(' ');

		return sb.toString();

		// if (districtareaid == -1) {
		// //do nothing
		// }else{
		//
		// }
	}

	private String old_getCondStrSomeDistrict(Integer[] _districtareaids) {
		String sDistricts = Joiner.on(IntArrayTypeHandler.SEPARATOR).join(
				_districtareaids);
		String sCond = String
				.format("AND T.ID IN (SELECT TEAMID FROM TEAMDISTRICT WHERE DISTRICTAREAID IN (%s) GROUP BY TEAMID) \r\n",
						sDistricts);
		return sCond;
	}

	private String getCondStrSomeDistrict(Integer[] _districtareaids) {
		String sDistricts = Joiner.on(IntArrayTypeHandler.SEPARATOR).join(
				_districtareaids);
		// String sCond = String.format("AND TD.districtareaid IN (%s)\r\n",
		// sDistricts);

		String sCond = String
				.format("AND TSEL.ID IN (SELECT DISTINCT TD.TEAMID FROM TEAMDISTRICT TD WHERE TD.districtareaid IN (%s))\r\n",
						sDistricts);

		return sCond;
	}

	private String old_getCondStrAllDistrict(Integer _cityareaid) {
		String sCond = String
				.format("AND T.ID IN (SELECT TD1.TEAMID FROM TEAMDISTRICT TD1 LEFT JOIN GEOAREA GA ON(TD1.DISTRICTAREAID=GA.AREAID) WHERE GA.PARENTID=%d) \r\n",
						_cityareaid);
		return sCond;

	}

	private String getCondStrAllDistrict(Integer _cityareaid) {
		String sCond = String.format("AND 2=2\r\n", _cityareaid);
		return sCond;

	}

	private String old_getCondStrSomeTypes(Integer[] _typeids) {
		String sTypes = Joiner.on(IntArrayTypeHandler.SEPARATOR).join(_typeids);
		String sCond = String
				.format("AND T.ID IN (SELECT TEAMID FROM TEAMTYPE WHERE TYPEID IN (%s) GROUP BY TEAMID) \r\n",
						sTypes);
		return sCond;
		// ID IN (SELECT TEAMID FROM TEAMDISTRICT WHERE districtareaid IN
		// (50,28) GROUP BY TEAMID)
	}

	private String getCondStrSomeTypes(Integer[] _typeids) {
		String sTypes = Joiner.on(IntArrayTypeHandler.SEPARATOR).join(_typeids);
		// String sCond = String.format("AND TT.TYPEID IN (%s)\r\n", sTypes);
		String sCond = String
				.format("AND TSEL.ID IN (SELECT DISTINCT TT.TEAMID FROM TEAMTYPE TT WHERE TT.TYPEID IN (%s))\r\n",
						sTypes);
		return sCond;
	}

	private String getCondNamelike(String _namelike) {
		// String sCond = "AND T.NAME LIKE '%" + _namelike + "%'\r\n";
		String sCond = "AND TSEL.NAME LIKE '%" + _namelike + "%'\r\n";

		return sCond;
	}

	private String getCondCommentlike(String _commentlike) {
		String sCond = "AND TSEL.COMMENT LIKE '%" + _commentlike + "%'\r\n";
		return sCond;
	}

	public String urlGetParamString() {
		StringBuffer sbParam = new StringBuffer("");
		if (districtareaids != null) {
			sbParam.append("&districtareaids=").append(
					Joiner.on(IntArrayTypeHandler.SEPARATOR).join(
							districtareaids));
		}
		if (typeids != null) {
			sbParam.append("&typeids=").append(
					Joiner.on(IntArrayTypeHandler.SEPARATOR).join(typeids));
		}
		if (cityareaid != null) {
			sbParam.append("&cityareaid=").append(cityareaid);
		}

		return sbParam.toString();
	}

	public String urlGetParamValue(String paramName) {
		if (paramName.equals("typeids")) {
			if (typeids == null) {
				return "null";
			} else {
				return Joiner.on(IntArrayTypeHandler.SEPARATOR).join(typeids);
			}
		} else if (paramName.equals("districtareaids")) {
			if (districtareaids == null) {
				return "null";
			} else {
				return Joiner.on(IntArrayTypeHandler.SEPARATOR).join(
						districtareaids);
			}
		} else if (paramName.equals("cityareaid")) {
			if (cityareaid == null) {
				return "null";
			} else {
				return String.valueOf(cityareaid);
			}
		} else if (paramName.equals("namelike")) {
			if (namelike == null) {
				return "null";
			} else {
				return String.valueOf(namelike);
			}
		}

		return "null";
	}

	// -----------------------------------getters and setters------------

	public Integer[] getDistrictareaids() {
		return districtareaids;
	}

	public void setDistrictareaids(Integer[] districtareaids) {
		this.districtareaids = districtareaids;
	}

	public Integer[] getTypeids() {
		return typeids;
	}

	public void setTypeids(Integer[] typeids) {
		this.typeids = typeids;
	}

	public Integer getCityareaid() {
		return cityareaid;
	}

	public void setCityareaid(Integer cityareaid) {
		this.cityareaid = cityareaid;
	}

	public String getNamelike() {
		return namelike;
	}

	public void setNamelike(String namelike) {
		this.namelike = namelike;
	}

	public String getCommentlike() {
		return commentlike;
	}

	public void setCommentlike(String commentlike) {
		this.commentlike = commentlike;
	}

}
