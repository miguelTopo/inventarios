package com.javdev.core.model;

import java.io.Serializable;

import com.javdev.core.api.IJavDevState;
import com.javdev.core.util.DateUtil;

public abstract class AParameter implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected Long id;

	protected String name;

	protected String dateInsert;

	protected String hourInsert;

	protected String dateUpdate;

	protected String hourUpdate;

	protected String userInsert;

	protected String userUpdate;

	protected Long state;

	public abstract Long getId();

	public abstract void setId(Long id);

	public abstract String getName();

	public abstract void setName(String name);

	public abstract String getDateInsert();

	public abstract void setDateInsert(String dateInsert);

	public abstract String getHourInsert();

	public abstract void setHourInsert(String hourInsert);

	public abstract String getDateUpdate();

	public abstract void setDateUpdate(String dateUpdate);

	public abstract String getHourUpdate();

	public abstract void setHourUpdate(String hourUpdate);

	public abstract String getUserInsert();

	public abstract void setUserInsert(String userInsert);

	public abstract String getUserUpdate();

	public abstract void setUserUpdate(String userUpdate);

	public abstract Long getState();

	public abstract void setState(Long state);


	/** @author MTorres 7/01/2016 6:15:30 p. m. **/
	public void initialize(boolean isNew) throws Exception {
		try {
			if (isNew) {
				this.dateInsert = DateUtil.formatDate(DateUtil.getCurrentDate(), DateUtil.YYYY_MM_DD);
				this.hourInsert = DateUtil.formatDate(DateUtil.getCurrentDate(), DateUtil.HH24_MM_SS);
			} else {
				this.dateUpdate = DateUtil.formatDate(DateUtil.getCurrentDate(), DateUtil.YYYY_MM_DD);
				this.hourUpdate = DateUtil.formatDate(DateUtil.getCurrentDate(), DateUtil.HH24_MM_SS);
			}
			this.state = IJavDevState.ACTIVE;
		} catch (Exception e) {
			throw e;
		}
	}
}
