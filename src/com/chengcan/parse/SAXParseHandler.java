package com.chengcan.parse;

import com.chengcan.entity.StringResource;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SAXParseHandler extends DefaultHandler {
    private static final String formatSpecifier
            = "%(\\d+\\$)?([-#+ 0,(\\<]*)?(\\d+)?(\\.\\d+)?([tT])?([a-zA-Z%])";
    private List<StringResource> resourceList;
    private StringResource resource;
    private String value;


    public List<StringResource> getResourceList() {
        return resourceList;
    }


    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        resourceList = new ArrayList<StringResource>();
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
//        System.out.println(resourceList.toString());

    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        if (qName.equals("string")) {
            resource = new StringResource(attributes.getValue("name"));
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        value = new String(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        if (qName.equals("string")) {
            resource.setValue(value);
            resource.setRegs(parse(value));
            resource.setOtherString(isOtherString(value));
            resourceList.add(resource);
        }

    }

    /**
     * 是@string/xxxx
     *
     * @param text
     * @return
     */
    private boolean isOtherString(String text) {
        return text != null && text.startsWith("@string/");
    }

    private List<String> parse(String text) {
        List<String> regs = new ArrayList<>();
        Pattern pat = Pattern.compile(formatSpecifier);
        Matcher matcher = pat.matcher(text);
        while (matcher.find()) {
            regs.add(matcher.group());
        }
        return regs;
    }
}
