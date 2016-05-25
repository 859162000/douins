/**
 * 
 */
package com.douins.bank.domain.model.nybc;

import com.douins.common.util.ReadConfig;
import com.mango.core.abstractclass.AbstractModel;

/** 
* @ClassName: GateWayUrl 
* @Description: nybc 的网关 URL
* @author G. F. 
* @date 2015年12月9日 上午9:14:37 
*  
*  
*/
public class GateWayUrl extends AbstractModel{
    private static final long serialVersionUID = 294520143142284040L;
    private static final String  http = ReadConfig.get("http");
    private static final String nyIp = ReadConfig.get("url_ip_nybc");
    private static final String url_pwd_set_h5 = String.format("%s://%s/moneymanager/H5/ptp_setTradePass.html", http, nyIp);
    private static final String url_pwd_set_pc = String.format("%s://%s/moneymanager/pages/ptp_setTradePass.html", http, nyIp);
    private static final String url_pwd_modify_h5 = String.format("%s://%s/moneymanager/H5/ptp_modPaypsw.html", http, nyIp);
    private static final String url_pwd_modify_pc = String.format("%s://%s/moneymanager/pages/ptp_modifyPaymentPassword.html", http, nyIp);
    private static final String url_pwd_reset_h5 = String.format("%s://%s/moneymanager/H5/ptp_resetPsw.html", http, nyIp);
    private static final String url_pwd_reset_pc = String.format("%s://%s/moneymanager/pages/ptp_resetTradePass.html", http, nyIp);
    private static final String url_card_set_h5 = String.format("%s://%s/moneymanager/H5/ptp_card.html", http, nyIp);
    private static final String url_card_set_pc = String.format("%s://%s/moneymanager/pages/ptp_ej_cardSet.html", http, nyIp);
    private static final String url_recharge_h5 = String.format("%s://%s/moneymanager/H5/ptp_recharge.html", http, nyIp);
    private static final String url_recharge_pc = String.format("%s://%s/moneymanager/pages/ptp_recharge.html", http, nyIp);
    private static final String url_withdraw_h5 = String.format("%s://%s/moneymanager/H5/ptp_withdraw.html", http, nyIp);
    private static final String url_withdraw_pc = String.format("%s://%s/moneymanager/pages/ptp_withdrawal.html", http, nyIp);
    private static final String url_buy_h5 = String.format("%s://%s/moneymanager/H5/ptp_buy.html", http, nyIp);
    private static final String url_buy_pc = String.format("%s://%s/moneymanager/pages/ptp_buy.html", http, nyIp);
    private static final String url_repay_h5 = String.format("%s://%s/moneymanager/H5/ptp_edSelfloan.html", http, nyIp);
    private static final String url_repay_pc = String.format("%s://%s/moneymanager/pages/ptp_ed_selfloan.html", http, nyIp);
    private static final String url_pay_h5 = String.format("%s://%s/moneymanager/H5/ptp_single_pay.html", http, nyIp);
    
  //  private GateWayUrl(){}
    //private static GateWayUrl gateWayUrl = new GateWayUrl();
    
   /* public static GateWayUrl getInstance(){
        return gateWayUrl;
    }*/
    
    public String getUrlPwdSetH5() {
        return url_pwd_set_h5;
    }
    public String getUrlPwdSetPc() {
        return url_pwd_set_pc;
    }
    public String getUrlPwdModifyH5() {
        return url_pwd_modify_h5;
    }
    public String getUrlPwdModifyPc() {
        return url_pwd_modify_pc;
    }
    public String getUrlPwdResetH5() {
        return url_pwd_reset_h5;
    }
    public String getUrlPwdResetPc() {
        return url_pwd_reset_pc;
    }
    public String getUrlCardSetH5() {
        return url_card_set_h5;
    }
    public String getUrlCardSetPc() {
        return url_card_set_pc;
    }
    public String getUrlRechargeH5() {
        return url_recharge_h5;
    }
    public String getUrlRechargePc() {
        return url_recharge_pc;
    }
    public String getUrlWithdrawH5() {
        return url_withdraw_h5;
    }
    public String getUrlWithdrawPc() {
        return url_withdraw_pc;
    }
    public String getUrlBuyH5() {
        return url_buy_h5;
    }
    public static String getUrlBuyPc() {
        return url_buy_pc;
    }
    public String getUrlRepayH5() {
        return url_repay_h5;
    }
    public String getUrlRepayPc() {
        return url_repay_pc;
    }

    public static String getUrlPayH5() {
		return url_pay_h5;
	}

    
	/* (non-Javadoc)
     * @see com.mango.core.abstractclass.AbstractModel#initPrimaryKey()
     */
    @Override
    public void initPrimaryKey() {
        // TODO Auto-generated method stub
        
    }
    
    
}
