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
Version:  @version ${pom.version}
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
        String key = "el-fako-key";
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
            api_key = "7d1334280ba333385be1e89e40a81a36";
        }
        return api_key;
    }
}



