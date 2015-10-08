package viensdansmacave

class Member implements Serializable {

	private static final long serialVersionUID = 1

	transient springSecurityService

	String username
	String password
	String email
	Date birthday
	String country
	String city
	boolean enabled = true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

	Member(String username, String password) {
		this()
		this.username = username
		this.password = password
	}

	@Override
	int hashCode() {
		username?.hashCode() ?: 0
	}

	@Override
	boolean equals(other) {
		is(other) || (other instanceof Member && other.username == username)
	}

	@Override
	String toString() {
		username
	}

	Set<Role> getAuthorities() {
		MemberRole.findAllByMember(this)*.role
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
	}

	static transients = ['springSecurityService']

	static constraints = {
		username blank: false, unique: true
		password blank: false
		email email:true, nullable: true
		birthday nullable: true, validator: {
			Calendar c1 = GregorianCalendar.getInstance()
			c1.set(Calendar.HOUR_OF_DAY, 0);
			c1.set(Calendar.MINUTE, 0);
			c1.set(Calendar.SECOND, 0);
			c1.set(Calendar.MILLISECOND, 0);
			if (it >= c1.getTime()) return false
		}
		country nullable: true
		city nullable: true


	}

	static mapping = {
		password column: '`password`'
	}
}
