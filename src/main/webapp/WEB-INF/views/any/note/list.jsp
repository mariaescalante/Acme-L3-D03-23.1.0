<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="any.note.form.label.instantiationMoment" path="instantiationMoment"  width="30%"/>
	<acme:list-column code="any.note.form.label.title" path="title" width="50%" />
	<acme:list-column code="any.note.form.label.author" path="author" width="20%" />
</acme:list>
<acme:button code="any.note.form.button.create" action="/any/note/create"/>