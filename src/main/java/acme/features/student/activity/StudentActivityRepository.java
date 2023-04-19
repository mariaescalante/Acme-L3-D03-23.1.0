
package acme.features.student.activity;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.datatypes.ActivityType;
import acme.entities.Activity;
import acme.entities.Enrolment;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface StudentActivityRepository extends AbstractRepository {

	@Query("select e from Enrolment e where e.id = :id")
	Enrolment findOneEnrolmentById(int id);

	@Query("select a from Activity a where a.id = :id")
	Activity findOneActivityById(int id);

	@Query("select a from Activity a where a.enrolment.id = :enrolmentId")
	Collection<Activity> findManyActivitiesByEnrolmentId(int enrolmentId);

	@Query("select a.enrolment from Activity a where a.id = :activityId")
	Enrolment findOneEnrolmentByActivityId(int activityId);

	@Query("select a.indication from Activity a where a.id = :activityId")
	ActivityType findOneActivityTypeByActivityId(int activityId);

}
