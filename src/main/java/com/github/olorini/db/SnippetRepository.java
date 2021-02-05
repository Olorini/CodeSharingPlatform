package com.github.olorini.db;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SnippetRepository extends CrudRepository<Snippet, Long> {

	@Query( "FROM snippets" +
			" WHERE id = :id" +
			" AND ((viewsCount IS NULL AND timeOfDisable IS NULL)" +
			" OR (viewsCount IS NULL AND timeOfDisable > current_timestamp)" +
			" OR (viewsCount > 0 AND timeOfDisable IS NULL)" +
			" OR (viewsCount > 0 AND timeOfDisable > current_timestamp))"
	)
	Optional<Snippet> findAccessibleSnippet(@Param("id") String id);

	@Query( value = "SELECT * FROM snippets" +
			" WHERE (views_count IS NULL AND time_of_disable IS NULL)" +
			" ORDER BY create_date DESC, code ASC LIMIT 10", nativeQuery = true
	)
	List<Snippet> findLatest10Snippets();

}