package com.zhichao.common.asposeword;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.aspose.words.Document;
import com.aspose.words.ImportFormatMode;
import com.aspose.words.SectionStart;

@Component
public class WordAddImple implements WordAdd {

	WordQuery wordQuery = (WordQuery) new WordQueryImple();

	/**
	 * 合并文档从第1个文档开始合并后输出，连续
	 */
	@Override
	public Document AddDocs2Doc(ArrayList paths, String outputPath) {
		try {
			Document docAll = new Document();
			ArrayList docArrayList = wordQuery.GetDocmentList(paths);

			for (int i = 0; i < docArrayList.size(); i++) {
				Document doc = (Document) docArrayList.get(i);
				if (i == 0)
					docAll = doc;
				else {
					doc.getFirstSection().getPageSetup().setSectionStart(SectionStart.CONTINUOUS);
					docAll.appendDocument(doc, ImportFormatMode.KEEP_SOURCE_FORMATTING);
				}
			}

			docAll.save(outputPath);
			return docAll;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	/**
	 * 合并文档从第1个文档开始合并后输出，不连续分页
	 */
	@Override
	public Document AddDocs2DocByContinuous(ArrayList paths, String outputPath) {
		try {
			Document docAll = new Document();
			ArrayList docArrayList = wordQuery.GetDocmentList(paths);
			for (int i = 0; i < docArrayList.size(); i++) {
				Document doc = (Document) docArrayList.get(i);

				if (i == 0)
					docAll = doc;
				else {
					doc.getFirstSection().getPageSetup().setSectionStart(SectionStart.NEW_PAGE);
					docAll.appendDocument(doc, ImportFormatMode.KEEP_SOURCE_FORMATTING);
				}
			}

			docAll.save(outputPath);
			return docAll;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
}
