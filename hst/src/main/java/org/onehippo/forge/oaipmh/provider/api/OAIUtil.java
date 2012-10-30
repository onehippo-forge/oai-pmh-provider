package org.onehippo.forge.oaipmh.provider.api;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.collections.map.MultiKeyMap;
import org.hippoecm.hst.content.beans.standard.HippoDocumentBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OAIUtil implements InvocationHandler {

    private static Logger log = LoggerFactory.getLogger(OAIUtil.class);

    private static final MultiKeyMap metaDataCache = new MultiKeyMap();

    private static OAIUtil ourInstance = new OAIUtil();

    public static OAIUtil getInstance() {
        return ourInstance;
    }

    private OAIUtil() {
    }


    public String getIdentifier(final HippoDocumentBean bean, Object... objects) {
        final Class<? extends HippoDocumentBean> aClass = bean.getClass();
        final Method[] methods = aClass.getMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(OAIIdentifier.class) && method.getReturnType().equals(String.class)) {
                return (String) invoke(bean, method, objects);
            }
        }
        return null;
    }

    public Object delegate(final HippoDocumentBean bean, final String prefix, Object... objects) {
        final Class<? extends HippoDocumentBean> aClass = bean.getClass();
        if (metaDataCache.containsKey(aClass, prefix)) {
            return invoke(bean, (Method) metaDataCache.get(aClass, prefix), objects);
        }
        final Method[] methods = aClass.getMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(OAIDelegate.class)) {
                if (method.getAnnotation(OAIDelegate.class).metadataPrefix().equals(prefix)) {
                    metaDataCache.put(aClass, prefix, method);
                    return invoke(bean, method, objects);
                }
            }
        }
        return null;
    }

    @Override
    public Object invoke(final Object proxy, final Method method, final Object... args) {
        Object object = null;
        try {
            object = method.invoke(proxy, args);
        } catch (IllegalAccessException e) {
            log.error("illegal access invoking {} {}", method.getName(), e);
        } catch (InvocationTargetException e) {
            log.error("invocation target invoking {} {}", method.getName(), e);
        }
        return object;
    }
}
