<%--
- form.jsp
-
- Copyright (C) 2012-2023 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form> 
	<acme:input-textbox code="any.course.form.label.code" path="code"/>	
	<acme:input-textbox code="any.course.form.label.title" path="title"/>
	<acme:input-textarea code="any.course.form.label.abstract$" path="abstract$"/>
	<acme:input-select code="any.course.form.label.theoreticalOrHandsOn" path="theoreticalOrHandsOn" choices="${theoreticalOrHandsOn2}"/>
	<acme:input-money code="any.course.form.label.price" path="price"/>
	<acme:input-url code="any.course.form.label.link" path="link"/>
</acme:form>
	<acme:button code="any.course.form.button.practicum" action="/any/practicum/list?masterId=${id}"/>
