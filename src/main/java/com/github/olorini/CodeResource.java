package com.github.olorini;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
public class CodeResource {

	private final CodeService service;

	public CodeResource() {
		this.service = new CodeService();
	}

	@GetMapping(path = "/api/code/{id}")
	public CodeResponse getApiCodeSnippet(@PathVariable Integer id) {
		return service.getCode(id);
	}

	@PostMapping(path = "/api/code/new", consumes = "application/json")
	public Map<String, Integer> createApiCodeSnippet(@RequestBody CodeRequest request) {
		int id = service.createCode(request);
		return Collections.singletonMap("id", id);
	}

	@GetMapping(path = "/api/code/latest")
	public List<CodeResponse> getApiLatestSnippets() {
		return service.getLatestSnippets();
	}

	@GetMapping(path = "/code/{id}")
	public String getCodeSnippet(HttpServletResponse response, @PathVariable Integer id) {
		response.addHeader("Content-Type", "text/html");
		return service.getHtmlCodePage(id);
	}

	@GetMapping(path = "/code/new")
	public String createCodeSnippet(HttpServletResponse response) {
		response.addHeader("Content-Type", "text/html");
		return service.getHtmlInputForm();
	}

	@GetMapping(path = "/code/latest")
	public String getLatestSnippets() {
		return service.getHtmlLatestSnippets();
	}
}
