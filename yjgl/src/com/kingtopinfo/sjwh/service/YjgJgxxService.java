package com.kingtopinfo.sjwh.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.RowBounds;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.kingtopinfo.base.entity.TblBaseFileEntity;
import com.kingtopinfo.base.entity.TblBaseSerialnumberEntity;
import com.kingtopinfo.base.entity.TblBaseTypeEntity;
import com.kingtopinfo.base.mapper.TblBaseFileMapper;
import com.kingtopinfo.base.mapper.TblBaseSerialnumberMapper;
import com.kingtopinfo.base.mapper.TblBaseTypeMapper;
import com.kingtopinfo.base.security.UserInfoUtil;
import com.kingtopinfo.base.service.TblBaseFileService;
import com.kingtopinfo.base.util.FilePathUtil;
import com.kingtopinfo.base.util.FileUtil;
import com.kingtopinfo.base.util.IDUtil;
import com.kingtopinfo.base.util.MyBatisUtil;
import com.kingtopinfo.base.util.TimeUtils;
import com.kingtopinfo.base.util.ZipUtil;
import com.kingtopinfo.sjwh.entity.YjgJgxxEntity;
import com.kingtopinfo.sjwh.mapper.YjgJgxxMapper;

/**
 * @ClassName service.YjgJgxxService
 * @Description YJG_JGXX表服务类
 * @author cyf
 * @date 2017-10-19 16:18:13
 * @version 1.0
 * @remark create by generator
 */
@Service
@Transactional
public class YjgJgxxService {
	
	private final static String			XLS		= "xls";
	private final static String			XLSX	= "xlsx";
	@Autowired
	private YjgJgxxMapper				yjgJgxxMapper;
	@Autowired
	private YjgHisjgxxService			yjgHisjgxxService;
	@Autowired
	private TblBaseFileMapper			tblBaseFileMapper;
	@Autowired
	private TblBaseSerialnumberMapper	tblBaseSerialnumberMapper;
	@Autowired
	private TblBaseTypeMapper			tblBaseTypeMapper;
	@Autowired
	private TblBaseFileService			tblBaseFileService;
	
	/**
	 * @Description 按条件查询总条数
	 * @param e
	 *            YjgJgxxEntity实体
	 * @return 条数
	 * @author cyf
	 * @date 2017-10-19 16:18:13
	 */
	public int getCount(YjgJgxxEntity e) {
		return yjgJgxxMapper.getCount(e);
	}
	
	/**
	 * @Description 按条件分页查询
	 * @param e
	 *            YjgJgxxEntity实体
	 * @param rowBounds
	 *            分页实体
	 * @return YjgJgxxEntity集合
	 * @author cyf
	 * @date 2017-10-19 16:18:13
	 */
	public List<YjgJgxxEntity> selectPagination(YjgJgxxEntity e, Integer rows, Integer page) {
		RowBounds rowBounds = MyBatisUtil.rowBounds(rows, page);
		return yjgJgxxMapper.selectPagination(e, rowBounds);
	}
	
	/**
	 * @Description 按入图状态分页查询
	 * @param e
	 *            YjgJgxxEntity实体
	 * @param rowBounds
	 *            分页实体
	 * @return YjgJgxxEntity集合
	 * @author cyf
	 * @date 2017-10-19 16:18:13
	 */
	public List<YjgJgxxEntity> selectPaginationByrtzt(YjgJgxxEntity e, Integer rows, Integer page) {
		RowBounds rowBounds = MyBatisUtil.rowBounds(rows, page);
		return yjgJgxxMapper.selectPaginationByrtzt(e, rowBounds);
	}
	
	/**
	 * @Description 按jgid查询YjgJgxxEntity信息
	 * @param jgid
	 *            主键jgid
	 * @return YjgJgxxEntity实体
	 * @author cyf
	 * @date 2017-10-19 16:18:13
	 */
	public YjgJgxxEntity getByPkey(String jgid) {
		return yjgJgxxMapper.getByPkey(jgid);
	}
	
