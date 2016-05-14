package com.javdev.core.model;

import java.io.Serializable;

import javax.swing.plaf.synth.SynthSeparatorUI;

import com.javdev.core.api.IState;
import com.javdev.core.system.model.SystemController;
import com.javdev.core.util.DateUtil;

public abstract class AParameter implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected Long id;

	protected String name;

	protected SystemController systemController;

	protected Long state;

	public abstract Long getId();

	public abstract void setId(Long id);

	public abstract String getName();



	public abstract void setName(String name);

	public abstract Long getState();

	public abstract void setState(Long state);

	public abstract SystemController getSystemController();

	public abstract void setSystemController(SystemController systemController);

	/** @author MTorres 11/05/2016 10:21:37 p. m. */
	public void initController(Class<?> sysClass, Long idActionDB, Long idTable, String user) throws Exception {
		try {
			this.systemController.setIdActionDB(idActionDB);
			this.systemController.setDateInsert(DateUtil.getCurrentStringDate());
			this.systemController.setHourInsert(DateUtil.getCurrentStringHour());
			this.systemController.setIdTable(idTable);
			this.systemController.setUserPerform(user);
			this.systemController.setTableName(sysClass.getClass().getSimpleName());
		} catch (Exception e) {
			throw e;
		}
	}

}
