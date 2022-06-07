package com.example.service;

import org.springframework.stereotype.Service;

/**
 * Author: qinbocheng
 * Date: 2022/4/8 12:59
 * Description:
 * History:
 * <author>    <time>          <version> <desc>
 * qinbocheng  2022/4/8 12:59 版本号    描述
 */
@Service
public class TestService {
    //校验手机号
    public boolean checkPhone(String phone) {
        return phone.matches("^1[3-9]\\d{9}$");
    }

    //校验密码
    public boolean checkPassword(String password) {
        return password.matches("^[a-zA-Z0-9]{6,16}$");
    }

    //校验验证码
    public boolean checkCode(String code) {
        return code.matches("^\\d{6}$");
    }

    //校验昵称
    public boolean checkNickname(String nickname) {
        return nickname.matches("^[\\u4E00-\\u9FA5\\uF900-\\uFA2D]{2,7}$");
    }

    //校验邮箱
    public boolean checkEmail(String email) {
        return email.matches("^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$");
    }

    //校验身份证号
    public boolean checkIdCard(String idCard) {
        return idCard.matches("^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$");
    }
    //校验银行卡号
    public boolean checkBankCard(String bankCard) {
        return bankCard.matches("^[1-9]\\d{14,18}$");
    }

    //校验微信号
    public boolean checkWechat(String wechat) {
        return wechat.matches("^[a-zA-Z]{1}[-_a-zA-Z0-9]{5,19}$");
    }

    //校验QQ号
    public boolean checkQQ(String qq) {
        return qq.matches("^[1-9]\\d{4,10}$");
    }
    //校验邮编
    public boolean checkPostCode(String postCode) {
        return postCode.matches("^[1-9]\\d{5}$");
    }
    //校验车牌号
    public boolean checkCarNumber(String carNumber) {
        return carNumber.matches("^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$");
    }
    //

}
