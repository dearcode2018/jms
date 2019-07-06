package com.hua.entity;

import java.math.BigDecimal;
import java.util.Date;

public class CollegeStudent {
  
    private Integer id;

    private String name;

   
    private Byte type;

  
    private BigDecimal credit;

    private Date birthday;
    
    private String address;

    private String remark;

    private static final long serialVersionUID = 1L;

    public static final String ID = "id";

    public static final String DB_ID = "id";

    public static final String NAME = "name";

    public static final String DB_NAME = "name";

    public static final String TYPE = "type";

    public static final String DB_TYPE = "type";

    public static final String CREDIT = "credit";

    public static final String DB_CREDIT = "credit";

    public static final String BIRTHDAY = "birthday";

    public static final String DB_BIRTHDAY = "birthday";

    public static final String ADDRESS = "address";

    public static final String DB_ADDRESS = "address";

    public static final String REMARK = "remark";

    public static final String DB_REMARK = "remark";

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取姓名
     *
     * @return name - 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置姓名
     *
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取类型，1-理科，2-工科，3-文科
     *
     * @return type - 类型，1-理科，2-工科，3-文科
     */
    public Byte getType() {
        return type;
    }

    /**
     * 设置类型，1-理科，2-工科，3-文科
     *
     * @param type 类型，1-理科，2-工科，3-文科
     */
    public void setType(Byte type) {
        this.type = type;
    }

    /**
     * 获取学分
     *
     * @return credit - 学分
     */
    public BigDecimal getCredit() {
        return credit;
    }

    /**
     * 设置学分
     *
     * @param credit 学分
     */
    public void setCredit(BigDecimal credit) {
        this.credit = credit;
    }

    /**
     * 获取出生日期 yyyy-MM-dd
     *
     * @return birthday - 出生日期 yyyy-MM-dd
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * 设置出生日期 yyyy-MM-dd
     *
     * @param birthday 出生日期 yyyy-MM-dd
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * 获取地址
     *
     * @return address - 地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置地址
     *
     * @param address 地址
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * 获取备注，详细说明
     *
     * @return remark - 备注，详细说明
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注，详细说明
     *
     * @param remark 备注，详细说明
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}