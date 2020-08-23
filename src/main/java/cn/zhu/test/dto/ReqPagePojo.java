package cn.zhu.test.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class ReqPagePojo {

	@ApiModelProperty(value = "每页显示数量")
	private Integer pageSize;

	@ApiModelProperty(value = "当前页码")
	private Integer pageNo;

	@ApiModelProperty(value = "导出时，必填。1 导出当前页 2 导出全部")
	private String exportType;

	@ApiModelProperty(value = "导出时，必填。例如：admin")
	private String userName;

	public Integer getPageSize() {
		if (pageSize == null || pageSize < 1) {
			pageSize = 10;
		}
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNo() {
		if (pageNo == null || pageNo < 1) {
			pageNo = 1;
		}
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public String getExportType() {
		return exportType;
	}

	public void setExportType(String exportType) {
		this.exportType = exportType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
