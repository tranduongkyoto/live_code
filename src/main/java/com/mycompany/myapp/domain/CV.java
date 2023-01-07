package com.mycompany.myapp.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A CV.
 */
@Entity
@Table(name = "cv")
public class CV implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(min = 5, max = 20)
    @Column(name = "name", length = 20, nullable = false)
    private String name;

    @NotNull
    @Size(min = 5, max = 20)
    @Column(name = "email", length = 20, nullable = false, unique = true)
    private String email;

    @Size(min = 5, max = 50)
    @Column(name = "mobile", length = 50)
    private String mobile;

    @Size(min = 5, max = 50)
    @Column(name = "github", length = 50)
    private String github;

    @Size(min = 5, max = 50)
    @Column(name = "linkedin", length = 50)
    private String linkedin;

    @Size(min = 5, max = 100)
    @Column(name = "short_des", length = 100)
    private String short_des;

    @Size(min = 5, max = 100)
    @Column(name = "job_title", length = 100)
    private String job_title;

    @Size(min = 5, max = 100)
    @Column(name = "company", length = 100)
    private String company;

    @Size(min = 5, max = 20)
    @Column(name = "period", length = 20)
    private String period;

    @Size(min = 5, max = 100)
    @Column(name = "job_des", length = 100)
    private String job_des;

    @Size(min = 5, max = 50)
    @Column(name = "university", length = 50)
    private String university;

    @Size(min = 5, max = 20)
    @Column(name = "gpa", length = 20)
    private String gpa;

    @Size(min = 5, max = 100)
    @Column(name = "skills", length = 100)
    private String skills;

    @Column(name = "user_id")
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public CV id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public CV name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public CV email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return this.mobile;
    }

    public CV mobile(String mobile) {
        this.setMobile(mobile);
        return this;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGithub() {
        return this.github;
    }

    public CV github(String github) {
        this.setGithub(github);
        return this;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public String getLinkedin() {
        return this.linkedin;
    }

    public CV linkedin(String linkedin) {
        this.setLinkedin(linkedin);
        return this;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getShort_des() {
        return this.short_des;
    }

    public CV short_des(String short_des) {
        this.setShort_des(short_des);
        return this;
    }

    public void setShort_des(String short_des) {
        this.short_des = short_des;
    }

    public String getJob_title() {
        return this.job_title;
    }

    public CV job_title(String job_title) {
        this.setJob_title(job_title);
        return this;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public String getCompany() {
        return this.company;
    }

    public CV company(String company) {
        this.setCompany(company);
        return this;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPeriod() {
        return this.period;
    }

    public CV period(String period) {
        this.setPeriod(period);
        return this;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getJob_des() {
        return this.job_des;
    }

    public CV job_des(String job_des) {
        this.setJob_des(job_des);
        return this;
    }

    public void setJob_des(String job_des) {
        this.job_des = job_des;
    }

    public String getUniversity() {
        return this.university;
    }

    public CV university(String university) {
        this.setUniversity(university);
        return this;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getGpa() {
        return this.gpa;
    }

    public CV gpa(String gpa) {
        this.setGpa(gpa);
        return this;
    }

    public void setGpa(String gpa) {
        this.gpa = gpa;
    }

    public String getSkills() {
        return this.skills;
    }

    public CV skills(String skills) {
        this.setSkills(skills);
        return this;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CV)) {
            return false;
        }
        return id != null && id.equals(((CV) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CV{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", email='" + getEmail() + "'" +
            ", mobile='" + getMobile() + "'" +
            ", github='" + getGithub() + "'" +
            ", linkedin='" + getLinkedin() + "'" +
            ", short_des='" + getShort_des() + "'" +
            ", job_title='" + getJob_title() + "'" +
            ", company='" + getCompany() + "'" +
            ", period='" + getPeriod() + "'" +
            ", job_des='" + getJob_des() + "'" +
            ", university='" + getUniversity() + "'" +
            ", gpa='" + getGpa() + "'" +
            ", skills='" + getSkills() + "'" +
            "}";
    }
}
