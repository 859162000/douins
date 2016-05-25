package com.douins.bank.domain.model.nybc;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("SDOROOT")
public class ProjecteRegistRequest extends NYTransRequest{
	@XStreamAlias("BODY")
    private ProjecteRegistRequestBody body;
    
    /* (non-Javadoc)
     * @see com.douins.bank.domain.model.nybc.NYTransRequest#setBody(java.lang.Object)
     */
    @Override
    public <T> void setBody(T body) {
        this.body = (ProjecteRegistRequestBody)body;
    }

    public ProjecteRegistRequestBody getBody() {
        return body;
    }
	
}
