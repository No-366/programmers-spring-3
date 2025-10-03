package com.back.standard;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.stereotype.Service;

@Service
public class MarkdownService {

    public String toHtml(String content){
        Parser parser = Parser.builder().build();
        HtmlRenderer renderer = HtmlRenderer.builder().build();

        //파서 & 랜더러 준비
        Node document = parser.parse(content);
        //HTML 변환
        String html = renderer.render(document);

        return html;
    }
}
