package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class RegistrationDetails implements SuperEntity {
    @Id
    private String programId;
    @ManyToOne
    @JoinColumn(name= "registerNumber",referencedColumnName = "regNo")
    private String regNo;
    private String joiningDate;

    public RegistrationDetails() {
    }

    public RegistrationDetails(String programId, String regNo, String joiningDate) {
        this.programId = programId;
        this.regNo = regNo;
        this.joiningDate = joiningDate;
    }

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(String joiningDate) {
        this.joiningDate = joiningDate;
    }

    @Override
    public String toString() {
        return "RegistrationDetails{" +
                "programId='" + programId + '\'' +
                ", regNo='" + regNo + '\'' +
                ", joiningDate='" + joiningDate + '\'' +
                '}';
    }
}
