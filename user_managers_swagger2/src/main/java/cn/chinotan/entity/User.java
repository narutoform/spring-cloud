package cn.chinotan.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@ApiModel(description = "用户实体类")
public class User implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value = "用户id", dataType = "Long", required = true)
    private Long id;
    
    @Column
    @ApiModelProperty(value = "用户名", dataType = "String", required = true)
    private String name;

    @Column
    @ApiModelProperty(value = "用户年龄", dataType = "Integer", required = true)
    private Integer age;

    @Column
    @ApiModelProperty(value = "用户收入", dataType = "BigDecimal", required = true)
    private BigDecimal balance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
