<%@ page import="viensdansmacave.Wine" %>



<div class="fieldcontain ${hasErrors(bean: wineInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="wine.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${wineInstance?.name}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: wineInstance, field: 'year', 'error')} required">
	<label for="year">
		<g:message code="wine.year.label" default="Year" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="year" type="number" value="${wineInstance.year}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: wineInstance, field: 'color', 'error')} required">
	<label for="color">
		<g:message code="wine.color.label" default="Color" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="color" from="${wine.WineColor?.values()}" keys="${wine.WineColor.values()*.name()}" required="" value="${wineInstance?.color?.name()}" />

</div>

