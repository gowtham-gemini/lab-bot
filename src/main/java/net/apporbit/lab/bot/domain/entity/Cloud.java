package net.apporbit.lab.bot.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

/**
 * Subjects are branch of knowledge studied or taught in a school, college, or university.
 *
 * Questions should be classified based on Subjects, so that we can evaluate the candidates
 * skill in the corresponding subject.
 *
 */
@Entity
@Table(name = "cloud_provider")
@SuppressWarnings("serial")
public class Cloud implements Serializable {
    
    /** Cloud provider Type Enum. */
    public static enum Type {
        /** The Private type of cloud. */
        PUBLIC,
        /** The Public type of cloud. */
        PRIVATE;
    };
 
    /** Cloud provider Platform Enum. */
    public static enum Platform {
        /** AWS. */
        AWS,

        /** Racksapce . */
        RACKSAPCE,

        /** Windows Azrue . */
        AZURE,

        /** Openstack V2. */
        OPENSTACK_V2,

        /** Openstack V3. */
        OPENSTACK_V3,

        /** Cloudstack V4.2. */
        CLOUDSTACK_4_2,

        /** Cloudstack V4.3. */
        CLOUDSTACK_4_3,

        /** Cloudstack V4.4. */
        CLOUDSTACK_4_4
        
        
    };
    
    /** Id of the Subject. */
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    /** Name of the Subject. */
    @NotEmpty
    @Size(min = 4, max = 20)
    @Column(name = "name", nullable = false)
    private String name;

    /** Version attribute to handle optimistic locking. */
    @Version
    @Column(name = "version")
    private Long version;

    /** Created by user. */
    @CreatedBy
    @JoinColumn(name = "created_user_id", referencedColumnName = "id")
    @OneToOne
    private User createdBy;

    /** Last updated by user. */
    @LastModifiedBy
    @JoinColumn(name = "updated_user_id", referencedColumnName = "id")
    @OneToOne
    private User updatedBy;

    /** Created date and time. */
    @CreatedDate
    private DateTime createdDateTime;

    /** Last modified date and time. */
    @LastModifiedDate
    private DateTime lastModifiedDateTime;

    /**
     * Default constructor.
     */
    protected Cloud() {
        super();
    }

    /**
     * Parameterized constructor.
     * @param name to set
     */
    public Cloud(String name) {
        super();
        this.name = name;
    }

    /**
     * Get the id.
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the id.
     * @param id - the Long to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the name.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name.
     * @param name - the String to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the version.
     * @return version
     */
    public Long getVersion() {
        return version;
    }

    /**
     * Set the version.
     * @param version - the Long to set
     */
    public void setVersion(Long version) {
        this.version = version;
    }

    /**
     * Get the createdBy.
     * @return createdBy
     */
    public User getCreatedBy() {
        return createdBy;
    }

    /**
     * Set the createdBy.
     * @param createdBy - the User to set
     */
    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Get the updatedBy.
     * @return updatedBy
     */
    public User getUpdatedBy() {
        return updatedBy;
    }

    /**
     * Set the updatedBy.
     * @param updatedBy - the User to set
     */
    public void setUpdatedBy(User updatedBy) {
        this.updatedBy = updatedBy;
    }

    /**
     * Get the createdDateTime.
     * @return createdDateTime
     */
    public DateTime getCreatedDateTime() {
        return createdDateTime;
    }

    /**
     * Set the createdDateTime.
     * @param createdDateTime - the DateTime to set
     */
    public void setCreatedDateTime(DateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    /**
     * Get the lastModifiedDateTime.
     * @return lastModifiedDateTime
     */
    public DateTime getLastModifiedDateTime() {
        return lastModifiedDateTime;
    }

    /**
     * Set the lastModifiedDateTime.
     * @param lastModifiedDateTime - the DateTime to set
     */
    public void setLastModifiedDateTime(DateTime lastModifiedDateTime) {
        this.lastModifiedDateTime = lastModifiedDateTime;
    }

}
