package com.form;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.example.plugins.model.Employee;
import com.example.plugins.service.EmployeeLocalServiceUtil;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.util.bridges.mvc.MVCPortlet;

/**
 * Portlet implementation class Form
 */
public class Form extends MVCPortlet {

    public void init() {
        viewTemplate = getInitParameter("view-template");
    }

    public void doView(
            RenderRequest renderRequest, RenderResponse renderResponse)
        throws IOException, PortletException {

        include(viewTemplate, renderRequest, renderResponse);
    }

    protected void include(
            String path, RenderRequest renderRequest,
            RenderResponse renderResponse)
        throws IOException, PortletException {

        PortletRequestDispatcher portletRequestDispatcher =
            getPortletContext().getRequestDispatcher(path);

        if (portletRequestDispatcher == null) {
            _log.error(path + " is not a valid include");
        }
        else {
            portletRequestDispatcher.include(renderRequest, renderResponse);
        }
    }
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
 
    protected String viewTemplate;

    private static Log _log = LogFactoryUtil.getLog(Form.class);

}
