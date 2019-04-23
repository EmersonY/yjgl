package com.kingtopinfo.activiti.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.XPath;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kingtopinfo.activiti.entity.TblFlowChartEntity;

@Service
@Transactional
public class TblProcessDiagramService {
	
	public List<TblFlowChartEntity> flowChartLocation(String path) {
		if(new File(path).exists()){
			TblProcessDiagramService oFlowChartService = new TblProcessDiagramService();
			Document doc = oFlowChartService.read(path);
			return oFlowChartService.getValue(doc);
		}else{
			return null;
		}
	}
	
	//读取xml文件节点信息
	private Document read(String fileName) {
		SAXReader reader = new SAXReader();
		Document document = null;
		try {
			document = reader.read(new File(fileName));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return document;
	}
	
	@SuppressWarnings("unchecked")
	private List<TblFlowChartEntity> getValue(Document document) {
		List<TblFlowChartEntity> flowChartList = new ArrayList<TblFlowChartEntity>();
		//任务list
		Map<String,String> xmlMap = new HashMap<String,String>();
		xmlMap.put("src", "http://www.omg.org/spec/BPMN/20100524/MODEL");
		//坐标取值list
		List<Element> zbList = document.selectNodes("//bpmndi:BPMNShape"); //坐标list
		XPath x0 = document.createXPath("//src:userTask"); 
		x0.setNamespaceURIs(xmlMap);
		List<Element> rwList = x0.selectNodes(document); //任务list
		XPath x1 = document.createXPath("//src:startEvent"); 
		x1.setNamespaceURIs(xmlMap);
		List<Element> ksList = x1.selectNodes(document); //开始事件list
		int flag = 0;
		if(zbList != null && !zbList.isEmpty()){
			for(Element zbEach:zbList){
				Attribute coordinateAttribute = zbEach.attribute("bpmnElement");
				String zbId = coordinateAttribute.getValue();
				//开始事件
				if(flag == 0){
					if(ksList != null && !ksList.isEmpty()){
						String ksId = ksList.get(0).attribute("id").getValue();
						if(ksId.equals(zbId)){
							TblFlowChartEntity oTblFlowChartEntity = new TblFlowChartEntity();
							oTblFlowChartEntity.setName(ksList.get(0).attribute("name").getValue()); // 名称
							oTblFlowChartEntity.setId(ksId);
							Node node = zbEach.selectSingleNode("omgdc:Bounds");
							double x = Double.parseDouble(node.valueOf("@x"));
							double y = Double.parseDouble(node.valueOf("@y"));
							double w = Double.parseDouble(node.valueOf("@width"));
							double h = Double.parseDouble(node.valueOf("@height"));
							oTblFlowChartEntity.setSx(x);
							oTblFlowChartEntity.setSy(y);
							oTblFlowChartEntity.setEx(x + w);
							oTblFlowChartEntity.setEy(y + h);
							oTblFlowChartEntity.setW(w);
							oTblFlowChartEntity.setH(h);
							flowChartList.add(oTblFlowChartEntity);
							flag = 1;
							continue;
						}
					}
				}
				//任务
				if(rwList != null && !rwList.isEmpty()){
					for(Element rwEach:rwList){
						String rwId = rwEach.attribute("id").getValue();	
						if(rwId.equals(zbId)){
							TblFlowChartEntity oTblFlowChartEntity = new TblFlowChartEntity();
							oTblFlowChartEntity.setName(rwEach.attribute("name").getValue()); // 名称
							oTblFlowChartEntity.setId(rwId);
							Node node = zbEach.selectSingleNode("omgdc:Bounds");
							double x = Double.parseDouble(node.valueOf("@x"));
							double y = Double.parseDouble(node.valueOf("@y"));
							double w = Double.parseDouble(node.valueOf("@width"));
							double h = Double.parseDouble(node.valueOf("@height"));
							oTblFlowChartEntity.setSx(x);
							oTblFlowChartEntity.setSy(y);
							oTblFlowChartEntity.setEx(x + w);
							oTblFlowChartEntity.setEy(y + h);
							oTblFlowChartEntity.setW(w);
							oTblFlowChartEntity.setH(h);
							flowChartList.add(oTblFlowChartEntity);
							rwList.remove(rwEach);
							break;
						}
					}
				}
			}
			
		}
		return flowChartList;
	}
	
	public List<TblFlowChartEntity> selectAllNodes(String path, String taskkey) {
		if(new File(path).exists()){
			TblProcessDiagramService oFlowChartService = new TblProcessDiagramService();
			Document doc = oFlowChartService.read(path);
			return oFlowChartService.getValue2(doc,taskkey);
		}else{
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	private List<TblFlowChartEntity> getValue2(Document document, String taskkey) {
		List<TblFlowChartEntity> flowChartList = new ArrayList<TblFlowChartEntity>();
		//任务list
		Map<String,String> xmlMap = new HashMap<String,String>();
		xmlMap.put("src", "http://www.omg.org/spec/BPMN/20100524/MODEL");
		XPath x0 = document.createXPath("//src:userTask"); 
		x0.setNamespaceURIs(xmlMap);
		List<Element> rwList = x0.selectNodes(document); //任务list
		XPath x1 = document.createXPath("//src:startEvent"); 
		x1.setNamespaceURIs(xmlMap);
		List<Element> ksList = x1.selectNodes(document); //开始事件list
		//任务
		if(rwList != null && !rwList.isEmpty()){
			for(Element rwEach:rwList){
				String taskid = rwEach.attribute("id").getValue();
				if(taskid != null && !taskid.equals(taskkey)){
					TblFlowChartEntity oTblFlowChartEntity = new TblFlowChartEntity();
					oTblFlowChartEntity.setId(taskid);
					oTblFlowChartEntity.setName(rwEach.attribute("name").getValue()); // 名称
					flowChartList.add(oTblFlowChartEntity);
				}
			}
		}
		if(ksList != null && !ksList.isEmpty()){
			String taskid = ksList.get(0).attribute("id").getValue();
			if(taskid != null && !taskid.equals(taskkey)){
				TblFlowChartEntity oTblFlowChartEntity = new TblFlowChartEntity();
				oTblFlowChartEntity.setId(taskid);
				oTblFlowChartEntity.setName(ksList.get(0).attribute("name").getValue()); // 名称
				flowChartList.add(oTblFlowChartEntity);
			}
		}
		return flowChartList;
	}

}