	public YjgJgxxEntity getByJgbh(String jgbh) {
		return yjgJgxxMapper.getByJgbh(jgbh);
	}
	
	/**
	 * @Description 添加YjgJgxxEntity信息
	 * @param e
	 *            YjgJgxxEntity实体
	 * @return 影响条数
	 * @author cyf
	 * @param resultMap
	 * @return
	 * @throws Exception
	 * @date 2017-10-19 16:18:13
	 */
	public Map<String, Object> insert(YjgJgxxEntity e, HttpServletRequest request, Map<String, Object> resultMap) throws Exception {
		int rows = 0;
		resultMap = new HashMap<String, Object>();
		String jgId = IDUtil.getId();
		String jgbh = createJgbh(5, e);
		rows = insert(e, jgId, jgbh);
		tblBaseFileService.saveNbImg(e, request, jgbh, jgId, resultMap);
		tblBaseFileService.saveJjImg(e, request, jgbh, jgId, resultMap);
		tblBaseFileService.saveYjImg(e, request, jgbh, jgId, resultMap);
		resultMap.put("jgbh", jgbh);
		resultMap.put("sec", true);
		resultMap.put("rows", rows);
		return resultMap;
	}
	
	public int insert(YjgJgxxEntity e, String jgId, String jgbh) {
		TblBaseTypeEntity tblBaseTypeEntity = new TblBaseTypeEntity();
		tblBaseTypeEntity.setName(e.getSsdl());
		tblBaseTypeEntity.setCode("SSDL");
		tblBaseTypeEntity = tblBaseTypeMapper.selectValueByCodeAndName(tblBaseTypeEntity);
		YjgJgxxEntity YjgJgxxEntity = new YjgJgxxEntity();
		YjgJgxxEntity.setCzr(UserInfoUtil.getUserName());
		YjgJgxxEntity.setCzsj(new Date());
		YjgJgxxEntity.setDljssj(tblBaseTypeEntity.getAttribute());
		YjgJgxxEntity.setJgbh(jgbh);
		YjgJgxxEntity.setJgid(jgId);
		YjgJgxxEntity.setRtzt(e.getRtzt());
		YjgJgxxEntity.setSsdl(e.getSsdl());
		YjgJgxxEntity.setXzb(e.getXzb());
		YjgJgxxEntity.setYzb(e.getYzb());
		if (!e.getQsdw().equals("")) {
			YjgJgxxEntity.setQsdw(e.getQsdw());
		}
		if (!e.getGldw().equals("")) {
			YjgJgxxEntity.setGldw(e.getGldw());
		}
		if (!e.getSfzw().equals("-1")) {
			YjgJgxxEntity.setSfzw(e.getSfzw());
		}
		if (!e.getJngj().equals("-1")) {
			YjgJgxxEntity.setJngj(e.getJngj());
		}
		if (!e.getJgxz().equals("-1")) {
			YjgJgxxEntity.setJgxz(e.getJgxz());
		}
		if (!e.getJglx().equals("-1")) {
			YjgJgxxEntity.setJglx(e.getJglx());
		}
		if (!e.getJggg().equals("-1")) {
			YjgJgxxEntity.setJggg(e.getJggg());
		}
		if (!e.getJgcz().equals("-1")) {
			YjgJgxxEntity.setJgcz(e.getJgcz());
		}
		if (!e.getJgzt().equals("-1")) {
			YjgJgxxEntity.setJgzt(e.getJgzt());
		}
		return yjgJgxxMapper.insert(YjgJgxxEntity);
	}
	
