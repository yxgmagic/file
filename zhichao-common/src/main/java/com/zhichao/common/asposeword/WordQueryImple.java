package com.zhichao.common.asposeword;

import java.util.ArrayList;

import com.aspose.words.Document;

public class WordQueryImple implements WordQuery {
	
	/**
	 * 获取单个文档
	 */
	@Override
	public Document GetDocment(String path) {
		Document doc;
		try {
			doc = new Document(path);
		} catch (Exception e) {
			doc = null;
		}
		return doc;
	}

	/**
	 * 获取文档列表
	 */
	@Override
	public ArrayList GetDocmentList(String[] paths) {
		try {
			ArrayList docArrayList = null;
			if (0 == paths.length)
				return docArrayList;
			Document doc;
			docArrayList = new ArrayList();
			for (int i = 0; i < paths.length; i++) {
				doc = new Document(paths[i]);
				docArrayList.add(doc);
			}
			return docArrayList;
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 获取文档列表
	 */
	@Override
	public ArrayList GetDocmentList(ArrayList paths) {
		try {
			ArrayList docArrayList = null;
			if (0 == paths.size())
				return docArrayList;
			Document doc;
			docArrayList = new ArrayList();
			for (int i = 0; i < paths.size(); i++) {
				doc = new Document((String)paths.get(i));
				docArrayList.add(doc);
			}
			return docArrayList;
		} catch (Exception e) {
			return null;
		}
	}
}
