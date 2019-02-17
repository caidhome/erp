package cn.itcast.erp.util.generator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import cn.itcast.erp.invoice.storeoper.vo.StoreOperModel;

public class GeneratorUitl {

	Class clazz;

	private String b;
	private String l;
	private String s;
	private String pkg;
	private String dir;

	public static void main(String[] args) throws Exception {
		// EmpModel RoleModel ResModel MenuModel
		// SupplierModel GoodsTypeModel GoodsModel
		// OrderModel OrderDetailModel
		// StoreModel StoreDetailModel StoreOperModel
		new GeneratorUitl(StoreOperModel.class);
		// 自动生成完之后还缺的操作
		System.out.println("struts.xml未进行映射");
		System.out.println("HbmXML未添加表关系");
		System.out.println("QueryMode为添加自定义范围查询条件");
		System.out.println("DaoImpl为对自定义范围查询条件进行条件设置");
	}

	public GeneratorUitl(Class clazz) throws Exception {
		this.clazz = clazz;
		// 1.数据初始化
		dataInit();

		// 2.创建目录
		generatorDirectory();

		// 3.QueryModel
		generatorQueryModel();

		// 4.Model.hbm.xml
		generatorHbmXML();

		// 5.dao
		generatorDao();

		// 6.impl
		generatorImpl();

		// 7.ebi
		generatorEbi();

		// 8.ebo
		generatorEbo();

		// 9.action
		generatorAction();

		// 10.applicateContext.xml
		generatorApplicateContextXML();

		// 11.struts.xml
		// generatorStrutsXML();

	}

	// 11.struts.xml
	private void generatorStrutsXML() throws Exception {
		File file = new File("resources/struts.xml");
		long len = file.length();
		byte[] b = new byte[(int) len];
		InputStream fis = new FileInputStream(file);
		fis.read(b);
		fis.close();
		String all = new String(b);
		int index = all.lastIndexOf("    </package>");
		String info = "    	<!-- " + s + " -->\r\n    	<action name=\"" + s
				+ "_*\" class=\"" + s
				+ "Action\" method=\"{1}\">\r\n    	</action>\r\n\r\n";
		StringBuilder sbf = new StringBuilder(all);
		sbf.insert(index, info);
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(sbf.toString().getBytes());
		fos.close();
	}

