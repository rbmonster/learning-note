package com.four.proxyannotation.annotation;

public interface IHandler<res, req> {

    req handle(res r);

}
