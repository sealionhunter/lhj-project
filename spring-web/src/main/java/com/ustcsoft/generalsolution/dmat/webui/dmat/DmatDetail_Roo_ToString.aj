// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.ustcsoft.generalsolution.dmat.webui.dmat;

import java.lang.String;

privileged aspect DmatDetail_Roo_ToString {
    
    public String DmatDetail.toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CategoryId: ").append(getCategoryId()).append(", ");
        sb.append("CategoryName: ").append(getCategoryName()).append(", ");
        sb.append("Details: ").append(getDetails()).append(", ");
        sb.append("DmatId: ").append(getDmatId()).append(", ");
        sb.append("Duration: ").append(getDuration()).append(", ");
        sb.append("GuestDeptId: ").append(getGuestDeptId()).append(", ");
        sb.append("GuestDeptName: ").append(getGuestDeptName()).append(", ");
        sb.append("LocalStartDate: ").append(getLocalStartDate()).append(", ");
        sb.append("ProjectId: ").append(getProjectId()).append(", ");
        sb.append("ProjectName: ").append(getProjectName()).append(", ");
        sb.append("StartDate: ").append(getStartDate()).append(", ");
        sb.append("SubcategoryId: ").append(getSubcategoryId()).append(", ");
        sb.append("SubcategoryName: ").append(getSubcategoryName()).append(", ");
        sb.append("UpdateDate: ").append(getUpdateDate()).append(", ");
        sb.append("UpdateUserId: ").append(getUpdateUserId()).append(", ");
        sb.append("UpdateUserName: ").append(getUpdateUserName()).append(", ");
        sb.append("UserId: ").append(getUserId()).append(", ");
        sb.append("UserName: ").append(getUserName());
        return sb.toString();
    }
    
}
