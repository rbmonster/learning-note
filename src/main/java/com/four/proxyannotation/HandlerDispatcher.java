package com.four.proxyannotation;

import com.four.proxyannotation.annotation.HandlerType;
import com.four.proxyannotation.annotation.IHandler;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Lists;
import com.google.common.collect.Table;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

//@Component
public class HandlerDispatcher implements ApplicationContextAware, ApplicationListener<ContextRefreshedEvent> {

    private ApplicationContext ac;

    private Table<Class, String, List<IHandler>> handlerTable = HashBasedTable.create();

    public List<Object> dispatch(String eventType, Object event) {
        if (event == null) {
            return null;
        }
        Class clz = event.getClass();
        List<IHandler> handlerList;
        do {
            handlerList = handlerTable.get(clz, eventType);
            clz = clz.getSuperclass();
        } while (CollectionUtils.isEmpty(handlerList) && clz != null);
        if (CollectionUtils.isEmpty(handlerList)) {
            return null;
        }
        List list = Lists.newArrayList();
        handlerList.forEach(handler -> {
            list.add(handler.handle(event));
        });
        return list;
    }

    public <T> List<T> dispatch(String eventType, Object event, Class<T> resClz) {
        if (event == null) {
            return null;
        }
        Class clz = event.getClass();
        List<IHandler> handlerList;
        do {
            handlerList = handlerTable.get(clz, eventType);
            clz = clz.getSuperclass();
        } while (CollectionUtils.isEmpty(handlerList) && clz != null);
        if (CollectionUtils.isEmpty(handlerList)) {
            return null;
        }
        List list = Lists.newArrayList();
        handlerList.forEach(handler -> {
            list.add(handler.handle(event));
        });
        return list;
    }

    public Object dispatchOne(String eventType, Object event) {
        List<Object> list = dispatch(eventType, event);
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }

    public <T> T dispatchOne(String eventType, Object event, Class<T> resClz) {
        List<T> list = dispatch(eventType, event, resClz);
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ac = applicationContext;
    }

    @Override
    public synchronized void onApplicationEvent(ContextRefreshedEvent event) {
        if (!handlerTable.isEmpty()) {
            return;
        }

        if (ac != event.getApplicationContext()) {
            return;
        }

        //Here is the right opportunity that Spring beans are ready.
        Map<String, Object> map = ac.getBeansWithAnnotation(HandlerType.class);
        map.values().stream().filter(obj -> obj instanceof IHandler).forEach(object -> {
            Class clz = object.getClass();
            IHandler handler = (IHandler) object;
            Type type = null;
            do {
                Type[] types = clz.getGenericInterfaces();
                if (types == null || types.length == 0) {
                    clz = clz.getSuperclass();
                    continue;
                }
                for (Type t : types) {
                    if (!(t instanceof ParameterizedType)
                            || (((ParameterizedType) t).getRawType()) != IHandler.class) {
                        continue;
                    }
                    type = ((ParameterizedType) t).getActualTypeArguments()[0];
                }
            } while (clz == null || type == null);
            HandlerType handlerType = object.getClass().getDeclaredAnnotation(HandlerType.class);
            Class typeClz = (Class) type;
            String[] eventTypes = handlerType.value();
            for (String eventType : eventTypes) {
                if (!handlerTable.contains(typeClz, eventType)) {
                    handlerTable.put(typeClz, eventType, Lists.newArrayList());
                }
                List<IHandler> handlerList = handlerTable.get(typeClz, eventType);
                if (!handlerList.contains(handler)) {
                    handlerList.add(handler);
                }
            }
        });
    }
}
