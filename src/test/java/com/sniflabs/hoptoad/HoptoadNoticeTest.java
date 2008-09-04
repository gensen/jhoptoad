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
                                          (c) 2008 SNIF Labs Inc.                                                                      
 projeect: HopToadProxy
     File: HoptoadErrorTest.java
  Package: com.sniflabs.HopToadProxy
  Created: Aug 20, 2008
  Authors: npaessel

***********************************************************************/

package com.sniflabs.hoptoad;


import java.util.Scanner;
import com.sniflabs.hoptoad.HoptoadNotice;

import junit.framework.TestCase;


public class HoptoadNoticeTest extends TestCase {
	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testWrapException() {	
		String key = getKey();
		try {
			throw new Exception("Test exception number 2");
		}
		catch (Exception exp) {
			HoptoadNotice notice = new HoptoadNotice(key,exp);
			System.out.print(notice.toYaml());
		}
	}

	
	public void testSendData() {
		String key = getKey();
		try {
			String s  = null;
			System.out.print(s.toLowerCase());
		}
		catch (Exception exp) {
			HoptoadNotice notice = new HoptoadNotice(key,exp);
			notice.postData(true);
		}
	}
	
	
	private String getKey() {
		String api_key;
	    Scanner in = new Scanner(System.in);
	    System.out.print("Please enter your HOPTOAD API Key here: ");
	    api_key = in.nextLine();
	    System.out.println();
	    return api_key;
	}
	
}



