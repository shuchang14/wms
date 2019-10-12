package com.shu.wms.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.File;
import java.util.List;

public class XmlUtil {

	public static <T>List<T> readXmlList(Class<T> c,String path){
		XStream xs = new XStream(new DomDriver());
		xs.alias(c.getSimpleName(), c);
		@SuppressWarnings("unchecked")
		List<T> list = (List<T>)xs.fromXML(new File(path));
		return list;
	}
	
	public static <T>Object readXmlObj(Class<T> c,String path){
		XStream xs = new XStream(new DomDriver());
		xs.alias(c.getSimpleName(), c);
	    return xs.fromXML(new File(path));
	}
}
