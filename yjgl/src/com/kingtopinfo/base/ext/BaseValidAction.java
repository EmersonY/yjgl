package com.kingtopinfo.base.ext;

import java.util.HashMap;
import java.util.Map;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class BaseValidAction {
	
	protected Map<String, Object> resultMap = new HashMap<String, Object>();
	
	/**
	 * 获取服务器端验证信息（JSR 303）
	 * 
	 * @param model
	 * @param result
	 * @return
	 */
	protected Map<String, Object> valid(Map<String, Object> model, BindingResult result) {
		
		if (result.hasErrors()) {
			Map<String, Object> map = new HashMap<String, Object>();
			for (FieldError fieldError : result.getFieldErrors()) {
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			model.put("valid", map);
		}
		return model;
	}
	
}
