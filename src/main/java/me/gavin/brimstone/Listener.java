package me.gavin.brimstone;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 *
 * @author Gav06
 * @since 4/20/2021
 *
 *        <p>
 *        Simple annotation to tag methods as listeners
 *
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface Listener {

    /**
     * Determines which order events are fired, from highest to lowest
     *
     * @return event priority
     */
    public int priority() default 1;
}