/**
 * 
 */
package com.douins.agency.service.insurance.ccic.domain.enums;

/** 
* @ClassName: CCICSaleStrategyType 
* @Description: CCIC 产品销售策略
* @author G. F. 
* @date 2016年1月1日 下午4:15:56 
*  
*   按天：0xx
*   按月：1xx
*   按年：2xx
*/
public enum CCICSaleStrategyType {
    BY_DAY_1("001", 0, 2, "0 ~ 2 天"),
    BY_DAY_2("002", 3, 5, "3 ~ 5 天"),
    BY_DAY_3("003", 6, 9, "6 ~ 9 天"),
    ;
    
    private String code;
    private int start;
    private int end;
    private String content;
    
    private CCICSaleStrategyType(String code, int start, int end, String content){
        this.code = code;
        this.start = start;
        this.end = end;
        this.content = content;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    /**
     * 根据销售的天数获取销售策略代码
     * @param days
     * @return
     */
    public static String getTypeCodeByDays(int days){
        CCICSaleStrategyType type = CCICSaleStrategyType.BY_DAY_1;
        for(CCICSaleStrategyType t : CCICSaleStrategyType.values()){
            if(t.getStart() <= days && t.getEnd() >= days){
                return t.getCode();
            }
        }
        
        return type.getCode();
    }
}
