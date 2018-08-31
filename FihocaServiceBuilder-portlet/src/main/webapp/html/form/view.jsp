<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ page import="com.example.plugins.model.Employee" %>

<portlet:defineObjects />

<portlet:actionURL var="addEmployeeActionURL" windowState="normal" name="addEmployee">
</portlet:actionURL>

<form action="<%=addEmployeeActionURL%>" name="employeeForm" method="post">
  <div>
    <label for="name">Name</label>
    <input type="text" name="<portlet:namespace/>name" id="<portlet:namespace/>name" placeholder="Enter Name">
  </div>
  <div>
    <label for="name">Address</label>
    <input type="text" name="<portlet:namespace/>address" id="<portlet:namespace/>address" placeholder="Enter Address">
  </div>
  <input type="submit" class="btn btn-primary" id="addEmployee" name="addEmployee" value="Submit"/>
</form>
