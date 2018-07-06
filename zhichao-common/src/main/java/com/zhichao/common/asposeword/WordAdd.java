package com.zhichao.common.asposeword;

import java.util.ArrayList;

import com.aspose.words.Document;

public interface WordAdd {
	Document AddDocs2Doc(ArrayList paths,String outputPath);  
    Document AddDocs2DocByContinuous(ArrayList paths, String outputPath);  
}
