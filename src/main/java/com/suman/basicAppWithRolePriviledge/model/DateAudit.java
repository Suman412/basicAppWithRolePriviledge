package com.suman.basicAppWithRolePriviledge.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.Instant;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt", "createdBy", "updatedBy"}, allowGetters = true)
@Getter
@Setter
public abstract class DateAudit implements Serializable {

    private static final long serialVersionUID = 6745441422356116858L;

    @CreatedDate
    @Column(name = "created_at", updatable = false, nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;

    @CreatedBy
    @Column(columnDefinition = "BIGINT(20) DEFAULT 0")
    private Long createdBy;

    @LastModifiedBy
    private Long updatedBy;
    @Column(columnDefinition = "BIT(1) DEFAULT 1")
    private Boolean isActive;

}
