package Annotations_All;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "teacher_table")
public class Teacher {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "Teacher_name")
	private String name;

	@Column(name = "Teacher_phonenumber", nullable = false, unique = true, updatable = false)
	private long phone;

	@CreationTimestamp
	private LocalDateTime createtime;

	@UpdateTimestamp
	private LocalDateTime updateTime;

	public Teacher() {
		super();
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public long getPhone() {
		return phone;
	}

	public LocalDateTime getCreatetime() {
		return createtime;
	}

	public LocalDateTime getUpdateTime() {
		return updateTime;
	}

	public void setId(int id) {

		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public void setCreatetime(LocalDateTime createtime) {
		this.createtime = createtime;
	}

	public void setUpdateTime(LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}

}
