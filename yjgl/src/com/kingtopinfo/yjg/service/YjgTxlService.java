package com.kingtopinfo.yjg.service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.kingtopinfo.app.mapper.YjgAppSjdjMapper;
import com.kingtopinfo.base.security.UserInfoUtil;
import com.kingtopinfo.base.util.IDUtil;
import com.kingtopinfo.base.util.MyBatisUtil;
import com.kingtopinfo.yjg.entity.YjgTxlEntity;

/**
 * @ClassName service.YjgTxlService
 * @Description YJG_TXL表服务类
 * @author cyf
 * @date 2017-12-04 09:16:38
 * @version 1.0
 * @remark create by generator
 */
@Service
@Transactional
public class YjgTxlService {
	
	private final static String			XLS		= "xls";
	private final static String			XLSX	= "xlsx";
	@Autowired
	private YjgAppSjdjMapper yjgTxlMapper;
	
	/**
	 * @Description 按条件查询总条数
	 * @param e YjgTxlEntity实体
	 * @return 条数
	 * @author cyf
	 * @date 2017-12-04 09:16:38
	 */
	public int getCount(YjgTxlEntity e){
		return yjgTxlMapper.getTxlCount(e);
	}
	
	/**
	 * @Description 按条件分页查询
	 * @param e YjgTxlEntity实体
	 * @param rowBounds 分页实体
	 * @return YjgTxlEntity集合
	 * @author cyf
	 * @date 2017-12-04 09:16:38
	 */
	public List<YjgTxlEntity> selectPagination(YjgTxlEntity e, Integer rows, Integer page){
		RowBounds rowBounds = MyBatisUtil.rowBounds(rows, page);
		return yjgTxlMapper.selectTxlPagination(e,rowBounds);
	}
	
	
 
	/**
	 * @Description 添加YjgTxlEntity信息
	 * @param e YjgTxlEntity实体
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-12-04 09:16:38
	 */	
	public int insert(YjgTxlEntity e){
		e.setTxlid(IDUtil.getId());
		e.setBaseuserid(UserInfoUtil.getBaseuserid());
		return yjgTxlMapper.inserttxl(e);
	}
	
	/**
	 * @Description 修改YjgTxlEntity信息
	 * @param e YjgTxlEntity实体
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-12-04 09:16:38
	 */	
	public int update(YjgTxlEntity e){
		e.setBaseuserid(UserInfoUtil.getBaseuserid());
		return yjgTxlMapper.updatetxl(e);
	}
	
	/**
	 * @Description 按txlid删除YjgTxlEntity信息
	 * @param txlid
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-12-04 09:16:38
	 */	
	public int delete(String txlid){
		YjgTxlEntity e = new YjgTxlEntity();
		e.setTxlid(txlid);
		return yjgTxlMapper.deletetxl(e);
	}
	
	/**
	 * @Description 按txlid集合批量删除YjgTxlEntity信息
	 * @param idArray txlid集合
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-12-04 09:16:38
	 */	
	public int deleteBatch(String[] idArray){
		int row = 0;
		for(String id : idArray){
			row += delete(id);
		}
		return row;
	}
    /**
     * 导入通讯录
     * @param fileList
     * @return
     * @throws Exception
     */
	public Integer importTxlExcelData(List<MultipartFile> fileList) throws Exception {
		int totalrows = 0;
		for (MultipartFile myFile : fileList) {
			// 获得文件名
			Workbook workbook = null;
			String fileName = myFile.getOriginalFilename();
			if (fileName.endsWith(XLS)) {
				// 2003
				workbook = new HSSFWorkbook(myFile.getInputStream());
			} else if (fileName.endsWith(XLSX)) {
				// 2007
				workbook = new XSSFWorkbook(myFile.getInputStream());
			} else {
				throw new Exception("文件不是Excel文件");
			}
			
			Sheet sheet = workbook.getSheet("Sheet1");
			int rows = sheet.getLastRowNum();// 指的行数，一共有多少行+
			if (rows == 0) {
				throw new Exception("请填写数据");
			}
			for (int i = 1; i <= rows + 1; i++) {
				System.out.println(i);
				// 读取左上端单元格
				Row row = sheet.getRow(i);
				// 行不为空
				if (row != null) {
					// **读取cell**
					String txlid = IDUtil.getId();
					YjgTxlEntity yjgTxlEntity = new YjgTxlEntity();
					yjgTxlEntity.setTxlid(txlid);
					yjgTxlEntity.setBaseuserid(UserInfoUtil.getBaseuserid());
					yjgTxlEntity.setTxlxm(getCellValue(row.getCell(0), 1));
					yjgTxlEntity.setTxlgs(getCellValue(row.getCell(1), 1));
					yjgTxlEntity.setTxljob(getCellValue(row.getCell(2), 1));
					yjgTxlEntity.setTxlxzqh(getCellValue(row.getCell(3), 1));
					yjgTxlEntity.setTxldh(getCellValue(row.getCell(4), 1));
					yjgTxlEntity.setTxlemail(getCellValue(row.getCell(5), 1));
					yjgTxlEntity.setTxlbz(getCellValue(row.getCell(6), 1));
					yjgTxlMapper.inserttxl(yjgTxlEntity);
				}
			}
			totalrows += rows;
		}
		return totalrows;
	}
	/**
	 * 获得Cell内容
	 * 
	 * @param cell
	 * @return
	 */
	public String getCellValue(Cell cell, int type) {
		String value = "";
		if (cell != null) {
			// 以下是判断数据的类型
			switch (cell.getCellType()) {
				case HSSFCell.CELL_TYPE_NUMERIC: // 数字
					value = cell.getNumericCellValue() + "";
					if (HSSFDateUtil.isCellDateFormatted(cell) && type == 1) {
						Date date = cell.getDateCellValue();
						if (date != null) {
							value = new SimpleDateFormat("yyyy-MM-dd").format(date);
						} else {
							value = "";
						}
					} else {
						if (type == 1) {
							value = new DecimalFormat("0").format(cell.getNumericCellValue());
						} else {
							value = new DecimalFormat("0.00").format(cell.getNumericCellValue());
						}
					}
					break;
				case HSSFCell.CELL_TYPE_STRING: // 字符串
					value = cell.getStringCellValue();
					break;
				case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
					value = cell.getBooleanCellValue() + "";
					break;
				case HSSFCell.CELL_TYPE_FORMULA: // 公式
					value = cell.getCellFormula() + "";
					break;
				case HSSFCell.CELL_TYPE_BLANK: // 空值
					value = "";
					break;
				case HSSFCell.CELL_TYPE_ERROR: // 故障
					value = "非法字符";
					break;
				default:
					value = "未知类型";
					break;
			}
		}
		return value.trim();
	}
}