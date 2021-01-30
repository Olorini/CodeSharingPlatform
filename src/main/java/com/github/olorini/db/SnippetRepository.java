package com.github.olorini.db;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SnippetRepository extends CrudRepository<Snippet, Long> {

	List<Snippet> findTop10ByOrderByIdDesc();

}
