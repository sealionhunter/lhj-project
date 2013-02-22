package validator;

import java.util.List;

import model.User;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import command.RegistCommand;

public class RegistValidator implements Validator {

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public boolean supports(Class clazz) {
        // TODO Auto-generated method stub
        return clazz.isAssignableFrom(RegistCommand.class);
    }

    public void validate(Object target, Errors errors) {
        validateId(target, errors);
    }

    public void validateId(Object target, Errors errors) {
        // RegistCommand v = (RegistCommand) target;
        // System.out.println("===============================================================");
        // if (v.getId() == null || v.getId().length() == 0) {
        // errors.rejectValue("id",
        // "required.id", "id must be input!");
        // }
    }

    public void validateRegistInfo(Object target, Errors errors,
            List<User> idCardNoLst) {
        RegistCommand v = (RegistCommand) target;

        if ("1".equals(v.getEditFlg())) {
            User user = null;
            if (v.getIdCardNo() == null || v.getIdCardNo().length() == 0) {
                errors.rejectValue("idCardNoError", "required.idCardNoError",
                        "身份证号必须填写!");
            } else {
                if (!isValidate18Idcard(v.getIdCardNo())) {
                    errors.rejectValue("idCardNoError",
                            "required.idCardNoError", "身份证号输入不正确!");
                } else {
                    if (idCardNoLst == null || idCardNoLst.isEmpty()) {
                        errors.rejectValue("idCardNoError",
                                "required.idCardNoError", "身份证号不存在!");
                    } else {
                        user = (User) idCardNoLst.get(0);
                    }
                }
            }
            if (v.getPassword() == null || v.getPassword().length() == 0) {
                errors.rejectValue("password", "required.password", "密码必须填写!");
            } else {
                if (user != null && !v.getPassword().equals(user.getPassword())) {
                    errors.rejectValue("password", "required.password", "密码错误!");
                }
            }
        } else {
            if (v.getName() == null || v.getName().length() == 0) {
                errors.rejectValue("name", "required.name", "用户名必须填写!");
            }
            if (v.getIdCardNo() == null || v.getIdCardNo().length() == 0) {
                errors.rejectValue("idCardNoError", "required.idCardNoError",
                        "身份证号必须填写!");
            } else {
                if (!isValidate18Idcard(v.getIdCardNo())) {
                    errors.rejectValue("idCardNoError",
                            "required.idCardNoError", "身份证号输入不正确!");
                } else {
                    if (idCardNoLst != null && idCardNoLst.size() != 0) {
                        errors.rejectValue("idCardNoError",
                                "required.idCardNoError", "身份证号已存在!");
                    }
                }
            }
            if (v.getPassword() == null || v.getPassword().length() == 0) {
                errors.rejectValue("password", "required.password", "密码必须填写!");
            } else {
                if (!v.getPassword().equals(v.getRePassword())) {
                    errors.rejectValue("rePasswordError",
                            "required.rePasswordError", "密码与重复密码必须一致!");
                }
            }
        }
    }

    public void validatePeopleInfo(Object target, Errors errors) {
        RegistCommand v = (RegistCommand) target;
        if (v.getHomeTown() == null || v.getHomeTown().length() == 0) {
            errors.rejectValue("homeTown", "required.homeTown", "籍贯必须填写!");
        }
        if (v.getDegree() == null || v.getDegree().length() == 0) {
            errors.rejectValue("degree", "required.degree", "最高学历必须填写!");
        }
        if (v.getUserSchool() == null || v.getUserSchool().length() == 0) {
            errors.rejectValue("userSchool", "required.userSchool", "毕业院校必须填写!");
        }
        if (v.getWorkYears() == null || v.getWorkYears().length() == 0) {
            errors.rejectValue("workYears", "required.workYears", "工作年限必须填写!");
        } else if (!isDigital(v.getWorkYears())) {
            errors.rejectValue("workYears", "required.workYears", "工作年限必须为数字!");
        }
        if (v.getUserMajor() == null || v.getUserMajor().length() == 0) {
            errors.rejectValue("userMajor", "required.userMajor", "专业必须填写!");
        }
        if (v.getComputerSkill() == null || v.getComputerSkill().length() == 0) {
            errors.rejectValue("computerSkill", "required.computerSkill",
                    "计算机程度必须填写!");
        }
        if (v.getLanguageSkill() == null || v.getLanguageSkill().length() == 0) {
            errors.rejectValue("languageSkill", "required.languageSkill",
                    "外语程度必须填写!");
        }
        if (v.getTelephone() == null || v.getTelephone().length() == 0) {
            errors.rejectValue("telephone", "required.telephone", "联系电话必须填写!");
        }
        if (v.getHeight() == null || v.getHeight().length() == 0) {
            errors.rejectValue("height", "required.height", "身高必须填写!");
        } else if (!isDigital(v.getHeight())) {
            errors.rejectValue("height", "required.height", "身高必须为数字!");
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
