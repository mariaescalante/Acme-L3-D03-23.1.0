/*
 * EmployerJobPublishService.java
 *
 * Copyright (C) 2012-2023 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.lecturer.course;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Course;
import acme.entities.CourseType;
import acme.entities.Lecture;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Lecturer;

@Service
public class LecturerCoursePublishService extends AbstractService<Lecturer, Course> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected LecturerCourseRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void check() {
		boolean status;

		status = super.getRequest().hasData("id", int.class);

		super.getResponse().setChecked(status);
	}

	@Override
	public void authorise() {
		boolean status;
		int courseId;
		Course course;
		Lecturer lecturer;

		courseId = super.getRequest().getData("id", int.class);
		course = this.repository.findOneCourseById(courseId);
		lecturer = course == null ? null : course.getLecturer();
		status = course != null && course.isDraftMode() && super.getRequest().getPrincipal().hasRole(lecturer);

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Course object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findOneCourseById(id);

		super.getBuffer().setData(object);
	}

	@Override
	public void bind(final Course object) {
		assert object != null;

		super.bind(object, "code", "title", "abstract$", "price", "link");

	}

	@Override
	public void validate(final Course object) {
		assert object != null;
		boolean purelyTheoretical;
		Collection<Lecture> lectures;
		Collection<Lecture> notPublished;

		lectures = this.repository.findManyLecturesByCourseId(object.getId());
		notPublished = this.repository.findNotPublishedLecturesByCourseId(object.getId());
		purelyTheoretical = object.purelyTheoretical(lectures);
		if (!super.getBuffer().getErrors().hasErrors("price"))
			super.state(object.getPrice().getAmount() > 0, "price", "lecturer.course.form.error.negative-price");
		if (!super.getBuffer().getErrors().hasErrors("theoreticalOrHandsOn"))
			super.state(!purelyTheoretical, "theoreticalOrHandsOn", "lecturer.course.form.error.purely-theoretical");
		super.state(notPublished.isEmpty(), "code", "lecturer.course.form.error.lectures-not-published");
	}

	@Override
	public void perform(final Course object) {
		assert object != null;

		object.setDraftMode(false);
		this.repository.save(object);
	}

	@Override
	public void unbind(final Course object) {
		assert object != null;

		Collection<Lecture> lectures;
		CourseType theoreticalOrHandsOn;
		Tuple tuple;

		tuple = super.unbind(object, "code", "title", "abstract$", "price", "link");
		lectures = this.repository.findManyLecturesByCourseId(object.getId());
		theoreticalOrHandsOn = object.theoreticalOrHandsOn(lectures);
		tuple.put("theoreticalOrHandsOn", theoreticalOrHandsOn);
		tuple.put("draftMode", object.isDraftMode());

		super.getResponse().setData(tuple);
	}

}
