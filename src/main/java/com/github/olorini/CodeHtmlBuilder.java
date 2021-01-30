package com.github.olorini;

import com.github.olorini.db.Snippet;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.apache.commons.io.output.StringBuilderWriter;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CodeHtmlBuilder {

	private final Configuration cfg;
	private final Logger LOGGER = Logger.getLogger(CodeHtmlBuilder.class);

	public CodeHtmlBuilder() {
		this.cfg = new Configuration(Configuration.VERSION_2_3_29);
		try {
			cfg.setDirectoryForTemplateLoading(new File("src/main/resources/html"));
			cfg.setDefaultEncoding("UTF-8");
			cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
			cfg.setLogTemplateExceptions(false);
			cfg.setWrapUncheckedExceptions(true);
			cfg.setFallbackOnNullLoopVariable(false);
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}
	}

	public String getCodeSnippet(Snippet model) {
		try {
			return getHtml("code_snippet_template.ftlh", createViewHtmlBinding(model));
		} catch (TemplateException | IOException e) {
			LOGGER.error(e.getMessage());
			return null;
		}
	}

	public String getLatestCodeSnippets(List<Snippet> models) {
		try {
			return getHtml("latest_code_snippets_template.ftlh", createLatestViewsHtmlBinding(models));
		} catch (TemplateException | IOException e) {
			LOGGER.error(e.getMessage());
			return null;
		}
	}

	public String getInputSnippetForm() {
		try {
			Map<String, Object> binding = new HashMap<>();
			binding.put("title", "Create");
			return getHtml("input_snippet_template.ftlh", binding);
		} catch (TemplateException | IOException e) {
			LOGGER.error(e.getMessage());
			return null;
		}
	}

	public String getPageNotFound() {
		try {
			Map<String, Object> binding = new HashMap<>();
			binding.put("title", "404 Error");
			return getHtml("page_not_found_template.ftlh", binding);
		} catch (TemplateException | IOException e) {
			LOGGER.error(e.getMessage());
			return null;
		}
	}

	private String getHtml(String fileName, Map<String, Object> binding) throws IOException, TemplateException {
		Template template = cfg.getTemplate(fileName);
		Writer out = new StringBuilderWriter();
		template.process(binding, out);
		return out.toString();
	}

	private Map<String, Object> createLatestViewsHtmlBinding(List<Snippet> models) {
		Map<String, Object> binding = new HashMap<>();
		binding.put("title", "Latest");
		binding.put("snippets", models);
		return binding;
	}

	private Map<String, Object> createViewHtmlBinding(Snippet model) {
		Map<String, Object> binding = new HashMap<>();
		binding.put("title", "Code");
		binding.put("snippet", model);
		return binding;
	}
}
