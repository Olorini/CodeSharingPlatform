package com.github.olorini.service;

import com.github.olorini.CodeHtmlBuilder;
import com.github.olorini.db.Snippet;
import com.github.olorini.db.SnippetRepository;
import com.github.olorini.ecxeptions.ResourceNotFoundException;
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

	CodeResponse getCode(String id) throws ResourceNotFoundException {
		Optional<Snippet> optionalSnippet = repository.findAccessibleSnippet(id);
		if (optionalSnippet.isPresent()) {
			Snippet snippet = optionalSnippet.get();
			if (snippet.getViewsCount()!= null && snippet.getViewsCount() > 0) {
				snippet.decrementViews();
				repository.save(snippet);
			}
			return new CodeResponse(snippet);
		}
		throw new ResourceNotFoundException();
	}

	public String createCode(CodeRequest request) {
		Snippet snippet = new Snippet(request.getCode(), request.getTime(), request.getViews());
 		Snippet answer = repository.save(snippet);
		return answer.getId();
	}

	public List<CodeResponse> getLatestSnippets() {
		List<Snippet> latestSnippets = repository.findLatest10Snippets();
		return latestSnippets.stream()
				.map(CodeResponse::new)
				.collect(Collectors.toList());
	}

	String getHtmlCodePage(String id) {
		try {
			CodeResponse response = getCode(id);
			return builder.getCodeSnippet(response);
		} catch (ResourceNotFoundException e) {
			return builder.getPageNotFound();
		}
	}

	String getHtmlInputForm() {
		return builder.getInputSnippetForm();
	}

	String getHtmlLatestSnippets() {
		List<CodeResponse> latestSnippets = getLatestSnippets();
		return builder.getLatestCodeSnippets(latestSnippets);
	}

}
