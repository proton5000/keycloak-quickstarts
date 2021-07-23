package org.keycloak.quickstart.storage.user.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PaginationDTO<T> {

    public PaginationDTO() {
    }

    public PaginationDTO(String status, Info pagination, List<T> value, String error, String errorDescription,
                         String message, String causeMessage, Long procExecTime, List<DebugInputArg> debugInputArgs,
                         ProcedureInfo procId) {
        this.status = status;
        this.pagination = pagination;
        this.value = value;
        this.error = error;
        this.errorDescription = errorDescription;
        this.message = message;
        this.causeMessage = causeMessage;
        this.procExecTime = procExecTime;
        this.debugInputArgs = debugInputArgs;
        this.procId = procId;
    }

    private String status;

    private Info pagination;

    @JsonProperty("data")
    private List<T> value;

    private String error;

    @JsonProperty("error_description")
    private String errorDescription;

    private String message;

    private String causeMessage;

    private Long procExecTime;

    private List<DebugInputArg> debugInputArgs;

    private ProcedureInfo procId;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Info getPagination() {
        return pagination;
    }

    public void setPagination(Info pagination) {
        this.pagination = pagination;
    }

    public List<T> getValue() {
        return value;
    }

    public void setValue(List<T> value) {
        this.value = value;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCauseMessage() {
        return causeMessage;
    }

    public void setCauseMessage(String causeMessage) {
        this.causeMessage = causeMessage;
    }

    public Long getProcExecTime() {
        return procExecTime;
    }

    public void setProcExecTime(Long procExecTime) {
        this.procExecTime = procExecTime;
    }

    public List<DebugInputArg> getDebugInputArgs() {
        return debugInputArgs;
    }

    public void setDebugInputArgs(List<DebugInputArg> debugInputArgs) {
        this.debugInputArgs = debugInputArgs;
    }

    public ProcedureInfo getProcId() {
        return procId;
    }

    public void setProcId(ProcedureInfo procId) {
        this.procId = procId;
    }

    public static class DebugInputArg {

        public DebugInputArg() {
        }

        public DebugInputArg(String argName, Object argValue, String argType) {
            this.argName = argName;
            this.argValue = argValue;
            this.argType = argType;
        }

        private String argName;
        private Object argValue;
        private String argType;

        public String getArgName() {
            return argName;
        }

        public void setArgName(String argName) {
            this.argName = argName;
        }

        public Object getArgValue() {
            return argValue;
        }

        public void setArgValue(Object argValue) {
            this.argValue = argValue;
        }

        public String getArgType() {
            return argType;
        }

        public void setArgType(String argType) {
            this.argType = argType;
        }

        @Override
        public String toString() {
            return "DebugInputArg{" +
                    "argName='" + argName + '\'' +
                    ", argValue=" + argValue +
                    ", argType='" + argType + '\'' +
                    '}';
        }
    }

    public static class ProcedureInfo {

        public ProcedureInfo() {
        }

        public ProcedureInfo(String apiumId, String procCode) {
            this.apiumId = apiumId;
            this.procCode = procCode;
        }

        private String apiumId;
        private String procCode;

        public String getApiumId() {
            return apiumId;
        }

        public void setApiumId(String apiumId) {
            this.apiumId = apiumId;
        }

        public String getProcCode() {
            return procCode;
        }

        public void setProcCode(String procCode) {
            this.procCode = procCode;
        }

        @Override
        public String toString() {
            return "ProcedureInfo{" +
                    "apiumId='" + apiumId + '\'' +
                    ", procCode='" + procCode + '\'' +
                    '}';
        }
    }

    public static class Info {

        public Info() {
        }

        public Info(Integer page, Integer pageSize, Integer count) {
            this.page = page;
            this.pageSize = pageSize;
            this.count = count;
        }

        private Integer page;
        private Integer pageSize;
        private Integer count;

        public Integer getPage() {
            return page;
        }

        public void setPage(Integer page) {
            this.page = page;
        }

        public Integer getPageSize() {
            return pageSize;
        }

        public void setPageSize(Integer pageSize) {
            this.pageSize = pageSize;
        }

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }

        @Override
        public String toString() {
            return "Info{" +
                    "page=" + page +
                    ", pageSize=" + pageSize +
                    ", count=" + count +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "PaginationDTO{" +
                "status='" + status + '\'' +
                ", pagination=" + pagination +
                ", value=" + value +
                ", error='" + error + '\'' +
                ", errorDescription='" + errorDescription + '\'' +
                ", message='" + message + '\'' +
                ", causeMessage='" + causeMessage + '\'' +
                ", procExecTime=" + procExecTime +
                ", debugInputArgs=" + debugInputArgs +
                ", procId=" + procId +
                '}';
    }
}
