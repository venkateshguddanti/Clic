/****************************************************************************
 *   Copyright (c)2013 healtheverAPI. All rights reserved.
 *
 *   THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF healtheverAPI.
 *
 *   The copyright notice above does not evidence any actual or intended
 *   publication of such source code.
 *****************************************************************************/
package com.clic.org.serve.data;

/**
 * ErrorMessages.java Purpose: This class is responsible for maintaining the
 * getter and setter method for Error Messages.
 * 
 * @author healtheverAPI
 * @version 1.0
 */
public class ErrorMessages {

	private String errorCode = "";
	private String errorMessage = "";

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
