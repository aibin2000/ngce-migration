package au.com.optus.ngcemigrations;

/**
 * This class was automatically generated by the data modeler tool.
 */

public class NGCE_Error implements java.io.Serializable {

	static final long serialVersionUID = 1L;

	private java.lang.String errorCode;
	private java.lang.String errorMsg;

	public NGCE_Error() {
	}

	public java.lang.String getErrorCode() {
		return this.errorCode;
	}

	public void setErrorCode(java.lang.String errorCode) {
		this.errorCode = errorCode;
	}

	public java.lang.String getErrorMsg() {
		return this.errorMsg;
	}

	public void setErrorMsg(java.lang.String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public NGCE_Error(java.lang.String errorCode, java.lang.String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

}