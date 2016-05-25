package com.douins.insurance.domain.modelLianlife;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
@XStreamAlias("UnderwirtingList")
public class UnderwritingList {
	    //for underwriting 
		@XStreamImplicit(itemFieldName="UnderwirtingItem")
		private List<UnderwritingItem> underwritinglist;

		public List<UnderwritingItem> getUnderwritinglist() {
			return underwritinglist;
		}

		public void setUnderwritinglist(List<UnderwritingItem> underwritinglist) {
			this.underwritinglist = underwritinglist;
		}
		

}
