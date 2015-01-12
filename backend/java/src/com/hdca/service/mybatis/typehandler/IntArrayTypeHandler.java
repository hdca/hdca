package com.hdca.service.mybatis.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

import com.google.common.base.Joiner;

@MappedJdbcTypes(JdbcType.OTHER)
public class IntArrayTypeHandler extends BaseTypeHandler<Object> {
	public static final String SEPARATOR = ",";

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, Object param,
			JdbcType jdbcType) throws SQLException {
		if (param == null) {
			ps.setString(i, null);
			return;
		}
		Object[] arr = (Object[]) param;
		ps.setString(i, Joiner.on(IntArrayTypeHandler.SEPARATOR).join(arr));

	}

	@Override
	public Object getNullableResult(ResultSet rs, String columnName)
			throws SQLException {
		String s = rs.getString(columnName);
		if (s == null || s.length() == 0) {// may be useless
			return null;
		}

		String[] sArr = s.split(",");
		return strArrayToIntArray(sArr);
	}

	@Override
	public Object getNullableResult(ResultSet rs, int columnIndex)
			throws SQLException {
		String s = rs.getString(columnIndex);
		if (s == null || s.length() == 0) {
			return null;
		}

		String[] sArr = s.split(",");
		return strArrayToIntArray(sArr);
	}

	@Override
	public Object getNullableResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		String s = cs.getString(columnIndex);
		if (s == null || s.length() == 0) {
			return null;
		}

		String[] sArr = s.split(",");
		return strArrayToIntArray(sArr);
	}

	private Integer[] strArrayToIntArray(String[] sArr) {
		Integer[] result = new Integer[sArr.length];
		for (int i = 0; i < sArr.length; i++) {
			result[i] = Integer.parseInt(sArr[i]);
		}
		return result;
	}

}
