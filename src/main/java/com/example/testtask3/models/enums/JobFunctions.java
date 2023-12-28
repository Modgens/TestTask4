package com.example.testtask3.models.enums;

import lombok.Getter;

@Getter
public enum JobFunctions {
    ACCOUNTING_FINANCE("\"Accounting & Finance\""),
    ADMINISTRATION("\"Administration\""),
    CUSTOMER_SERVICE("\"Customer Service\""),
    DATA_SCIENCE("\"Data Science\""),
    DESIGN("\"Design"),
    IT("\"IT\""),
    LEGAL("\"Legal"),
    MARKETING_COMMUNICATIONS("\"Marketing & Communications\""),
    OPERATIONS("\"Operations\""),
    OTHER_ENGINEERING("\"Other Engineering\""),
    PEOPLE_HR("\"People & HR\""),
    PRODUCT("\"Product\""),
    QUALITY_ASSURANCE("\"Quality Assurance\""),
    SALES_BUSINESS_DEVELOPMENT("\"Sales & Business Development\""),
    SOFTWARE_ENGINEERING("\"Software Engineering\"");

    private final String value;

    JobFunctions(String value) {
        this.value = value;
    }

}