	public String createJgbh(Integer size, YjgJgxxEntity e) {
		TblBaseTypeEntity tblBaseTypeEntity = new TblBaseTypeEntity();
		// 井盖类型编码
		tblBaseTypeEntity.setName(e.getJglx());
		tblBaseTypeEntity.setCode("JGLX");
		tblBaseTypeEntity = tblBaseTypeMapper.selectValueByCodeAndName(tblBaseTypeEntity);
		String jglxCode = tblBaseTypeEntity.getCodeattrbute();
		// 道路编码
		tblBaseTypeEntity.setName(e.getSsdl());
		tblBaseTypeEntity.setCode("DLBM");
		tblBaseTypeEntity = tblBaseTypeMapper.selectValueByCodeAndName(tblBaseTypeEntity);
		String ssdlCode = tblBaseTypeEntity.getValue();
		// 获取最大值
		List<String> list = tblBaseSerialnumberMapper.findMaxSeqByType("2");
		Integer maxSeq = 0;
		if (list.size() > 0) {
			if (list.get(0) != null) {
				maxSeq = Integer.valueOf(String.valueOf(list.get(0)));
			} else {
				maxSeq = 0000;
			}
		} else {
			maxSeq = 0000;
		}
		maxSeq = maxSeq + 1;
		String seqStr = String.valueOf(maxSeq);
		StringBuffer seqsb = new StringBuffer();
		if (seqStr.length() < size) {
			Integer len = size - seqStr.length();
			for (int i = 0; i < len; i++) {
				seqsb.append("0");
			}
		}
		seqsb.append(maxSeq);
		seqStr = seqsb.toString();
		TblBaseSerialnumberEntity tblBaseSerialnumberEntity = new TblBaseSerialnumberEntity();
		tblBaseSerialnumberEntity.setSerialnumberid(IDUtil.getId());
		tblBaseSerialnumberEntity.setType("2");
		tblBaseSerialnumberEntity.setSeq(String.valueOf(maxSeq));
		tblBaseSerialnumberMapper.insert(tblBaseSerialnumberEntity);
		return jglxCode + ssdlCode + "X" + seqStr;
	}
	
	/**
	 * @Description 修改YjgJgxxEntity信息
	 * @param e
	 *            YjgJgxxEntity实体
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-10-19 16:18:13
	 */
	public int update(YjgJgxxEntity e) {
		YjgJgxxEntity yjgJgxxEntity = getByJgbh(e.getJgbh());
		yjgJgxxEntity.setCzr(UserInfoUtil.getUserName());
		yjgJgxxEntity.setCzsj(new Date());
		yjgJgxxEntity.setDljssj(e.getDljssj());
		yjgJgxxEntity.setGldw(e.getGldw());
		yjgJgxxEntity.setQsdw(e.getQsdw());
		yjgJgxxEntity.setSsdl(e.getSsdl());
		yjgJgxxEntity.setRtzt(e.getRtzt());
		
		if (e.getJgzt().equals("--请选择--") || e.getJgzt().equals("-1")) {
			yjgJgxxEntity.setJgzt("");
		} else {
			yjgJgxxEntity.setJgzt(e.getJgzt());
		}
		if (e.getJgcz().equals("--请选择--") || e.getJgcz().equals("-1")) {
			yjgJgxxEntity.setJgcz("");
		} else {
			yjgJgxxEntity.setJgcz(e.getJgcz());
		}
		if (e.getJgxz().equals("--请选择--") || e.getJgxz().equals("-1")) {
			yjgJgxxEntity.setJgxz("");
		} else {
			yjgJgxxEntity.setJgxz(e.getJgxz());
		}
		if (e.getJggg().equals("") || e.getJggg().equals("-1")) {
			yjgJgxxEntity.setJggg("");
		} else {
			yjgJgxxEntity.setJggg(e.getJggg());
		}
		if (e.getJglx().equals("--请输入信息--") || e.getJglx().equals("-1")) {
			yjgJgxxEntity.setJglx("");
		} else {
			yjgJgxxEntity.setJglx(e.getJglx());
		}
		if (e.getJngj().equals("--请选择--") || e.getJngj().equals("")) {
			yjgJgxxEntity.setJngj("");
		} else {
			yjgJgxxEntity.setJngj(e.getJngj());
		}
		if (e.getSfzw().equals("--请选择--") || e.getSfzw().equals("-1")) {
			yjgJgxxEntity.setSfzw("");
		} else {
			yjgJgxxEntity.setSfzw(e.getSfzw());
		}
		
		return yjgJgxxMapper.update(yjgJgxxEntity);
	}
	
