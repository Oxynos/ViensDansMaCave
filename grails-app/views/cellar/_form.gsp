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

<div class="fieldcontain ${hasErrors(bean: cellarInstance, field: 'wines', 'error')} ">
	<label for="wines">
		<g:message code="cellar.wines.label" default="Wines" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${cellarInstance?.wines?}" var="w">
    <li><g:link controller="wineCellar" action="show" id="${w.id}">${w?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="wineCellar" action="create" params="['cellar.id': cellarInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'wineCellar.label', default: 'WineCellar')])}</g:link>
</li>
</ul>


</div>

