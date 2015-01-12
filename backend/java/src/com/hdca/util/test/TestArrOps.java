package com.hdca.util.test;

import com.google.common.base.Joiner;

public class TestArrOps {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer[] arrI = new Integer[]{1,2,5};
		Object[] arrObj = (Object[])arrI;
		System.out.println(Joiner.on("|").join(arrObj));
	}

}
