package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.CV} entity.
 */
public class CVDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(min = 5, max = 20)
    private String name;

    @NotNull
    @Size(min = 5, max = 20)
    private String email;

    @Size(min = 5, max = 50)
    private String mobile;

    @Size(min = 5, max = 50)
    private String github;

    @Size(min = 5, max = 50)
    private String linkedin;

    @Size(min = 5, max = 100)
    private String short_des;

    @Size(min = 5, max = 100)
    private String job_title;

    @Size(min = 5, max = 100)
    private String company;

    @Size(min = 5, max = 20)
    private String period;

    @Size(min = 5, max = 100)
    private String job_des;

    @Size(min = 5, max = 50)
    private String university;

    @Size(min = 5, max = 20)
    private String gpa;

    @Size(min = 5, max = 100)
    private String skills;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getShort_des() {
        return short_des;
    }

    public void setShort_des(String short_des) {
        this.short_des = short_des;
    }

    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getJob_des() {
        return job_des;
    }

    public void setJob_des(String job_des) {
        this.job_des = job_des;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getGpa() {
        return gpa;
    }

    public void setGpa(String gpa) {
        this.gpa = gpa;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CVDTO)) {
            return false;
        }

        CVDTO cVDTO = (CVDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, cVDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CVDTO{" +
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
