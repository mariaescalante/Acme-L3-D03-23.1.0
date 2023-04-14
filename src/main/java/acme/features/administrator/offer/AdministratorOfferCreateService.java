
package acme.features.administrator.offer;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Offer;
import acme.framework.components.accounts.Administrator;
import acme.framework.components.models.Tuple;
import acme.framework.helpers.MomentHelper;
import acme.framework.services.AbstractService;

@Service
public class AdministratorOfferCreateService extends AbstractService<Administrator, Offer> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorOfferRepository repository;

	// AbstractService ---------------------------


	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void check() {
		super.getResponse().setChecked(true);
	}

	@Override
	public void load() {
		final Offer object = new Offer();
		final Date actualDate = MomentHelper.getCurrentMoment();

		object.setInstantiationMoment(actualDate);

		super.getBuffer().setData(object);
	}

	@Override
	public void bind(final Offer object) {
		assert object != null;

		super.bind(object, "instantiationMoment", "heading", "summary", "startDate", "endDate", "price", "optionalLink");
	}

	@Override
	public void validate(final Offer object) {
		assert object != null;

		/*
		 * if (!super.getBuffer().getErrors().hasErrors("price"))
		 * super.state(this.auxiliarService.validatePrice(object.getPrice()), "price", "administrator.offer.form.error.price");
		 * 
		 * if (!super.getBuffer().getErrors().hasErrors("startPeriod")) {
		 * Date minimumStartDate;
		 * minimumStartDate = MomentHelper.deltaFromMoment(object.getInstantiationMoment(), 1, ChronoUnit.DAYS);
		 * super.state(MomentHelper.isAfter(object.getStartPeriod(), minimumStartDate), "startPeriod", "administrator.offer.form.error.startPeriod");
		 * }
		 * 
		 * if (!super.getBuffer().getErrors().hasErrors("endPeriod") && !super.getBuffer().getErrors().hasErrors("startPeriod")) {
		 * Date maximumPeriod;
		 * maximumPeriod = MomentHelper.deltaFromMoment(object.getStartPeriod(), 7, ChronoUnit.DAYS);
		 * super.state(MomentHelper.isAfter(object.getEndPeriod(), maximumPeriod) && object.getEndPeriod().after(object.getStartPeriod()), "endPeriod", "administrator.offer.form.error.endPeriod");
		 * }
		 * if (!super.getBuffer().getErrors().hasErrors("heading"))
		 * super.state(this.auxiliarService.validateTextImput(object.getHeading()), "heading", "administrator.offer.form.spam");
		 * if (!super.getBuffer().getErrors().hasErrors("summary"))
		 * super.state(this.auxiliarService.validateTextImput(object.getSummary()), "summary", "administrator.offer.form.spam");
		 */
	}

	@Override
	public void perform(final Offer object) {
		assert object != null;

		this.repository.save(object);
	}

	@Override
	public void unbind(final Offer object) {
		assert object != null;

		Tuple tuple;

		tuple = super.unbind(object, "instantiationMoment", "heading", "summary", "startDate", "endDate", "price", "optionalLink");

		super.getResponse().setData(tuple);
	}
}
