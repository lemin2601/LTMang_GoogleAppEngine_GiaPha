/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

public class ScratchSpace {

    public static void main(String[] args) {
        String userName = "Max Headroom";
        String productKey = "ABCDEF";
        String versionNumber = "6";

        final String licenseKey = createLicenseKey(userName, productKey, versionNumber);
        System.out.println("licenseKey = " + licenseKey);
    }

    public static String createLicenseKey(String userName, String email, String action) {
        final String s = userName + "|" + email + "|" + action;
        final HashFunction hashFunction = Hashing.sha1();
        final HashCode hashCode = hashFunction.hashString(s);
        final String upper = hashCode.toString().toUpperCase();
        return group(upper);
    }
//6DB1CC-B972DB-7035C0-955DFF-F7AC98-319EE8-0632
    private static String group(String s) {
        String result = "";
        for (int i=0; i < s.length(); i++) {
            if (i%6==0 && i > 0) {
                result += '-';
            }
            result += s.charAt(i);
        }
        return result;
    }

}