/**********************************************************************
 *                                                                     *
 *        ***    ***       ***************       ***    ***            *
 *       ***    ***    ***********************    ***    ***           *
 *      ***    ***   ***************************   ***    ***          *
 *     ****   ****  ****     ***********     ****  ****   ****         *
 *     ****   ****   **      ***********      **   ****   ****         *
 *      ***    ***          *************          ***    ***          *
 *       ***    ***       *****************       ***    ***           *
 *        ***    ***       ***************       ***    ***            *
 *                                                                     *
 ***********************************************************************
 
(c) 2008 SNIF Labs Inc. MIT Licenses (see readme)                                                                
project: HopToadProxy
File: HoptoadErrorTest.java
Package: com.sniflabs.HopToadProxy
Version: @version @rev.maj@.@rev.min@  (Build No. @build.number@ built on @build.date@)
Authors: Noah Paessel (noah AT sniflabs.com)
 ***********************************************************************/

package com.sniflabs.hoptoad;

/**
 * Simple exception for HoptoadNotifier
 * modified Sep 5, 2008
 * 
 * @author Noah Paessel (noah AT sniflabs.com)
 * @version @rev.maj@.@rev.min@  (Build No. @build.number@ built on @build.date@)
 */
public class HoptoadException extends RuntimeException {
	final static String DEFAULT_MESSAGE = "Unable to post to hoptoad service. " +
			"Check your network connection, and verify your hoptoad API_KEY";
	/**
	 * Create a simple HoptoadException with a reasonable default message.
	 */
	public HoptoadException() {
		super(DEFAULT_MESSAGE);
	}

	/**
	 * Create a simple HoptoadException with a specified message.
	 * @param message use some message other than the default
	 */
	public HoptoadException(String message) {
		super(message);
	}

	/**
	 * Create a simple HoptoadException with a default message and 
	 * a root cause
	 * @param cause
	 */
	public HoptoadException(Throwable cause) {
		super(DEFAULT_MESSAGE,cause);
	}

	/**
	 * Create a simple HoptoadException with specific cause and message
	 * @param message
	 * @param cause
	 */
	public HoptoadException(String message, Throwable cause) {
		super(message, cause);
	}

}



