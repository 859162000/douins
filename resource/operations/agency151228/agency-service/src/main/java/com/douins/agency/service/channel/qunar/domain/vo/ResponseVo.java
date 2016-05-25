/**
 * 
 */
package com.douins.agency.service.channel.qunar.domain.vo;

import java.util.List;

import com.douins.agency.service.channel.qunar.domain.model.ResponseRecord;

/** 
* @ClassName: UWResponse 
* @Description: 返回给 Quanr 的数据结构
* @author G. F. 
* @date 2016年1月8日 下午5:00:31 
*  
*/
public class ResponseVo {
        private String status;
        private String orderNo;
        private String agencyNo;
        private String businessId;
        private List<ResponseRecord> records;
        private String errMsg;
        
        public String getStatus() {
            return status;
        }
        public void setStatus(String status) {
            this.status = status;
        }
        public String getOrderNo() {
            return orderNo;
        }
        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }
        public String getAgencyNo() {
            return agencyNo;
        }
        public void setAgencyNo(String agencyNo) {
            this.agencyNo = agencyNo;
        }
        public String getBusinessId() {
            return businessId;
        }
        public void setBusinessId(String businessId) {
            this.businessId = businessId;
        }
        public List<ResponseRecord> getRecords() {
            return records;
        }
        public void setRecords(List<ResponseRecord> records) {
            this.records = records;
        }
        public String getErrMsg() {
            return errMsg;
        }
        public void setErrMsg(String errMsg) {
            this.errMsg = errMsg;
        }
}
