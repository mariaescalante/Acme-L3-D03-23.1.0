
package acme.features.auditor.audit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.Audit;
import acme.framework.controllers.AbstractController;
import acme.roles.Auditor;

@Controller
public class AuditorAuditController extends AbstractController<Auditor, Audit> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AuditorAuditShowService		showService;

	@Autowired
	protected AuditorAuditCreateService		createService;

	@Autowired
	protected AuditorAuditUpdateService		updateService;

	@Autowired
	protected AuditorAuditDeleteService		deleteService;

	@Autowired
	protected AuditorAuditListService		listService;

	@Autowired
	protected AuditorAuditPublishService	publishService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("show", this.showService);
		super.addBasicCommand("create", this.createService);
		super.addBasicCommand("update", this.updateService);
		super.addBasicCommand("delete", this.deleteService);
		super.addBasicCommand("list", this.listService);

		super.addCustomCommand("publish", "update", this.publishService);
	}

}
