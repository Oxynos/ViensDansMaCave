<%@ page import="viensdansmacave.Cellar" %>



<div class="fieldcontain ${hasErrors(bean: cellarInstance, field: 'rate', 'error')} required">
	<label for="rate">
		<g:message code="cellar.rate.label" default="Rate" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="rate" value="${fieldValue(bean: cellarInstance, field: 'rate')}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: cellarInstance, field: 'member', 'error')} required">
	<label for="member">
		<g:message code="cellar.member.label" default="Member" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="member" name="member.id" from="${viensdansmacave.Member.list()}" optionKey="id" required="" value="${cellarInstance?.member?.id}" class="many-to-one"/>

</div>

