package cn.jeeweb.modules.tbs.helper;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.xpath.XPathConstants;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.XPath;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * Xml文档解析工具类
 * 
 * @author Administrator
 * 
 */
public class XmlHelper {

	private static Logger logger = Logger.getLogger(XmlHelper.class);

	/**
	 * 从XML文件中读取Document对象
	 * 
	 * @param filePath
	 *            文件路径
	 * @return
	 */
	public static Document readXml(String filePath) {
		Document document = null;
		File file = new File(filePath);
		if (file.exists()) {
			SAXReader reader = new SAXReader();
			reader.setEncoding("UTF-8");
			try {
				document = reader.read(file);
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		}
		return document;
	}

	/**
	 * 从XML文件中读取Document对象
	 * 
	 * @param filePath
	 *            文件路径
	 * @return
	 */
	public static Document readXml_GBK(String filePath) {
		Document document = null;
		File file = new File(filePath);
		if (file.exists()) {
			SAXReader reader = new SAXReader();
			reader.setEncoding("GBK");
			try {
				document = reader.read(file);
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		}
		return document;
	}

	/**
	 * 将xml字符串解析成Document对象
	 * 
	 * @param xmlStr
	 *            xml字符串
	 * @return
	 */
	public static Document parseXml(String xmlStr) {
		Document document = null;
		if (xmlStr.indexOf("为准备运行的报表提供值") > 0) {
			logger.error("xml参数缺少：" + xmlStr);
		} else {
			try {
				document = DocumentHelper.parseText(xmlStr);
			} catch (DocumentException e) {
				logger.error("xml解析失败:" + xmlStr);
			}
		}
		return document;
	}

	/**
	 * 将文档格式化成XML字符串
	 * 
	 * @param document
	 *            文档
	 * @return
	 */
	public static String formatXml(Document document) {
		String xmlStr = null;
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("UTF-8");
		StringWriter sw = new StringWriter();
		XMLWriter xw = new XMLWriter(sw, format);
		xw.setEscapeText(true);
		try {
			xw.write(document);
			xmlStr = sw.toString();
			xw.flush();
			xw.close();
		} catch (IOException e) {
			logger.error("格式化XML文档发生异常，请检查!");
		}
		return xmlStr;
	}

	/**
	 * 将文档格式化成XML字符串
	 * 
	 * @param document
	 *            文档
	 * @return
	 */
	public static String formatXml(Document document, String encodeType) {
		String xmlStr = null;
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding(encodeType);
		StringWriter sw = new StringWriter();
		XMLWriter xw = new XMLWriter(sw, format);
		xw.setEscapeText(true);
		try {
			xw.write(document);
			xmlStr = sw.toString();
			xw.flush();
			xw.close();
		} catch (IOException e) {
			logger.error("格式化XML文档发生异常，请检查!");
		}
		return xmlStr;
	}

	/**
	 * 将文档格式化成XML字符串
	 * 
	 * @param document
	 *            文档
	 * @return
	 */
	public static String formatXml_NUllEncode(Document document) {
		String xmlStr = null;
		OutputFormat format = OutputFormat.createPrettyPrint();
		StringWriter sw = new StringWriter();
		XMLWriter xw = new XMLWriter(sw, format);
		xw.setEscapeText(true);
		try {
			xw.write(document);
			xmlStr = sw.toString();
			xw.flush();
			xw.close();
		} catch (IOException e) {
			logger.error("格式化XML文档发生异常，请检查!");
		}
		return xmlStr;
	}

	/**
	 * 将XML文档写到指定文件
	 * 
	 * @param document
	 * @param filePath
	 */
	public static void writeXml(Document document, String filePath) {
		try {
			File oFile = new File(filePath);
			FileOutputStream fos = new FileOutputStream(oFile);
			String xmlStr = formatXml(document);
			ByteArrayInputStream bais = new ByteArrayInputStream(xmlStr.getBytes("UTF-8"));
			logger.debug("XML文档正在保存到" + filePath);
			while (bais.available() > 0) {
				fos.write(bais.read());
			}
			;
			fos.flush();
			fos.close();
		} catch (Exception e) {
			logger.debug("XML文档保存到" + filePath + "失败");
		}
	}

	/**
	 * 获取XML文档对象指定XPATH的结点列表
	 * 
	 * @param document
	 *            XML文档
	 * @param xpath
	 *            结点路径
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Element> selectNodes(Document document, String xpathExpression) {
		XPath xPath = null;
		Element root = document.getRootElement();
		String uri = root.getNamespaceURI();
		if (uri != null && !"".equals(uri)) {
			Map<String, String> map = new HashMap<String, String>();
			String prefix = "ns";
			map.put(prefix, uri);
			xpathExpression = xpathExpression.replaceAll("/", "/" + prefix + ":");
			xpathExpression = xpathExpression.replaceAll("/ns:/", "//");
			xPath = document.createXPath(xpathExpression);
			xPath.setNamespaceURIs(map);
		} else {
			xPath = document.createXPath(xpathExpression);
		}
		List<Element> list = xPath.selectNodes(document);
		return list;
	}

	@SuppressWarnings("unchecked")
	public static List<Element> selectNodes(Element element, String xpathExpression) {
		XPath xPath = null;
		String uri = element.getNamespaceURI();
		if (uri != null && !"".equals(uri)) {
			Map<String, String> map = new HashMap<String, String>();
			String prefix = "ns";
			map.put(prefix, uri);
			xpathExpression = xpathExpression.replaceAll("/", "/" + prefix + ":");
			xpathExpression = xpathExpression.replaceAll("/ns:/", "//");
			xPath = element.createXPath(xpathExpression);
			xPath.setNamespaceURIs(map);
		} else {
			xPath = element.createXPath(xpathExpression);
		}
		List<Element> list = xPath.selectNodes(element);
		return list;
	}

	/**
	 * 获取XML文档对象指定结点的属性值
	 * 
	 * @param element
	 *            XML结点
	 * @param attributeName
	 *            属性名
	 * @return 属性值
	 */
	public static String getNodeAttributeValue(Element element, String attributeName) {
		return element.attributeValue(attributeName, "");
	}

	/**
	 * 获取XML文档对象指定结点的文本值
	 * 
	 * @param element
	 *            XML结点
	 * @return 文本值
	 */
	public static String getNodeText(Element element) {
		return element.getText();
	}

	/**
	 * 获取XML文档对象指定element节点下childNodeName的结点 用于element节点下只有一个子节点的情况下
	 * 
	 * @param element
	 * @param childNodeName
	 * @return
	 */
	public static Element getChildNode(Element element, String childNodeName) {
		return element.element(childNodeName);
	}

	/**
	 * 获取XML文档对象指定element节点下childNodeName的结点列表 用于element节点下有多个子节点的情况下
	 * 
	 * @param element
	 * @param childNodeName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Element> getChildNodes(Element element, String childNodeName) {
		return (List<Element>) element.elements(childNodeName);
	}

	/**
	 * 添加节点
	 * 
	 * @param element
	 * @param addNodeName
	 *            添加的子节点名字
	 * @return
	 */
	public static Element addNode(Element element, String addNodeName) {
		Element addNode = DocumentHelper.createElement(addNodeName);
		element.add(addNode);
		return addNode;
	}

	/**
	 * 删除子节点
	 * 
	 * @param element
	 * @param nodeName
	 *            要删除的节点名
	 * @return
	 */
	public static void removeChildNode(Element element, String nodeName) {
		Element nodeElement = getChildNode(element, nodeName);
		nodeElement.detach();
	}

	/**
	 * 设置节点内容
	 * 
	 * @param element
	 * @param updateNodeText
	 *            修改节点的文本
	 */
	public static void setNodeText(Element element, String updateNodeText) {
		element.setText(updateNodeText);
	}

	/**
	 * 设置节点属性和值
	 * 
	 * @param element
	 * @param attributeName属性名
	 * @param attributeValue属性值
	 */
	public static void addNodeAttributeValue(Element element, String attributeName, String attributeValue) {
		element.addAttribute(attributeName, attributeValue);
	}

	/**
	 * 删除节点属性
	 * 
	 * @param document
	 * @param XPATHEXPRESSION
	 *            节点路径 父级路径
	 * @param attributeName
	 *            属性名称 childNodeName 节点路径的子节点名
	 */
	public static void removeNodeAttribute(Element element, Attribute attribute) {
		element.remove(attribute);
	}

	/**
	 * 根据节点名称得到节点属性
	 * 
	 * @param element
	 * @param attributeName
	 * @return
	 */
	public static Attribute getNodeAttribute(Element element, String attributeName) {
		if (element != null) {
			return element.attribute(attributeName);
		}
		return null;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List xmltoList(String xml) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			Document document = DocumentHelper.parseText(xml);
			Element nodesElement = document.getRootElement();
			List<Element> nodes = nodesElement.elements();
			for (Iterator its = nodes.iterator(); its.hasNext();) {
				Element nodeElement = (Element) its.next();
				Map<String, Object> map = xmltoMap(nodeElement.asXML());
				list.add(map);
				map = null;
			}
			nodes = null;
			nodesElement = null;
			document = null;
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * xml字符串转换成bean对象
	 * 
	 * @param xmlStr
	 *            xml字符串
	 * @param clazz
	 *            待转换的class
	 * @return 转换后的对象
	 */
	@SuppressWarnings("rawtypes")
	public static Object xmlStrToBean(String xmlStr, Class clazz) {
		Object obj = null;
		try {
			// 将xml格式的数据转换成Map对象
			Map<String, Object> map = xmltoMap(xmlStr);
			//将map对象的数据转换成Bean对象
			obj = mapToBean(map, clazz);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	/**
	 * @Title: xmltoMap
	 * @Description: (将XML转成MAP)
	 * @param @param xml
	 * @param @return 设定文件
	 * @return Map 返回类型
	 * @throws
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map<String, Object> xmltoMap(String xml) {
		if (StringUtils.isBlank(xml))
			return new HashMap<String, Object>();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			Document document = DocumentHelper.parseText(xml);
			Element nodeElement = document.getRootElement();
			List<Element> node = nodeElement.elements();
			for (Iterator it = node.iterator(); it.hasNext();) {
				Element elm = (Element) it.next();
				map.put(elm.getName(), elm.getText());
				elm = null;
			}
			node = null;
			nodeElement = null;
			document = null;
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将Map对象通过反射机制转换成Bean对象
	 * 
	 * @param map
	 *            存放数据的map对象
	 * @param clazz
	 *            待转换的class
	 * @return 转换后的Bean对象
	 * @throws Exception
	 *             异常
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Object mapToBean(Map<String, Object> map, Class clazz) throws Exception {
		Object obj = clazz.newInstance();
		if (map != null && map.size() > 0) {
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				String propertyName = entry.getKey();
				Object value = entry.getValue();
				String setMethodName = "set" + propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
				Field field = getClassField(clazz, propertyName);
				Class fieldTypeClass = field.getType();
				value = convertValType(value, fieldTypeClass);
				clazz.getMethod(setMethodName, field.getType()).invoke(obj, value);
			}
		}
		return obj;
	}

	/**
	 * 
	 * @Title: Bean2Map
	 * @Description: (Java beantoMap)
	 * @param @param javaBean
	 * @param @return 设定文件
	 * @return Map<K,V> 返回类型
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public static <K, V> Map<String, Object> Bean2Map(Object javaBean) {
		Map<K, V> ret = new HashMap<K, V>();
		try {
			Method[] methods = javaBean.getClass().getDeclaredMethods();
			for (Method method : methods) {
				if (method.getName().startsWith("get")) {
					String field = method.getName();
					field = field.substring(field.indexOf("get") + 3);
					field = field.toLowerCase().charAt(0) + field.substring(1);
					Object value = method.invoke(javaBean, (Object[]) null);
					ret.put((K) field, (V) (null == value ? "" : value));
				}
			}
		} catch (Exception e) {
		}
		return (Map<String, Object>) ret;
	}

	/**
	 * 将Object类型的值，转换成bean对象属性里对应的类型值
	 * 
	 * @param value
	 *            Object对象值
	 * @param fieldTypeClass
	 *            属性的类型
	 * @return 转换后的值
	 */
	private static Object convertValType(Object value, @SuppressWarnings("rawtypes") Class fieldTypeClass) {
		Object retVal = null;
		if (Long.class.getName().equals(fieldTypeClass.getName()) || long.class.getName().equals(fieldTypeClass.getName())) {
			retVal = Long.parseLong(value.toString());
		} else if (Integer.class.getName().equals(fieldTypeClass.getName()) || int.class.getName().equals(fieldTypeClass.getName())) {
			retVal = Integer.parseInt(value.toString());
		} else if (Float.class.getName().equals(fieldTypeClass.getName()) || float.class.getName().equals(fieldTypeClass.getName())) {
			retVal = Float.parseFloat(value.toString());
		} else if (Double.class.getName().equals(fieldTypeClass.getName()) || double.class.getName().equals(fieldTypeClass.getName())) {
			retVal = Double.parseDouble(value.toString());
		} else {
			retVal = value;
		}
		return retVal;
	}

	/**
	 * 获取指定字段名称查找在class中的对应的Field对象(包括查找父类)
	 * 
	 * @param clazz
	 *            指定的class
	 * @param fieldName
	 *            字段名称
	 * @return Field对象
	 */
	@SuppressWarnings("rawtypes")
	private static Field getClassField(Class clazz, String fieldName) {
		if (Object.class.getName().equals(clazz.getName())) {
			return null;
		}
		Field[] declaredFields = clazz.getDeclaredFields();
		for (Field field : declaredFields) {
			if (field.getName().equals(fieldName)) {
				return field;
			}
		}
		Class superClass = clazz.getSuperclass();
		if (superClass != null) {// 简单的递归一下
			return getClassField(superClass, fieldName);
		}
		return null;
	}

	public static String mapToXml(Map<String, Object> map) {
		if (map != null && map.size() > 0) {
			Document document = DocumentHelper.createDocument();
			Element nodeElement = document.addElement("return");
			for (Object obj : map.keySet()) {
				Element keyElement = nodeElement.addElement(String.valueOf(obj));
				keyElement.setText(String.valueOf(map.get(obj)));
			}
			return formatXml(document);
		}
		return null;
	}

	public static String mapToXml(Map<String, Object> map, String rootElement, String encodeType) {
		if (map != null && map.size() > 0) {
			Document document = DocumentHelper.createDocument();
			Element nodeElement = document.addElement(rootElement);
			for (Object obj : map.keySet()) {
				Element keyElement = nodeElement.addElement(String.valueOf(obj));
				keyElement.setText(String.valueOf(map.get(obj)));
			}
			return formatXml(document, encodeType);

		}
		return null;
	}

	
	public static String mapToXmlForALLTYPE(Map<String, Object> map){
		return mapToXmlForALLTYPE(map,"tbl");
		
	}
	/**
	 * 
	 * @Title: mapToXml
	 * @Description: (所有类型的都加入MAP转XML范围内) 支持的MAP范围为 LIST MAP
	 * @param @param map
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@SuppressWarnings("rawtypes")
	public static String mapToXmlForALLTYPE(Map<String, Object> map, String rootElement) {
		if (map != null && map.size() > 0) {
			Document document = DocumentHelper.createDocument();
			Element nodeElement = document.addElement(rootElement);
			for (Object obj : map.keySet()) {
				String objName = String.valueOf(obj);
				Object o = map.get(objName);
				if ((o instanceof java.lang.String) || (o instanceof java.lang.Integer) || (o instanceof java.lang.Long)) {
					Element keyElement = nodeElement.addElement(objName.toLowerCase());
					if ("BLOBDATA".equalsIgnoreCase(objName)) {
						keyElement.addCDATA(StringHelper.getNotNullStr(o));
					} else {
						keyElement.setText(StringHelper.getNotNullStr(o));
					}
				} else if (o instanceof java.util.Map) {
					Element mapElement = nodeElement.addElement(objName.toLowerCase());
					for (Object obj1 : ((Map) o).keySet()) {
						String objName1 = String.valueOf(obj1);
						Object o1 = ((Map) o).get(objName1);
						Element mapElement1 = mapElement.addElement(objName1);
						if ("BLOBDATA".equalsIgnoreCase(objName1)) {
							mapElement1.addCDATA(StringHelper.getNotNullStr(o1));
						} else {
							mapElement1.setText(StringHelper.getNotNullStr(o1));
						}
					}
				} else if (o instanceof java.util.List) {
					Element listElement = nodeElement.addElement(objName.toLowerCase());
					List list = (List) o;
					for (int i = 0; i < list.size(); i++) {
						Object obj2 = list.get(i);
						Element itemElement = listElement.addElement(objName+"item");
						if (obj2 instanceof java.util.Map) {
							Map o2 = ((Map) obj2);
							for (Object obj3 : o2.keySet()) {
								String objName3 = String.valueOf(obj3);
								Object o3 = ((Map) o2).get(objName3);
								Element obj3Element = itemElement.addElement(objName3.toLowerCase());
								if ("BLOBDATA".equalsIgnoreCase(objName3)) {
									obj3Element.addCDATA(StringHelper.getNotNullStr(o3));
								} else {
									obj3Element.setText(StringHelper.getNotNullStr(o3));
								}
							}
						}
					}
				}
			}
			return formatXml(document);
		}
		return null;
	}

	/**
	 * 
	 * @Title: transXmlWithXSLT
	 * @Description: (通过xsl文件转换xml)
	 * @param @param inputXml
	 * @param @param xslUrl
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public static String transXmlWithXSLT(String inputXml, String xslUrl) {
		String resultXml = null;
		if ((StringUtils.isNotBlank(inputXml)) && (StringUtils.isNotBlank(xslUrl))) {
			TransformerFactory tranFac = TransformerFactory.newInstance();
			try {
				File xsltFile = new File(xslUrl);
				Source xlstSource = new StreamSource(new InputStreamReader(new FileInputStream(xsltFile), "utf-8"));
				Templates template = tranFac.newTemplates(xlstSource);
				Transformer transformer = template.newTransformer();
				transformer.setOutputProperty("indent", "yes");
				transformer.setOutputProperty("encoding", "utf-8");
				StreamResult result = new StreamResult(new StringWriter());
				Source src = new StreamSource(new StringReader(inputXml));
				transformer.transform(src, result);
				resultXml = result.getWriter().toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return resultXml;
	}
 

	/**
	 * 字符串转义
	 * 
	 * @param inputXML
	 * @return
	 * @throws Exception
	 */
	public static String formatXML_cdata(String inputXML) throws Exception {
		SAXReader reader = new SAXReader();
		org.dom4j.Document document = (org.dom4j.Document) reader.read(new StringReader(inputXML));
		String requestXML = null;
		XMLWriter xw = null;
		if (document != null) {
			try {
				OutputFormat format = OutputFormat.createPrettyPrint();
				format.setEncoding("UTF-8");
				StringWriter sw = new StringWriter();
				xw = new XMLWriter(sw, format);
				xw.setEscapeText(false);
				xw.write(document);
				requestXML = sw.toString();
				xw.flush();
			} finally {
				if (xw != null) {
					try {
						xw.close();
					} catch (IOException e) {
						throw e;
					}
				}
			}
		}
		return requestXML;
	}
 

	/**
	 * 
	 * @Title: getXpath
	 * @Description: (获取Xpath的值)
	 * @param @param sourceXML
	 * @param @param xpathString
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public static String getXpath(String sourceXML, String xpathString) {
		if (StringUtils.isBlank(sourceXML))
			return null;
		String r = null;
		javax.xml.parsers.DocumentBuilder builder;
		try {
			builder = javax.xml.parsers.DocumentBuilderFactory.newInstance().newDocumentBuilder();
			org.w3c.dom.Document document = builder.parse(new ByteArrayInputStream(sourceXML.toString().getBytes("gbk")));
			javax.xml.xpath.XPath xpath = javax.xml.xpath.XPathFactory.newInstance().newXPath();
			r = (String) xpath.evaluate(xpathString, document, XPathConstants.STRING);
		} catch (Exception e) {
			//  Auto-generated catch block
			e.printStackTrace();
		}
		return r;

	}

	/**
	 * 
	 * @Title: convertW3cToDom4j
	 * @Description: (实现 org.w3c.dom.Document到org.dom4j.Document的转换)
	 * @param @param org.w3c.dom.Document
	 * @param @param
	 * @param @return 设定文件
	 * @return org.dom4j.Document 返回类型
	 * @throws
	 */
	public static org.dom4j.Document convertW3cToDom4j(org.w3c.dom.Document doc) throws Exception {
		if (doc == null) {
			return (null);
		}
		org.dom4j.io.DOMReader xmlReader = new org.dom4j.io.DOMReader();
		return (xmlReader.read(doc));
	}

}
