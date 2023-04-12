
package acme.features.assistant.tutorial;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.Tutorial;
import acme.framework.controllers.AbstractController;
import acme.roles.Assistant;

@Controller
public class AssistantTutorialController extends AbstractController<Assistant, Tutorial> {

	@Autowired
	protected AssistantTutorialCreateService	createService;
	@Autowired
	protected AssistantTutorialDeleteService	deleteService;
	@Autowired
	protected AssistantTutorialListService		listService;
	@Autowired
	protected AssistantTutorialPublishService	publishService;
	@Autowired
	protected AssistantTutorialShowService		showService;
	@Autowired
	protected AssistantTutorialUpdateService	updateService;


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("create", this.createService);
		super.addBasicCommand("delete", this.deleteService);
		super.addBasicCommand("list", this.listService);
		super.addCustomCommand("publish", "update", this.publishService);
		super.addBasicCommand("show", this.showService);
		super.addBasicCommand("update", this.updateService);
	}
}
