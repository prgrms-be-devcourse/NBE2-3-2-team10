package org.team10.washcode.exception;


import lombok.Getter;
import java.time.Instant;


@Getter
public class ResponseResult<T> {
    private final int statusCode;     // 상태 코드
    private final String message;     // 메시지
    private final String timestamp;   // 타임스탬프
    private final T data;

    // 응답은 하지만, 응답할 데이턱 없는 경우
    public ResponseResult(ResponseState responseState) {
        this.statusCode = responseState.getStatus();
        this.message = responseState.getMessage();
        this.timestamp = Instant.now().toString();
        this.data = null;
    }

    // 응답 코드 및 응답 데이터를 담아 보냄
    public ResponseResult(ResponseState responseState, T resultObject) {
        this.statusCode = responseState.getStatus();
        this.message = responseState.getMessage();
        this.timestamp = Instant.now().toString();
        this.data = resultObject;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + statusCode +
                ", msg='" + message + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", data=" + data +
                '}';
    }
}
