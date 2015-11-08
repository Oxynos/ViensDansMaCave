<%@ page import="viensdansmacave.Wine" %>



<div class="${hasErrors(bean: wineInstance, field: 'name', 'has-error')} form-group">
	<label for="name" class="control-label">
		<g:message code="wine.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${wineInstance?.name}" class="form-control"/>

</div>

<div class="${hasErrors(bean: wineInstance, field: 'year', 'has-error')} form-group">
	<label for="year" class="control-label">
		<g:message code="wine.year.label" default="Year" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="year" type="number" value="${wineInstance.year}" required="" class="form-control"/>

</div>

<div class="${hasErrors(bean: wineInstance, field: 'color', 'has-error')} form-group">
	<label for="color" class="control-label">
		<g:message code="wine.color.label" default="Color" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="color" from="${wine.WineColor?.values()}" keys="${wine.WineColor.values()*.name()}" required="" value="${wineInstance?.color?.name()}" class="form-control"/>

</div>

