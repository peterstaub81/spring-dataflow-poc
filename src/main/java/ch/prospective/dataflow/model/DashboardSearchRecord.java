package ch.prospective.dataflow.model;

import java.util.Date;

public class DashboardSearchRecord {

    long stelleId;
    long auftragId;
    long publikationId;
    long inseratId;
    String status;
    String sprache;
    Date startDate;
    Date endDate;
    String medienTyp;
    long mediumId;
    String mediumName;
    String internerStellentitel;
    String externerStellentitel;
    long kontakt;
    long stellenInhaber;
    String recruiterName;
    long recruiterId;
    long hk_id;
    long h1_id;
    long h2_id;
    long h3_id;
    long h4_id;
    long h5_id;
    long h6_id;
    long h7_id;

    public DashboardSearchRecord(long stelleId, long auftragId, long publikationId, long inseratId, String status, String sprache, Date startDate, Date endDate, String medienTyp, long mediumId, String mediumName, String internerStellentitel, String externerStellentitel, long kontakt, long stellenInhaber, String recruiterName, long recruiterId, long hk_id, long h1_id, long h2_id, long h3_id, long h4_id, long h5_id, long h6_id, long h7_id) {
        this.stelleId = stelleId;
        this.auftragId = auftragId;
        this.publikationId = publikationId;
        this.inseratId = inseratId;
        this.status = status;
        this.sprache = sprache;
        this.startDate = startDate;
        this.endDate = endDate;
        this.medienTyp = medienTyp;
        this.mediumId = mediumId;
        this.mediumName = mediumName;
        this.internerStellentitel = internerStellentitel;
        this.externerStellentitel = externerStellentitel;
        this.kontakt = kontakt;
        this.stellenInhaber = stellenInhaber;
        this.recruiterName = recruiterName;
        this.recruiterId = recruiterId;
        this.hk_id = hk_id;
        this.h1_id = h1_id;
        this.h2_id = h2_id;
        this.h3_id = h3_id;
        this.h4_id = h4_id;
        this.h5_id = h5_id;
        this.h6_id = h6_id;
        this.h7_id = h7_id;
    }

    public DashboardSearchRecord() {
    }

    public long getStelleId() {
        return stelleId;
    }

    public void setStelleId(long stelleId) {
        this.stelleId = stelleId;
    }

    public long getAuftragId() {
        return auftragId;
    }

    public void setAuftragId(long auftragId) {
        this.auftragId = auftragId;
    }

    public long getPublikationId() {
        return publikationId;
    }

    public void setPublikationId(long publikationId) {
        this.publikationId = publikationId;
    }

    public long getInseratId() {
        return inseratId;
    }

    public void setInseratId(long inseratId) {
        this.inseratId = inseratId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSprache() {
        return sprache;
    }

    public void setSprache(String sprache) {
        this.sprache = sprache;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getMedienTyp() {
        return medienTyp;
    }

    public void setMedienTyp(String medienTyp) {
        this.medienTyp = medienTyp;
    }

    public long getMediumId() {
        return mediumId;
    }

    public void setMediumId(long mediumId) {
        this.mediumId = mediumId;
    }

    public String getMediumName() {
        return mediumName;
    }

    public void setMediumName(String mediumName) {
        this.mediumName = mediumName;
    }

    public String getInternerStellentitel() {
        return internerStellentitel;
    }

    public void setInternerStellentitel(String internerStellentitel) {
        this.internerStellentitel = internerStellentitel;
    }

    public String getExternerStellentitel() {
        return externerStellentitel;
    }

    public void setExternerStellentitel(String externerStellentitel) {
        this.externerStellentitel = externerStellentitel;
    }

    public long getKontakt() {
        return kontakt;
    }

    public void setKontakt(long kontakt) {
        this.kontakt = kontakt;
    }

    public long getStellenInhaber() {
        return stellenInhaber;
    }

    public void setStellenInhaber(long stellenInhaber) {
        this.stellenInhaber = stellenInhaber;
    }

    public String getRecruiterName() {
        return recruiterName;
    }

    public void setRecruiterName(String recruiterName) {
        this.recruiterName = recruiterName;
    }

    public long getRecruiterId() {
        return recruiterId;
    }

    public void setRecruiterId(long recruiterId) {
        this.recruiterId = recruiterId;
    }

    public long getHk_id() {
        return hk_id;
    }

    public void setHk_id(long hk_id) {
        this.hk_id = hk_id;
    }

    public long getH1_id() {
        return h1_id;
    }

    public void setH1_id(long h1_id) {
        this.h1_id = h1_id;
    }

    public long getH2_id() {
        return h2_id;
    }

    public void setH2_id(long h2_id) {
        this.h2_id = h2_id;
    }

    public long getH3_id() {
        return h3_id;
    }

    public void setH3_id(long h3_id) {
        this.h3_id = h3_id;
    }

    public long getH4_id() {
        return h4_id;
    }

    public void setH4_id(long h4_id) {
        this.h4_id = h4_id;
    }

    public long getH5_id() {
        return h5_id;
    }

    public void setH5_id(long h5_id) {
        this.h5_id = h5_id;
    }

    public long getH6_id() {
        return h6_id;
    }

    public void setH6_id(long h6_id) {
        this.h6_id = h6_id;
    }

    public long getH7_id() {
        return h7_id;
    }

    public void setH7_id(long h7_id) {
        this.h7_id = h7_id;
    }
}