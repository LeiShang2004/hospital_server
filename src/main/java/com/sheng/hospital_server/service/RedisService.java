package com.sheng.hospital_server.service;


public interface RedisService {

    /* department相关 */
    /**
     * 获取Redis中department的key
     *
     * @param departmentId 科室id
     * @return key redis中的key
     */
    static String getDepartmentKey(Integer departmentId) {
        return "departmentId:" + departmentId;
    }

    Boolean isDepartmentsLoaded(Integer departmentId);

    void loadDepartments();

    String getDepartmentName(Integer departmentId);


    /* specialization相关 */
    /**
     * 获取Redis中specialization的key
     *
     * @param specializationId 专业id
     * @return key redis中的key
     */
    static String getSpecializationKey(Integer specializationId) {
        return "specializationId:" + specializationId;
    }

    Boolean isSpecializationsLoaded(Integer specializationId);

    void loadSpecializations();

    String getSpecializationName(Integer specializationId);


    /* title相关 */
    /**
     * 获取Redis中title的key
     *
     * @param titleId 职称id
     * @return key redis中的key
     */
    static String getTitleKey(Integer titleId) {
        return "titleId:" + titleId;
    }

    Boolean isTitlesLoaded(Integer titleId);

    void loadTitles(Integer titleId);

    String getTitleName(Integer titleId);

    String getTitleFee(Integer titleId);


}
