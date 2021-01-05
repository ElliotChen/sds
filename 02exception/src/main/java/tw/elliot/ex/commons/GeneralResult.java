package tw.elliot.ex.commons;

import lombok.Data;

@Data
public class GeneralResult {
	private Status status;

	private ErrorInfo error;
	private ErrorInfo[] errors;
}