	/**
	 * @Description 按jgid删除YjgJgxxEntity信息
	 * @param jgid
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-10-19 16:18:13
	 */
	public int delete(String jgbh) {
		return yjgJgxxMapper.delete(jgbh);
	}
	
	/**
	 * @Description 按jgid集合批量删除YjgJgxxEntity信息
	 * @param idArray
	 *            jgid集合
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-10-19 16:18:13
	 */
	public int deleteBatch(String[] idArray) {
		int row = 0;
		for (String jgbh : idArray) {
			YjgJgxxEntity yjgJgxxEntity = getByJgbh(jgbh);
			yjgHisjgxxService.insert(yjgJgxxEntity);
			row += delete(jgbh);
		}
		return row;
	}
	
	public int updatertztBatch(List<YjgJgxxEntity> list) {
		int row = 0;
		for (YjgJgxxEntity yjgJgxxEntity : list) {
			row += update(yjgJgxxEntity);
		}
		return row;
	}
	
	public Integer importExcelData(List<MultipartFile> fileList) throws Exception {
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
					YjgJgxxEntity e = yjgJgxxMapper.getByJgbh(getCellValue(row.getCell(0), 1));
					if (e == null) {
						// **读取cell**
						String jgid = IDUtil.getId();
						YjgJgxxEntity yjgJgxxEntity = new YjgJgxxEntity();
						yjgJgxxEntity.setJgbh(getCellValue(row.getCell(0), 1));
						yjgJgxxEntity.setJglx(getCellValue(row.getCell(1), 1));
						yjgJgxxEntity.setJngj(getCellValue(row.getCell(2), 1));
						yjgJgxxEntity.setSfzw(getCellValue(row.getCell(3), 1));
						yjgJgxxEntity.setJgcz(getCellValue(row.getCell(4), 1));
						yjgJgxxEntity.setJggg(getCellValue(row.getCell(5), 1));
						yjgJgxxEntity.setJgzt(getCellValue(row.getCell(6), 1));
						yjgJgxxEntity.setXzb(getCellValue(row.getCell(7), 3));
						yjgJgxxEntity.setYzb(getCellValue(row.getCell(8), 3));
						yjgJgxxEntity.setJgxz(getCellValue(row.getCell(9), 1));
						yjgJgxxEntity.setSsdl(getCellValue(row.getCell(10), 1));
						yjgJgxxEntity.setDljssj(getCellValue(row.getCell(11), 1));
						yjgJgxxEntity.setQsdw(getCellValue(row.getCell(12), 1));
						yjgJgxxEntity.setGldw(getCellValue(row.getCell(13), 1));
						yjgJgxxEntity.setJs(getCellValue(row.getCell(14), 1));
						yjgJgxxEntity.setJgsl(getCellValue(row.getCell(15), 1));
						yjgJgxxEntity.setCzr(UserInfoUtil.getUserName());
						yjgJgxxEntity.setCzsj(new Date());
						yjgJgxxEntity.setJgid(jgid);
						yjgJgxxEntity.setRtzt("1");
						yjgJgxxMapper.insert(yjgJgxxEntity);
						for (int j = 16; j < 19; j++) {
							if (row.getCell(j) != null && !row.getCell(j).equals("")) {
								insertJgImg(j, row, jgid);
							}
							
						}
					}
				}
			}
			totalrows += rows;
		}
		return totalrows;
	}
	
	public void insertJgImg(int i, Row row, String jgid) {
		String sjwhPath = FilePathUtil.getFilePath("sjwh_Img_Path");
		String filePath = sjwhPath + "/" + row.getCell(i);
		TblBaseFileEntity tblBaseFileEntity = new TblBaseFileEntity();
		tblBaseFileEntity.setFileid(IDUtil.getId());
		if (i == 16) {
			tblBaseFileEntity.setOperatype("YJG_IMG_NBJG_FILE");
		} else if (i == 17) {
			tblBaseFileEntity.setOperatype("YJG_IMG_YJJG_FILE");
		} else if (i == 18) {
			tblBaseFileEntity.setOperatype("YJG_IMG_JJJG_FILE");
		}
		tblBaseFileEntity.setFilepath(filePath);
		tblBaseFileEntity.setCjr(UserInfoUtil.getUserName());
		tblBaseFileEntity.setFilename(getCellValue(row.getCell(i), 1));
		tblBaseFileEntity.setOperaid(jgid);
		tblBaseFileEntity.setState(1);
		tblBaseFileEntity.setGxsj(new Date());
		tblBaseFileMapper.insert(tblBaseFileEntity);
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
						} else if (type == 2) {
							value = new DecimalFormat("0.00").format(cell.getNumericCellValue());
						} else {
							value = new DecimalFormat("0.0000000").format(cell.getNumericCellValue());
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
	
	public void exportExcelData(String[] idArray, HttpServletRequest request, HttpServletResponse response) throws IOException {
		OutputStream outputStream = null;
		BufferedInputStream bufferedInputStream = null;
		FileOutputStream fileOutputStream = null;
		FileInputStream fileInputStream = null;
		String dateStr = TimeUtils.formatyyyyMMddHHmmss(new Date());
		String realPath = FilePathUtil.getFilePath("disk_Path");
		try {
			String toPath = realPath + "/upload/" + dateStr + "/";
			FilePathUtil.createDir(toPath);
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("井盖信息");
			
			HSSFRow row0 = sheet.createRow(0);
			HSSFCell cell0 = row0.createCell(0);
			String bt = "厦门市市政窨井管理信息系统";
			cell0.setCellValue(bt);
			// 表头
			HSSFRow row1 = sheet.createRow(0);
			HSSFCell cell1;
			
			cell1 = row1.createCell(0);
			cell1.setCellValue("井盖编号");
			cell1 = row1.createCell(1);
			cell1.setCellValue("井盖类型");
			cell1 = row1.createCell(2);
			cell1.setCellValue("井内管径");
			cell1 = row1.createCell(3);
			cell1.setCellValue("防坠网状态");
			cell1 = row1.createCell(4);
			cell1.setCellValue("井盖材质");
			cell1 = row1.createCell(5);
			cell1.setCellValue("井盖规格");
			cell1 = row1.createCell(6);
			cell1.setCellValue("井盖状态");
			cell1 = row1.createCell(7);
			cell1.setCellValue("经度");
			cell1 = row1.createCell(8);
			cell1.setCellValue("纬度");
			cell1 = row1.createCell(9);
			cell1.setCellValue("井盖形状");
			cell1 = row1.createCell(10);
			cell1.setCellValue("所属道路");
			cell1 = row1.createCell(11);
			cell1.setCellValue("道路建设时间");
			cell1 = row1.createCell(12);
			cell1.setCellValue("权属单位");
			cell1 = row1.createCell(13);
			cell1.setCellValue("管理单位");
			
			HSSFCellStyle cellStyle0 = cell0.getCellStyle();
			cellStyle0.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cellStyle0.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			
			HSSFCellStyle cellStyle1 = cell1.getCellStyle();
			cellStyle1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cellStyle1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			
			// //合并单元格
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 13));
			int k = 2;
			// 内容添加
			for (String jgid : idArray) {
				YjgJgxxEntity yjgJgxxEntity = getByPkey(jgid);
				HSSFRow rowk = sheet.createRow(k);
				HSSFCell cellk = rowk.createCell(0);
				cellk.setCellValue(yjgJgxxEntity.getJgbh());
				cellk = rowk.createCell(1);
				cellk.setCellValue(yjgJgxxEntity.getJglx());
				cellk = rowk.createCell(2);
				cellk.setCellValue(yjgJgxxEntity.getJngj());
				cellk = rowk.createCell(3);
				cellk.setCellValue(yjgJgxxEntity.getSfzw());
				cellk = rowk.createCell(4);
				cellk.setCellValue(yjgJgxxEntity.getJgcz());
				cellk = rowk.createCell(5);
				cellk.setCellValue(yjgJgxxEntity.getJggg());
				cellk = rowk.createCell(6);
				cellk.setCellValue(yjgJgxxEntity.getJgzt());
				cellk = rowk.createCell(7);
				cellk.setCellValue(yjgJgxxEntity.getXzb());
				cellk = rowk.createCell(8);
				cellk.setCellValue(yjgJgxxEntity.getYzb());
				cellk = rowk.createCell(9);
				cellk.setCellValue(yjgJgxxEntity.getJgxz());
				cellk = rowk.createCell(10);
				cellk.setCellValue(yjgJgxxEntity.getSsdl());
				cellk = rowk.createCell(11);
				cellk.setCellValue(yjgJgxxEntity.getDljssj());
				cellk = rowk.createCell(12);
				cellk.setCellValue(yjgJgxxEntity.getQsdw());
				cellk = rowk.createCell(13);
				cellk.setCellValue(yjgJgxxEntity.getGldw());
				k++;
				TblBaseFileEntity tblBaseFileEntity = new TblBaseFileEntity();
				tblBaseFileEntity.setOperaid(yjgJgxxEntity.getJgid());
				List<TblBaseFileEntity> list = tblBaseFileMapper.getByOperaid(tblBaseFileEntity);
				String imgPath = toPath + yjgJgxxEntity.getJgbh();
				FilePathUtil.createDir(imgPath);
				for (TblBaseFileEntity e : list) {
					File file = new File(realPath + e.getFilepath());
					FileUtil.copyFile(file, imgPath);
				}
			}
			// 设置每列的宽度
			setSheetColumnWidth(sheet);
			// 生成excel
			fileOutputStream = new FileOutputStream(toPath + dateStr + ".xls");
			wb.write(fileOutputStream);
			// 压缩
			ZipUtil.getInstance().doZip(toPath, toPath + dateStr);
			// 设置response的Header
			String filename = "厦门市市政窨井管理信息系统" + dateStr + ".zip";
			response.setHeader("Content-disposition", "attachment;filename=" + filename);
			response.setContentType("application/zip");
			outputStream = response.getOutputStream();
			// 获取ZIP文件
			File file = new File(toPath + dateStr + ".zip");
			fileInputStream = new FileInputStream(file);
			bufferedInputStream = new BufferedInputStream(fileInputStream);
			byte[] b = new byte[1024];// 相当于我们的缓存
			long l = 0;// 该值用于计算当前实际下载了多少字节
			// 开始循环下载
			while (l < file.length()) {
				int j = bufferedInputStream.read(b, 0, 1024);
				l += j;
				outputStream.write(b, 0, j);
			}
		} finally {
			fileInputStream.close();
			fileOutputStream.close();
			bufferedInputStream.close();
			outputStream.flush();
			outputStream.close();
			File file = new File(realPath + "/upload/" + dateStr);
			FilePathUtil.deleteDir(file);
		}
	}
	
	private void setSheetColumnWidth(HSSFSheet sheet) {
		sheet.setColumnWidth(0, 4000);
		sheet.setColumnWidth(1, 4000);
		sheet.setColumnWidth(2, 4000);
		sheet.setColumnWidth(3, 6000);
		sheet.setColumnWidth(4, 6000);
		sheet.setColumnWidth(5, 4000);
		sheet.setColumnWidth(6, 4000);
		sheet.setColumnWidth(7, 6000);
		sheet.setColumnWidth(8, 6000);
		sheet.setColumnWidth(9, 5000);
		sheet.setColumnWidth(10, 5000);
		sheet.setColumnWidth(11, 6000);
		sheet.setColumnWidth(12, 4000);
		sheet.setColumnWidth(13, 4000);
		sheet.setColumnWidth(14, 3000);
		sheet.setColumnWidth(15, 3000);
		sheet.setColumnWidth(16, 6000);
		sheet.setColumnWidth(17, 6000);
	}
	
}