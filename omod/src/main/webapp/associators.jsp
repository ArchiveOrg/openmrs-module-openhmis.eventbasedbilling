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
<openmrs:require allPrivileges="Manage Billing Metadata, View Billing Metadata" otherwise="/login.htm" redirect="/module/openhmis/eventbasedbilling/associators.form" />
<%@ include file="/WEB-INF/template/header.jsp"%>
<%@ include file="template/localHeader.jsp"%>
<openmrs:htmlInclude file="/moduleResources/openhmis/eventbasedbilling/js/screen/billAssociators.js" />
<%@ include file="template/linksHeader.jsp"%>

<h2>
    <spring:message code="openhmis.eventbasedbilling.admin.associators" />
</h2>
<%@ include file="/WEB-INF/template/footer.jsp"%>