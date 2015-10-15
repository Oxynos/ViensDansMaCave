<%@ page import="viensdansmacave.Member" %>



<div class="fieldcontain ${hasErrors(bean: memberInstance, field: 'username', 'error')} required">
	<label for="username">
		<g:message code="member.username.label" default="Username" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="username" required="" value="${memberInstance?.username}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: memberInstance, field: 'password', 'error')} required">
	<label for="password">
		<g:message code="member.password.label" default="Password" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="password" required="" value="${memberInstance?.password}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: memberInstance, field: 'email', 'error')} ">
	<label for="email">
		<g:message code="member.email.label" default="Email" />
		
	</label>
	<g:field type="email" name="email" value="${memberInstance?.email}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: memberInstance, field: 'birthday', 'error')} ">
	<label for="birthday">
		<g:message code="member.birthday.label" default="Birthday" />
		
	</label>
	<g:datePicker name="birthday" precision="day"  value="${memberInstance?.birthday}" default="none" noSelection="['': '']" />

</div>

<div class="fieldcontain ${hasErrors(bean: memberInstance, field: 'country', 'error')} ">
	<label for="country">
		<g:message code="member.country.label" default="Country" />
		
	</label>
	<g:textField name="country" value="${memberInstance?.country}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: memberInstance, field: 'city', 'error')} ">
	<label for="city">
		<g:message code="member.city.label" default="City" />
		
	</label>
	<g:textField name="city" value="${memberInstance?.city}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: memberInstance, field: 'cellar', 'error')} ">
	<label for="cellar">
		<g:message code="member.cellar.label" default="Cellar" />
		
	</label>
	<g:select id="cellar" name="cellar.id" from="${viensdansmacave.Cellar.list()}" optionKey="id" value="${memberInstance?.cellar?.id}" class="many-to-one" noSelection="['null': '']"/>

</div>

<div class="fieldcontain ${hasErrors(bean: memberInstance, field: 'accountExpired', 'error')} ">
	<label for="accountExpired">
		<g:message code="member.accountExpired.label" default="Account Expired" />
		
	</label>
	<g:checkBox name="accountExpired" value="${memberInstance?.accountExpired}" />

</div>

<div class="fieldcontain ${hasErrors(bean: memberInstance, field: 'accountLocked', 'error')} ">
	<label for="accountLocked">
		<g:message code="member.accountLocked.label" default="Account Locked" />
		
	</label>
	<g:checkBox name="accountLocked" value="${memberInstance?.accountLocked}" />

</div>

<div class="fieldcontain ${hasErrors(bean: memberInstance, field: 'enabled', 'error')} ">
	<label for="enabled">
		<g:message code="member.enabled.label" default="Enabled" />
		
	</label>
	<g:checkBox name="enabled" value="${memberInstance?.enabled}" />

</div>

<div class="fieldcontain ${hasErrors(bean: memberInstance, field: 'passwordExpired', 'error')} ">
	<label for="passwordExpired">
		<g:message code="member.passwordExpired.label" default="Password Expired" />
		
	</label>
	<g:checkBox name="passwordExpired" value="${memberInstance?.passwordExpired}" />

</div>

