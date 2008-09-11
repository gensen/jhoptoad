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
Version: @version @rev.maj@.@rev.min@  (Build No. @build.number@ on @build.date@)
Authors: Noah Paessel (noah AT sniflabs.com)
 ***********************************************************************/
package com.sniflabs.hoptoad;
import java.util.Scanner;

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
        } catch (Exception exp) {
            HoptoadNotice notice = new HoptoadNotice(key, exp);
            System.out.print(notice.toYaml());
            assertTrue(notice.toYaml().contains("notice"));
        }
    }

    public void testSendData() {
        String key = getKey();
        try {
            String s = null;
            System.out.print(s.toLowerCase());
        } catch (Exception exp) {
            HoptoadNotice notice = new HoptoadNotice(key, exp);
            assertTrue(notice.postData(true,false));
        }
    }

    private String getKey() {
        String api_key = System.getProperty("hoptoad.key");
        if (null == api_key || "".equals(api_key)) {
            api_key = askForKey();
        }
        return api_key;
    }

    private String askForKey() {
        String api_key;
        Scanner in = new Scanner(System.in);
        System.out.print("Please enter your HOPTOAD API Key here: ");
        api_key = in.nextLine();
        System.out.println();
        return api_key;
    }
}



