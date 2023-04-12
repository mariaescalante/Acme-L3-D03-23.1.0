<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="assistant.tutorial.list.label.title" path="title"  width="30%"/>
	<acme:list-column code="assistant.tutorial.list.label.goal" path="goal" width="35%" />
	<acme:list-column code="assistant.tutorial.list.label.totalTime" path="totalTime" width="30%" />
</acme:list>

<acme:button code="assistant.tutorial.list.button.create" action="/assistant/tutorial/create"/>