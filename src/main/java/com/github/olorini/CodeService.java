package com.github.olorini;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CodeService {

	private final List<CodeModel> models = new ArrayList<>();
	private final CodeHtmlBuilder builder;

	public CodeService() {
		this.builder = new CodeHtmlBuilder();
	}

	CodeResponse getCode(Integer id) {
		if (id <= models.size() && id > 0) {
			return new CodeResponse(models.get(id - 1));
		} else {
			return null;
		}
	}

	public int createCode(CodeRequest request) {
		models.add(new CodeModel(request.getCode()));
		return models.size();
	}

	public List<CodeResponse> getLatestSnippets() {
		return models.stream()
				.sorted((o1, o2) -> {
					int res = (-1) * o1.getCreateDate().compareTo(o2.getCreateDate());
					if (res == 0) {
						return o1.getCode().compareTo(o2.getCode());
					} else {
						return res;
					}
				})
				.limit(10)
				.map(CodeResponse::new)
				.collect(Collectors.toList());
	}

	String getHtmlCodePage(Integer id) {
		if (id <= models.size() && id > 0) {
			return builder.getCodeSnippet(models.get(id - 1));
		} else {
			return builder.getPageNotFound();
		}
	}

	String getHtmlInputForm() {
		return builder.getInputSnippetForm();
	}

	String getHtmlLatestSnippets() {
		List<CodeModel> latestModels = models.stream()
		.sorted((o1, o2) -> {
			int res = (-1) * o1.getCreateDate().compareTo(o2.getCreateDate());
			if (res == 0) {
				return o1.getCode().compareTo(o2.getCode());
			} else {
				return res;
			}
		})
				.limit(10)
				.collect(Collectors.toList());
		return builder.getLatestCodeSnippets(latestModels);
	}

}
