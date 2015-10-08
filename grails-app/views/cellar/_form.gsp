<%@ page import="viensdansmacave.Cellar" %>



<div class="fieldcontain ${hasErrors(bean: cellarInstance, field: 'rate', 'error')} required">
    <label for="rate">
        <g:message code="cellar.rate.label" default="Rate"/>
        <span class="required-indicator">*</span>
    </label>
    <g:field name="rate" value="${fieldValue(bean: cellarInstance, field: 'rate')}" required=""/>

</div>

