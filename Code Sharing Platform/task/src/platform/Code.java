package platform;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Entity
@Table(name = "code")
public class Code {
    private static final String PATTERN = "yyyy/MM/dd HH:mm:ss";
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(PATTERN);
    public static final String BASIC_CODE =
            "public static void main(String[] args) {\n" +
            "    System.out.println(\"Hello World!\");\n" +
            "}";

    @Id
    @JsonIgnore
    private final String id;
    private String code;
    private final LocalDateTime updateDate;
    private int views;
    private LocalDateTime seconds;
    @JsonIgnore
    private boolean secret;

    public Code() {
        this.id = UUID.randomUUID().toString();
        this.code = BASIC_CODE;
        updateDate = LocalDateTime.now();
        views = 0;
        seconds = null;
        secret = false;
    }

    public String getId() {
        return id;
    }

    @JsonProperty("code")
    public String getCode() {
        return code;
    }

    @JsonProperty("date")
    public String getUpdateDate() {
        return FORMATTER.format(updateDate);
    }

    @JsonProperty("views")
    public int getViews() {
        return views;
    }

    @JsonProperty("time")
    public long getSeconds() {
        if (seconds == null) {
            return 0;
        } else {
            return Duration.between(LocalDateTime.now(), seconds).getSeconds();
        }
    }

    public boolean isSecret() {
        return secret;
    }

    @JsonProperty("code")
    public void setCode(String code) {
        this.code = code;
    }

    @JsonProperty("views")
    public void setViews(int views) {
        this.views = views;
        checkSecret();
    }

    @JsonProperty("time")
    public void setSeconds(long seconds) {
        if (seconds <= 0) {
            this.seconds = null;
        } else {
            this.seconds = updateDate.plusSeconds(seconds);
        }
        checkSecret();
    }

    private void checkSecret() {
        if ((views > 0) || (getSeconds() > 0)) {
            secret = true;
        }
    }

    public boolean isSeconds() {
        return seconds != null;
    }

    public void oneView() {
        views--;
    }
}