	// 10.applicateContext.xml
	private void generatorApplicateContextXML() throws Exception {
		File file = new File("resources/applicationContext-" + s + ".xml");
		if (file.exists()) {
			return;
		}
		file.createNewFile();
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));

		bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		bw.newLine();

		bw.write("<beans xmlns=\"http://www.springframework.org/schema/beans\"");
		bw.newLine();

		bw.write("	xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"");
		bw.newLine();

		bw.write("	xsi:schemaLocation=\"");
		bw.newLine();

		bw.write("		http://www.springframework.org/schema/beans ");
		bw.newLine();

		bw.write("		http://www.springframework.org/schema/beans/spring-beans.xsd\">");
		bw.newLine();

		bw.write("<!-- action -->");
		bw.newLine();

		bw.write("<bean id=\"" + s + "Action\" class=\"" + pkg + ".web." + b
				+ "Action\" scope=\"prototype\">");
		bw.newLine();

		bw.write("	<property name=\"" + s + "Ebi\" ref=\"" + s + "Ebi\"/>");
		bw.newLine();

		bw.write("</bean>");
		bw.newLine();

		bw.newLine();

		bw.write("<!-- business -->");
		bw.newLine();

		bw.write("<bean id=\"" + s + "Ebi\" class=\"" + pkg + ".business.ebo."
				+ b + "Ebo\">");
		bw.newLine();

		bw.write("	<property name=\"" + s + "Dao\" ref=\"" + s + "Dao\"/>");
		bw.newLine();

		bw.write("</bean>");
		bw.newLine();

		bw.newLine();

		bw.write("<!-- dao -->");
		bw.newLine();

		bw.write("<bean id=\"" + s + "Dao\" class=\"" + pkg + ".dao.impl." + b
				+ "Impl\">");
		bw.newLine();

		bw.write("	<property name=\"sessionFactory\" ref=\"sessionFactory\"/>");
		bw.newLine();

		bw.write("</bean>");
		bw.newLine();

		bw.write("</beans>");
		bw.flush();
		bw.close();
	}

	// 9.action
	private void generatorAction() throws Exception {
		File file = new File("src/" + dir + "/web/" + b + "Action.java");
		if (file.exists()) {
			return;
		}
		file.createNewFile();
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));

		bw.write("package " + pkg + ".web;");
		bw.newLine();

		bw.newLine();

		bw.write("import java.util.List;");
		bw.newLine();

		bw.newLine();

		bw.write("import " + pkg + ".business.ebi." + b + "Ebi;");
		bw.newLine();

		bw.write("import " + pkg + ".vo." + b + "Model;");
		bw.newLine();

		bw.write("import " + pkg + ".vo." + b + "QueryModel;");
		bw.newLine();

		bw.write("import cn.itcast.erp.util.base.BaseAction;");
		bw.newLine();

		bw.newLine();

		bw.write("public class " + b + "Action extends BaseAction {");
		bw.newLine();

		bw.write("	public " + b + "Model " + l + "m = new " + b + "Model();");
		bw.newLine();

		bw.write("	public " + b + "QueryModel " + l + "qm = new " + b
				+ "QueryModel();");
		bw.newLine();

		bw.newLine();

		bw.write("	private " + b + "Ebi " + s + "Ebi;");
		bw.newLine();

		bw.newLine();

		bw.write("	public void set" + b + "Ebi(" + b + "Ebi " + s + "Ebi) {");
		bw.newLine();

		bw.write("		this." + s + "Ebi = " + s + "Ebi;");
		bw.newLine();

		bw.write("	}");
		bw.newLine();

		bw.newLine();

		bw.write("	// 跳转到列表页面");
		bw.newLine();

		bw.write("	public String list() {");
		bw.newLine();

		bw.write("		setDataTotal(" + s + "Ebi.getCount(" + l + "qm));");
		bw.newLine();

		bw.write("		List<" + b + "Model> " + s + "List = " + s + "Ebi.getAll("
				+ l + "qm, pageNum, pageCount);");
		bw.newLine();

		bw.write("		put(\"" + s + "List\", " + s + "List);");
		bw.newLine();

		bw.write("		return LIST;");
		bw.newLine();

		bw.write("	}");
		bw.newLine();

		bw.newLine();

		bw.write("	// 跳转到添加页面");
		bw.newLine();

		bw.write("	public String input() {");
		bw.newLine();

		bw.write("		if (" + l + "m.getUuid() != null) {");
		bw.newLine();

		bw.write("			" + l + "m = " + s + "Ebi.get(" + l + "m.getUuid());");
		bw.newLine();

		bw.write("		}");
		bw.newLine();

		bw.write("		return INPUT;");
		bw.newLine();

		bw.write("	}");
		bw.newLine();

		bw.newLine();

		bw.write("	// 添加功能");
		bw.newLine();

		bw.write("	public String save() {");
		bw.newLine();

		bw.write("		if (" + l + "m.getUuid() == null) {");
		bw.newLine();

		bw.write("			" + s + "Ebi.save(" + l + "m);");
		bw.newLine();

		bw.write("		} else {");
		bw.newLine();

		bw.write("			" + s + "Ebi.update(" + l + "m);");
		bw.newLine();

		bw.write("		}");
		bw.newLine();

		bw.write("		return TO_LIST;");
		bw.newLine();

		bw.write("	}");
		bw.newLine();

		bw.newLine();

		bw.write("	// 删除");
		bw.newLine();

		bw.write("	public String delete() {");
		bw.newLine();

		bw.write("		" + s + "Ebi.delete(" + l + "m);");
		bw.newLine();

		bw.write("		return TO_LIST;");
		bw.newLine();

		bw.write("	}");
		bw.newLine();

		bw.newLine();

		bw.write("}");
		bw.flush();
		bw.close();
	}

	// 8.ebo
	private void generatorEbo() throws Exception {
		File file = new File("src/" + dir + "/business/ebo/" + b + "Ebo.java");
		if (file.exists()) {
			return;
		}
		file.createNewFile();
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));

		bw.write("package " + pkg + ".business.ebo;");
		bw.newLine();

		bw.newLine();

		bw.write("import java.io.Serializable;");
		bw.newLine();

		bw.write("import java.util.List;");
		bw.newLine();

		bw.newLine();

		bw.write("import " + pkg + ".business.ebi." + b + "Ebi;");
		bw.newLine();

		bw.write("import " + pkg + ".dao.dao." + b + "Dao;");
		bw.newLine();

		bw.write("import " + pkg + ".vo." + b + "Model;");
		bw.newLine();

		bw.write("import cn.itcast.erp.util.base.BaseQueryModel;");
		bw.newLine();

		bw.newLine();

		bw.write("public class " + b + "Ebo implements " + b + "Ebi {");
		bw.newLine();

		bw.newLine();

		bw.write("	private " + b + "Dao " + s + "Dao;");
		bw.newLine();

		bw.write("	public void set" + b + "Dao(" + b + "Dao " + s + "Dao) {");
		bw.newLine();

		bw.write("		this." + s + "Dao = " + s + "Dao;");
		bw.newLine();

		bw.write("	}");
		bw.newLine();

		bw.newLine();

		bw.write("	public void save(" + b + "Model " + l + "m) {");
		bw.newLine();

		bw.write("		" + s + "Dao.save(" + l + "m);");
		bw.newLine();

		bw.write("	}");
		bw.newLine();

		bw.newLine();

		bw.write("	public void update(" + b + "Model " + l + "m) {");
		bw.newLine();

		bw.write("		" + s + "Dao.update(" + l + "m);");
		bw.newLine();

		bw.write("	}");
		bw.newLine();

		bw.newLine();

		bw.write("	public void delete(" + b + "Model " + l + "m) {");
		bw.newLine();

		bw.write("		" + s + "Dao.delete(" + l + "m);");
		bw.newLine();

		bw.write("	}");
		bw.newLine();

		bw.newLine();

		bw.write("	public List<" + b + "Model> getAll() {");
		bw.newLine();

		bw.write("		return " + s + "Dao.getAll();");
		bw.newLine();

		bw.write("	}");
		bw.newLine();

		bw.newLine();

		bw.write("	public " + b + "Model get(Serializable uuid) {");
		bw.newLine();

		bw.write("		return " + s + "Dao.get(uuid);");
		bw.newLine();

		bw.write("	}");
		bw.newLine();

		bw.newLine();

		bw.write("	public List<" + b + "Model> getAll(BaseQueryModel " + l
				+ "qm, Integer pageNum, Integer pageCount) {");
		bw.newLine();

		bw.write("		return " + s + "Dao.getAll(" + l
				+ "qm, pageNum, pageCount);");
		bw.newLine();

		bw.write("	}");
		bw.newLine();

		bw.newLine();

		bw.write("	public Integer getCount(BaseQueryModel " + l + "qm) {");
		bw.newLine();

		bw.write("		return " + s + "Dao.getCount(" + l + "qm);");
		bw.newLine();

		bw.write("	}");
		bw.newLine();

		bw.write("}");

		bw.flush();
		bw.close();
	}

	// 7.ebi
	private void generatorEbi() throws Exception {
		File file = new File("src/" + dir + "/business/ebi/" + b + "Ebi.java");
		if (file.exists()) {
			return;
		}
		file.createNewFile();
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));

		bw.write("package " + pkg + ".business.ebi;");
		bw.newLine();

		bw.newLine();

		bw.write("import org.springframework.transaction.annotation.Transactional;");
		bw.newLine();

		bw.newLine();

		bw.write("import " + pkg + ".vo." + b + "Model;");
		bw.newLine();

		bw.write("import cn.itcast.erp.util.base.BaseEbi;");
		bw.newLine();

		bw.newLine();

		bw.write("@Transactional");
		bw.newLine();

		bw.write("public interface " + b + "Ebi extends BaseEbi<" + b
				+ "Model> {");
		bw.newLine();

		bw.newLine();

		bw.write("}");
		bw.flush();
		bw.close();
	}

	// 6.impl
	private void generatorImpl() throws Exception {
		File file = new File("src/" + dir + "/dao/impl/" + b + "Impl.java");
		if (file.exists()) {
			return;
		}
		file.createNewFile();
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));

		bw.write("package " + pkg + ".dao.impl;");
		bw.newLine();

		bw.newLine();

		bw.write("import org.hibernate.criterion.DetachedCriteria;");
		bw.newLine();

		bw.write("import org.hibernate.criterion.Restrictions;");
		bw.newLine();

		bw.newLine();

		bw.write("import " + pkg + ".dao.dao." + b + "Dao;");
		bw.newLine();

		bw.write("import " + pkg + ".vo." + b + "Model;");
		bw.newLine();

		bw.write("import " + pkg + ".vo." + b + "QueryModel;");
		bw.newLine();

		bw.write("import cn.itcast.erp.util.base.BaseImpl;");
		bw.newLine();

		bw.write("import cn.itcast.erp.util.base.BaseQueryModel;");
		bw.newLine();

		bw.newLine();

		bw.write("public class " + b + "Impl extends BaseImpl<" + b
				+ "Model> implements " + b + "Dao {");
		bw.newLine();

		bw.newLine();

		bw.write("	public void doQbc(DetachedCriteria dc, BaseQueryModel qm) {");
		bw.newLine();

		bw.write("		" + b + "QueryModel " + l + "qm = (" + b
				+ "QueryModel) qm;");
		bw.newLine();

		bw.write("		// TODO 编写自定义查询条件");
		bw.newLine();

		bw.newLine();

		bw.newLine();
		bw.write("	}");

		bw.newLine();
		bw.write("}");

		bw.flush();
		bw.close();
	}

	// 5.dao
	private void generatorDao() throws Exception {
		File file = new File("src/" + dir + "/dao/dao/" + b + "Dao.java");
		if (file.exists()) {
			return;
		}
		file.createNewFile();
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));

		bw.write("package " + pkg + ".dao.dao;");
		bw.newLine();

		bw.newLine();

		bw.write("import " + pkg + ".vo." + b + "Model;");
		bw.newLine();

		bw.write("import cn.itcast.erp.util.base.BaseDao;");
		bw.newLine();

		bw.newLine();

		bw.write("public interface " + b + "Dao extends BaseDao<" + b
				+ "Model> {");
		bw.newLine();

		bw.newLine();

		bw.write("}");
		bw.newLine();

		bw.flush();
		bw.close();
	}

	// 4.Model.hbm.xml
	public void generatorHbmXML() throws Exception {
		File file = new File("src/" + dir + "/vo/" + b + "Model.hbm.xml");
		if (file.exists()) {
			return;
		}
		file.createNewFile();
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));

		bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		bw.newLine();

		bw.write("<!DOCTYPE hibernate-mapping PUBLIC");
		bw.newLine();

		bw.write("    \"-//Hibernate/Hibernate Mapping DTD 3.0//EN\"");
		bw.newLine();

		bw.write("   \"http://www.hibernate.org/dtd/hibernate-mapping-3.0.dtd\">");
		bw.newLine();

		bw.write("<hibernate-mapping>");
		bw.newLine();

		bw.write("	<class name=\"" + pkg + ".vo." + b + "Model\" table=\"tbl_"
				+ s + "\">");
		bw.newLine();

		bw.write("		<id name=\"uuid\" column=\"uuid\">");
		bw.newLine();

		bw.write("			<generator class=\"native\"/>");
		bw.newLine();

		bw.write("		</id>");
		bw.newLine();

		Field[] fields = clazz.getDeclaredFields();

		for (Field field : fields) {
			if (field.getModifiers() == Modifier.PRIVATE
					&& !field.getName().equals("uuid"))
				if (field.getType().equals(String.class)
						|| field.getType().equals(Integer.class)
						|| field.getType().equals(Double.class)
						|| field.getType().equals(Long.class)) {
					if (!field.getName().endsWith("View")) {
						bw.write("		<property name=\"" + field.getName()
								+ "\" column=\"" + field.getName() + "\"/>");
						bw.newLine();
					}
				}
		}

		bw.write("	</class>");
		bw.newLine();
		bw.write("</hibernate-mapping>");
		bw.flush();
		bw.close();
	}

	// 3.QueryModel
	public void generatorQueryModel() throws Exception {
		File file = new File("src/" + dir + "/vo/" + b + "QueryModel.java");
		if (file.exists()) {
			return;
		}
		file.createNewFile();
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));

		bw.write("package " + pkg + ".vo;");
		bw.newLine();

		bw.newLine();

		bw.write("import cn.itcast.erp.util.base.BaseQueryModel;");
		bw.newLine();

		bw.newLine();

		bw.write("public class " + b + "QueryModel extends " + b
				+ "Model implements BaseQueryModel {");
		bw.newLine();
		bw.write("	// TODO 编写自定义查询条件");
		bw.newLine();
		bw.newLine();
		bw.write("}");
		bw.flush();
		bw.close();
	}

	// 2.创建目录
	public void generatorDirectory() {
		File f = new File("src/" + dir + "/business/ebi");
		f.mkdirs();
		f = new File("src/" + dir + "/business/ebo");
		f.mkdirs();
		f = new File("src/" + dir + "/dao/dao");
		f.mkdirs();
		f = new File("src/" + dir + "/dao/impl");
		f.mkdirs();
		f = new File("src/" + dir + "/web");
		f.mkdirs();
	}

	// 1.数据初始化
	public void dataInit() {
		String className = clazz.getSimpleName();
		b = className.substring(0, className.length() - 5);

		String first = b.substring(0, 1).toLowerCase();

		l = first.toLowerCase();

		s = l + b.substring(1);

		String rootPkg = clazz.getPackage().getName();

		pkg = rootPkg.substring(0, rootPkg.length() - 3);

		dir = pkg.replace('.', '/');
	}

}
