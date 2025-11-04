package com.newhker.util;

import cn.hutool.crypto.digest.BCrypt;

/**
 * 密码生成工具（用于生成BCrypt哈希）
 */
public class PasswordGenerator {
    
    public static void main(String[] args) {
        String password = "admin123";
        
        // 生成BCrypt哈希
        String hashed = BCrypt.hashpw(password);
        
        System.out.println("========================================");
        System.out.println("密码哈希生成工具");
        System.out.println("========================================");
        System.out.println("原始密码: " + password);
        System.out.println("BCrypt哈希: " + hashed);
        System.out.println("哈希长度: " + hashed.length());
        System.out.println("========================================");
        
        // 验证哈希
        boolean isValid = BCrypt.checkpw(password, hashed);
        System.out.println("验证结果: " + isValid);
        
        // 生成SQL语句
        System.out.println("\n可用的SQL更新语句:");
        System.out.println("DELETE FROM tb_admin WHERE username = 'admin';");
        System.out.println("INSERT INTO `tb_admin` (`username`, `password`, `nickname`, `role`) VALUES ('admin', '" + hashed + "', '超级管理员', 1);");
        System.out.println("========================================");
    }
}
