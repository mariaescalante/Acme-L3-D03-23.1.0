
package acme.features.any.note;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Note;
import acme.framework.components.accounts.Any;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;

@Service
public class AnyNoteShowService extends AbstractService<Any, Note> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnyNoteRepository repository;

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
		int masterId;
		Note note;

		masterId = super.getRequest().getData("id", int.class);
		note = this.repository.findOneNoteById(masterId);
		status = note != null;

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Note object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findOneNoteById(id);

		super.getBuffer().setData(object);
	}

	@Override
	public void unbind(final Note object) {
		assert object != null;
		Tuple tuple;

		tuple = super.unbind(object, "instantiationMoment", "title", "author", "message", "emailAddress", "link");

		super.getResponse().setData(tuple);
	}

}
