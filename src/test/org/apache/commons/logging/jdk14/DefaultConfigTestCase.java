/*
 * $Header: /home/jerenkrantz/tmp/commons/commons-convert/cvs/home/cvs/jakarta-commons//logging/src/test/org/apache/commons/logging/jdk14/DefaultConfigTestCase.java,v 1.2 2003/03/30 02:30:37 craigmcc Exp $
 * $Revision: 1.2 $
 * $Date: 2003/03/30 02:30:37 $
 *
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999-2003 The Apache Software Foundation.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution, if
 *    any, must include the following acknowlegement:
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowlegement may appear in the software itself,
 *    if and wherever such third-party acknowlegements normally appear.
 *
 * 4. The names "The Jakarta Project", "Commons", and "Apache Software
 *    Foundation" must not be used to endorse or promote products derived
 *    from this software without prior written permission. For written
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache"
 *    nor may "Apache" appear in their names without prior written
 *    permission of the Apache Group.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */

package org.apache.commons.logging.jdk14;


import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * <p>TestCase for JDK 1.4 logging when running on a JDK 1.4 system with
 * zero configuration, and with Log4J not present (so JDK 1.4 logging
 * should be automatically configured.</p>
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.2 $ $Date: 2003/03/30 02:30:37 $
 */

public class DefaultConfigTestCase extends TestCase {


    // ----------------------------------------------------------- Constructors


    /**
     * <p>Construct a new instance of this test case.</p>
     */
    public DefaultConfigTestCase() {
        super();
    }


    /**
     * <p>Construct a new instance of this test case.</p>
     *
     * @param name Name of the test case
     */
    public DefaultConfigTestCase(String name) {
        super(name);
    }


    // ----------------------------------------------------- Instance Variables


    /**
     * <p>The {@link LogFactory} implementation we have selected.</p>
     */
    protected LogFactory factory = null;


    /**
     * <p>The {@link Log} implementation we have selected.</p>
     */
    protected Log log = null;


    // ------------------------------------------- JUnit Infrastructure Methods


    /**
     * Set up instance variables required by this test case.
     */
    public void setUp() throws Exception {
        setUpFactory();
        setUpLog("TestLogger");
    }


    /**
     * Return the tests included in this test suite.
     */
    public static Test suite() {
        return (new TestSuite(DefaultConfigTestCase.class));
    }

    /**
     * Tear down instance variables required by this test case.
     */
    public void tearDown() {
        log = null;
        if (factory != null) {
            factory.releaseAll();
            factory = null;
        }
    }


    // ----------------------------------------------------------- Test Methods


    // Test pristine Log instance
    public void testPristineLog() {

        assertNotNull("Log exists", log);
        assertEquals("Log class",
                     "org.apache.commons.logging.impl.Jdk14Logger",
                     log.getClass().getName());

        // Can we call level checkers with no exceptions?
        log.isDebugEnabled();
        log.isErrorEnabled();
        log.isFatalEnabled();
        log.isInfoEnabled();
        log.isTraceEnabled();
        log.isWarnEnabled();

    }


    // Test pristine LogFactory instance
    public void testPristineFactory() {

        assertNotNull("LogFactory exists", factory);
        assertEquals("LogFactory class",
                     "org.apache.commons.logging.impl.LogFactoryImpl",
                     factory.getClass().getName());

        String names[] = factory.getAttributeNames();
        assertNotNull("Names exists", names);
        assertEquals("Names empty", 0, names.length);

    }


    // -------------------------------------------------------- Support Methods



    // Set up factory instance
    protected void setUpFactory() throws Exception {
        factory = LogFactory.getFactory();
    }


    // Set up log instance
    protected void setUpLog(String name) throws Exception {
        log = factory.getLog(name);
    }


}
