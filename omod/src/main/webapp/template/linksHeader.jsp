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
<ul id="menu">
	<li class="first">
		<a href="${pageContext.request.contextPath}/admin"><openmrs:message code="admin.title.short"/></a>
	</li>
	<openmrs:hasPrivilege privilege="Manage Event-Based Billing">
		<li <c:if test='<%= request.getRequestURI().contains("eventbasedbilling/options") %>'>class="active"</c:if>>
			<a href="${pageContext.request.contextPath}/module/openhmis/eventbasedbilling/options.form">
				<spring:message code="openhmis.eventbasedbilling.admin.options"/>
			</a>
		</li>
	</openmrs:hasPrivilege>
	<openmrs:hasPrivilege privilege="Manage Event-Based Billing">
		<li <c:if test='<%= request.getRequestURI().contains("eventbasedbilling/associators") %>'>class="active"</c:if>>
			<a href="${pageContext.request.contextPath}/module/openhmis/eventbasedbilling/associators.form">
				<spring:message code="openhmis.eventbasedbilling.admin.associators"/>
			</a>
		</li>
	</openmrs:hasPrivilege>
</ul>