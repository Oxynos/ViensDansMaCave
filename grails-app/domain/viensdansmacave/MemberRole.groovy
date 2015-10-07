package viensdansmacave

import grails.gorm.DetachedCriteria
import groovy.transform.ToString

import org.apache.commons.lang.builder.HashCodeBuilder

@ToString(cache=true, includeNames=true, includePackage=false)
class MemberRole implements Serializable {

	private static final long serialVersionUID = 1

	Member member
	Role role

	MemberRole(Member u, Role r) {
		this()
		member = u
		role = r
	}

	@Override
	boolean equals(other) {
		if (!(other instanceof MemberRole)) {
			return false
		}

		other.member?.id == member?.id && other.role?.id == role?.id
	}

	@Override
	int hashCode() {
		def builder = new HashCodeBuilder()
		if (member) builder.append(member.id)
		if (role) builder.append(role.id)
		builder.toHashCode()
	}

	static MemberRole get(long memberId, long roleId) {
		criteriaFor(memberId, roleId).get()
	}

	static boolean exists(long memberId, long roleId) {
		criteriaFor(memberId, roleId).count()
	}

	private static DetachedCriteria criteriaFor(long memberId, long roleId) {
		MemberRole.where {
			member == Member.load(memberId) &&
			role == Role.load(roleId)
		}
	}

	static MemberRole create(Member member, Role role, boolean flush = false) {
		def instance = new MemberRole(member, role)
		instance.save(flush: flush, insert: true)
		instance
	}

	static boolean remove(Member u, Role r, boolean flush = false) {
		if (u == null || r == null) return false

		int rowCount = MemberRole.where { member == u && role == r }.deleteAll()

		if (flush) { MemberRole.withSession { it.flush() } }

		rowCount
	}

	static void removeAll(Member u, boolean flush = false) {
		if (u == null) return

		MemberRole.where { member == u }.deleteAll()

		if (flush) { MemberRole.withSession { it.flush() } }
	}

	static void removeAll(Role r, boolean flush = false) {
		if (r == null) return

		MemberRole.where { role == r }.deleteAll()

		if (flush) { MemberRole.withSession { it.flush() } }
	}

	static constraints = {
		role validator: { Role r, MemberRole ur ->
			if (ur.member == null || ur.member.id == null) return
			boolean existing = false
			MemberRole.withNewSession {
				existing = MemberRole.exists(ur.member.id, r.id)
			}
			if (existing) {
				return 'userRole.exists'
			}
		}
	}

	static mapping = {
		id composite: ['member', 'role']
		version false
	}
}
