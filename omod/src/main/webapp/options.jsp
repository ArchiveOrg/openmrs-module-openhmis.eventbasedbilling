<%@ include file="/WEB-INF/template/include.jsp"%>
<%--
  ~ The contents of this file are subject to the OpenMRS Public License
  ~ Version 2.0 (the "License"); you may not use this file except in
  ~ compliance with the License. You may obtain a copy of the License at
  ~ http://license.openmrs.org
  ~
  ~ Software distributed under the License is distributed on an "AS IS"
  ~ basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
  ~ License for the specific language governing rights and limitations
  ~ under the License.
  ~
  ~ Copyright (C) OpenMRS, LLC.  All Rights Reserved.
  --%>
<openmrs:require allPrivileges="Manage Event-Based Billing, View Event-Based Billing" otherwise="/login.htm" redirect="/module/openhmis/eventbasedbilling/options.form" />
<%@ include file="/WEB-INF/template/header.jsp"%>
<%@ include file="template/localHeader.jsp"%>
<%@ include file="template/linksHeader.jsp"%>

<h2>
    <spring:message code="openhmis.eventbasedbilling.admin.options" />
</h2>

<form method="POST">
	<table cellpadding="5">
	  <tr>
	   <td colspan="2"><input type="checkbox" name="isEnabled" id="isEnabled"<c:if test="${options.enabled}"> checked</c:if>>
	   		<label for="isEnabled">Enable automatic billing</label></td>
	  </tr>
	  <tr>
	   <td><label for="defaultAssociator">Default Bill Associator</label></td>
	   <td><select name="associatorId">
	   		<c:forEach items="${associators}" var="associator">
	   			<option value="<c:out value="${associator.id}"/>"<c:if test="${associator.id == options.billAssociator.id}"> selected</c:if>>
	   				<c:out value="${associator.name}"/>
	   			</option>
	   		</c:forEach>
	   </select></td>
	  </tr>
	</table>
	
	<input type="submit" value="Save Options" />
</form>

<%@ include file="/WEB-INF/template/footer.jsp"%>