
package acme.features.lecturer.lecture;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.Lecture;
import acme.framework.controllers.AbstractController;
import acme.roles.Lecturer;

@Controller
public class LecturerLectureController extends AbstractController<Lecturer, Lecture> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected LecturerLectureListCourseService	listCourseService;

	@Autowired
	protected LecturerLectureListMineService	listMineService;

	@Autowired
	protected LecturerLectureShowService		showService;

	@Autowired
	protected LecturerLectureCreateService		createService;

	@Autowired
	protected LecturerLectureUpdateService		updateService;

	@Autowired
	protected LecturerLecturePublishService		publishService;

	@Autowired
	protected LecturerLectureDeleteService		deleteService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("show", this.showService);
		super.addBasicCommand("create", this.createService);
		super.addBasicCommand("update", this.updateService);
		super.addBasicCommand("delete", this.deleteService);

		super.addCustomCommand("list-course", "list", this.listCourseService);
		super.addCustomCommand("list-mine", "list", this.listMineService);
		super.addCustomCommand("publish", "update", this.publishService);
	}

}
