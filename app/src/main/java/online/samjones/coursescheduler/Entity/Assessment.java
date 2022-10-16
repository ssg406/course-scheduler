package online.samjones.coursescheduler.Entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(tableName = "assessments",
        foreignKeys = {@ForeignKey(entity = Course.class, parentColumns = "courseId",
                childColumns = "courseId", onDelete = ForeignKey.CASCADE)})
public class Assessment {

    @PrimaryKey(autoGenerate = true)
    private int assessmentId;

    private String title;
    private LocalDate assessmentStart;
    private LocalDate assessmentEnd;
    private int courseId;

    public Assessment(String title, LocalDate assessmentStart, LocalDate assessmentEnd, int courseId) {
        this.title = title;
        this.assessmentStart = assessmentStart;
        this.assessmentEnd = assessmentEnd;
        this.courseId = courseId;
    }

    public int getAssessmentId() {
        return assessmentId;
    }

    public void setAssessmentId(int assessmentId) {
        this.assessmentId = assessmentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getAssessmentStart() {
        return assessmentStart;
    }

    public void setAssessmentStart(LocalDate assessmentStart) {
        this.assessmentStart = assessmentStart;
    }

    public LocalDate getAssessmentEnd() {
        return assessmentEnd;
    }

    public void setAssessmentEnd(LocalDate assessmentEnd) {
        this.assessmentEnd = assessmentEnd;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "Assessment{" +
                "assessmentId=" + assessmentId +
                ", title='" + title + '\'' +
                ", assessmentStart=" + assessmentStart +
                ", assessmentEnd=" + assessmentEnd +
                ", courseId=" + courseId +
                '}';
    }
}
