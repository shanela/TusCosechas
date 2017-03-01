/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.shalued.web.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author luis
 */
public class Hash {
        public static String passwordToMD5(String password) {
        return DigestUtils.md5Hex(
                new StringBuffer(DigestUtils.md5Hex("linux"))
                .append(password)
                .append(String.valueOf(password.length()))
                .toString());
    }
}
