package validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import command.ModifyPasswordCommand;

public class ModifyPasswordValidator implements Validator {

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public boolean supports(Class clazz) {
        // TODO Auto-generated method stub
        return clazz.isAssignableFrom(ModifyPasswordCommand.class);
    }

    public void validate(Object target, Errors errors) {
        validateRegistInfo(target, errors);
    }

    public void validateRegistInfo(Object target, Errors errors) {
        ModifyPasswordCommand v = (ModifyPasswordCommand) target;

        if (v.getIdCardNo() == null || v.getIdCardNo().length() == 0) {
            errors.rejectValue("idCardNo", "idCardNo", "身份证号必须填写!");
        } else {
            if (!isValidate18Idcard(v.getIdCardNo())) {
                errors.rejectValue("idCardNoError", "idCardNoError",
                        "身份证号输入不正确!");
            }
        }
        if (v.getOldPassword() == null || v.getOldPassword().length() == 0) {
            errors.rejectValue("oldPassword", "oldPassword",
                    "旧密码必须填写!");
        }
        if (v.getPassword() == null || v.getPassword().length() == 0) {
            errors.rejectValue("password", "password", "新密码必须填写!");
        } else {
            if (!v.getPassword().equals(v.getRePassword())) {
                errors.rejectValue("rePasswordError",
                        "rePasswordError", "新密码与重复新密码必须一致!");
            }
        }
    }

    private boolean isValidate18Idcard(String idcard) {
        // 非18位为假
        if (idcard.length() != 18) {
            return false;
        }
        // 获取前17位
        String idcard17 = idcard.substring(0, 17);
        // 获取第18位
        String idcard18Code = idcard.substring(17, 18);
        char c[] = null;
        String checkCode = "";
        // 是否都为数字
        if (isDigital(idcard17)) {
            c = idcard17.toCharArray();
        } else {
            return false;
        }

        if (null != c) {
            int bit[] = new int[idcard17.length()];

            bit = converCharToInt(c);

            int sum17 = 0;

            sum17 = getPowerSum(bit);

            // 将和值与11取模得到余数进行校验码判断
            checkCode = getCheckCodeBySum(sum17);
            if (null == checkCode) {
                return false;
            }
            // 将身份证的第18位与算出来的校码进行匹配，不相等就为假
            if (!idcard18Code.equalsIgnoreCase(checkCode)) {
                return false;
            }
        }
        return true;
    }

    public boolean isDigital(String str) {
        return str == null || "".equals(str) ? false : str.matches("^[0-9]*$");
    }

    private int power[] = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };

    public int getPowerSum(int[] bit) {

        int sum = 0;

        if (power.length != bit.length) {
            return sum;
        }

        for (int i = 0; i < bit.length; i++) {
            for (int j = 0; j < power.length; j++) {
                if (i == j) {
                    sum = sum + bit[i] * power[j];
                }
            }
        }
        return sum;
    }

    public int[] converCharToInt(char[] c) throws NumberFormatException {
        int[] a = new int[c.length];
        int k = 0;
        for (char temp : c) {
            a[k++] = Integer.parseInt(String.valueOf(temp));
        }
        return a;
    }

    public String getCheckCodeBySum(int sum17) {
        String checkCode = null;
        switch (sum17 % 11) {
        case 10:
            checkCode = "2";
            break;
        case 9:
            checkCode = "3";
            break;
        case 8:
            checkCode = "4";
            break;
        case 7:
            checkCode = "5";
            break;
        case 6:
            checkCode = "6";
            break;
        case 5:
            checkCode = "7";
            break;
        case 4:
            checkCode = "8";
            break;
        case 3:
            checkCode = "9";
            break;
        case 2:
            checkCode = "x";
            break;
        case 1:
            checkCode = "0";
            break;
        case 0:
            checkCode = "1";
            break;
        }
        return checkCode;
    }
}
