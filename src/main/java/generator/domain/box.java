package generator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * 盲盒表
 * @TableName box
 */
@TableName(value ="box")
public class box implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    @TableField(value = "create_time")
    private Date create_time;

    /**
     * 
     */
    @TableField(value = "modify_time")
    private Date modify_time;

    /**
     * 
     */
    @TableField(value = "version")
    private Integer version;

    /**
     * 盲盒名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 盲盒图片
     */
    @TableField(value = "img")
    private String img;

    /**
     * 盲盒价格
     */
    @TableField(value = "price")
    private Double price;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public Long getId() {
        return id;
    }

    /**
     * 
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 
     */
    public Date getCreate_time() {
        return create_time;
    }

    /**
     * 
     */
    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    /**
     * 
     */
    public Date getModify_time() {
        return modify_time;
    }

    /**
     * 
     */
    public void setModify_time(Date modify_time) {
        this.modify_time = modify_time;
    }

    /**
     * 
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * 
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * 盲盒名称
     */
    public String getName() {
        return name;
    }

    /**
     * 盲盒名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 盲盒图片
     */
    public String getImg() {
        return img;
    }

    /**
     * 盲盒图片
     */
    public void setImg(String img) {
        this.img = img;
    }

    /**
     * 盲盒价格
     */
    public Double getPrice() {
        return price;
    }

    /**
     * 盲盒价格
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        box other = (box) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCreate_time() == null ? other.getCreate_time() == null : this.getCreate_time().equals(other.getCreate_time()))
            && (this.getModify_time() == null ? other.getModify_time() == null : this.getModify_time().equals(other.getModify_time()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getImg() == null ? other.getImg() == null : this.getImg().equals(other.getImg()))
            && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCreate_time() == null) ? 0 : getCreate_time().hashCode());
        result = prime * result + ((getModify_time() == null) ? 0 : getModify_time().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getImg() == null) ? 0 : getImg().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", create_time=").append(create_time);
        sb.append(", modify_time=").append(modify_time);
        sb.append(", version=").append(version);
        sb.append(", name=").append(name);
        sb.append(", img=").append(img);
        sb.append(", price=").append(price);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}