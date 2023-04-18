
package acme.features.any.note;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Note;
import acme.framework.components.accounts.Any;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;

@Service
public class AnyNoteListService extends AbstractService<Any, Note> {

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
		Collection<Note> objects;
		objects = this.repository.findManyNote();

		super.getBuffer().setData(objects);
	}

	@Override
	public void unbind(final Note object) {
		assert object != null;

		Tuple tuple;
		tuple = super.unbind(object, "instantiationMoment", "title", "message");

		super.getResponse().setData(tuple);
	}

}
