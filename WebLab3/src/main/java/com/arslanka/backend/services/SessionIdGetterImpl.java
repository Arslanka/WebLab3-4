package com.arslanka.backend.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

@Named("sessionIdGetterImpl")
@ApplicationScoped
public class SessionIdGetterImpl implements SessionIdGetter {
    @Override
    public String getCurrentSessionId() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        return facesContext.getExternalContext().getSessionId(false);
    }
}
