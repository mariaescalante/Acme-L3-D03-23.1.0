
package acme.features.any.note;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Note;
import acme.framework.components.accounts.Any;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;

@Service
public class AnyNoteCreateService extends AbstractService<Any, Note> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnyNoteRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void check() {
		super.getResponse().setChecked(true);
	}

	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		Note object;

		object = new Note();

		super.getBuffer().setData(object);
	}

	@Override
	public void bind(final Note object) {
		assert object != null;

		super.bind(object, "instantiationMoment", "title", "author", "message", "emailAddress", "link");
	}

	@Override
	public void validate(final Note object) {
		assert object != null;
	}

	@Override
	public void perform(final Note object) {
		assert object != null;

		this.repository.save(object);
	}

	@Override
	public void unbind(final Note object) {
		assert object != null;

		Tuple tuple;

		tuple = super.unbind(object, "instantiationMoment", "title", "author", "message", "emailAddress", "link");

		super.getResponse().setData(tuple);
	}

}
