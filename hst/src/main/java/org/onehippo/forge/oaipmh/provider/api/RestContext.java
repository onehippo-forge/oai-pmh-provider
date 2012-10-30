package org.onehippo.forge.oaipmh.provider.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.UriInfo;

import org.hippoecm.hst.core.request.HstRequestContext;
import org.onehippo.forge.oaipmh.provider.api.BaseOAIResource;

/**
 * @version $Id$
 */
public class RestContext {

    private BaseOAIResource service;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private UriInfo uriInfo;

    /**
     * Detail
     *
     * @param service
     * @param request
     * @param response
     * @param uriInfo
     */
    public RestContext(final BaseOAIResource service, final HttpServletRequest request, final HttpServletResponse response, final UriInfo uriInfo) {
        this.service = service;
        this.request = request;
        this.response = response;
        this.uriInfo = uriInfo;
    }


    public HstRequestContext getHstRequestContext() {
        return getService().getRequestContext(request);
    }


    public BaseOAIResource getService() {
        return service;
    }

    public void setService(final BaseOAIResource service) {
        this.service = service;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(final HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(final HttpServletResponse response) {
        this.response = response;
    }

    public UriInfo getUriInfo() {
        return uriInfo;
    }

    public void setUriInfo(final UriInfo uriInfo) {
        this.uriInfo = uriInfo;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("RestContext");
        sb.append(", service=").append(service);
        sb.append(", request=").append(request);
        sb.append(", response=").append(response);
        sb.append(", uriInfo=").append(uriInfo);
        sb.append('}');
        return sb.toString();
    }
}
