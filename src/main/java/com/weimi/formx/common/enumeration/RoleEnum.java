package com.weimi.formx.common.enumeration;

import lombok.Getter;

/**
 * Created by yangsh on 2018-05-18
 */
@Getter
public enum RoleEnum {

    ADVISER(1, "顾问"),
    SHOPKEEPER(2, "店主"),
    SHOPOWNER(3, "店长");

    private Integer roleId;
    private String roleName;

    private RoleEnum(Integer roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

}
