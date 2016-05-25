package com.douins.common.util;

import com.thoughtworks.xstream.XStream;

public class XmlUtil {

	public static String object2String(Object object, Class<?> clazz) {
		XStream xs = new XStream();
		xs.processAnnotations(clazz);
		String xml = xs.toXML(object);
		return xml;
	}

	public static Object string2Object(String xml, Class<?> clazz) {
		XStream xs = new XStream();
		xs.processAnnotations(clazz);
		Object object = xs.fromXML(xml);
		return object;
	}
}
