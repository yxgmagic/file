package com.zhichao.common.asposeword;

import java.util.ArrayList;

import com.aspose.words.Document;

public interface WordQuery {
	 Document GetDocment(String path);
     ArrayList GetDocmentList(String[] paths);
     ArrayList GetDocmentList(ArrayList paths);
}
