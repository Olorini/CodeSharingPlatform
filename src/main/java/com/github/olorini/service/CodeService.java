package com.github.olorini.service;

import com.github.olorini.CodeHtmlBuilder;
import com.github.olorini.db.Snippet;
import com.github.olorini.db.SnippetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CodeService {

	private final SnippetRepository repository;
	private final CodeHtmlBuilder builder = new CodeHtmlBuilder();

	@Autowired
	public CodeService(SnippetRepository repository) {
		this.repository = repository;
	}

	CodeResponse getCode(long id) {
		Optional<Snippet> snippet = repository.findById(id);
		return snippet.map(CodeResponse::new).orElse(null);
	}

	public long createCode(CodeRequest request) {
		Snippet snippet = new Snippet(request.getCode());
		Snippet answer = repository.save(snippet);
		return answer.getId();
	}

	public List<CodeResponse> getLatestSnippets() {
		List<Snippet> latestSnippets = repository.findTop10ByOrderByIdDesc();
		return latestSnippets.stream()
				.map(CodeResponse::new)
				.collect(Collectors.toList());
	}

	String getHtmlCodePage(long id) {
		Optional<Snippet> snippet = repository.findById(id);
		return snippet.map(builder::getCodeSnippet).orElse(builder.getPageNotFound());
	}

	String getHtmlInputForm() {
		return builder.getInputSnippetForm();
	}

	String getHtmlLatestSnippets() {
		List<Snippet> latestSnippets = repository.findTop10ByOrderByIdDesc();
		return builder.getLatestCodeSnippets(latestSnippets);
	}

}
