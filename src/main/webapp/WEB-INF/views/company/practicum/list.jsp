
<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="company.practicum.list.label.code" path="code" width="20%"/>
	<acme:list-column code="company.practicum.list.label.title" path="title" width="20%"/>
	<acme:list-column code="company.practicum.list.label.abstract$" path="abstract$" width="40%"/>	
</acme:list>

<acme:button code="company.practicum.list.button.create" action="/company/practicum/create"/>