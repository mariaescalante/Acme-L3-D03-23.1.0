
package acme.features.any.note;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.Note;
import acme.framework.components.accounts.Any;
import acme.framework.controllers.AbstractController;

@Controller
public class AnyNoteController extends AbstractController<Any, Note> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnyNoteShowService	showService;

	@Autowired
	protected AnyNoteListService	listAllService;

	@Autowired
	protected AnyNoteCreateService	createService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("show", this.showService);
		super.addBasicCommand("list", this.listAllService);
		super.addBasicCommand("create", this.createService);
	}

}
