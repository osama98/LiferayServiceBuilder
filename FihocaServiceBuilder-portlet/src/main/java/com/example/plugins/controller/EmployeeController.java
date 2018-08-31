package com.example.plugins.controller;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

import com.example.plugins.model.Employee;
import com.example.plugins.service.EmployeeLocalServiceUtil;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.util.bridges.mvc.MVCPortlet;

public class EmployeeController extends MVCPortlet{

	public void addEmployee(ActionRequest actionRequest, ActionResponse actionResponse) throws IOException,PortletException{
		String name = ParamUtil.get(actionRequest, "name", StringPool.BLANK);
		String address = ParamUtil.get(actionRequest, "address", StringPool.BLANK);
		
		Employee employee = null;
		try{
			employee = EmployeeLocalServiceUtil.createEmployee(
					(int) (CounterLocalServiceUtil.increment(
							Employee.class.getName())+1));
			
			employee.setName(name);
			employee.setAddress(address);
			
			EmployeeLocalServiceUtil.addEmployee(employee);
			System.out.println("Employee inserted successfully");
		}catch(SystemException e){
			e.printStackTrace();
			throw new PortletException("ERROR RO ADD NEW EMPLOYEE",e);
		}
	}
}
