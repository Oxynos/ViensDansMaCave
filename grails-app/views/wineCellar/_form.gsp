<%@ page import="viensdansmacave.WineCellar" %>



<div class="fieldcontain ${hasErrors(bean: wineCellarInstance, field: 'cellar', 'error')} required">
	<label for="cellar">
		<g:message code="wineCellar.cellar.label" default="Cellar" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="cellar" name="cellar.id" from="${viensdansmacave.Cellar.list()}" optionKey="id" required="" value="${wineCellarInstance?.cellar?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: wineCellarInstance, field: 'quantity', 'error')} required">
	<label for="quantity">
		<g:message code="wineCellar.quantity.label" default="Quantity" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="quantity" type="number" value="${wineCellarInstance.quantity}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: wineCellarInstance, field: 'wine', 'error')} required">
	<label for="wine">
		<g:message code="wineCellar.wine.label" default="Wine" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="wine" name="wine.id" from="${viensdansmacave.Wine.list()}" optionKey="id" required="" value="${wineCellarInstance?.wine?.id}" class="many-to-one"/>

</div>

