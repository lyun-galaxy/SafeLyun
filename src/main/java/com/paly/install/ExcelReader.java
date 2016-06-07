package com.paly.install;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import com.paly.domain.*;
import com.paly.service.*;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.SystemOutLogger;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @package ：com.changhongit.andy.util<br>
 * @file ：ExcelReader.java<br>
 * @describe ：读取 Excel 文件<br>
 * @author ：wanglongjie<br>
 * @createDate ：2015年8月31日下午1:37:32<br>
 * @updater ：<br>
 * @updateDate ：<br>
 * @updateContent ：<br>
 */
@Component
public class ExcelReader {
	private Workbook wb;
	private Sheet sheet;
	private Row row;
	@Resource
	private DepartmentService departmentService;
	@Resource
	private SpecialtyService specialtyService;
	@Resource
	private ClassesService classesService;
	@Resource
	private DatadicGroupsService datadicGroupsService;
	@Resource
	private DatadicItemsService datadicItemsService;

	/**
	 * 
	 * @method ：readExcelTitle<br>
	 * @describe ：读取 Excel 文件<br>
	 * @createDate ：2015年8月31日下午2:41:25 <br>
	 * @param fileName
	 *            ：Excel 文件路径
	 * @return String[]
	 */
	public String[] readExcelTitle(String fileName) {
		InputStream is;
		try {
			is = new FileInputStream(fileName);
			String postfix = fileName.substring(fileName.lastIndexOf("."), fileName.length());
			if (postfix.equals(".xls")) {
				// 针对 2003 Excel 文件
				wb = new HSSFWorkbook(new POIFSFileSystem(is));
				sheet = wb.getSheetAt(0);
			} else {
				// 针对2007 Excel 文件
				wb = new XSSFWorkbook(is);
				sheet = wb.getSheetAt(0);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = wb.getSheetAt(0);
		row = sheet.getRow(0);// 获取第一行（约定第一行是标题行）
		int colNum = row.getPhysicalNumberOfCells();// 获取行的列数
		String[] titles = new String[colNum];
		for (int i = 0; i < titles.length; i++) {
			titles[i] = getCellFormatValue(row.getCell(i));
		}
		return titles;
	}

	/**
	 * 
	 * @method ：readExcelContent<br>
	 * @describe ：读取 Excel 内容<br>
	 * @author ：wanglongjie<br>
	 * @createDate ：2015年8月31日下午3:12:06 <br>
	 * @param fileName
	 *            ：Excel 文件路径
	 * @return List<Map<String,String>>
	 */
	public List<Map<String, String>> readExcelContent(String fileName) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> content = null;
		try {
			InputStream is;
			is = new FileInputStream(fileName);
			String postfix = fileName.substring(fileName.lastIndexOf("."), fileName.length());
			if (postfix.equals(".xls")) {
				// 针对 2003 Excel 文件
				wb = new HSSFWorkbook(new POIFSFileSystem(is));
				sheet = wb.getSheetAt(0);
			} else {
				// 针对2007 Excel 文件
				wb = new XSSFWorkbook(is);
				sheet = wb.getSheetAt(0);
			}
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = wb.getSheetAt(0);
		int rowNum = sheet.getLastRowNum();// 得到总行数
		row = sheet.getRow(0);
		int colNum = row.getPhysicalNumberOfCells();
		String titles[] = readExcelTitle(fileName);
		// 正文内容应该从第二行开始,第一行为表头的标题
		for (int i = 1; i <= rowNum; i++) {
			int j = 0;
			row = sheet.getRow(i);
			content = new LinkedHashMap<String, String>();
			do {
				content.put(titles[j], getCellFormatValue(row.getCell(j)).trim());
				j++;
			} while (j < colNum);
			list.add(content);
		}
		return list;
	}

	/**
	 * 根据Cell类型设置数据
	 * 
	 * @param cell
	 * @return
	 */
	private String getCellFormatValue(Cell cell) {
		String cellvalue = "";
		if (cell != null) {
			// 判断当前Cell的Type
			switch (cell.getCellType()) {
			// 如果当前Cell的Type为NUMERIC
			case Cell.CELL_TYPE_NUMERIC:
			case Cell.CELL_TYPE_FORMULA: {
				// 判断当前的cell是否为Date
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					// 方法2：这样子的data格式是不带带时分秒的：2011-10-12
					Date date = cell.getDateCellValue();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					cellvalue = sdf.format(date);
				} else {
					// 如果是纯数字取得当前Cell的数值
					cellvalue = String.valueOf(cell.getNumericCellValue());
				}
				break;
			}
			// 如果当前Cell的Type为STRIN
			case Cell.CELL_TYPE_STRING:
				// 取得当前的Cell字符串
				cellvalue = cell.getRichStringCellValue().getString();
				break;
			default:
				// 默认的Cell值
				cellvalue = " ";
			}
		} else {
			cellvalue = "";
		}
		return cellvalue;

	}

	public DepartmentService getDepartmentService() {
		return departmentService;
	}

	public SpecialtyService getSpecialtyService() {
		return specialtyService;
	}

	public ClassesService getClassesService() {
		return classesService;
	}

	@Transactional
	public void insert() {
		String file = "C://Users//root//Desktop//student.xls";
		List<Map<String, String>> list = readExcelContent(file);
		Map<String, String> map = null;
		Map<String, String> department = new HashMap<String, String>();
		Map<String, String> specty = new HashMap<String, String>();
		Map<String, String> classes = new HashMap<String, String>();
		for (int i = 0; i < list.size(); i++) {
			map = list.get(i);
			Entry<String, String> entry = null;
			String number = "";
			String classesName = "";
			for (Iterator<Entry<String, String>> it = map.entrySet().iterator(); it.hasNext();) {
				entry = it.next();
				String key = entry.getKey().trim();
				String value = entry.getValue().trim();
				if (key.equals("学号")) {
					number = value;
				}
				if (key.equals("行政班")) {
					classesName = value;
					// classes.put(spitClasses(number), value);
					classes.put(spitClasses(number), value + "-" + number);
				}
				if (key.equals("专业")) {
					specty.put(spitSpecialty(number), value + "-" + number + "-" + classesName);
					// specty.put(spitSpecialty(number), value);
				}
				if (key.equals("学院")) {
					department.put(spitDepartment(number), value + "-" + number);
					// department.put(spitDepartment(number), value);
				}
			}
		}
		DatadicGroups xueyun = new DatadicGroups();
		xueyun.setGroupCode("0");
		xueyun.setGroupName("学院");
		datadicGroupsService.save(xueyun);
		for (Entry<String, String> entry : department.entrySet()) {
			// System.out.println(entry.getKey()+" : "+entry.getValue());
			String name = entry.getValue().split("-")[0];
			String code = entry.getKey();
			saveDepartment(name, code);

			DatadicGroups groups = new DatadicGroups();
			groups.setGroupCode(entry.getKey());
			groups.setGroupName(name);
			datadicGroupsService.save(groups);

			saveItems(name, entry.getKey(), "0");
		}
		for (Entry<String, String> entry : specty.entrySet()) {
			// System.out.println(entry.getKey()+" : "+entry.getValue());
			Specialty spe = new Specialty();
			String[] values = entry.getValue().split("-");
			String name = values[0] + values[2].replaceAll("[^(A-Za-z)]", "");
			String code = entry.getKey();
			spe.setSpecialtyId(Integer.parseInt(code));
			spe.setSpecialtyName(name);
			Department dep = departmentService.getById(Integer.parseInt(spitDepartment(values[1])));
			spe.setDepartment(dep);
			specialtyService.save(spe);

			DatadicGroups groups = new DatadicGroups();
			groups.setGroupCode(code);
			groups.setGroupName(name);
			datadicGroupsService.save(groups);

			saveItems(name, code, code.substring(0, code.length() - 1));

		}
		for (Entry<String, String> entry : classes.entrySet()) {
			// System.out.println(entry.getKey()+" : "+entry.getValue());
			Classes cla = new Classes();
			String[] values = entry.getValue().split("-");
			String code = entry.getKey();
			String name = values[0];
			String tag = name.replaceAll("[^(A-Za-z)]", "");
			String classesIndex = makeClassesIndex(name);
			String className = name.replaceAll("([0-9])|([A-Za-z])", "");
			// 重新构建班级名称
			name = className + tag + classesIndex;
			cla.setClassesId(Integer.parseInt(code));
			cla.setClassesName(name);
			Specialty spe = specialtyService.getById(Integer.parseInt(spitSpecialty(values[1])));
			cla.setSpecialty(spe);
			classesService.save(cla);

			saveItems(name, code, code.substring(0, code.length() - 1));
		}
	}

	private static String makeClassesIndex(String name) {
		name = name.replaceAll("[^(0-9)]", "");
		name = name.substring(name.length() - 1) + "班";
		return name;
	}

	private void saveDepartment(String name, String code) {
		Department dep = new Department();
		dep.setDepartmentId(Integer.parseInt(code));
		dep.setDepartmentName(name);
		departmentService.save(dep);
	}

	private void saveItems(String name, String code, String groupCode) {
		DatadicGroups group = datadicGroupsService.selectByPrimaryKey(groupCode);
		DatadicItems items = new DatadicItems();
		items.setDatadicGroup(group);
		items.setItemCode(code);
		items.setItemName(name);
		datadicItemsService.save(items);
	}

	private String spitClasses(String number) {
		return number.substring(4, 8);
	}

	private String spitSpecialty(String number) {
		return number.substring(4, 7);
	}

	private String spitDepartment(String number) {
		return number.substring(4, 6);
	}

	public static void main(String[] args) {
		 ApplicationContext ac = new
		 ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		 ExcelReader reader = (ExcelReader) ac.getBean("excelReader");
		 reader.insert();
	}
}