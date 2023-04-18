
package acme.features.any.note;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Note;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyNoteRepository extends AbstractRepository {

	@Query("select p from Note p where p.id = :id")
	Note findOneNoteById(int id);

	@Query("select p from Note p")
	Collection<Note> findManyNote();

}
